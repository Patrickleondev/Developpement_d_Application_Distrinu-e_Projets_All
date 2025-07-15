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

@Path("/auth")
public class AuthenticationResource {

    @Resource(lookup = "jms/cf1")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/authentificationTopic")
    private Destination authentificationTopic;

    private final AuthenticationService authService = AuthenticationService.getInstance();

    /**
     * Endpoint d'authentification
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response authenticate(
            @QueryParam("login") String login,
            @QueryParam("password") String password) throws JMSException {

        if (login == null || password == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Login et mot de passe requis")
                           .build();
        }

        String token = authService.authentifier(login, password);

        if (token != null) {
            sendJMSNotification(login); // peut lancer JMSException
            return Response.ok(token).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                           .entity("Credentials invalides")
                           .build();
        }
    }

    /**
     * Endpoint de vérification de jeton
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
