/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.api.entites;

/**
 *
 * @author techwizard
 */
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@XmlRootElement

public final class Achat implements Serializable{
    private Long id;
    private LocalDate dateAchat;
    private double remise;
    private Client client;
    private final List<ProduitAchete> produits = new ArrayList<>();

    public Achat() {}

    public Achat(Long id, LocalDate dateAchat, Client client) {
        this.setId(id);
        this.setDateAchat(dateAchat);
        this.setClient(client);
    }

    // Getters et setters avec validation
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("L'id de l'achat doit être un long positif");
        }
        this.id = id;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        if (dateAchat == null) {
            throw new IllegalArgumentException("La date d'achat ne peut être nulle");
        }
        if (dateAchat.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La date d'achat ne peut être dans le futur");
        }
        this.dateAchat = dateAchat;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        if (remise < 0) {
            throw new IllegalArgumentException("La remise ne peut être négative");
        }
        this.remise = remise;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Un achat doit être associé à un client");
        }
        this.client = client;
    }

    public List<ProduitAchete> getProduits() {
        return Collections.unmodifiableList(produits);
    }

    public void ajouterProduit(ProduitAchete produitAchete) {
        if (produitAchete == null) {
            throw new IllegalArgumentException("Le produit acheté ne peut être null");
        }
        if (produits.contains(produitAchete)) {
            throw new IllegalStateException("Ce produit est déjà dans l'achat");
        }
        produits.add(produitAchete);
    }

    public void retirerProduit(ProduitAchete produitAchete) {
        if (!produits.remove(produitAchete)) {
            throw new IllegalStateException("Produit non présent dans l'achat");
        }
    }

    // Méthodes métier avec validation
    public double getRemiseTotale() {
        if (produits.isEmpty()) {
            throw new IllegalStateException("L'achat ne contient aucun produit");
        }
        return produits.stream()
                .mapToDouble(ProduitAchete::getRemise)
                .sum() + remise;
    }

    public double getTotalAPayer() {
        if (produits.isEmpty()) {
            throw new IllegalStateException("L'achat ne contient aucun produit");
        }
        double totalProduits = produits.stream()
                .mapToDouble(p -> {
                    double prix = p.getProduit().getPrixUnitaire();
                    if (prix <= p.getRemise()) {
                        throw new IllegalStateException(
                                "La remise (" + p.getRemise() + 
                                ") ne peut excéder le prix du produit (" + prix + ")");
                    }
                    return prix - p.getRemise();
                })
                .sum();
        
        if (remise > totalProduits) {
            throw new IllegalStateException(
                    "La remise globale (" + remise + 
                    ") ne peut excéder le total des produits (" + totalProduits + ")");
        }
        
        return totalProduits - remise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Achat achat = (Achat) o;
        return Objects.equals(id, achat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Achat{" +
                "id=" + id +
                ", dateAchat=" + dateAchat +
                ", remise=" + remise +
                ", client=" + client +
                ", produits=" + produits +
                ", totalAPayer=" + getTotalAPayer() +
                '}';
    }
}