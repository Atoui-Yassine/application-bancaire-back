package vermeg.com.applicationbancaire;


import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import java.util.HashMap;

@SpringBootApplication
//@EnableProcessApplication
//@EnableAutoConfiguration

public class ApplicationbancaireApplication {
//    @Autowired
//    private RuntimeService runtimeService;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationbancaireApplication.class, args);
    }


//    @EventListener
//    private void processPostDeploy(PostDeployEvent event) {
//
//        HashMap<String,Object> data2=new HashMap<>();
//        data2.put("name","testrim");
//        data2.put("financetype","auto");
//        data2.put("nombremois","3");
//        data2.put("autofinancement","62");
//
//        runtimeService.startProcessInstanceByKey("test",data2);
//    }
}
