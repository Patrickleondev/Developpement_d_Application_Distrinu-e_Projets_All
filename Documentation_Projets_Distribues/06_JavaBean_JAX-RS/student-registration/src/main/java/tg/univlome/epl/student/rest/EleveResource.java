package tg.univlome.epl.student.rest;

import tg.univlome.epl.student.entity.Eleve;
import tg.univlome.epl.student.service.EleveService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * Service web JAX-RS permettant d'exposer les opérations CRUD sur le web
 * Endpoint principal : /api/eleves
 */
@Path("/eleves")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EleveResource {
    
    private final EleveService service = new EleveService();
    
    /**
     * CREATE - Créer un nouvel élève
     * POST /api/eleves
     * 
     * @param eleve l'élève à créer (dans le body de la requête)
     * @return Response avec l'élève créé et le code 201 (Created)
     */
    @POST
    public Response create(Eleve eleve) {
        try {
            if (eleve == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("L'élève ne peut pas être null")
                    .build();
            }
            
            // Validation des champs obligatoires
            if (eleve.getNom() == null || eleve.getNom().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le nom est obligatoire")
                    .build();
            }
            
            if (eleve.getPrenom() == null || eleve.getPrenom().trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le prénom est obligatoire")
                    .build();
            }
            
            Eleve created = service.create(eleve);
            return Response.status(Response.Status.CREATED)
                .entity(created)
                .build();
                
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erreur lors de la création : " + e.getMessage())
                .build();
        }
    }
    
    /**
     * READ - Récupérer tous les élèves
     * GET /api/eleves
     * 
     * @return Response avec la liste de tous les élèves
     */
    @GET
    public Response findAll() {
        try {
            List<Eleve> eleves = service.findAll();
            return Response.ok(eleves).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erreur lors de la récupération : " + e.getMessage())
                .build();
        }
    }
    
    /**
     * READ - Rechercher un élève par nom et prénom
     * GET /api/eleves/{nom}/{prenom}
     * 
     * @param nom le nom de l'élève
     * @param prenom le prénom de l'élève
     * @return Response avec l'élève s'il existe, 404 sinon
     */
    @GET
    @Path("/{nom}/{prenom}")
    public Response findOne(
            @PathParam("nom") String nom,
            @PathParam("prenom") String prenom) {
        try {
            if (nom == null || prenom == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le nom et le prénom sont obligatoires")
                    .build();
            }
            
            return service.findByNomAndPrenom(nom, prenom)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND)
                    .entity("Élève non trouvé : " + nom + " " + prenom))
                .build();
                
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erreur lors de la recherche : " + e.getMessage())
                .build();
        }
    }
    
    /**
     * UPDATE - Mettre à jour un élève existant
     * PUT /api/eleves
     * 
     * @param eleve l'élève à mettre à jour (dans le body de la requête)
     * @return Response avec l'élève mis à jour, 404 si l'élève n'existe pas
     */
    @PUT
    public Response update(Eleve eleve) {
        try {
            if (eleve == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("L'élève ne peut pas être null")
                    .build();
            }
            
            Eleve updated = service.update(eleve);
            if (updated != null) {
                return Response.ok(updated).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                    .entity("Élève non trouvé pour la mise à jour : " + eleve.getNom() + " " + eleve.getPrenom())
                    .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erreur lors de la mise à jour : " + e.getMessage())
                .build();
        }
    }
    
    /**
     * DELETE - Supprimer un élève par nom et prénom
     * DELETE /api/eleves/{nom}/{prenom}
     * 
     * @param nom le nom de l'élève à supprimer
     * @param prenom le prénom de l'élève à supprimer
     * @return Response 204 (No Content) si supprimé, 404 sinon
     */
    @DELETE
    @Path("/{nom}/{prenom}")
    public Response delete(
            @PathParam("nom") String nom,
            @PathParam("prenom") String prenom) {
        try {
            if (nom == null || prenom == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le nom et le prénom sont obligatoires")
                    .build();
            }
            
            boolean deleted = service.delete(nom, prenom);
            if (deleted) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                    .entity("Élève non trouvé pour la suppression : " + nom + " " + prenom)
                    .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erreur lors de la suppression : " + e.getMessage())
                .build();
        }
    }
    
    /**
     * Endpoint de test pour vérifier que l'API fonctionne
     * GET /api/eleves/test
     * 
     * @return Response avec un message de test
     */
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {
        return Response.ok("API EleveResource fonctionne correctement !").build();
    }
    
    /**
     * Obtenir le nombre total d'élèves
     * GET /api/eleves/count
     * 
     * @return Response avec le nombre d'élèves
     */
    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response count() {
        try {
            int count = service.count();
            return Response.ok(String.valueOf(count)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erreur lors du comptage : " + e.getMessage())
                .build();
        }
    }
} 