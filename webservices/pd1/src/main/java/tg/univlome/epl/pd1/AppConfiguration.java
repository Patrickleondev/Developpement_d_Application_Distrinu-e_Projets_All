package tg.univlome.epl.pd1;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */

// Tous chemin précéder par le chemin de contexte ( Chemin de base )
/*/localhost:8080/pd1/rs/hello*/
@ApplicationPath("/rs")
public class AppConfiguration extends Application {
    
}
