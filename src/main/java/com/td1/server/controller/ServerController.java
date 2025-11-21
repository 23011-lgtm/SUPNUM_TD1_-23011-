package com.td1.server.controller;

import com.td1.server.model.Server;
import com.td1.server.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/servers")
public class ServerController {
    
    @Autowired
    private ServerService service;
    
    @PostMapping
    public ResponseEntity<Server> create(@RequestBody Server server) {
        Server created = service.createServer(server);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<Server> listAll() {
        return service.listAll();
    }
    
    @GetMapping("/{id}")
    public Server getById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PutMapping("/{id}/rename")
    public Server rename(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String newName = body.get("newName");
        return service.rename(id, newName);
    }
    
    @GetMapping("/{id}/status")
    public Map<String, Object> getStatus(@PathVariable Long id) {
        Boolean status = service.getStatus(id);
        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("status", status);
        response.put("message", status ? "En cours d'exécution" : "Arrêté");
        return response;
    }
    
    @PutMapping("/{id}/start")
    public Server start(@PathVariable Long id) {
        return service.start(id);
    }
    
    @PutMapping("/{id}/stop")
    public Server stop(@PathVariable Long id) {
        return service.stop(id);
    }
    
    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        service.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Serveur supprimé avec succès");
        return response;
    }
}
