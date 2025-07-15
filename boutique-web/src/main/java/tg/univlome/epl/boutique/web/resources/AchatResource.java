package tg.univlome.epl.boutique.web.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import tg.univlome.epl.boutique.api.entites.Achat;
import tg.univlome.epl.boutique.api.service.AchatService;

/**
 *
 * @author 
 */

@Path("/achats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AchatResource {

    private final AchatService service = AchatService.getInstance();

    @POST
    public Response ajouter(Achat achat) {
        try {
            service.ajouter(achat);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response modifier(@PathParam("id") Long id, Achat achat) {
        try {
            if (!id.equals(achat.getId())) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("L'ID fourni ne correspond pas à celui de l'achat.").build();
            }
            service.modifier(achat);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response supprimer(@PathParam("id") Long id) {
        try {
            service.supprimer(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response trouver(@PathParam("id") Long id) {
        Achat achat = service.trouver(id);
        if (achat != null) {
            return Response.ok(achat).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<Achat> lister() {
        return service.lister();
    }

    @GET
    @Path("/obsoletes")
    public List<Achat> listerObsoletes() {
        return service.listeperime();
    }
}
