package vermeg.com.applicationbancaire.camunda.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

@Service
public class ProcessDeploymentService {
    private static final Logger LOGGER = Logger.getLogger(ProcessDeploymentService.class.getName());

    private final String CAMUNDA_REST_URL = "http://localhost:8085/engine-rest/deployment/create";

    private final RestTemplate restTemplate;

    public ProcessDeploymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Deploy BPMN file using multipart/form-data
    private void deployBPMN(String bpmnFilePath) throws IOException {
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(new ClassPathResource(bpmnFilePath).getFile().toPath())) {
            @Override
            public String getFilename() {
                return "process(4).bpmn";
            }
        };

        // Prepare the multipart body
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("deployment-name", "Process Deployment");
        bodyBuilder.part("bpmnFile", resource, MediaType.APPLICATION_OCTET_STREAM);

        // Create headers for multipart request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, HttpEntity<?>>> requestEntity = new HttpEntity<>(bodyBuilder.build(), headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(CAMUNDA_REST_URL, requestEntity, String.class);
            LOGGER.info("BPMN deployed successfully. Response: " + response.getBody());
        } catch (Exception e) {
            LOGGER.severe("Failed to deploy BPMN via REST API: " + e.getMessage());
        }
    }

    // Deploy JSON form using multipart/form-data
    private void deployForm(Resource formResource) throws IOException {
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(formResource.getFile().toPath())) {
            @Override
            public String getFilename() {
                return formResource.getFilename();
            }
        };

        // Prepare the multipart body
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("deployment-name", "Form Deployment for " + formResource.getFilename());
        bodyBuilder.part("formFile", resource, MediaType.APPLICATION_JSON);

        // Create headers for multipart request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, HttpEntity<?>>> requestEntity = new HttpEntity<>(bodyBuilder.build(), headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(CAMUNDA_REST_URL, requestEntity, String.class);
            LOGGER.info("Form deployed successfully. Response: " + response.getBody());
        } catch (Exception e) {
            LOGGER.severe("Failed to deploy form via REST API: " + e.getMessage());
        }
    }
}
