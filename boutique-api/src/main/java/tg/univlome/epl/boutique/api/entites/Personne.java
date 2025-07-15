/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.api.entites;

/**
 *
 * @author techwizard
 */
import java.io.Serializable;
import java.util.Objects;

public class Personne implements Serializable{
    private Long id;
    private String nom;
    private String prenom;

    public Personne() {}

    public Personne(Long id, String nom, String prenom) {
        this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);
    }

    // Getters et setters avec validation
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'id de la personne doit être un long positif");
        }
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom ne peut être vide");
        }
        if (nom.length() > 50) {
            throw new IllegalArgumentException("Le nom ne peut excéder 50 caractères");
        }
        this.nom = nom.trim();
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if (prenom == null || prenom.trim().isEmpty()) {
            throw new IllegalArgumentException("Le prénom ne peut être vide");
        }
        if (prenom.length() > 50) {
            throw new IllegalArgumentException("Le prénom ne peut excéder 50 caractères");
        }
        this.prenom = prenom.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(id, personne.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}