package com.td1.server.soap;

import com.td1.server.model.Server;
import com.td1.server.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

@Endpoint
public class ServerEndpoint {
    
    private static final String NAMESPACE = "http://td1.com/server";
    
    @Autowired
    private ServerService serverService;
    
    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    
    // Créer un serveur
    @PayloadRoot(namespace = NAMESPACE, localPart = "CreateServerRequest")
    @ResponsePayload
    public Element createServer(@RequestPayload Element request) throws Exception {
        String name = getTextContent(request, "name");
        String ipAddress = getTextContent(request, "ipAddress");
        
        Server server = new Server(name, ipAddress);
        Server created = serverService.createServer(server);
        
        return createServerResponse(created);
    }
    
    // Lister tous les serveurs
    @PayloadRoot(namespace = NAMESPACE, localPart = "ListServersRequest")
    @ResponsePayload
    public Element listServers(@RequestPayload Element request) throws Exception {
        List<Server> servers = serverService.listAll();
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element response = doc.createElementNS(NAMESPACE, "ListServersResponse");
        
        for (Server server : servers) {
            Element serverElement = createServerElement(doc, server);
            response.appendChild(serverElement);
        }
        
        return response;
    }
    
    // Démarrer un serveur
    @PayloadRoot(namespace = NAMESPACE, localPart = "StartServerRequest")
    @ResponsePayload
    public Element startServer(@RequestPayload Element request) throws Exception {
        Long id = Long.parseLong(getTextContent(request, "id"));
        Server server = serverService.start(id);
        return createServerResponse(server);
    }
    
    // Arrêter un serveur
    @PayloadRoot(namespace = NAMESPACE, localPart = "StopServerRequest")
    @ResponsePayload
    public Element stopServer(@RequestPayload Element request) throws Exception {
        Long id = Long.parseLong(getTextContent(request, "id"));
        Server server = serverService.stop(id);
        return createServerResponse(server);
    }
    
    // Supprimer un serveur
    @PayloadRoot(namespace = NAMESPACE, localPart = "DeleteServerRequest")
    @ResponsePayload
    public Element deleteServer(@RequestPayload Element request) throws Exception {
        Long id = Long.parseLong(getTextContent(request, "id"));
        serverService.delete(id);
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element response = doc.createElementNS(NAMESPACE, "DeleteServerResponse");
        Element message = doc.createElement("message");
        message.setTextContent("Serveur supprimé avec succès");
        response.appendChild(message);
        
        return response;
    }
    
    // Méthodes utilitaires
    private String getTextContent(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
    
    private Element createServerResponse(Server server) throws Exception {
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element response = doc.createElementNS(NAMESPACE, "ServerResponse");
        Element serverElement = createServerElement(doc, server);
        response.appendChild(serverElement);
        return response;
    }
    
    private Element createServerElement(Document doc, Server server) {
        Element serverElement = doc.createElement("server");
        
        Element id = doc.createElement("id");
        id.setTextContent(server.getId().toString());
        serverElement.appendChild(id);
        
        Element name = doc.createElement("name");
        name.setTextContent(server.getName());
        serverElement.appendChild(name);
        
        Element ipAddress = doc.createElement("ipAddress");
        ipAddress.setTextContent(server.getIpAddress());
        serverElement.appendChild(ipAddress);
        
        Element status = doc.createElement("status");
        status.setTextContent(server.getStatus().toString());
        serverElement.appendChild(status);
        
        return serverElement;
    }
}
