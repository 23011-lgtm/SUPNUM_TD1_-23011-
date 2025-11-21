package com.td1.server.service;

import com.td1.server.model.Server;
import com.td1.server.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerService {
    
    @Autowired
    private ServerRepository repository;
    
    public Server createServer(Server server) {
        return repository.save(server);
    }
    
    public List<Server> listAll() {
        return repository.findAll();
    }
    
    public Server getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Serveur non trouvé: " + id));
    }
    
    public Server rename(Long id, String newName) {
        Server server = getById(id);
        server.setName(newName);
        return repository.save(server);
    }
    
    public Boolean getStatus(Long id) {
        Server server = getById(id);
        return server.getStatus();
    }
    
    public Server start(Long id) {
        Server server = getById(id);
        server.setStatus(true);
        return repository.save(server);
    }
    
    public Server stop(Long id) {
        Server server = getById(id);
        server.setStatus(false);
        return repository.save(server);
    }
    
    public void delete(Long id) {
        Server server = getById(id);
        if (server.isRunning()) {
            throw new RuntimeException("Impossible de supprimer un serveur en cours d'exécution");
        }
        repository.delete(server);
    }
}
