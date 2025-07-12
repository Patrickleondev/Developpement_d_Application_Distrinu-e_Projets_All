/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.api.service;

import java.util.Objects;
import tg.univlome.epl.boutique.api.entites.Categorie;

/**
 *
 * @author techwizard
 */
public class CategorieService extends GenericService<Categorie, Long> {
    private static CategorieService instance;
    
    private CategorieService(){}
    
    public static synchronized CategorieService getInstance() {
        if (instance == null) {
            instance = new CategorieService();
        }
        return instance;
    }
    
    @Override
    public void supprimer(Long id){
        liste.removeIf(a-> Objects.equals(a.getId(), id));
    }
    
    @Override
    public Categorie trouver(Long id) {
        return liste.stream()
                .filter(a -> Objects.equals(a.getId(), id))
                .findFirst()
                .orElse(null);
    }
}
