/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique;

/**
 *
 * @author techwizard
 */

import java.util.Objects;

public class ProduitAchete {
    private Produit produit;
    private double remise;

    public ProduitAchete() {}

    public ProduitAchete(Produit produit, double remise) {
        this.setProduit(produit);
        this.setRemise(remise);
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        if (produit == null) {
            throw new IllegalArgumentException("Le produit ne peut être null");
        }
        this.produit = produit;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        if (remise < 0) {
            throw new IllegalArgumentException("La remise ne peut être négative");
        }
        if (produit != null && remise >= produit.getPrixUnitaire()) {
            throw new IllegalArgumentException(
                    "La remise (" + remise + 
                    ") ne peut excéder le prix du produit (" + produit.getPrixUnitaire() + ")");
        }
        this.remise = remise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitAchete that = (ProduitAchete) o;
        return Objects.equals(produit, that.produit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produit);
    }

    @Override
    public String toString() {
        return "ProduitAchete{" +
                "produit=" + produit.getLibelle() +
                ", prixUnitaire=" + produit.getPrixUnitaire() +
                ", remise=" + remise +
                ", prixFinal=" + (produit.getPrixUnitaire() - remise) +
                '}';
    }
}