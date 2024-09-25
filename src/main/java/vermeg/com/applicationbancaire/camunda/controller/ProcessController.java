package vermeg.com.applicationbancaire.camunda.controller;


import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/process")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProcessController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private HistoryService historyService;
    private final RestTemplate restTemplate;
    @Autowired
    private TaskService taskService;
    private final String camundaRestUrl = "http://localhost:8085/engine-rest";

    public ProcessController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Deploy BPMN and JSON Form files from the resources folder


    // Start a process instance with a given process key
    @PostMapping("/start/{processKey}")
    public ResponseEntity<String> startProcess(@PathVariable("processKey") String processKey) {
        String startUrl = camundaRestUrl + "/process-definition/key/" + processKey + "/start";
        RestTemplate restTemplate = new RestTemplate();

        // Create the request body for starting the process (you can pass variables here)
        Map<String, Object> requestBody = new HashMap<>();

        // Start the process instance and capture the response (JSON object)
        Map<String, Object> response = restTemplate.postForObject(startUrl, requestBody, Map.class);

        // Extract the processInstanceId from the response
        String processInstanceId = (String) response.get("id");

        // Return the processInstanceId in the response
        return ResponseEntity.ok(processInstanceId);

    }
    @GetMapping("/all-potential-tasks/{processInstanceId}")
    public List<Map<String, String>> getAllPotentialTasks(@PathVariable("processInstanceId") String processInstanceId) {
        // 1. Récupérer le processDefinitionId à partir du processInstanceId
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();

        if (processInstance == null) {
            return Collections.singletonList(Map.of("error", "Process instance not found"));
        }

        String processDefinitionId = processInstance.getProcessDefinitionId();

        // 2. Récupérer la définition du processus à partir du processDefinitionId
        BpmnModelInstance bpmnModelInstance = repositoryService.getBpmnModelInstance(processDefinitionId);

        // 3. Extraire toutes les tâches utilisateur du modèle BPMN
        Collection<UserTask> userTasks = bpmnModelInstance.getModelElementsByType(UserTask.class);

        // 4. Créer une liste pour stocker les détails des tâches utilisateur
        List<Map<String, String>> taskDetailsList = new ArrayList<>();

        // 5. Ajouter chaque tâche utilisateur dans la liste
        userTasks.forEach(userTask -> {
            Map<String, String> taskDetails = new HashMap<>();
            taskDetails.put("taskId", userTask.getId());
            taskDetails.put("taskName", userTask.getName());
            taskDetails.put("taskType", "User Task");
            taskDetailsList.add(taskDetails);
        });

        // 6. Retourner la liste des tâches utilisateur potentiellement activables
        return taskDetailsList;
    }


    // Complete a task by its ID and submit form variables
    @PostMapping("/task/{taskId}/complete")
    public String completeTask(@PathVariable("taskId") String taskId, @RequestBody Map<String, Object> formData) {
        // Prepare variables to complete the task
        VariableMap variables = Variables.createVariables();
        formData.forEach(variables::put);

        // Complete the task with the given variables
        taskService.complete(taskId, variables);
        return "Task with ID " + taskId + " completed successfully.";
    }

    // Get the details of a single task by task ID
    @GetMapping("/task/{taskId}")
    public Map<String, Object> getTaskDetails(@PathVariable("taskId") String taskId) {
        Task task = taskService.createTaskQuery()
                .taskId(taskId)
                .initializeFormKeys()  // Initialize the form key
                .singleResult();

        if (task != null) {
            Map<String, Object> taskDetails = new HashMap<>();
            taskDetails.put("taskId", task.getId());
            taskDetails.put("taskName", task.getName());
            taskDetails.put("assignee", task.getAssignee() != null ? task.getAssignee() : "Unassigned");
            taskDetails.put("formKey", task.getFormKey());  // Get form key if applicable
            return taskDetails;
        }

        return Map.of("error", "Task not found");
    }
    @DeleteMapping("/kill/{processInstanceId}")
    public ResponseEntity<String> killProcessInstance(@PathVariable String processInstanceId) {
        String url = camundaRestUrl + "/process-instance/" + processInstanceId;

        try {
            restTemplate.delete(url);
            return ResponseEntity.ok("Process instance " + processInstanceId + " has been deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting process instance: " + processInstanceId);
        }
    }
    @GetMapping("/taskcurrent/{processInstanceId}")
    public ResponseEntity<?> getCurrentTask(@PathVariable String processInstanceId) {
        String url = camundaRestUrl + "/task?processInstanceId=" + processInstanceId;

        try {
            ResponseEntity<Object[]> response = restTemplate.getForEntity(url, Object[].class);
            Object[] tasks = response.getBody();

            if (tasks != null && tasks.length > 0) {
                return ResponseEntity.ok(tasks[0]); // Retourner la première tâche trouvée
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No active tasks found for processInstanceId: " + processInstanceId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving task for processInstanceId: " + processInstanceId);
        }
    }
}
