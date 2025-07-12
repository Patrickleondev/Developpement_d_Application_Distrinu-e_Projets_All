# Application Météo - Consommation d'API REST

## Vue d'ensemble

L'application météo (meteo0) démontre la consommation d'une API REST externe pour récupérer des données météorologiques. Elle utilise l'API Weatherstack pour obtenir les informations météo d'une ville donnée. **Toutes les classes respectent les conventions JavaBean.**

## Architecture générale

```
┌─────────────────┐    HTTP/REST    ┌─────────────────┐
│  meteo0         │ ──────────────► │  Weatherstack   │
│  (Client)       │                 │  API            │
│                 │                 │                 │
│ - Jersey Client │                 │ - API REST      │
│ - Gson          │                 │ - Données JSON  │
│ - JavaBean      │                 │                 │
└─────────────────┘                 └─────────────────┘
```

## Configuration du projet

### Type de projet
- **Type** : Application Java standalone
- **Packaging** : JAR
- **Java Version** : 24
- **Classe principale** : `tg.univlome.epl.meteo0.WeatherClient`
- **Conventions** : JavaBean (Java Blue Print)

### Dépendances Maven

```xml
<dependencies>
    <!-- Gson pour la sérialisation JSON -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>
    
    <!-- Jersey Client pour les requêtes HTTP -->
    <dependency>
        <groupId>org.glassfish.jersey.core</groupId>
        <artifactId>jersey-client</artifactId>
        <version>3.1.10</version>
    </dependency>
    
    <!-- API JAX-RS -->
    <dependency>
        <groupId>jakarta.ws.rs</groupId>
        <artifactId>jakarta.ws.rs-api</artifactId>
        <version>3.1.0</version>
    </dependency>
</dependencies>
```

**Explication des dépendances** :
- **Gson** : Bibliothèque Google pour la sérialisation/désérialisation JSON
- **Jersey Client** : Client HTTP pour consommer les services REST
- **JAX-RS API** : API pour les services REST

## Structure du projet

```
meteo0/
├── src/main/java/
│   └── tg/univlome/epl/meteo0/
│       ├── WeatherClient.java       # Client principal
│       └── WeatherResponse.java     # Classes JavaBean
└── pom.xml
```

## Conventions JavaBean

### Règles JavaBean
1. **Attributs privés** : `private String type;`
2. **Constructeur par défaut** : `public Request() {}`
3. **Getters/Setters** : `getType()`, `setType(String type)`
4. **Sérialisation** : Implémenter `Serializable`
5. **Encapsulation** : Accès via méthodes

### Avantages JavaBean
- **Interopérabilité** : Compatible avec tous les frameworks
- **Réflexion** : Accès dynamique aux propriétés
- **Sérialisation** : Persistance et transmission
- **Validation** : Contrôle d'accès aux données

## Classes principales

### 1. WeatherClient.java

```java
/**
 * Client météo respectant les conventions JavaBean
 * @author techwizard
 */
public class WeatherClient {
    private static final String API_KEY = "5195c8c6f9af520e15549dad53e4ecad";
    private static final String BASE_URL = "http://api.weatherstack.com/current";

    public static void main(String[] args) {
        String city = "lome";
        try {
            // Création du client Jersey
            Client client = ClientBuilder.newClient();
            
            // Configuration de la requête
            WebTarget target = client.target(BASE_URL)
                .queryParam("access_key", API_KEY)
                .queryParam("query", city);

            // Envoi de la requête
            Response response = target.request(MediaType.APPLICATION_JSON).get();

            // Traitement de la réponse
            if (response.getStatus() == 200) {
                String json = response.readEntity(String.class);
                Gson gson = new Gson();
                WeatherResponse weather = gson.fromJson(json, WeatherResponse.class);
                
                // Affichage des données en utilisant les getters JavaBean
                if (weather != null && weather.getLocation() != null && weather.getCurrent() != null) {
                    System.out.println("Ville: " + weather.getLocation().getName());
                    System.out.println("Température: " + weather.getCurrent().getTemperature() + "°C");
                    System.out.println("Humidité: " + weather.getCurrent().getHumidity() + "%");
                    System.out.println("Précipitation: " + weather.getCurrent().getPrecip() + "mm");
                    
                    if (weather.getCurrent().getAstro() != null) {
                        System.out.println("Lever du soleil: " + weather.getCurrent().getAstro().getSunrise());
                        System.out.println("Coucher du soleil: " + weather.getCurrent().getAstro().getSunset());
                    } else {
                        System.out.println("Lever/Coucher du soleil: Données non disponibles");
                    }
                } else {
                    System.out.println("Réponse inattendue ou incomplète de l'API.");
                }
            } else {
                System.out.println("Erreur HTTP : " + response.getStatus());
            }
            
            // Fermeture du client
            client.close();
            
        } catch (Exception e) {
            System.err.println("Erreur lors de la communication avec Weatherstack : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

**Rôle** : Client principal qui consomme l'API météo
- **Configuration** : URL de base et clé API
- **Requête HTTP** : Utilisation de Jersey Client
- **Traitement JSON** : Désérialisation avec Gson
- **JavaBean** : Utilisation des getters/setters
- **Affichage** : Présentation des données météo

### 2. WeatherResponse.java (Classes JavaBean)

```java
/**
 * Classe principale de réponse météo respectant les conventions JavaBean
 * @author techwizard
 */
