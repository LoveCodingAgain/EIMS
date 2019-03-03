package com.lx.eimsadminserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class EimsadminserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(EimsadminserverApplication.class, args);
    }

}
