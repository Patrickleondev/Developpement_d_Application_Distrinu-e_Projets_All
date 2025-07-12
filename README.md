# 🌐 Développement d'Applications Distribuées - Projets Complets

## 📋 Vue d'ensemble

Ce repository contient une collection complète de projets Java démontrant les concepts fondamentaux dans le cadre de notre cours de développement d'applications distribuées durant tout le semestre 6. Chaque projet illustre des technologies et patterns spécifiques utilisés dans le développement d'applications d'entreprise.

## 🚀 Projets inclus

### 1. 🌐 [Web Services REST](pd1-pd2/)
**Projets** : `pd1` (Serveur REST) et `pd2` (Client REST)
- **Technologies** : JAX-RS, Jersey, Jakarta EE
- **Concepts** : API REST, communication HTTP, sérialisation JSON
- **Architecture** : Client-Serveur
- [📖 Documentation détaillée](Documentation_Projets_Distribues/01_WebServices_REST/README.md)

### 2. 📨 [JMS - Java Message Service](jms-client1-jms-client2-jms-client3/)
**Projets** : `jms-client1`, `jms-client2`, `jms-client3`
- **Technologies** : JMS, Jakarta EE
- **Concepts** : Communication asynchrone, Queue vs Topic, Producteurs/Consommateurs
- **Architecture** : Publish/Subscribe, Point-to-Point
- [📖 Documentation détaillée](Documentation_Projets_Distribues/02_JMS_Java_Message_Service/README.md)

### 3. 🏪 [Architecture Boutique](Boutique-boutique-api-boutique-web/)
**Projets** : `Boutique`, `boutique-api`, `boutique-web`
- **Technologies** : Jakarta EE, JPA, JAX-RS, Servlets
- **Concepts** : Architecture en couches, Pattern MVC, CRUD operations
- **Architecture** : Multi-module, Séparation des responsabilités
- [📖 Documentation détaillée](Documentation_Projets_Distribues/03_Architecture_Boutique/README.md)

### 4. 🌤️ [Application Météo](meteo0/)
**Projet** : `meteo0`
- **Technologies** : Jersey Client, Gson, JavaBean
- **Concepts** : Consommation d'API externe, Traitement JSON, Conventions JavaBean
- **Architecture** : Client d'API externe
- [📖 Documentation détaillée](Documentation_Projets_Distribues/04_Application_Meteo/README.md)

## 📚 Documentation complète

### 📖 [Documentation principale](Documentation_Projets_Distribues/)
- [📋 Index de navigation](Documentation_Projets_Distribues/INDEX.md)
- [📖 README principal](Documentation_Projets_Distribues/README.md)
- [📝 Synthèse générale](Documentation_Projets_Distribues/Synthese_Generale.md)

### 📁 Structure de la documentation
```
Documentation_Projets_Distribues/
├── README.md                           # Vue d'ensemble générale
├── INDEX.md                           # Index de navigation
├── Synthese_Generale.md               # Synthèse complète
├── 01_WebServices_REST/              # Documentation Web Services
├── 02_JMS_Java_Message_Service/      # Documentation JMS
├── 03_Architecture_Boutique/         # Documentation Boutique
└── 04_Application_Meteo/             # Documentation Météo
```

##  Concepts clés couverts

### Technologies Java EE/Jakarta EE
- **JAX-RS** : Services web RESTful
- **JMS** : Messaging asynchrone
- **JPA** : Persistance des données
- **Servlets** : Applications web
- **CDI** : Injection de dépendances

### 🏗️ Patterns architecturaux
- **MVC** (Model-View-Controller)
- **Singleton** : Instance unique
- **Template Method** : Méthodes à redéfinir
- **DAO** : Accès aux données
- **Service Layer** : Couche métier

### 📋 Conventions et bonnes pratiques
- **JavaBean** : Conventions de nommage et structure
- **REST** : Principes et bonnes pratiques
- **Messaging** : Communication asynchrone
- **Architecture** : Séparation des responsabilités

##  Démarrage rapide

### Prérequis
- **Java** : Version 11 ou supérieure
-  **JDK** : 17,21
- **Maven** : Version 3.6 ou supérieure
- **Serveur d'application** : Payara6 (Recommandé par le prof), GlassFish,
- **IDE** : NetBeans25 (Recommandé) , Eclipse, ou IntelliJ IDEA

