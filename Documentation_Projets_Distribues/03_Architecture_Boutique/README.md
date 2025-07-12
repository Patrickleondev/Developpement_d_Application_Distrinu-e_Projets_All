# Architecture Boutique - Application Distribuée

## Vue d'ensemble

L'architecture Boutique est un exemple d'application distribuée qui démontre les concepts de séparation des responsabilités et de communication entre couches. Le projet est divisé en trois modules :

- **Boutique** : Module principal avec les entités métier
- **boutique-api** : API avec les services métier
- **boutique-web** : Interface web avec les endpoints REST

## Architecture générale

```
┌─────────────────┐    HTTP/REST    ┌─────────────────┐
│   Client Web    │ ──────────────► │  boutique-web   │
│   (Navigateur)  │                 │                 │
└─────────────────┘                 └─────────────────┘
                                           │
                                           ▼
┌─────────────────┐    Services     ┌─────────────────┐
│  boutique-api   │ ◄────────────── │  boutique-web   │
│                 │                 │                 │
│ - Entités       │                 │ - Resources     │
│ - Services      │                 │ - Controllers   │
└─────────────────┘                 └─────────────────┘
        │
        ▼
┌─────────────────┐
│    Boutique     │
│                 │
│ - Entités       │
│ - Logique       │
└─────────────────┘
```

## Module Boutique - Entités métier

### Configuration du projet

**Type de projet** : Application Java standalone
**Packaging** : JAR
**Java Version** : 11

### Structure du projet

```
Boutique/
├── src/main/java/
│   └── tg/univlome/epl/boutique/
│       ├── Boutique.java           # Classe principale
│       ├── Personne.java           # Classe abstraite
│       ├── Client.java             # Hérite de Personne
│       ├── Employe.java            # Hérite de Personne
│       ├── Categorie.java          # Catégorie de produit
│       ├── Produit.java            # Produit de la boutique
│       ├── ProduitAchete.java      # Produit dans un achat
│       └── Achat.java              # Achat d'un client
└── pom.xml
```

### Classes principales

#### 1. Personne.java (Classe abstraite)

```java
public abstract class Personne {
    private Long id;
    private String nom;
    private String prenom;
    
    // Constructeurs, getters, setters
    // Méthodes abstraites et concrètes
}
```

**Rôle** : Classe de base pour Client et Employé
- **Abstraction** : Factorise les propriétés communes
- **Héritage** : Permet la spécialisation

#### 2. Client.java

```java
public class Client extends Personne {
    // Propriétés spécifiques au client
    // Méthodes métier
}
```

**Rôle** : Représente un client de la boutique

#### 3. Employe.java

```java
public class Employe extends Personne {
    private LocalDate dateNaissance;
    
    // Validation de l'âge
    // Méthodes métier
}
```

**Rôle** : Représente un employé de la boutique

#### 4. Categorie.java

```java
public class Categorie {
    private Long id;
    private String nom;
    private String description;
    
    // Validation des données
    // Méthodes métier
}
```

**Rôle** : Catégorise les produits

#### 5. Produit.java

```java
public class Produit {
    private Long id;
    private String nom;
    private double prix;
    private LocalDate dateExpiration;
    private Categorie categorie;
    
    public boolean estPerime() {
        return dateExpiration != null && 
               dateExpiration.isBefore(LocalDate.now());
    }
}
```

**Rôle** : Représente un produit de la boutique
- **Validation** : Vérification de la péremption
- **Relations** : Association avec Categorie

#### 6. ProduitAchete.java

```java
public class ProduitAchete {
    private Produit produit;
    private double remise;
    
    public double getPrixFinal() {
        return produit.getPrix() - remise;
    }
}
```

**Rôle** : Produit dans le contexte d'un achat
- **Remise** : Calcul du prix final
- **Composition** : Contient un Produit

#### 7. Achat.java

```java
public class Achat {
    private Long id;
    private LocalDate date;
    private Client client;
    private List<ProduitAchete> produits;
    private double remiseGlobale;
    
    public double getTotalAPayer() {
        double total = produits.stream()
            .mapToDouble(ProduitAchete::getPrixFinal)
            .sum();
        return total - remiseGlobale;
    }
}
```

