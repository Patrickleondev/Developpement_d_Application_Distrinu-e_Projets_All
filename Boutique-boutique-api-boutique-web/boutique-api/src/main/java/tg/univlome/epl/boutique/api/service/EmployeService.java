/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.api.service;
import java.util.Objects;
import tg.univlome.epl.boutique.api.entites.Employe;

/**
 *
 * @author techwizard
 */
public class EmployeService extends GenericService<Employe, Long> {
    private static EmployeService instance;
    
    private EmployeService() {}
    
    public static synchronized EmployeService getInstance() {
        if (instance == null) {
            instance = new EmployeService();
        }
        return instance;
    }
    
    @Override
    public void supprimer(Long id) {
        liste.removeIf(a-> Objects.equals(a.getId(), id));
    }
    
    @Override
    public Employe trouver(Long id) {
        return liste.stream()
                .filter(a -> Objects.equals(a.getId(), id))
                .findFirst()
                .orElse(null);
    }
}
