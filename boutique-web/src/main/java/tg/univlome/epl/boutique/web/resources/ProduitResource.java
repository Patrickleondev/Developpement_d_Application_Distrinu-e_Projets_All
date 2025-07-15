package tg.univlome.epl.boutique.web.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import tg.univlome.epl.boutique.api.entites.Produit;
import tg.univlome.epl.boutique.api.service.ProduitService;


/**
 *
 * @author 
 */



@Path("/produits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProduitResource {

    private final ProduitService service = ProduitService.getInstance();

    @POST
    public Response ajouter(Produit produit) {
        try {
            service.ajouter(produit);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response modifier(@PathParam("id") Long id, Produit produit) {
        try {
            if (!id.equals(produit.getId())) {
                throw new IllegalArgumentException("ID produit incohérent");
            }
            service.modifier(produit);
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
        Produit produit = service.trouver(id);
        if (produit != null) {
            return Response.ok(produit).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<Produit> lister() {
        return service.lister();
    }

    @GET
    @Path("/perimes")
    public List<Produit> listerPerimes() {
        return service.listeperime();
    }
}