**Rôle** : Représente un achat d'un client
- **Calculs** : Total à payer, remises
- **Relations** : Client et liste de produits

#### 8. Boutique.java

```java
public class Boutique {
    public static void main(String[] args) {
        // Création des données de test
        // Démonstration des fonctionnalités
        // Tests de validation
    }
}
```

**Rôle** : Classe principale de démonstration
- **Tests** : Validation des règles métier
- **Exemples** : Utilisation des classes

## Module boutique-api - Services métier

### Configuration du projet

**Type de projet** : Application Java standalone
**Packaging** : JAR
**Java Version** : 11

### Structure du projet

```
boutique-api/
├── src/main/java/
│   └── tg/univlome/epl/boutique/api/
│       ├── BoutiqueApi.java         # Classe principale
│       ├── entites/                 # Entités (copies de Boutique)
│       └── service/                 # Services métier
│           ├── GenericService.java  # Service générique
│           ├── ProduitService.java  # Service des produits
│           ├── ClientService.java   # Service des clients
│           ├── CategorieService.java
│           ├── EmployeService.java
│           └── AchatService.java
└── pom.xml
```

### Classes principales

#### 1. GenericService.java

```java
public class GenericService<E, ID> {
    protected List<E> liste = new ArrayList<>();

    public void ajouter(E p) {
        liste.add(p);
    }

    public void modifier(E p) {
        liste.replaceAll(a -> a.equals(p) ? p : a);
    }

    public void supprimer(ID id) {
        throw new UnsupportedOperationException(
            "Cette méthode doit être redéfinie dans la sous-classe.");
    }

    public E trouver(ID id) {
        throw new UnsupportedOperationException(
            "Cette méthode doit être redéfinie dans la sous-classe.");
    }

    public List<E> lister() {
        return liste;
    }

    public List<E> listeperime() {
        throw new UnsupportedOperationException(
            "Cette méthode doit être redéfinie dans la sous-classe.");
    }
}
```

**Rôle** : Service générique avec opérations CRUD
- **Généricité** : `<E, ID>` pour type d'entité et type d'ID
- **Pattern Template Method** : Méthodes à redéfinir
- **Gestion** : Liste en mémoire des entités

#### 2. ProduitService.java

```java
public class ProduitService extends GenericService<Produit, Long> {
    private static ProduitService instance;
    
    private ProduitService() {}
    
    public static ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }
        return instance;
    }
    
    @Override
    public void supprimer(Long id) {
        liste.removeIf(p -> p.getId().equals(id));
    }
    
    @Override
    public Produit trouver(Long id) {
        return liste.stream()
                   .filter(p -> p.getId().equals(id))
                   .findFirst()
                   .orElse(null);
    }
    
    @Override
    public List<Produit> listeperime() {
        return liste.stream()
                   .filter(Produit::estPerime)
                   .collect(Collectors.toList());
    }
}
```

**Rôle** : Service spécifique pour les produits
- **Singleton** : Une seule instance
- **Spécialisation** : Implémentation des méthodes abstraites
- **Filtrage** : Produits périmés

## Module boutique-web - Interface REST

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
boutique-web/
├── src/main/java/
│   └── tg/univlome/epl/boutique/web/
│       ├── JakartaRestConfiguration.java
│       └── resources/               # Endpoints REST
│           ├── ProduitResource.java
│           ├── ClientResource.java
│           ├── CategorieResource.java
│           ├── EmployeResource.java
│           ├── AchatResource.java
│           └── AppExeptionMapper.java
└── pom.xml
```

### Classes principales

#### 1. JakartaRestConfiguration.java

```java
@ApplicationPath("/api")
public class JakartaRestConfiguration extends Application {
}
```

**Rôle** : Configuration JAX-RS
- **Chemin de base** : `/api`
- **URL complète** : `http://localhost:8080/boutique-web/api`

#### 2. ProduitResource.java

