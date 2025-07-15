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

public class Categorie  implements Serializable{
    private Integer id;
    private String libelle;
    private String description;

    public Categorie() {}

    public Categorie(Integer id, String libelle, String description) {
        this.setId(id);
        this.setLibelle(libelle);
        this.setDescription(description);
    }

    // Getters et setters avec validation
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'id de la catégorie doit être un entier positif");
        }
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        if (libelle == null || libelle.trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé de la catégorie ne peut être vide");
        }
        if (libelle.length() > 50) {
            throw new IllegalArgumentException("Le libellé ne peut excéder 50 caractères");
        }
        this.libelle = libelle.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = (description != null) ? description.trim() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return Objects.equals(id, categorie.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}