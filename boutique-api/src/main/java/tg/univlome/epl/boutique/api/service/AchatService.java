/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.api.service;

import java.util.Objects;
import tg.univlome.epl.boutique.api.entites.Achat;

/**
 *
 * @author techwizard
 */

public class AchatService extends GenericService<Achat, Long> {
    private static AchatService instance;
    
    private AchatService() {}
    
    public static synchronized AchatService getInstance() {
        if (instance == null) {
            instance = new AchatService();
        }
        return instance;
    }
    
    @Override
    public void supprimer(Long id) {
        liste.removeIf(a-> Objects.equals(a.getId(), id));
    }
    
    @Override
    public Achat trouver(Long id) {
        return liste.stream()
                .filter(a -> Objects.equals(a.getId(), id))
                .findFirst()
                .orElse(null);
    }
    
}
