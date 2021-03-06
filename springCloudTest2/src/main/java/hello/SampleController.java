package hello;



import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;


import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.*;



@SpringBootApplication
@EnableDiscoveryClient
public class SampleController {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
} 
