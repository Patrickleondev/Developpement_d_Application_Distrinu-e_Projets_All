package tg.univlome.epl.student.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe JavaBean Eleve pour recueillir les données du formulaire d'inscription
 * Implémente Serializable et suit les conventions JavaBean
 */
public class Eleve implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // Propriétés privées selon les conventions JavaBean
    private String nom;
    private String prenom;
    private String classe;
    private String sexe;
    private String option;
    private String journee;
    
    /**
     * Constructeur par défaut requis pour JavaBean
     */
    public Eleve() {
    }
    
    /**
     * Constructeur avec paramètres
     */
    public Eleve(String nom, String prenom, String classe, String sexe, String option, String journee) {
        this.nom = nom;
        this.prenom = prenom;
        this.classe = classe;
        this.sexe = sexe;
        this.option = option;
        this.journee = journee;
    }
    
    // Getters et Setters selon les conventions JavaBean
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getClasse() {
        return classe;
    }
    
    public void setClasse(String classe) {
        this.classe = classe;
    }
    
    public String getSexe() {
        return sexe;
    }
    
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    
    public String getOption() {
        return option;
    }
    
    public void setOption(String option) {
        this.option = option;
    }
    
    public String getJournee() {
        return journee;
    }
    
    public void setJournee(String journee) {
        this.journee = journee;
    }
    
    /**
     * Méthode equals() pour comparer les objets Eleve
     * Deux élèves sont égaux s'ils ont le même nom, prénom et classe
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Eleve other = (Eleve) obj;
        return Objects.equals(nom, other.nom) 
            && Objects.equals(prenom, other.prenom)
            && Objects.equals(classe, other.classe);
    }
    
    /**
     * Méthode hashCode() cohérente avec equals()
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, classe);
    }
    
    /**
     * Méthode toString() pour l'affichage
     */
    @Override
    public String toString() {
        return "Eleve{" 
            + "nom='" + nom + '\''
            + ", prenom='" + prenom + '\''
            + ", classe='" + classe + '\''
            + ", sexe='" + sexe + '\''
            + ", option='" + option + '\''
            + ", journee='" + journee + '\''
            + '}';
    }
} 