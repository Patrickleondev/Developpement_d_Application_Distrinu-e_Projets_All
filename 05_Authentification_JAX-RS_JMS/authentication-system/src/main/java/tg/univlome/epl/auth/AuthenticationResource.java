package tg.univlome.epl.auth;

import jakarta.jms.*;
import jakarta.naming.InitialContext;
import jakarta.naming.NamingException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

@Path("/auth")
public class AuthenticationResource {

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
            sendJMSNotification(login);  // Si une JMSException se produit, elle sera capturée par le mapper
            return Response.ok(token).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Identifiants invalides")
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
    private void sendJMSNotification(String login) throws NamingException, JMSException {
        InitialContext context = new InitialContext();

        ConnectionFactory cf = (ConnectionFactory) context.lookup("jms/cf1");
        Destination topic = (Destination) context.lookup("jms/authentificationTopic");

        try (JMSContext jmsContext = cf.createContext("admin", "admin")) {
            JMSProducer producer = jmsContext.createProducer();
            String message = String.format("Utilisateur %s authentifié avec succès à %s",
                                           login, LocalDateTime.now());
            producer.send(topic, message);
        }
    }
}