### Installation
```bash
# Cloner le repository
git clone https://github.com/Patrickleondev/Developpement_d_Application_Distrinu-e_Projets_All.git

# Naviguer vers le projet souhaité
cd pd1-pd2/          # Web Services REST
cd jms-client1-jms-client2-jms-client3/  # JMS
cd Boutique-boutique-api-boutique-web/   # Architecture Boutique
cd meteo0/           # Application Météo
```

### Compilation et déploiement
```bash
# Compiler le projet
mvn clean compile

# Créer le package
mvn clean package

# Déployer sur le serveur d'application
# (selon votre serveur : Payara6, GlassFish,)
```

## 🧪 Tests et exemples

### Tests Web Services
```bash
Ou soit avec RESTed carrément une extension dans les navigateur pour les requetes HTTP
# Test du serveur pd1
curl http://localhost:8080/pd1/api/hello

# Test du client pd2
curl http://localhost:8080/pd2/api/client
```

### Tests JMS
1. Configurer les ressources JMS sur le serveur
2. Déployer les applications dans l'ordre :
   - jms-client1 (producteur)
   - jms-client2 (consommateur)
   - jms-client3 (consommateur)

### Tests Boutique
```bash
# Lister les produits
curl http://localhost:8080/boutique-api/api/produits

# Créer un produit
curl -X POST http://localhost:8080/boutique-api/api/produits \
  -H "Content-Type: application/json" \
  -d '{"nom":"Produit Test","prix":29.99}'

(Possible avec RESTed)
```

### Tests Météo
```bash
# Exécuter l'application (Ou exécuter avec Netbeans)
java -jar meteo0/target/meteo0-1.0-SNAPSHOT.jar

# Test avec curl
curl "http://api.weatherstack.com/current?access_key=YOUR_KEY&query=Paris"
```

## 📊 Comparaison des technologies

| Projet | Communication | Type | Avantages |
|--------|---------------|------|-----------|
| **Web Services** | REST/HTTP | Synchrone | Simple, standard |
| **JMS** | Messages | Asynchrone | Fiable, découplé |
| **Boutique** | REST/HTTP | Synchrone | Architecture complète |
| **Météo** | REST/HTTP | Synchrone | API externe |

## 🎓 Préparation à l'examen

### Points importants à maîtriser
1. **Conventions JavaBean** : Structure et règles
2. **Architecture REST** : Principes et implémentation
3. **Communication JMS** : Queue vs Topic
4. **Patterns de conception** : MVC, Singleton, etc.
5. **Gestion des erreurs** : Codes HTTP, exceptions

### Questions de révision
- [Questions générales](Documentation_Projets_Distribues/Synthese_Generale.md#questions-de-révision-générales)
- [Questions par projet](Documentation_Projets_Distribues/INDEX.md#questions-de-révision-par-thème)
- [Conseils pour l'examen](Documentation_Projets_Distribues/Synthese_Generale.md#conseils-pour-lexamen)

## 🔗 Ressources utiles

### Documentation officielle
- [Jakarta EE](https://jakarta.ee/)
- [JAX-RS Specification](https://jakarta.ee/specifications/restful-ws/)
- [JMS Specification](https://jakarta.ee/specifications/messaging/)
- [JavaBean Specification](https://www.oracle.com/java/technologies/javase/javabeans-spec.html)

### Outils de développement
- [NetBeans IDE](https://netbeans.apache.org/)
- [RESTed](https://addons.mozilla.org/fr/firefox/addon/rested/) - Extension intégré dans le navigateur (Ce lien est pour Firefox, il y en a pour edge, chrome,...).
- [Postman](https://www.postman.com/) - Test d'APIs
- [curl](https://curl.se/) - Tests en ligne de commande

## 🤝 Contribution

Ce repository est conçu pour l'apprentissage et la préparation aux examens. Les contributions sont les bienvenues pour améliorer la documentation ou corriger des erreurs.

## 📄 Licence

Ce projet est destiné à des fins éducatives et dans le cadre de notre cours de développement d'application distribuée.

---

**💡 Conseil** : Commencez par explorer la [documentation principale](Documentation_Projets_Distribues/) pour comprendre l'architecture globale, puis plongez dans les projets spécifiques qui vous intéressent.

**⭐ N'oubliez pas** : Tous les projets respectent les conventions JavaBean et les bonnes pratiques de développement Java EE/Jakarta EE ! 
