/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique;

/**
 *
 * @author techwizard
 */
import java.time.LocalDate;
import java.time.Period;

public class Employe extends Personne {
    private LocalDate dateNaissance;

    public Employe() {}

    public Employe(Long id, String nom, String prenom, LocalDate dateNaissance) {
        super(id, nom, prenom);
        this.setDateNaissance(dateNaissance);
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        if (dateNaissance == null) {
            throw new IllegalArgumentException("La date de naissance ne peut être nulle");
        }
        if (dateNaissance.isAfter(LocalDate.now().minusYears(14))) {
            throw new IllegalArgumentException("L'employé doit avoir au moins 14 ans");
        }
        this.dateNaissance = dateNaissance;
    }

    public int getAge() {
        return Period.between(dateNaissance, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "") +
                ", dateNaissance=" + dateNaissance +
                ", age=" + getAge() +
                '}';
    }
}