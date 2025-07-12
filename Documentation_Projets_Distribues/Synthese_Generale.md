# Synthèse Générale - Développement d'Applications Distribuées

## Vue d'ensemble des concepts

Cette synthèse regroupe tous les concepts fondamentaux étudiés dans les différents projets de développement d'applications distribuées.

## 1. Conventions JavaBean (Java Blue Print)

### Définition
Les JavaBeans sont des classes Java qui respectent des conventions spécifiques pour assurer l'interopérabilité et la réutilisabilité.

### Règles fondamentales
1. **Attributs privés** : `private String nom;`
2. **Constructeur par défaut** : `public MaClasse() {}`
3. **Getters/Setters** : `getNom()`, `setNom(String nom)`
4. **Sérialisation** : `implements Serializable`
5. **Encapsulation** : Accès via méthodes

### Exemple JavaBean
```java
public class Personne implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nom;
    private String prenom;
    private int age;
    
    // Constructeur par défaut
    public Personne() {
    }
    
    // Getters et Setters
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                '}';
    }
}
```

### Avantages JavaBean
- **Interopérabilité** : Compatible avec tous les frameworks
- **Réflexion** : Accès dynamique aux propriétés
- **Sérialisation** : Persistance et transmission
- **Validation** : Contrôle d'accès aux données
- **Standards** : Respect des conventions Java

### Utilisation dans les projets
- **meteo0** : Classes de réponse JSON
- **Boutique** : Entités métier
- **Web Services** : Objets de transfert
- **JMS** : Messages sérialisables

## 2. Web Services REST

### Concepts fondamentaux
- **REST** : Representational State Transfer
- **HTTP** : Protocole de communication
- **JSON** : Format d'échange de données
- **Stateless** : Pas d'état entre les requêtes

### Annotations JAX-RS importantes
```java
@ApplicationPath("/api")        // Chemin de base
@Path("/users")                 // Chemin de l'endpoint
@GET, @POST, @PUT, @DELETE      // Méthodes HTTP
@QueryParam("name")             // Paramètres de requête
@PathParam("id")                // Paramètres de chemin
@Produces(MediaType.APPLICATION_JSON)  // Type de sortie
@Consumes(MediaType.APPLICATION_JSON)  // Type d'entrée
```

### Codes de statut HTTP
- **200** : OK (Succès)
- **201** : Created (Créé)
- **400** : Bad Request (Requête invalide)
- **404** : Not Found (Non trouvé)
- **500** : Internal Server Error (Erreur serveur)

### Architecture Client-Serveur
```
Client REST → HTTP Request → Serveur REST
Client REST ← HTTP Response ← Serveur REST
```

## 3. JMS (Java Message Service)

### Types de destinations
- **Queue** : Point-to-Point (un seul consommateur)
- **Topic** : Publish/Subscribe (plusieurs consommateurs)

### Classes JMS principales
```java
InitialContext context = new InitialContext();
ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
JMSContext jmsContext = cf.createContext();
JMSProducer producer = jmsContext.createProducer();
JMSConsumer consumer = jmsContext.createConsumer(dest);
```

### Flux de communication
```
Producteur → Message → Destination → Message → Consommateur
```

### Avantages JMS
- **Asynchrone** : Pas de blocage
- **Découplage** : Producteur et consommateur indépendants
- **Fiabilité** : Garantie de livraison (Queue)
- **Scalabilité** : Plusieurs consommateurs

## 4. Architecture en couches

### Pattern MVC (Model-View-Controller)
- **Model** : Données et logique métier
- **View** : Interface utilisateur
- **Controller** : Gestion des requêtes

### Séparation des responsabilités
```
┌─────────────────┐
│   Présentation  │ ← Interface utilisateur
├─────────────────┤
│   Contrôleur    │ ← Gestion des requêtes
├─────────────────┤
│   Service       │ ← Logique métier
├─────────────────┤
│   Données       │ ← Accès aux données
└─────────────────┘
```

### Patterns de conception
- **Singleton** : Une seule instance
- **Template Method** : Méthodes à redéfinir
- **DAO** : Accès aux données encapsulé
- **Factory** : Création d'objets

## 5. Communication entre applications

### Types de communication
1. **Synchrones** : REST, RPC
2. **Asynchrones** : JMS, Message Queues
3. **Événementielles** : Pub/Sub, WebSockets

### Protocoles
- **HTTP/HTTPS** : Web Services
- **TCP** : Communication directe
- **AMQP** : Message Queues
- **WebSocket** : Communication bidirectionnelle

## 6. Gestion des données

### Formats d'échange
- **JSON** : JavaScript Object Notation
- **XML** : Extensible Markup Language
- **Protobuf** : Protocol Buffers
- **Avro** : Apache Avro

