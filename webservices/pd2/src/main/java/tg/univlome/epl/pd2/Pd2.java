/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tg.univlome.epl.pd2;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author dzobest
 */
public class Pd2 {
    
    private static final String URL = "http://localhost:8080/pd1/rs";
    
    public static void main(String[] args) {
        try {
            // Création du client Jersey
            Client client = ClientBuilder.newClient();
            
            // Création de la cible (target) pour l'URL
            WebTarget target = client.target(URL).path("Hello");
            
            // Envoi de la requête GET et récupération de la réponse
            Response response = target.request(MediaType.TEXT_PLAIN).get();
            
            // Vérification du statut de la réponse
            if (response.getStatus() == 200) {
                // Récupération et affichage du contenu de la réponse
                String result = response.readEntity(String.class);
                System.out.println("Réponse du serveur : " + result);
            } else {
                System.out.println("Erreur : " + response.getStatus());
            }
            
            // Fermeture du client
            client.close();
            
        } catch (Exception e) {
            System.err.println("Erreur lors de la communication avec le serveur : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
