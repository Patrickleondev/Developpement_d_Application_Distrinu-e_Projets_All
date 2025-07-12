# 📚 Index de Navigation - Projets Distribués

## 🏠 [Retour à l'accueil](../README.md)

## 📋 Structure de la documentation

### 📖 [README.md](README.md)
Documentation principale avec vue d'ensemble de tous les projets

### 🔗 [01_WebServices_REST/README.md](01_WebServices_REST/README.md)
**Projets** : [pd1-pd2/](../pd1-pd2/) (Serveur REST et Client REST)
- Configuration JAX-RS
- Endpoints REST
- Client Jersey
- Communication HTTP
- [📁 Dossier des projets](../pd1-pd2/)

### 📨 [02_JMS_Java_Message_Service/README.md](02_JMS_Java_Message_Service/README.md)
**Projets** : [jms-client1-jms-client2-jms-client3/](../jms-client1-jms-client2-jms-client3/)
- Communication asynchrone
- Queue vs Topic
- Producteurs et consommateurs
- Patterns JMS
- [📁 Dossier des projets](../jms-client1-jms-client2-jms-client3/)

### 🏪 [03_Architecture_Boutique/README.md](03_Architecture_Boutique/README.md)
**Projets** : [Boutique-boutique-api-boutique-web/](../Boutique-boutique-api-boutique-web/)
- Architecture en couches
- Pattern MVC
- Services métier
- API REST complète
- [📁 Dossier des projets](../Boutique-boutique-api-boutique-web/)

### 🌤️ [04_Application_Meteo/README.md](04_Application_Meteo/README.md)
**Projet** : [meteo0/](../meteo0/)
- Consommation d'API externe
- Traitement JSON
- Client HTTP
- **Conventions JavaBean**
- Gestion d'erreurs
- [📁 Dossier du projet](../meteo0/)

### 📝 [Synthese_Generale.md](Synthese_Generale.md)
Synthèse complète de tous les concepts
- **Conventions JavaBean**
- Points clés à retenir
- Questions de révision
- Conseils pour l'examen

## 🎯 Navigation rapide par concept