### Sérialisation/Désérialisation
```java
// Désérialisation JSON avec Gson
Gson gson = new Gson();
WeatherResponse weather = gson.fromJson(json, WeatherResponse.class);

// Sérialisation JSON
String json = gson.toJson(weather);
```

### JavaBean et sérialisation
```java
// Classe JavaBean sérialisable
public class WeatherResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Location location;
    
    // Getters/Setters
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
}
```

## 7. Gestion d'erreurs

### Stratégies
1. **Validation** : Vérification des données
2. **Try-catch** : Capture des exceptions
3. **Codes de statut** : Réponses HTTP appropriées
4. **Logging** : Enregistrement des erreurs

### Bonnes pratiques
```java
try {
    // Code métier
    return Response.ok(result).build();
} catch (ValidationException e) {
    return Response.status(400).entity(e.getMessage()).build();
} catch (Exception e) {
    return Response.status(500).entity("Erreur interne").build();
}
```

## 8. Configuration et déploiement

### Types de projets
- **JAR** : Application standalone
- **WAR** : Application web
- **EAR** : Application entreprise

### Serveurs d'application
- **GlassFish** : Serveur Jakarta EE
- **WildFly** : Serveur JBoss
- **TomEE** : Tomcat + Jakarta EE
- **Tomcat** : Serveur web simple

### Configuration Maven
```xml
<packaging>war</packaging>
<properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
</properties>
```

## 9. Tests et validation

### Types de tests
1. **Tests unitaires** : Classes individuelles
2. **Tests d'intégration** : Communication entre composants
3. **Tests de charge** : Performance
4. **Tests de sécurité** : Vulnérabilités

### Outils de test
- **JUnit** : Tests unitaires
- **Postman** : Tests d'API
- **curl** : Tests en ligne de commande
- **JMeter** : Tests de performance

## 10. Sécurité

### Concepts
- **Authentification** : Vérification de l'identité
- **Autorisation** : Vérification des permissions
- **Chiffrement** : Protection des données
- **HTTPS** : Communication sécurisée

### Bonnes pratiques
- Validation des entrées
- Échappement des données
- Gestion sécurisée des clés API
- Logs de sécurité

## 11. Performance et optimisation

### Techniques
- **Cache** : Mise en cache des données
- **Pooling** : Réutilisation des connexions
- **Asynchrone** : Traitement non-bloquant
- **Compression** : Réduction de la taille des données

### Monitoring
- **Métriques** : Temps de réponse, débit
- **Logs** : Traçabilité des opérations
- **Alertes** : Notification des problèmes
- **Profiling** : Analyse des performances

## Questions de révision générales

### JavaBean
1. **Quelles sont les conventions JavaBean ?**
   - Attributs privés, constructeur par défaut, getters/setters, Serializable

2. **Pourquoi utiliser JavaBean ?**
   - Interopérabilité, réflexion, sérialisation, standards

3. **Comment accéder aux propriétés d'un JavaBean ?**
   - Via les méthodes getters/setters

### Architecture
1. **Quelle est la différence entre architecture monolithique et distribuée ?**
2. **Quels sont les avantages des microservices ?**
3. **Comment assurer la cohérence des données dans un système distribué ?**

### Communication
1. **Quand utiliser REST vs JMS ?**
2. **Quelle est la différence entre synchrone et asynchrone ?**
3. **Comment gérer la latence dans les communications distribuées ?**

### Sécurité
1. **Comment sécuriser une API REST ?**
2. **Qu'est-ce que l'authentification JWT ?**
3. **Comment protéger les données sensibles ?**

### Performance
1. **Quelles sont les techniques d'optimisation d'une API ?**
2. **Comment gérer la montée en charge ?**
3. **Qu'est-ce que le cache et quand l'utiliser ?**

### Déploiement
1. **Quelle est la différence entre JAR, WAR et EAR ?**
2. **Comment déployer une application sur un serveur d'application ?**
3. **Qu'est-ce que le CI/CD ?**

## Conseils pour l'examen

### Préparation
1. **Comprendre les concepts** : Ne pas juste mémoriser
2. **Pratiquer le code** : Écrire du code à la main
3. **Analyser les architectures** : Comprendre les choix de conception
4. **Tester les applications** : Vérifier le fonctionnement

### Pendant l'examen
1. **Lire attentivement** : Comprendre les exigences
2. **Planifier** : Structurer la réponse
3. **Justifier** : Expliquer les choix techniques
4. **Vérifier** : Relire et corriger

### Points importants
- **JavaBean** : Respecter les conventions
- **Architecture** : Comprendre les patterns
- **Communication** : Maîtriser les protocoles
- **Sécurité** : Connaître les bonnes pratiques
- **Performance** : Optimiser les solutions

---

**Rappel** : Cette documentation est conçue pour vous préparer à l'examen sur papier. Assurez-vous de comprendre les concepts fondamentaux et de pouvoir expliquer l'architecture de chaque projet sans avoir accès au code. 