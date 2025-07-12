# JMS (Java Message Service) - Communication Asynchrone

## Vue d'ensemble

JMS (Java Message Service) est une API Java qui permet la communication asynchrone entre applications via des messages. Cette section couvre trois projets qui démontrent les concepts fondamentaux de JMS :

- **jms-client1** : Producteur de messages (File et Topic)
- **jms-client2** : Consommateur de File et abonné Topic
- **jms-client3** : Consommateur de Topic uniquement

## Concepts JMS fondamentaux

### Types de destinations

#### 1. Queue (File d'attente)
- **Modèle** : Point-to-Point (P2P)
- **Comportement** : Un seul consommateur reçoit le message
- **Utilisation** : Traitement séquentiel, garantie de livraison

#### 2. Topic (Sujet)
- **Modèle** : Publish/Subscribe (Pub/Sub)
- **Comportement** : Tous les abonnés reçoivent le message
- **Utilisation** : Diffusion d'événements, notifications

### Architecture JMS

```
┌─────────────────┐    Messages    ┌─────────────────┐
│  Producteur     │ ──────────────► │   Destination   │
│  (jms-client1)  │                 │  (Queue/Topic)  │
└─────────────────┘                 └─────────────────┘
                                           │
                                           ▼
┌─────────────────┐    Messages    ┌─────────────────┐
│  Consommateur   │ ◄────────────── │   Destination   │
│ (jms-client2,3) │                 │  (Queue/Topic)  │
└─────────────────┘                 └─────────────────┘
```

## Projet jms-client1 - Producteur de Messages

### Configuration du projet

**Type de projet** : Application Web (WAR)
**Packaging** : WAR
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

### Structure du projet

```
jms-client1/
├── src/main/java/
│   └── tg/univlome/epl/jms/client1/
│       ├── JmsClientListener.java      # Producteur de messages
│       ├── JakartaRestConfiguration.java
│       └── resources/
└── pom.xml
```

### Classes principales

#### JmsClientListener.java

```java
@WebListener
public class JmsClientListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            this.envoyerFile();
            this.envoyerTopic();
        } catch (NamingException | JMSException ex) {
            Logger.getLogger(JmsClientListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void envoyerFile() throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/file1");
        
        try (JMSContext jmsContext = cf.createContext()) {
            JMSProducer producer = jmsContext.createProducer();
            TextMessage message = jmsContext.createTextMessage();
            message.setText("Bonjour, c'est mon premier message jms dans file1");
            producer.send(dest, message);
        }
    }
    
    private void envoyerTopic() throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/topic1");
        
        try (JMSContext jmsContext = cf.createContext()) {
            JMSProducer producer = jmsContext.createProducer();
            TextMessage message = jmsContext.createTextMessage();
            message.setText("Bonjour, c'est mon premier message jms dans topic1");
            producer.send(dest, message);
        }
    }
}
```

**Rôle** : Producteur de messages JMS
- `@WebListener` : Écouteur de contexte web
- `ServletContextListener` : Interface pour écouter les événements de cycle de vie

**Flux d'exécution** :
1. **Initialisation du contexte** : `InitialContext()`
2. **Recherche des ressources** : `context.lookup()`
3. **Création du contexte JMS** : `cf.createContext()`
4. **Création du producteur** : `jmsContext.createProducer()`
5. **Création du message** : `jmsContext.createTextMessage()`
6. **Envoi du message** : `producer.send(dest, message)`

### Ressources JMS utilisées

- **ConnectionFactory** : `jms/cf1`
- **Queue** : `jms/file1`
- **Topic** : `jms/topic1`

## Projet jms-client2 - Consommateur de File et Topic

### Configuration du projet

**Type de projet** : Application Web (WAR)
**Packaging** : WAR
**Java Version** : 11
**Framework** : Jakarta EE 10.0.0

### Classes principales

#### JmsClient2Listener.java

```java
@WebListener
public class JmsClient2Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            this.lireFile();
            this.abonnerTopic();
        } catch (NamingException | JMSException ex) {
            Logger.getLogger(JmsClient2Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void lireFile() throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/file1");
        
        try (JMSContext jmsContext = cf.createContext()) {
            JMSConsumer consumer = jmsContext.createConsumer(dest);
            Message message = consumer.receive();
            System.out.println("Message lu : " + message.getBody(String.class));
        }
    }
    
    private void abonnerTopic() throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/topic1");
        
        try (JMSContext jmsContext = cf.createContext()) {
            JMSConsumer consumer = jmsContext.createConsumer(dest);
            Message message = consumer.receive();
            System.out.println("Message lu : " + message.getBody(String.class));
        }
    }
}
```

**Rôle** : Consommateur de messages JMS
- **Lecture de File** : `lireFile()` - Consomme un message de la queue
- **Abonnement Topic** : `abonnerTopic()` - Reçoit un message du topic

