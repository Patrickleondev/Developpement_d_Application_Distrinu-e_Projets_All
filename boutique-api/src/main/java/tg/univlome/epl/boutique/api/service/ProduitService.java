/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tg.univlome.epl.boutique.api.entites.Produit;

/**
 *
 * @author techwizard
 */
public class ProduitService extends GenericService<Produit, Long>{
    private static ProduitService instance;
    
    private ProduitService(){}
    
    public static synchronized ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }
        return instance;
    }
    
    @Override
    public void supprimer(Long id){
        liste.removeIf(a-> a.getId() == id);
    }
    
    @Override
    public Produit trouver(Long id) {
        return liste.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    @Override
    public List<Produit> listeperime(){
        return  liste.stream()
                     .filter((a)->a.estPerime())
                     .collect(Collectors.toCollection(ArrayList::new));
    }
    
}
