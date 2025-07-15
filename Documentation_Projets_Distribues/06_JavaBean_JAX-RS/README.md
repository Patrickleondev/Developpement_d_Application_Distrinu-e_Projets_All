# JavaBean et JAX-RS - Gestion des Élèves

## 📸 Photo de l'Examen

![Examen Programmation Distribuée](exam2.jpg)

*Examen de fin de semestre - Programmation Distribuée (2021-2022)*

## Contexte de l'Examen

Examen de fin de semestre en Programmation Distribuée (2021-2022) portant sur la création d'un système d'inscription des élèves avec :
- JavaBean pour la gestion des données
- Service CRUD
- API REST avec JAX-RS

## Objectifs

1. Créer une classe JavaBean `Eleve` pour stocker les informations du formulaire
2. Implémenter un service `EleveService` avec les opérations CRUD
3. Exposer ces opérations via une API REST JAX-RS

## Structure du Projet

```
student-registration/
├── src/
│   └── main/
│       ├── java/
│       │   └── tg/univlome/epl/student/
│       │       ├── entity/
│       │       │   └── Eleve.java
│       │       ├── service/
│       │       │   └── EleveService.java
│       │       └── rest/
│       │           └── EleveResource.java
│       └── webapp/
│           └── WEB-INF/
│               └── web.xml
└── pom.xml
```

## Solution Détaillée

### 1. Classe JavaBean Eleve

La classe `Eleve` implémente `Serializable` et suit les conventions JavaBean :
- Constructeur par défaut
- Propriétés privées
- Getters et setters
- Méthodes equals(), hashCode() et toString()

```java
package tg.univlome.epl.student.entity;

import java.io.Serializable;
import java.util.Objects;

public class Eleve implements Serializable {
    private String nom;
    private String prenom;
    private String classe;
    private String sexe;
    private String option;
    private String journee;
    
    public Eleve() {
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
    
    public String getClasse() {
        return classe;
    }
    
    public void setClasse(String classe) {
        this.classe = classe;
    }
    
    public String getSexe() {
        return sexe;
    }
    
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    public String getOption() {
        return option;
    }
    
    public void setOption(String option) {
        this.option = option;
    }
    
    public String getJournee() {
        return journee;
    }
    
    public void setJournee(String journee) {
        this.journee = journee;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, classe);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Eleve other = (Eleve) obj;
        return Objects.equals(nom, other.nom) 
            && Objects.equals(prenom, other.prenom)
            && Objects.equals(classe, other.classe);
    }
    
    @Override
    public String toString() {
        return "Eleve{" 
            + "nom='" + nom + '\''
            + ", prenom='" + prenom + '\''
            + ", classe='" + classe + '\''
            + ", sexe='" + sexe + '\''
            + ", option='" + option + '\''
            + ", journee='" + journee + '\''
            + '}';
    }
}
```

### 2. Service CRUD EleveService

La classe `EleveService` implémente les opérations CRUD basiques :

```java
package tg.univlome.epl.student.service;

import tg.univlome.epl.student.entity.Eleve;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EleveService {
    private final List<Eleve> eleves = new ArrayList<>();
    
    // CREATE
    public Eleve create(Eleve eleve) {
        eleves.add(eleve);
        return eleve;
    }
    
    // READ
    public List<Eleve> findAll() {
        return new ArrayList<>(eleves);
    }
    
    public Optional<Eleve> findByNomAndPrenom(String nom, String prenom) {
        return eleves.stream()
            .filter(e -> e.getNom().equals(nom) && e.getPrenom().equals(prenom))
            .findFirst();
    }
    
    // UPDATE
    public Eleve update(Eleve eleve) {
        Optional<Eleve> existing = findByNomAndPrenom(eleve.getNom(), eleve.getPrenom());
        if (existing.isPresent()) {
            eleves.remove(existing.get());
            eleves.add(eleve);
            return eleve;
        }
        return null;
    }
    
    // DELETE
    public boolean delete(String nom, String prenom) {
        return findByNomAndPrenom(nom, prenom)
            .map(eleves::remove)
            .orElse(false);
    }
}
```