public class WeatherResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Request request;
    private Location location;
    private Current current;

    // Constructeur par défaut
    public WeatherResponse() {
    }

    // Getters et Setters
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "request=" + request +
                ", location=" + location +
                ", current=" + current +
                '}';
    }
}
```

**Rôle** : Classes de données JavaBean pour la réponse JSON
- **Encapsulation** : Attributs privés avec getters/setters
- **Sérialisation** : Implémentation de Serializable
- **Constructeur** : Constructeur par défaut
- **Mapping** : Correspondance avec la structure JSON de l'API
- **Classes imbriquées** : Organisation hiérarchique des données

### 3. Exemple de classe imbriquée JavaBean

```java
/**
 * Classe Location respectant les conventions JavaBean
 */
public static class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String name;
    private String country;
    private String region;
    private String lat;
    private String lon;
    private String timezone_id;
    private String localtime;
    private long localtime_epoch;
    private String utc_offset;

    // Constructeur par défaut
    public Location() {
    }

    // Getters et Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // ... autres getters/setters

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                // ... autres propriétés
                '}';
    }
}
```

## Flux d'exécution

### 1. Initialisation
```
1. Définition de la ville (Lomé)
2. Configuration de l'URL et de la clé API
3. Création du client Jersey
```

### 2. Requête HTTP
```
1. Construction de l'URL avec paramètres
2. Envoi de la requête GET
3. Récupération de la réponse JSON
```

### 3. Traitement des données
```
1. Vérification du statut HTTP (200)
2. Lecture du contenu JSON
3. Désérialisation avec Gson vers JavaBean
4. Validation des données via getters
```

### 4. Affichage
```
1. Extraction des informations via getters JavaBean
2. Formatage des données
3. Affichage à la console
```

## API Weatherstack

### Endpoint utilisé
- **URL** : `http://api.weatherstack.com/current`
- **Méthode** : GET
- **Format** : JSON

### Paramètres de requête
- **access_key** : Clé d'authentification API
- **query** : Nom de la ville (ex: "lome")

### Exemple de requête
```
GET http://api.weatherstack.com/current?access_key=5195c8c6f9af520e15549dad53e4ecad&query=lome
```

### Structure de réponse JSON
```json
{
  "request": {
    "type": "City",
    "query": "Lome, Togo",
    "language": "en",
    "unit": "m"
  },
  "location": {
    "name": "Lome",
    "country": "Togo",
    "region": "Maritime",
    "lat": "6.137",
    "lon": "1.212",
    "timezone_id": "Africa/Lome",
    "localtime": "2023-12-07 20:30",
    "localtime_epoch": 1701970200,
    "utc_offset": "0.0"
  },
  "current": {
    "observation_time": "08:30 PM",
    "temperature": 28,
    "weather_code": 113,
    "weather_icons": ["https://assets.weatherstack.com/images/wsymbols01_png_64/wsymbol_0001_sunny.png"],
    "weather_descriptions": ["Sunny"],
    "wind_speed": 11,
    "wind_degree": 200,
    "wind_dir": "SSW",
    "pressure": 1012,
    "precip": 0,
    "humidity": 75,
    "cloudcover": 0,
    "feelslike": 32,
    "uv_index": 6,
    "visibility": 10,
    "is_day": "no"
  }
}
```

