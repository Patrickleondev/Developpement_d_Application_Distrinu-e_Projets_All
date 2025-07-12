# Web Services REST - pd1 (Serveur) et pd2 (Client)

## Vue d'ensemble

Cette section couvre l'implémentation des Web Services REST avec Jakarta EE. Le projet est divisé en deux parties :
- **pd1** : Serveur REST qui expose des endpoints
- **pd2** : Client REST qui consomme les services du serveur

## Architecture générale

```
┌─────────────────┐    HTTP/REST    ┌─────────────────┐
│   Client (pd2)  │ ──────────────► │  Serveur (pd1)  │
│                 │                 │                 │
│ - Jersey Client │                 │ - Jakarta EE    │
│ - HTTP Client   │                 │ - JAX-RS        │
└─────────────────┘                 └─────────────────┘
```

## Projet pd1 - Serveur REST

### Configuration du projet

**Type de projet** : Application Web (WAR)
**Packaging** : WAR (Web Application Archive)
**Java Version** : 11
**Framework** : Jakarta EE 10.0.0

### Dépendances Maven

```xml
<dependency>
    <groupId>jakarta.platform</groupId>
    <artifactId>jakarta.jakartaee-api</artifactId>
    <version>10.0.0</version>
    <scope>provided</scope>
</dependency>
```

**Explication** :
- `jakarta.jakartaee-api` : Contient toutes les APIs Jakarta EE (JAX-RS, CDI, etc.)
- `scope=provided` : La dépendance est fournie par le serveur d'application

### Structure du projet

```
pd1/
├── src/main/java/
│   └── tg/univlome/epl/pd1/
│       ├── AppConfiguration.java     # Configuration JAX-RS
│       └── resources/
│           └── HelloRessource.java   # Endpoint REST
├── src/main/webapp/
│   └── WEB-INF/
│       ├── beans.xml                 # Configuration CDI
│       └── web.xml                   # Descripteur de déploiement
└── pom.xml
```

### Classes principales

#### 1. AppConfiguration.java

```java
@ApplicationPath("/rs")
public class AppConfiguration extends Application {
}
```

**Rôle** : Configuration de l'application JAX-RS
- `@ApplicationPath("/rs")` : Définit le chemin de base pour tous les endpoints REST
- `extends Application` : Classe de configuration JAX-RS
- **URL de base** : `http://localhost:8080/pd1/rs`

#### 2. HelloRessource.java

```java
@Path("/Hello")
public class HelloRessource {
    
    @GET
    public String saluer(@QueryParam("n") String nom, 
                        @QueryParam("p") String prenom){
        return "Bonjour" + nom + " " + prenom + " " + ", je suis le Boss";
    }
}
```

**Rôle** : Endpoint REST pour saluer un utilisateur
- `@Path("/Hello")` : Définit le chemin de l'endpoint
- `@GET` : Méthode HTTP GET
- `@QueryParam` : Paramètres de requête URL

**URL complète** : `http://localhost:8080/pd1/rs/Hello?n=John&p=Doe`

### Configuration Web

#### beans.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://xmlns.jcp.org/xml/ns/javaee"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
       http://xmlns.jcp.org/xml/ns/javaee/beans_2_0.xsd"
       version="2.0">
</beans>
```

**Rôle** : Active CDI (Contexts and Dependency Injection)

#### web.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="6.0" xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee 
         https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd">
    <display-name>pd1</display-name>
</web-app>
```

**Rôle** : Descripteur de déploiement de l'application web

## Projet pd2 - Client REST

### Configuration du projet

**Type de projet** : Application Java standalone
**Packaging** : JAR
**Java Version** : 24
**Framework** : Jersey Client

### Dépendances Maven

```xml
<dependency>
    <groupId>org.glassfish.jersey.core</groupId>
    <artifactId>jersey-client</artifactId>
    <version>3.1.10</version>
</dependency>
```

**Explication** :
- `jersey-client` : Client HTTP pour consommer les services REST
- Jersey est l'implémentation de référence de JAX-RS