### 3. API REST avec JAX-RS

Le service REST expose les opérations CRUD via HTTP :

```java
package tg.univlome.epl.student.rest;

import tg.univlome.epl.student.entity.Eleve;
import tg.univlome.epl.student.service.EleveService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/eleves")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EleveResource {
    private final EleveService service = new EleveService();
    
    @POST
    public Response create(Eleve eleve) {
        Eleve created = service.create(eleve);
        return Response.status(Response.Status.CREATED)
            .entity(created)
            .build();
    }
    
    @GET
    public List<Eleve> findAll() {
        return service.findAll();
    }
    
    @GET
    @Path("/{nom}/{prenom}")
    public Response findOne(
            @PathParam("nom") String nom,
            @PathParam("prenom") String prenom) {
        return service.findByNomAndPrenom(nom, prenom)
            .map(Response::ok)
            .orElse(Response.status(Response.Status.NOT_FOUND))
            .build();
    }
    
    @PUT
    public Response update(Eleve eleve) {
        Eleve updated = service.update(eleve);
        if (updated != null) {
            return Response.ok(updated).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @DELETE
    @Path("/{nom}/{prenom}")
    public Response delete(
            @PathParam("nom") String nom,
            @PathParam("prenom") String prenom) {
        boolean deleted = service.delete(nom, prenom);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
```

## Configuration du Projet

### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>tg.univlome.epl</groupId>
    <artifactId>student-registration</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>jakarta.platform</groupId>
            <artifactId>jakarta.jakartaee-api</artifactId>
            <version>10.0.0</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.3.2</version>
            </plugin>
        </plugins>
    </build>
</project>
```

### web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/web-app_6_0.xsd"
         version="6.0">
    
    <servlet>
        <servlet-name>jakarta.ws.rs.core.Application</servlet-name>
    </servlet>
    <servlet-mapping>
        <servlet-name>jakarta.ws.rs.core.Application</servlet-name>
        <url-pattern>/api/*</url-pattern>
    </servlet-mapping>
    
</web-app>
```

## Tests des Endpoints

### Créer un élève (POST)
```bash
curl -X POST http://localhost:8080/student-registration/api/eleves \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Doe",
    "prenom": "John",
    "classe": "Terminal",
    "sexe": "Homme",
    "option": "Scientifique",
    "journee": "Lundi"
  }'
```

### Lister tous les élèves (GET)
```bash
curl http://localhost:8080/student-registration/api/eleves
```

### Rechercher un élève (GET)
```bash
curl http://localhost:8080/student-registration/api/eleves/Doe/John
```

### Mettre à jour un élève (PUT)
```bash
curl -X PUT http://localhost:8080/student-registration/api/eleves \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Doe",
    "prenom": "John",
    "classe": "Terminal",
    "sexe": "Homme",
    "option": "Langue",
    "journee": "Mardi"
  }'
```

### Supprimer un élève (DELETE)
```bash
curl -X DELETE http://localhost:8080/student-registration/api/eleves/Doe/John
```

## Points Importants

1. **JavaBean Conventions**
   - Implémentation de `Serializable`
   - Constructeur par défaut
   - Propriétés privées avec getters/setters
   - Méthodes equals(), hashCode(), toString()

2. **Service CRUD**
   - Gestion en mémoire avec `ArrayList`
   - Opérations basiques : Create, Read, Update, Delete
   - Recherche par nom et prénom

3. **API REST**
   - Utilisation des annotations JAX-RS
   - Gestion des réponses HTTP appropriées
   - Format JSON pour les données
   - Routes RESTful

4. **Bonnes Pratiques**
   - Séparation des couches (entity, service, rest)
   - Gestion appropriée des erreurs
   - Utilisation des codes HTTP standards
   - Documentation des endpoints 
