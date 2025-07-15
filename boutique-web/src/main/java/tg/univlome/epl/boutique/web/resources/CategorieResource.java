package tg.univ.epl.boutique.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import tg.univlome.epl.boutique.api.entites.Categorie;
import tg.univlome.epl.boutique.api.service.CategorieService;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategorieResource {

    private final CategorieService service = CategorieService.getInstance();

    @POST
    public Response ajouter(Categorie categorie) {
        try {
            service.ajouter(categorie);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response modifier(@PathParam("id") Long id, Categorie categorie) {
        try {
            if (!id.equals(categorie.getId())) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("L'ID fourni ne correspond pas à celui de la catégorie.").build();
            }
            service.modifier(categorie);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage()).build();
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
                           .entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response trouver(@PathParam("id") Long id) {
        Categorie categorie = service.trouver(id);
        if (categorie != null) {
            return Response.ok(categorie).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<Categorie> lister() {
        return service.lister();
    }

    @GET
    @Path("/obsoletes")
    public List<Categorie> listerObsoletes() {
        return service.listeperime();
    }
}
