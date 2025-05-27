package com.example.mobilemanagement;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MobileManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileManagementApplication.class, args);
    }

    @PostConstruct
    public void delayStart() throws InterruptedException {
        Thread.sleep(10000); // 10 δευτερόλεπτα καθυστέρηση
    }


}