```java
@Path("/produits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProduitResource {
    private final ProduitService service = ProduitService.getInstance();

    @POST
    public Response ajouter(Produit produit) {
        try {
            service.ajouter(produit);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response modifier(@PathParam("id") Long id, Produit produit) {
        try {
            if (!id.equals(produit.getId())) {
                throw new IllegalArgumentException("ID produit incohérent");
            }
            service.modifier(produit);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage())
                           .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response supprimer(@PathParam("id") Long id) {
        try {
            service.supprimer(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response trouver(@PathParam("id") Long id) {
        Produit produit = service.trouver(id);
        if (produit != null) {
            return Response.ok(produit).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<Produit> lister() {
        return service.lister();
    }

    @GET
    @Path("/perimes")
    public List<Produit> listerPerimes() {
        return service.listeperime();
    }
}
```

**Rôle** : Endpoints REST pour les produits
- **CRUD complet** : Create, Read, Update, Delete
- **Gestion d'erreurs** : Codes de statut HTTP appropriés
- **Validation** : Vérification des données

### Endpoints disponibles

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/produits` | Lister tous les produits |
| GET | `/api/produits/{id}` | Trouver un produit par ID |
| GET | `/api/produits/perimes` | Lister les produits périmés |
| POST | `/api/produits` | Ajouter un produit |
| PUT | `/api/produits/{id}` | Modifier un produit |
| DELETE | `/api/produits/{id}` | Supprimer un produit |

## Flux de données

### 1. Requête client
```
Client → HTTP Request → boutique-web
```

### 2. Traitement
```
boutique-web → Resource → Service → Entité
```

### 3. Réponse
```
Entité → Service → Resource → boutique-web → HTTP Response → Client
```

## Patterns utilisés

### 1. Pattern Singleton
```java
public static ProduitService getInstance() {
    if (instance == null) {
        instance = new ProduitService();
    }
    return instance;
}
```

### 2. Pattern Template Method
```java
public abstract class GenericService<E, ID> {
    // Méthodes concrètes
    public void ajouter(E p) { ... }
    
    // Méthodes abstraites à redéfinir
    public abstract void supprimer(ID id);
}
```

### 3. Pattern DAO (Data Access Object)
```java
public class ProduitService extends GenericService<Produit, Long> {
    // Accès aux données encapsulé
}
```

### 4. Pattern MVC (Model-View-Controller)
- **Model** : Entités (Boutique, boutique-api)
- **View** : Interface REST (boutique-web)
- **Controller** : Resources REST

## Points clés à retenir

### Séparation des responsabilités
1. **Boutique** : Entités métier et logique
2. **boutique-api** : Services et couche métier
3. **boutique-web** : Interface et présentation

### Communication entre couches
- **Dépendances** : boutique-web → boutique-api → Boutique
- **Couplage faible** : Interface REST entre couches
- **Réutilisabilité** : API utilisable par différents clients

### Gestion des erreurs
- **Validation** : Dans les entités et services
- **Codes HTTP** : Réponses appropriées
- **Messages** : Informations d'erreur claires

### Questions de révision

1. **Quelle est l'architecture de l'application Boutique ?**
   - 3 modules : Boutique (entités), boutique-api (services), boutique-web (REST)

2. **Quel pattern utilise GenericService ?**
   - Template Method avec méthodes abstraites

3. **Comment les modules communiquent-ils ?**
   - Via les services et l'interface REST

4. **Quel est le rôle de @PathParam ?**
   - Récupérer les paramètres de l'URL

5. **Pourquoi utiliser le pattern Singleton ?**
   - Garantir une seule instance du service

6. **Comment gérer les erreurs dans les Resources ?**
   - Try-catch avec codes de statut HTTP appropriés

## Test de l'application

### Démarrage
1. Déployer boutique-web sur un serveur d'application
2. Accéder à `http://localhost:8080/boutique-web/api/produits`

### Tests avec curl
```bash
# Lister les produits
curl http://localhost:8080/boutique-web/api/produits

# Ajouter un produit
curl -X POST http://localhost:8080/boutique-web/api/produits \
  -H "Content-Type: application/json" \
  -d '{"id":1,"nom":"Test","prix":100}'

# Trouver un produit
curl http://localhost:8080/boutique-web/api/produits/1
``` 