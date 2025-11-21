package com.td1.server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "servers")
public class Server {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String ipAddress;
    private Boolean status = false;
    
    public Server() {
    }
    
    public Server(String name, String ipAddress) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.status = false;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getIpAddress() {
        return ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    public Boolean getStatus() {
        return status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    public boolean isRunning() {
        return status != null && status;
    }
}
