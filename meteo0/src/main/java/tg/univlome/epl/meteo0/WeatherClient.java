package tg.univlome.epl.meteo0;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.google.gson.Gson;

/**
 * Client météo respectant les conventions JavaBean
 * @author techwizard
 */
public class WeatherClient {
    private static final String API_KEY = "5195c8c6f9af520e15549dad53e4ecad";
    private static final String BASE_URL = "http://api.weatherstack.com/current";

    public static void main(String[] args) {
        String city = "lome";
        try {
            // Création du client Jersey
            Client client = ClientBuilder.newClient();
            
            // Configuration de la requête
            WebTarget target = client.target(BASE_URL)
                .queryParam("access_key", API_KEY)
                .queryParam("query", city);

            // Envoi de la requête
            Response response = target.request(MediaType.APPLICATION_JSON).get();

            // Traitement de la réponse
            if (response.getStatus() == 200) {
                String json = response.readEntity(String.class);
                Gson gson = new Gson();
                WeatherResponse weather = gson.fromJson(json, WeatherResponse.class);
                
                // Affichage des données en utilisant les getters JavaBean
                if (weather != null && weather.getLocation() != null && weather.getCurrent() != null) {
                    System.out.println("Ville: " + weather.getLocation().getName());
                    System.out.println("Température: " + weather.getCurrent().getTemperature() + "°C");
                    System.out.println("Humidité: " + weather.getCurrent().getHumidity() + "%");
                    System.out.println("Précipitation: " + weather.getCurrent().getPrecip() + "mm");
                    
                    if (weather.getCurrent().getAstro() != null) {
                        System.out.println("Lever du soleil: " + weather.getCurrent().getAstro().getSunrise());
                        System.out.println("Coucher du soleil: " + weather.getCurrent().getAstro().getSunset());
                    } else {
                        System.out.println("Lever/Coucher du soleil: Données non disponibles");
                    }
                } else {
                    System.out.println("Réponse inattendue ou incomplète de l'API.");
                }
            } else {
                System.out.println("Erreur HTTP : " + response.getStatus());
            }
            
            // Fermeture du client
            client.close();
            
        } catch (Exception e) {
            System.err.println("Erreur lors de la communication avec Weatherstack : " + e.getMessage());
            e.printStackTrace();
        }
    }
} 