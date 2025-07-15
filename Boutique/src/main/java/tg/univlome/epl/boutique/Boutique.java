/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tg.univlome.epl.boutique;

/**
 *
 * @author techwizard
 */

import java.time.LocalDate;
import java.time.Month;

public class Boutique {
    public static void main(String[] args) {
        System.out.println("Bienvenue à la Boutique !");
        System.out.println("=================================\n");

        try {
            // 1. Création des catégories typiques au Togo
            Categorie alimentaire = new Categorie(1, "Produits Alimentaires", "Nourriture locale et importée");
            Categorie vetement = new Categorie(2, "Vêtements", "Vêtements traditionnels et modernes");
            Categorie electronique = new Categorie(3, "Électronique", "Appareils électroniques et accessoires");

            // 2. Création de produits typiques
            Produit pateMais = new Produit(101L, "Pâte de Maïs", 500, LocalDate.of(2023, Month.DECEMBER, 31));
            pateMais.setCategorie(alimentaire);

            Produit pagne = new Produit(102L, "Pagne Wax", 3500, null);
            pagne.setCategorie(vetement);

            Produit telephone = new Produit(103L, "Téléphone portable", 45000, null);
            telephone.setCategorie(electronique);

            Produit sodabi = new Produit(104L, "Sodabi (1L)", 2500, LocalDate.of(2024, Month.JUNE, 30));
            sodabi.setCategorie(alimentaire);

            // 3. Création d'employés
            Employe employe1 = new Employe(1L, "ADJOBO", "Koffi", LocalDate.of(1990, Month.APRIL, 15));
            Employe employe2 = new Employe(2L, "AMEGAN", "Afi", LocalDate.of(1995, Month.AUGUST, 22));

            // 4. Création de clients
            Client client1 = new Client(1001L, "GBEDJE", "Yawo");
            Client client2 = new Client(1002L, "AGBODJINOU", "Mawulolo");

            // 5. Création d'un achat
            Achat achatClient1 = new Achat(5001L, LocalDate.now(), client1);
            achatClient1.setRemise(1000); // Remise globale de 1000 FCFA

            // Ajout des produits achetés avec remises spécifiques
            ProduitAchete pateAchetee = new ProduitAchete(pateMais, 50); // 50 FCFA de remise
            ProduitAchete pagneAchete = new ProduitAchete(pagne, 300); // 300 FCFA de remise
            ProduitAchete sodabiAchete = new ProduitAchete(sodabi, 0); // Pas de remise

            achatClient1.ajouterProduit(pateAchetee);
            achatClient1.ajouterProduit(pagneAchete);
            achatClient1.ajouterProduit(sodabiAchete);

            // 6. Affichage des informations
            System.out.println("=== CATÉGORIES ===");
            System.out.println(alimentaire);
            System.out.println(vetement);
            System.out.println(electronique);
            System.out.println();

            System.out.println("=== PRODUITS ===");
            System.out.println(pateMais);
            System.out.println("Le produit est périmé? " + pateMais.estPerime());
            System.out.println(pagne);
            System.out.println(telephone);
            System.out.println(sodabi);
            System.out.println();

            System.out.println("=== EMPLOYÉS ===");
            System.out.println(employe1);
            System.out.println(employe2);
            System.out.println();

            System.out.println("=== CLIENTS ===");
            System.out.println(client1);
            System.out.println(client2);
            System.out.println();
            
            System.out.println("=== ACHAT ===");
            System.out.println(achatClient1);
            System.out.println("Remise totale: " + achatClient1.getRemiseTotale() + " FCFA");
            System.out.println("Total à payer: " + achatClient1.getTotalAPayer() + " FCFA");
            System.out.println();

            // 7. Test des validations (décommenter pour tester)
            // testValidations();

        } catch (Exception e) {
            System.err.println("Erreur dans la boutique: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testValidations() {
        System.out.println("\n=== TESTS DE VALIDATION ===");
        
        // Test création catégorie invalide
        try {
            new Categorie(0, "", null);
        } catch (IllegalArgumentException e) {
            System.out.println("Test catégorie invalide: " + e.getMessage());
        }

        // Test produit périmé
        try {
            Produit produitPerime = new Produit(999L, "Test", 100, LocalDate.of(2020, 1, 1));
            System.out.println("Produit périmé? " + produitPerime.estPerime());
        } catch (Exception e) {
            System.out.println("Test produit périmé: " + e.getMessage());
        }

        // Test employé trop jeune
        try {
            new Employe(999L, "Test", "Jeune", LocalDate.now().minusYears(10));
        } catch (IllegalArgumentException e) {
            System.out.println("Test employé trop jeune: " + e.getMessage());
        }

        // Test remise invalide
        try {
            Achat achat = new Achat(999L, LocalDate.now(), new Client(999L, "Test", "Client"));
            Produit produit = new Produit(999L, "Test", 100, null);
            achat.ajouterProduit(new ProduitAchete(produit, 150)); // Remise > prix
        } catch (IllegalArgumentException e) {
            System.out.println("Test remise invalide: " + e.getMessage());
        }
    }
}