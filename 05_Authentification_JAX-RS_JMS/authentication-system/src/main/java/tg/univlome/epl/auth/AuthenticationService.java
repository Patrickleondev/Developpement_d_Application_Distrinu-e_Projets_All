package tg.univlome.epl.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Singleton qui gère l'authentification des utilisateurs
 * Implémente les spécifications JavaBeans
 */
public class AuthenticationService {
    
    // Singleton instance
    private static AuthenticationService instance;
    
    // Map pour stocker les couples login/password
    private static final Map<String, String> credentials = new HashMap<>();
    
    // Liste pour stocker les jetons générés
    private static final List<String> jetons = new ArrayList<>();
    
    // Constructeur privé pour le pattern Singleton
    private AuthenticationService() {
        // Initialisation avec quelques utilisateurs de test
        credentials.put("admin", "admin123");
        credentials.put("user", "user123");
    }
    
    // Méthode pour obtenir l'instance unique
    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }
    
    /**
     * Authentifie un utilisateur et génère un jeton si les credentials sont valides
     * @param login Le login de l'utilisateur
     * @param password Le mot de passe de l'utilisateur
     * @return Le jeton généré ou null si l'authentification échoue
     */
    public String authentifier(String login, String password) {
        // Vérifier si les credentials sont valides
        if (credentials.containsKey(login) && credentials.get(login).equals(password)) {
            // Générer un nouveau jeton
            String token = UUID.randomUUID().toString();
            jetons.add(token);
            return token;
        }
        return null;
    }
    
    /**
     * Vérifie si un jeton est valide
     * @param jeton Le jeton à vérifier
     * @return true si le jeton est valide, false sinon
     */
    public boolean isValid(String jeton) {
        return jetons.contains(jeton);
    }
    
    // Getters et setters pour JavaBeans
    public List<String> getJetons() {
        return new ArrayList<>(jetons);
    }
    
    public Map<String, String> getCredentials() {
        return new HashMap<>(credentials);
    }
} 