### JavaBean (Java Blue Print)
- [Conventions JavaBean](04_Application_Meteo/README.md#conventions-javabean)
- [Règles JavaBean](04_Application_Meteo/README.md#règles-javabean)
- [Avantages JavaBean](04_Application_Meteo/README.md#avantages-javabean)
- [Exemple JavaBean](Synthese_Generale.md#exemple-javabean)

### Web Services REST
- [Configuration JAX-RS](01_WebServices_REST/README.md#configuration-jax-rs)
- [Annotations importantes](01_WebServices_REST/README.md#annotations-jax-rs-importantes)
- [Codes de statut HTTP](01_WebServices_REST/README.md#codes-de-statut-http)

### JMS
- [Types de destinations](02_JMS_Java_Message_Service/README.md#types-de-destinations)
- [Classes principales](02_JMS_Java_Message_Service/README.md#classes-jms-principales)
- [Différences Queue vs Topic](02_JMS_Java_Message_Service/README.md#différences-queue-vs-topic)

### Architecture
- [Pattern MVC](03_Architecture_Boutique/README.md#pattern-mvc)
- [Séparation des responsabilités](03_Architecture_Boutique/README.md#séparation-des-responsabilités)
- [Patterns de conception](03_Architecture_Boutique/README.md#patterns-utilisés)

### API Externes
- [Consommation d'API](04_Application_Meteo/README.md#consommation-dapi-rest)
- [Traitement JSON](04_Application_Meteo/README.md#traitement-json)
- [Gestion d'erreurs](04_Application_Meteo/README.md#gestion-derreurs)

## 📝 Questions de révision par thème

### JavaBean
1. [Quelles sont les conventions JavaBean ?](04_Application_Meteo/README.md#questions-de-révision)
2. [Pourquoi respecter les conventions JavaBean ?](04_Application_Meteo/README.md#questions-de-révision)
3. [Comment accéder aux propriétés d'un JavaBean ?](Synthese_Generale.md#javabean)

### Web Services REST
1. [Quelle est la différence entre pd1 et pd2 ?](01_WebServices_REST/README.md#questions-de-révision)
2. [Quel est le rôle de @ApplicationPath ?](01_WebServices_REST/README.md#questions-de-révision)
3. [Comment le client communique-t-il avec le serveur ?](01_WebServices_REST/README.md#questions-de-révision)

### JMS
1. [Quelle est la différence entre Queue et Topic ?](02_JMS_Java_Message_Service/README.md#questions-de-révision)
2. [Quel est le rôle de @WebListener ?](02_JMS_Java_Message_Service/README.md#questions-de-révision)
3. [Comment créer un producteur JMS ?](02_JMS_Java_Message_Service/README.md#questions-de-révision)

### Architecture Boutique
1. [Quelle est l'architecture de l'application Boutique ?](03_Architecture_Boutique/README.md#questions-de-révision)
2. [Quel pattern utilise GenericService ?](03_Architecture_Boutique/README.md#questions-de-révision)
3. [Comment les modules communiquent-ils ?](03_Architecture_Boutique/README.md#questions-de-révision)

### Application Météo
1. [Quel est le rôle de Gson dans cette application ?](04_Application_Meteo/README.md#questions-de-révision)
2. [Comment configurer une requête avec des paramètres ?](04_Application_Meteo/README.md#questions-de-révision)
3. [Comment valider une réponse HTTP ?](04_Application_Meteo/README.md#questions-de-révision)

## 🧪 Tests et exemples

### Tests Web Services
- [Test du serveur pd1](01_WebServices_REST/README.md#test-de-lapplication)
- [Test du client pd2](01_WebServices_REST/README.md#test-de-lapplication)
- [Test manuel avec curl](01_WebServices_REST/README.md#test-manuel)

### Tests JMS
- [Configuration du serveur JMS](02_JMS_Java_Message_Service/README.md#test-de-lapplication)
- [Démarrage des applications](02_JMS_Java_Message_Service/README.md#test-de-lapplication)
- [Résultats attendus](02_JMS_Java_Message_Service/README.md#test-de-lapplication)

### Tests Boutique
- [Démarrage de l'application](03_Architecture_Boutique/README.md#test-de-lapplication)
- [Tests avec curl](03_Architecture_Boutique/README.md#tests-avec-curl)
- [Endpoints disponibles](03_Architecture_Boutique/README.md#endpoints-disponibles)

### Tests Météo
- [Exécution de l'application](04_Application_Meteo/README.md#test-de-lapplication)
- [Test avec curl](04_Application_Meteo/README.md#test-avec-curl)
- [Résultat attendu](04_Application_Meteo/README.md#test-de-lapplication)

## 🔧 Configuration et déploiement

### Configuration Maven
- [Dépendances Web Services](01_WebServices_REST/README.md#dépendances-maven)
- [Dépendances JMS](02_JMS_Java_Message_Service/README.md#dépendances-maven)
- [Dépendances Boutique](03_Architecture_Boutique/README.md#dépendances-maven)
- [Dépendances Météo](04_Application_Meteo/README.md#dépendances-maven)

### Serveurs d'application
- [GlassFish](01_WebServices_REST/README.md#test-de-lapplication)
- [WildFly](02_JMS_Java_Message_Service/README.md#test-de-lapplication)
- [TomEE](03_Architecture_Boutique/README.md#test-de-lapplication)

## 📊 Comparaisons et choix techniques

### Communication
| Type | Avantages | Inconvénients | Utilisation |
|------|-----------|---------------|-------------|
| **REST** | Simple, standard | Synchrone | APIs web |
| **JMS** | Asynchrone, fiable | Complexe | Communication interne |
| **WebSocket** | Bidirectionnel | État | Temps réel |

### Architecture
| Pattern | Description | Avantages |
|---------|-------------|-----------|
| **MVC** | Séparation des responsabilités | Maintenabilité |
| **Singleton** | Une seule instance | Contrôle des ressources |
| **Template Method** | Méthodes à redéfinir | Réutilisabilité |

### JavaBean vs Classes simples
| Aspect | JavaBean | Classe simple |
|--------|----------|---------------|
| **Encapsulation** | ✅ Getters/Setters | ❌ Attributs publics |
| **Sérialisation** | ✅ Serializable | ❌ Pas de sérialisation |
| **Interopérabilité** | ✅ Standards | ❌ Propriétaire |
| **Réflexion** | ✅ Accès dynamique | ❌ Accès limité |

## 🎓 Préparation à l'examen

### Concepts fondamentaux
- [Conventions JavaBean](Synthese_Generale.md#conventions-javabean-java-blue-print)
- [Architecture distribuée](Synthese_Generale.md#architecture)
- [Communication entre applications](Synthese_Generale.md#communication-entre-applications)
- [Gestion des données](Synthese_Generale.md#gestion-des-données)
- [Sécurité](Synthese_Generale.md#sécurité)

### Questions types
- [Questions de révision générales](Synthese_Generale.md#questions-de-révision-générales)
- [Conseils pour l'examen](Synthese_Generale.md#conseils-pour-lexamen)
- [Points importants](Synthese_Generale.md#points-importants)

## 📞 Support et ressources

### Documentation officielle
- [Jakarta EE](https://jakarta.ee/)
- [JAX-RS](https://jakarta.ee/specifications/restful-ws/)
- [JMS](https://jakarta.ee/specifications/messaging/)
- [Maven](https://maven.apache.org/)
- [JavaBean Specification](https://www.oracle.com/java/technologies/javase/javabeans-spec.html)

### Outils de développement
- [NetBeans](https://netbeans.apache.org/)
- [Postman](https://www.postman.com/)
- [curl](https://curl.se/)

## 🚀 Accès rapide aux projets

### 📁 Dossiers des projets
- [🌐 pd1-pd2/](../pd1-pd2/) - Web Services REST
- [📨 jms-client1-jms-client2-jms-client3/](../jms-client1-jms-client2-jms-client3/) - JMS
- [🏪 Boutique-boutique-api-boutique-web/](../Boutique-boutique-api-boutique-web/) - Architecture Boutique
- [🌤️ meteo0/](../meteo0/) - Application Météo

### 📚 Documentation par projet
- [📖 Documentation Web Services](01_WebServices_REST/README.md)
- [📖 Documentation JMS](02_JMS_Java_Message_Service/README.md)
- [📖 Documentation Boutique](03_Architecture_Boutique/README.md)
- [📖 Documentation Météo](04_Application_Meteo/README.md)

---

**💡 Conseil** : Utilisez cet index pour naviguer rapidement dans la documentation et trouver les informations dont vous avez besoin pour votre révision. **N'oubliez pas que toutes les classes doivent respecter les conventions JavaBean !**

**🏠 [Retour à l'accueil](../README.md)** 