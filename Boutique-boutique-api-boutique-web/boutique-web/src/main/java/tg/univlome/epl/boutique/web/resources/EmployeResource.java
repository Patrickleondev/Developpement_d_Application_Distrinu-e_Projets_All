package tg.univlome.epl.boutique.web.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import tg.univlome.epl.boutique.api.entites.Employe;
import tg.univlome.epl.boutique.api.service.EmployeService;

@Path("/employes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeResource {

    private final EmployeService service = EmployeService.getInstance();

    @POST
    public Response ajouter(Employe employe) {
        try {
            service.ajouter(employe);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(e.getMessage())
                           .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response modifier(@PathParam("id") Long id, Employe employe) {
        try {
            if (!id.equals(employe.getId())) {
                throw new IllegalArgumentException("ID employé incohérent");
            }
            service.modifier(employe);
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
        Employe employe = service.trouver(id);
        if (employe != null) {
            return Response.ok(employe).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public List<Employe> lister() {
        return service.lister();
    }

    @GET
    @Path("/perimes")
    public List<Employe> listerPerimes() {
        return service.listeperime();
    }
}
