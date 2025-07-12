package tg.univlome.epl.boutique.web.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import tg.univlome.epl.boutique.api.entites.Client;
import tg.univlome.epl.boutique.api.service.ClientService;

/**
 *
 * @author 
 */

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    private final ClientService service = ClientService.getInstance();

    @POST
    public Response ajouter(Client client) {
        try {
            service.ajouter(client);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response modifier(@PathParam("id") Long id, Client client) {
        try {
            if (!id.equals(client.getId())) {
                throw new IllegalArgumentException("ID client incohérent");
            }
            service.modifier(client);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage())
                           .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response supprimer(@PathParam("id") Long id) {
        try {
            service.supprimer(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage())
                           .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response trouver(@PathParam("id") Long id) {
        Client client = service.trouver(id);
        if (client != null) {
            return Response.ok(client).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<Client> lister() {
        return service.lister();
    }

    @GET
    @Path("/inactifs")
    public List<Client> listerInactifs() {
        return service.listeperime();  // Renomme aussi dans le service si nécessaire
    }
}
