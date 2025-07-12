package tg.univlome.epl.pd1.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

/**
 *
 * @author 
 */
@Path("/Hello")
public class HelloRessource {
    
    @GET
    public String saluer(@QueryParam("n") String nom, @QueryParam("p") String prenom){
        
        return "Bonjour" + nom + " " + prenom + " " + ", je suis le Boss";
    }
}


