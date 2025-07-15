package tg.univlome.epl.auth.client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class AuthenticationClient {
    
    private static final String BASE_URL = "http://localhost:8080/authentication-system/api";
    
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(BASE_URL);
        
        // Test d'authentification
        Response authResponse = target.path("/auth")
                .queryParam("login", "admin")
                .queryParam("password", "admin123")
                .request(MediaType.TEXT_PLAIN)
                .post(null);
        
        if (authResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            String token = authResponse.readEntity(String.class);
            System.out.println("Authentification réussie. Token : " + token);
            
            // Test de vérification du token
            Response verifyResponse = target.path("/auth/verify")
                    .request(MediaType.TEXT_PLAIN)
                    .header("Authorization", token)
                    .get();
            
            if (verifyResponse.getStatus() == Response.Status.OK.getStatusCode()) {
                String currentDate = verifyResponse.readEntity(String.class);
                System.out.println("Token valide. Date courante : " + currentDate);
            } else {
                System.out.println("Erreur de vérification : " + 
                        verifyResponse.readEntity(String.class));
            }
        } else {
            System.out.println("Erreur d'authentification : " + 
                    authResponse.readEntity(String.class));
        }
        
        client.close();
    }
} 