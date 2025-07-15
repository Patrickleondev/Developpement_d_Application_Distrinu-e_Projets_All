/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique;

/**
 *
 * @author techwizard
 */
import java.time.LocalDate;
import java.util.Objects;

public class Produit {
    private Long id;
    private String libelle;
    private double prixUnitaire;
    private LocalDate datePeremption;
    private Categorie categorie;

    public Produit() {}

    public Produit(Long id, String libelle, double prixUnitaire, LocalDate datePeremption) {
        this.setId(id);
        this.setLibelle(libelle);
        this.setPrixUnitaire(prixUnitaire);
        this.setDatePeremption(datePeremption);
    }

    // Getters et setters avec validation
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'id du produit doit être un long positif");
        }
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        if (libelle == null || libelle.trim().isEmpty()) {
            throw new IllegalArgumentException("Le libellé du produit ne peut être vide");
        }
        if (libelle.length() > 100) {
            throw new IllegalArgumentException("Le libellé ne peut excéder 100 caractères");
        }
        this.libelle = libelle.trim();
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        if (prixUnitaire < 0) {
            throw new IllegalArgumentException("Le prix unitaire ne peut être négatif");
        }
        this.prixUnitaire = prixUnitaire;
    }

    public LocalDate getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(LocalDate datePeremption) {
        this.datePeremption = datePeremption;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        if (categorie == null) {
            throw new IllegalArgumentException("Un produit doit appartenir à une catégorie");
        }
        this.categorie = categorie;
    }

    // Méthodes métier avec gestion des nulls
    public boolean estPerime() {
        return estPerime(LocalDate.now());
    }

    public boolean estPerime(LocalDate dateReference) {
        if (dateReference == null) {
            throw new IllegalArgumentException("La date de référence ne peut être nulle");
        }
        return datePeremption != null && datePeremption.isBefore(dateReference);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produit produit = (Produit) o;
        return Objects.equals(id, produit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", prixUnitaire=" + prixUnitaire +
                ", datePeremption=" + datePeremption +
                ", categorie=" + (categorie != null ? categorie.getLibelle() : "null") +
                '}';
    }
}