### Structure du projet

```
pd2/
├── src/main/java/
│   └── tg/univlome/epl/pd2/
│       └── Pd2.java                  # Client REST
└── pom.xml
```

### Classes principales

#### Pd2.java

```java
public class Pd2 {
    private static final String URL = "http://localhost:8080/pd1/rs";
    
    public static void main(String[] args) {
        try {
            // Création du client Jersey
            Client client = ClientBuilder.newClient();
            
            // Création de la cible (target) pour l'URL
            WebTarget target = client.target(URL).path("Hello");
            
            // Envoi de la requête GET et récupération de la réponse
            Response response = target.request(MediaType.TEXT_PLAIN).get();
            
            // Vérification du statut de la réponse
            if (response.getStatus() == 200) {
                String result = response.readEntity(String.class);
                System.out.println("Réponse du serveur : " + result);
            } else {
                System.out.println("Erreur : " + response.getStatus());
            }
            
            // Fermeture du client
            client.close();
            
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

**Rôle** : Client qui consomme le service REST du serveur pd1

**Flux d'exécution** :
1. **Création du client** : `ClientBuilder.newClient()`
2. **Configuration de la cible** : `client.target(URL).path("Hello")`
3. **Envoi de la requête** : `target.request(MediaType.TEXT_PLAIN).get()`
4. **Traitement de la réponse** : Vérification du statut et lecture du contenu
5. **Fermeture du client** : `client.close()`

## Flux de communication

### 1. Démarrage du serveur (pd1)
```
1. Déploiement de l'application WAR sur le serveur
2. Initialisation de JAX-RS avec @ApplicationPath("/rs")
3. Enregistrement des endpoints REST
4. Serveur prêt à recevoir des requêtes
```

### 2. Exécution du client (pd2)
```
1. Création du client Jersey
2. Construction de l'URL : http://localhost:8080/pd1/rs/Hello
3. Envoi de la requête GET
4. Réception de la réponse
5. Affichage du résultat
```

## Points clés à retenir

### Annotations JAX-RS importantes
- `@ApplicationPath` : Chemin de base de l'application
- `@Path` : Chemin de l'endpoint
- `@GET`, `@POST`, `@PUT`, `@DELETE` : Méthodes HTTP
- `@QueryParam` : Paramètres de requête
- `@PathParam` : Paramètres de chemin
- `@Produces` : Type de contenu de sortie
- `@Consumes` : Type de contenu d'entrée

### Bonnes pratiques
1. **Gestion des ressources** : Toujours fermer le client
2. **Gestion d'erreurs** : Vérifier le statut de la réponse
3. **Types MIME** : Spécifier le type de contenu attendu
4. **URLs** : Utiliser des URLs relatives quand possible

### Questions de révision

1. **Quelle est la différence entre pd1 et pd2 ?**
   - pd1 est le serveur qui expose des services REST
   - pd2 est le client qui consomme ces services

2. **Quel est le rôle de @ApplicationPath ?**
   - Définit le chemin de base pour tous les endpoints REST

3. **Comment le client communique-t-il avec le serveur ?**
   - Via HTTP/REST en utilisant Jersey Client

4. **Quelles sont les étapes pour créer un endpoint REST ?**
   - Créer une classe avec @Path
   - Ajouter des méthodes avec @GET, @POST, etc.
   - Configurer les paramètres avec @QueryParam ou @PathParam

5. **Pourquoi utiliser @QueryParam ?**
   - Pour récupérer les paramètres de la requête URL

## Test de l'application

### Démarrage du serveur
1. Déployer pd1 sur un serveur d'application (GlassFish, TomEE, etc.)
2. Vérifier que l'application est accessible à `http://localhost:8080/pd1/rs`

### Test du client
1. Exécuter la classe Pd2
2. Vérifier que la réponse est reçue correctement

### Test manuel
```bash
curl "http://localhost:8080/pd1/rs/Hello?n=John&p=Doe"
```

**Résultat attendu** : `Bonjour John Doe , je suis le Boss` 