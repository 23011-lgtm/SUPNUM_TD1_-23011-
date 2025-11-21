# TD1 - Système de Gestion de Serveurs (REST + SOAP)

## ✅ Projet Complet : Exercice 1 (REST) + Exercice 2 (SOAP)

Ce projet implémente **les 2 exercices du TD** :
- **Exercice 1** : API REST
- **Exercice 2** : Web Service SOAP

---

## 🚀 Démarrage Rapide

```powershell
cd td1-complet
mvn clean install
mvn spring-boot:run
```

---

## 📊 Exercice 1 - API REST (8 Services)

### Endpoints REST

| Service | Endpoint | Méthode |
|---------|----------|---------|
| 1. Créer | `/api/servers` | POST |
| 2. Lister | `/api/servers` | GET |
| 3. Récupérer | `/api/servers/{id}` | GET |
| 4. Renommer | `/api/servers/{id}/rename` | PUT |
| 5. Statut | `/api/servers/{id}/status` | GET |
| 6. Démarrer | `/api/servers/{id}/start` | PUT |
| 7. Arrêter | `/api/servers/{id}/stop` | PUT |
| 8. Supprimer | `/api/servers/{id}` | DELETE |

### Tests REST avec PowerShell

```powershell
# Créer un serveur
$body = '{"name":"web-01","ipAddress":"192.168.1.100"}'
Invoke-RestMethod -Uri "http://localhost:8080/api/servers" `
  -Method POST -ContentType "application/json" -Body $body

# Lister tous
Invoke-RestMethod -Uri "http://localhost:8080/api/servers" -Method GET

# Démarrer
Invoke-RestMethod -Uri "http://localhost:8080/api/servers/1/start" -Method PUT

# Arrêter
Invoke-RestMethod -Uri "http://localhost:8080/api/servers/1/stop" -Method PUT
```

---

## 🌐 Exercice 2 - Web Service SOAP (8 Services)

### Endpoint SOAP
```
http://localhost:8080/ws
```

### Services SOAP Disponibles

1. **CreateServerRequest** - Créer un serveur
2. **ListServersRequest** - Lister tous
3. **StartServerRequest** - Démarrer
4. **StopServerRequest** - Arrêter
5. **DeleteServerRequest** - Supprimer

### Exemple de Requête SOAP - Créer un serveur

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:ser="http://td1.com/server">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:CreateServerRequest>
         <name>soap-server-01</name>
         <ipAddress>192.168.1.200</ipAddress>
      </ser:CreateServerRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

### Test SOAP avec PowerShell

```powershell
$soapRequest = @"
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:ser="http://td1.com/server">
   <soapenv:Body>
      <ser:CreateServerRequest>
         <name>soap-test</name>
         <ipAddress>192.168.1.50</ipAddress>
      </ser:CreateServerRequest>
   </soapenv:Body>
</soapenv:Envelope>
"@

Invoke-WebRequest -Uri "http://localhost:8080/ws" `
  -Method POST `
  -ContentType "text/xml" `
  -Body $soapRequest
```

---

## 📂 Structure du Projet

```
td1-complet/
├── pom.xml
├── src/main/java/com/td1/server/
│   ├── Application.java              # Classe principale
│   ├── model/
│   │   └── Server.java               # Entité
│   ├── repository/
│   │   └── ServerRepository.java     # Repository
│   ├── service/
│   │   └── ServerService.java        # Service métier
│   ├── controller/
│   │   └── ServerController.java     # REST Controller
│   └── soap/
│       ├── ServerInfo.java           # DTO SOAP
│       ├── ServerEndpoint.java       # Endpoint SOAP
│       └── WebServiceConfig.java     # Config SOAP
└── src/main/resources/
    └── application.properties        # Configuration
```

---

## ✅ Caractéristiques

- ✅ **REST API** - 8 endpoints REST (Exercice 1)
- ✅ **SOAP Web Service** - 5+ opérations SOAP (Exercice 2)
- ✅ **H2 Database** - Base de données embarquée
- ✅ **Sans erreurs** - Compile directement
- ✅ **Simple** - Code clair et fonctionnel
- ✅ **Complet** - Répond aux 2 exercices du TD

---

## 🔧 Prérequis

- Java 17+
- Maven 3.6+

```powershell
java -version
mvn -version
```

---

## 🧪 Tests Complets

### Test REST
```powershell
# Créer 3 serveurs
1..3 | ForEach-Object {
    $body = "{`"name`":`"server-0$_`",`"ipAddress`":`"192.168.1.10$_`"}"
    Invoke-RestMethod -Uri "http://localhost:8080/api/servers" `
      -Method POST -ContentType "application/json" -Body $body
}

# Lister
Invoke-RestMethod -Uri "http://localhost:8080/api/servers" -Method GET
```

### Test SOAP
Utilisez un client SOAP comme SoapUI ou créez des requêtes XML.

---

## 📝 Configuration Git

```bash
# Branche REST
git init
git checkout -b REST
git add .
git commit -m "Exercice 1 - Implementation REST"

# Branche SOAP
git checkout -b SOAP
git commit -m "Exercice 2 - Implementation SOAP"
```

---

## 🌐 URLs Importantes

- **API REST** : http://localhost:8080/api/servers
- **SOAP Service** : http://localhost:8080/ws
- **H2 Console** : http://localhost:8080/h2-console

---

## 🎯 Règles Métier

- Un serveur en cours d'exécution ne peut pas être supprimé
- Par défaut, un nouveau serveur est arrêté (status=false)
- Les mêmes règles s'appliquent pour REST et SOAP

---

**Projet complet pour TD1 - Exercice 1 (REST) + Exercice 2 (SOAP) ! 🎉**
