package tg.univlome.epl.student.service;

import tg.univlome.epl.student.entity.Eleve;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service implémentant les opérations CRUD classiques sur la classe Eleve
 * CREATE - READ - UPDATE - DELETE
 */
public class EleveService {
    
    // Stockage en mémoire des élèves
    private final List<Eleve> eleves = new ArrayList<>();
    
    /**
     * CREATE - Créer un nouvel élève
     * @param eleve l'élève à créer
     * @return l'élève créé
     */
    public Eleve create(Eleve eleve) {
        if (eleve == null) {
            throw new IllegalArgumentException("L'élève ne peut pas être null");
        }
        eleves.add(eleve);
        return eleve;
    }
    
    /**
     * READ - Récupérer tous les élèves
     * @return la liste de tous les élèves
     */
    public List<Eleve> findAll() {
        return new ArrayList<>(eleves);
    }
    
    /**
     * READ - Rechercher un élève par nom et prénom
     * @param nom le nom de l'élève
     * @param prenom le prénom de l'élève
     * @return Optional contenant l'élève s'il existe
     */
    public Optional<Eleve> findByNomAndPrenom(String nom, String prenom) {
        if (nom == null || prenom == null) {
            return Optional.empty();
        }
        
        return eleves.stream()
            .filter(e -> nom.equals(e.getNom()) && prenom.equals(e.getPrenom()))
            .findFirst();
    }
    
    /**
     * READ - Rechercher un élève par nom et prénom (version simple)
     * @param nom le nom de l'élève
     * @param prenom le prénom de l'élève
     * @return l'élève s'il existe, null sinon
     */
    public Eleve findByNomAndPrenomSimple(String nom, String prenom) {
        return findByNomAndPrenom(nom, prenom).orElse(null);
    }
    
    /**
     * UPDATE - Mettre à jour un élève existant
     * @param eleve l'élève à mettre à jour
     * @return l'élève mis à jour, null si l'élève n'existe pas
     */
    public Eleve update(Eleve eleve) {
        if (eleve == null) {
            return null;
        }
        
        Optional<Eleve> existing = findByNomAndPrenom(eleve.getNom(), eleve.getPrenom());
        if (existing.isPresent()) {
            eleves.remove(existing.get());
            eleves.add(eleve);
            return eleve;
        }
        return null;
    }
    
    /**
     * DELETE - Supprimer un élève par nom et prénom
     * @param nom le nom de l'élève à supprimer
     * @param prenom le prénom de l'élève à supprimer
     * @return true si l'élève a été supprimé, false sinon
     */
    public boolean delete(String nom, String prenom) {
        if (nom == null || prenom == null) {
            return false;
        }
        
        return findByNomAndPrenom(nom, prenom)
            .map(eleves::remove)
            .orElse(false);
    }
    
    /**
     * DELETE - Supprimer un élève
     * @param eleve l'élève à supprimer
     * @return true si l'élève a été supprimé, false sinon
     */
    public boolean delete(Eleve eleve) {
        if (eleve == null) {
            return false;
        }
        return delete(eleve.getNom(), eleve.getPrenom());
    }
    
    /**
     * Obtenir le nombre total d'élèves
     * @return le nombre d'élèves
     */
    public int count() {
        return eleves.size();
    }
    
    /**
     * Vérifier si un élève existe
     * @param nom le nom de l'élève
     * @param prenom le prénom de l'élève
     * @return true si l'élève existe, false sinon
     */
    public boolean exists(String nom, String prenom) {
        return findByNomAndPrenom(nom, prenom).isPresent();
    }
} 