## Points clés à retenir

### Conventions JavaBean
1. **Attributs privés** : `private String type;`
2. **Constructeur par défaut** : `public Request() {}`
3. **Getters/Setters** : `getType()`, `setType(String type)`
4. **Sérialisation** : `implements Serializable`
5. **Encapsulation** : Accès via méthodes

### Gestion des requêtes HTTP
1. **Client Jersey** : `ClientBuilder.newClient()`
2. **Configuration** : `client.target(BASE_URL).queryParam()`
3. **Envoi** : `target.request(MediaType.APPLICATION_JSON).get()`
4. **Fermeture** : `client.close()`

### Traitement JSON avec JavaBean
1. **Lecture** : `response.readEntity(String.class)`
2. **Désérialisation** : `gson.fromJson(json, WeatherResponse.class)`
3. **Accès** : `weather.getLocation().getName()`
4. **Validation** : Vérification des objets null

### Gestion d'erreurs
1. **Statut HTTP** : Vérification du code 200
2. **Exceptions** : Try-catch pour les erreurs réseau
3. **Validation** : Vérification des données reçues

### Bonnes pratiques JavaBean
1. **Encapsulation** : Toujours utiliser getters/setters
2. **Sérialisation** : Implémenter Serializable
3. **Documentation** : JavaDoc pour les classes
4. **Validation** : Vérification des données avant affichage

## Questions de révision

1. **Quel est le rôle de Gson dans cette application ?**
   - Désérialisation JSON vers objets JavaBean

2. **Comment configurer une requête avec des paramètres ?**
   - `client.target(BASE_URL).queryParam("key", "value")`

3. **Quelle méthode utilise-t-on pour envoyer une requête GET ?**
   - `target.request(MediaType.APPLICATION_JSON).get()`

4. **Comment valider une réponse HTTP ?**
   - Vérifier `response.getStatus() == 200`

5. **Pourquoi utiliser des classes imbriquées dans WeatherResponse ?**
   - Organiser la structure hiérarchique des données JSON

6. **Quels sont les avantages de consommer une API REST externe ?**
   - Données à jour, pas de maintenance, spécialisation

7. **Comment gérer les erreurs de communication ?**
   - Try-catch avec messages d'erreur appropriés

8. **Quelle est la différence entre sérialisation et désérialisation ?**
   - Sérialisation : Objet → JSON
   - Désérialisation : JSON → Objet

9. **Quelles sont les conventions JavaBean ?**
   - Attributs privés, constructeur par défaut, getters/setters, Serializable

10. **Pourquoi respecter les conventions JavaBean ?**
    - Interopérabilité, réflexion, sérialisation, encapsulation

## Test de l'application

### Prérequis
1. Connexion Internet
2. Clé API Weatherstack valide
3. Java 24 installé

### Exécution
```bash
# Compilation
mvn clean compile

# Exécution
mvn exec:java
```

### Résultat attendu
```
Ville: Lome
Température: 28°C
Humidité: 75%
Précipitation: 0mm
Lever du soleil: 05:45 AM
Coucher du soleil: 05:45 PM
```

### Test avec curl
```bash
curl "http://api.weatherstack.com/current?access_key=5195c8c6f9af520e15549dad53e4ecad&query=lome"
```

## Extensions possibles

### 1. Interface utilisateur
- Ajouter une interface graphique
- Permettre la sélection de ville
- Afficher des graphiques météo

### 2. Persistance
- Sauvegarder les données météo
- Historique des températures
- Statistiques météo

### 3. Notifications
- Alertes météo
- Notifications push
- Rapports quotidiens

### 4. Multi-villes
- Comparaison de villes
- Carte météo
- Prévisions multi-villes

### 5. Validation JavaBean
- Validation des données avec Bean Validation
- Contraintes sur les propriétés
- Messages d'erreur personnalisés 