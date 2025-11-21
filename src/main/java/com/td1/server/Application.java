package com.td1.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("\n========================================");
        System.out.println("  TD1 - Système de Gestion de Serveurs");
        System.out.println("  REST + SOAP");
        System.out.println("========================================");
        System.out.println("API REST: http://localhost:8080/api/servers");
        System.out.println("SOAP Service: http://localhost:8080/ws");
        System.out.println("H2 Console: http://localhost:8080/h2-console");
        System.out.println("========================================\n");
    }
}