**Différences avec le producteur** :
- Utilise `JMSConsumer` au lieu de `JMSProducer`
- `consumer.receive()` pour recevoir le message
- `message.getBody(String.class)` pour lire le contenu

## Projet jms-client3 - Consommateur de Topic uniquement

### Configuration du projet

**Type de projet** : Application Web (WAR)
**Packaging** : WAR
**Java Version** : 11
**Framework** : Jakarta EE 10.0.0

### Classes principales

#### JmsClient3Listener.java

```java
@WebListener
public class JmsClient3Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            this.abonnerTopic();
        } catch (NamingException | JMSException ex) {
            Logger.getLogger(JmsClient3Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void abonnerTopic() throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination dest = (Destination) context.lookup("jms/topic1");
        
        try (JMSContext jmsContext = cf.createContext()) {
            JMSConsumer consumer = jmsContext.createConsumer(dest);
            Message message = consumer.receive();
            System.out.println("Message lu : " + message.getBody(String.class));
        }
    }
}
```

**Rôle** : Consommateur de topic uniquement
- Seulement abonné au topic `jms/topic1`
- Démonstration du modèle Publish/Subscribe

## Flux de communication JMS

### 1. Configuration du serveur JMS
```
1. Configuration des ressources JMS sur le serveur d'application
2. Création de la ConnectionFactory : jms/cf1
3. Création de la Queue : jms/file1
4. Création du Topic : jms/topic1
```

### 2. Démarrage des applications
```
1. Déploiement de jms-client1 (producteur)
2. Déploiement de jms-client2 (consommateur file + topic)
3. Déploiement de jms-client3 (consommateur topic)
```

### 3. Flux des messages
```
1. jms-client1 envoie un message à jms/file1
2. jms-client1 envoie un message à jms/topic1
3. jms-client2 reçoit le message de jms/file1
4. jms-client2 et jms-client3 reçoivent le message de jms/topic1
```

## Points clés à retenir

### Annotations importantes
- `@WebListener` : Marque une classe comme écouteur de contexte web
- `ServletContextListener` : Interface pour écouter les événements de cycle de vie

### Classes JMS principales
- `InitialContext` : Contexte JNDI pour rechercher les ressources
- `ConnectionFactory` : Factory pour créer des connexions JMS
- `JMSContext` : Contexte JMS pour les opérations
- `JMSProducer` : Producteur de messages
- `JMSConsumer` : Consommateur de messages
- `TextMessage` : Message de type texte
- `Destination` : Interface pour Queue et Topic

### Méthodes importantes
- `context.lookup()` : Recherche une ressource JNDI
- `cf.createContext()` : Crée un contexte JMS
- `jmsContext.createProducer()` : Crée un producteur
- `jmsContext.createConsumer()` : Crée un consommateur
- `producer.send()` : Envoie un message
- `consumer.receive()` : Reçoit un message
- `message.getBody()` : Lit le contenu du message

### Différences Queue vs Topic

| Aspect | Queue | Topic |
|--------|-------|-------|
| **Modèle** | Point-to-Point | Publish/Subscribe |
| **Consommateurs** | Un seul | Plusieurs |
| **Persistance** | Oui | Non (par défaut) |
| **Garantie** | Livraison garantie | Pas de garantie |
| **Utilisation** | Traitement séquentiel | Notifications |

### Bonnes pratiques
1. **Gestion des ressources** : Utiliser try-with-resources
2. **Gestion d'erreurs** : Capturer JMSException et NamingException
3. **Fermeture** : Fermer automatiquement le JMSContext
4. **Logging** : Logger les erreurs appropriées

### Questions de révision

1. **Quelle est la différence entre Queue et Topic ?**
   - Queue : Point-to-Point, un seul consommateur
   - Topic : Publish/Subscribe, plusieurs consommateurs

2. **Quel est le rôle de @WebListener ?**
   - Marque une classe comme écouteur de contexte web

3. **Comment créer un producteur JMS ?**
   - `JMSProducer producer = jmsContext.createProducer()`

4. **Comment créer un consommateur JMS ?**
   - `JMSConsumer consumer = jmsContext.createConsumer(dest)`

5. **Quelle méthode utilise-t-on pour recevoir un message ?**
   - `consumer.receive()`

6. **Pourquoi utiliser JMS ?**
   - Communication asynchrone, découplage, fiabilité

## Test de l'application

### Prérequis
1. Serveur d'application avec JMS configuré (GlassFish, WildFly, etc.)
2. Ressources JMS configurées :
   - ConnectionFactory : `jms/cf1`
   - Queue : `jms/file1`
   - Topic : `jms/topic1`

### Démarrage
1. Déployer jms-client1 (producteur)
2. Déployer jms-client2 (consommateur)
3. Déployer jms-client3 (consommateur)

### Résultats attendus
- jms-client2 reçoit le message de la file
- jms-client2 et jms-client3 reçoivent le message du topic 