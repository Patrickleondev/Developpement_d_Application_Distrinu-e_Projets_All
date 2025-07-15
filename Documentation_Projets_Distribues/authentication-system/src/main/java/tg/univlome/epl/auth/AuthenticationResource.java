package tg.univlome.epl.auth;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.JMSProducer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/auth")
public class AuthenticationResource {
    
    @Resource(lookup = "jms/cf1")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/authentificationTopic")
    private Destination authentificationTopic;
    
    private final AuthenticationService authService = AuthenticationService.getInstance();
    
    /**
     * Endpoint d'authentification
     * @param login Login de l'utilisateur
     * @param password Mot de passe de l'utilisateur
     * @return Response HTTP avec le jeton ou une erreur
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response authenticate(
            @QueryParam("login") String login,
            @QueryParam("password") String password) {
        
        // Vérifier les paramètres
        if (login == null || password == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Login et mot de passe requis")
                    .build();
        }
        
        // Tenter l'authentification
        String token = authService.authentifier(login, password);
        
        if (token != null) {
            // Authentification réussie
            try {
                // Envoyer notification JMS
                sendJMSNotification(login);
                
                return Response.ok(token).build();
            } catch (JMSException ex) {
                Logger.getLogger(AuthenticationResource.class.getName())
                      .log(Level.SEVERE, "Erreur lors de l'envoi de la notification JMS", ex);
                // On renvoie quand même le token même si la notification échoue
                return Response.ok(token).build();
            }
        } else {
            // Authentification échouée
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Credentials invalides")
                    .build();
        }
    }
    
    /**
     * Endpoint de vérification de jeton
     * @return La date courante si le jeton est valide
     */
    @GET
    @Path("/verify")
    @Produces(MediaType.TEXT_PLAIN)
    public Response verifyToken(@HeaderParam("Authorization") String token) {
        if (token == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Token requis")
                    .build();
        }
        
        if (authService.isValid(token)) {
            return Response.ok(LocalDateTime.now().toString()).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Token invalide")
                    .build();
        }
    }
    
    /**
     * Envoie une notification JMS lors d'une authentification réussie
     */
    private void sendJMSNotification(String login) throws JMSException {
        try (JMSContext context = connectionFactory.createContext()) {
            JMSProducer producer = context.createProducer();
            String message = String.format("Utilisateur %s authentifié avec succès à %s",
                    login, LocalDateTime.now());
            producer.send(authentificationTopic, message);
        }
    }
} 