# Système d'Authentification avec JAX-RS et JMS

## Table des matières
1. [Vue d'ensemble](#vue-densemble)
2. [Architecture du système](#architecture-du-système)
3. [Composants principaux](#composants-principaux)
4. [Flux d'authentification](#flux-dauthentification)
5. [Implémentation détaillée](#implémentation-détaillée)
6. [Points clés pour l'examen](#points-clés-pour-lexamen)

## Vue d'ensemble

Le système implémente un service d'authentification REST avec les fonctionnalités suivantes :
- Authentification par login/mot de passe
- Génération et validation de jetons
- Notification JMS des authentifications réussies
- Architecture conforme aux standards JavaBeans

```
┌─────────────────┐    REST     ┌──────────────────┐
│  Client HTTP    │◄──────────► │ AuthResource     │
└─────────────────┘             └──────────────────┘
                                         │
                                         ▼
                                ┌──────────────────┐
                                │ AuthService      │
                                └──────────────────┘
                                         │
                                         ▼ JMS
                                ┌──────────────────┐
                                │ Topic           │
                                └──────────────────┘
```

## Architecture du système

### 1. Structure du projet
```
authentication-system/
├── src/
│   └── main/
│       └── java/
│           └── tg/univlome/epl/auth/
│               ├── AuthenticationService.java
│               ├── AuthenticationResource.java
│               └── client/
│                   └── AuthenticationClient.java
└── pom.xml
```

### 2. Dépendances principales
```xml
<dependency>
    <groupId>jakarta.platform</groupId>
    <artifactId>jakarta.jakartaee-api</artifactId>
    <version>10.0.0</version>
</dependency>
```

## Composants principaux

### 1. AuthenticationService (Singleton)

```java
public class AuthenticationService {
    private static AuthenticationService instance;
    private static final Map<String, String> credentials;
    private static final List<String> jetons;

    // Pattern Singleton
    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    // Méthodes principales
    public String authentifier(String login, String password) { ... }
    public boolean isValid(String jeton) { ... }
}
```

**Points clés :**
- Pattern Singleton pour instance unique
- Collections thread-safe pour credentials et jetons
- Méthodes synchronisées pour thread safety
- Respect des conventions JavaBeans

### 2. AuthenticationResource (JAX-RS)

```java
@Path("/auth")
public class AuthenticationResource {
    @Resource(lookup = "jms/cf1")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/authentificationTopic")
    private Destination authentificationTopic;

    // Endpoints REST
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response authenticate(@QueryParam("login") String login,
                               @QueryParam("password") String password) { ... }

    @GET
    @Path("/verify")
    public Response verifyToken(@HeaderParam("Authorization") String token) { ... }
}
```

**Points clés :**
- Annotations JAX-RS pour REST
- Injection des ressources JMS
- Gestion des codes HTTP
- Validation des paramètres

## Flux d'authentification

### 1. Processus d'authentification
```
1. Client ─► POST /auth?login=xxx&password=yyy
2. AuthResource ─► AuthService.authentifier()
3. Si succès :
   - Génération token UUID
   - Envoi notification JMS
   - Retour token (200 OK)
4. Si échec :
   - Retour erreur (401)
```

### 2. Vérification de token
```
1. Client ─► GET /auth/verify
   Header: Authorization: <token>
2. AuthResource ─► AuthService.isValid()
3. Si valide :
   - Retour date (200 OK)
4. Si invalide :
   - Retour erreur (401)
```

## Implémentation détaillée

### 1. Génération de token
```java
String token = UUID.randomUUID().toString();
jetons.add(token);
```

### 2. Notification JMS
```java
try (JMSContext context = connectionFactory.createContext()) {
    JMSProducer producer = context.createProducer();
    String message = "Utilisateur " + login + " authentifié";
    producer.send(authentificationTopic, message);
}
```

### 3. Gestion des erreurs
```java
// Validation des paramètres
if (login == null || password == null) {
    return Response.status(Response.Status.BAD_REQUEST)
                  .entity("Paramètres manquants")
                  .build();
}

// Gestion authentification
if (token == null) {
    return Response.status(Response.Status.UNAUTHORIZED)
                  .entity("Credentials invalides")
                  .build();
}
```

## Points clés pour l'examen

### 1. Annotations essentielles
```java
@Path("/auth")           // Chemin base REST
@POST                    // Méthode HTTP
@GET                     // Méthode HTTP
@Produces("text/plain")  // Type de réponse
@QueryParam("login")     // Paramètre URL
@HeaderParam("Authorization") // Header HTTP
```

### 2. Codes HTTP à connaître
- 200 OK : Succès
- 400 Bad Request : Paramètres invalides
- 401 Unauthorized : Authentication échouée
- 403 Forbidden : Token invalide

### 3. Structure JMS
```java
// Ressources
@Resource(lookup = "jms/cf1")
ConnectionFactory cf;

// Création contexte
try (JMSContext context = cf.createContext()) {
    JMSProducer producer = context.createProducer();
    producer.send(topic, message);
}
```

### 4. Bonnes pratiques
1. **Sécurité**
   - Validation des entrées
   - Gestion sécurisée des tokens
   - Pas de mots de passe en clair

2. **Design Patterns**
   - Singleton pour AuthService
   - Factory pour JMS
   - Builder pour Response

3. **JavaBeans**
   - Constructeur sans arguments
   - Getters/Setters appropriés
   - Propriétés privées

4. **Gestion des ressources**
   - try-with-resources pour JMS
   - Fermeture des connexions
   - Gestion des exceptions

### 5. Points à retenir pour l'examen écrit
1. **Structure des classes**
   - Attributs (private, static final)
   - Méthodes (public, synchronized)
   - Annotations REST et JMS

2. **Flux d'authentification**
   - Validation credentials
   - Génération token
   - Notification JMS
   - Réponse HTTP

3. **Gestion JMS**
   - ConnectionFactory
   - JMSContext
   - Producer
   - Topic

4. **Codes de retour**
   - Quand utiliser chaque code
   - Structure Response
   - Messages d'erreur 