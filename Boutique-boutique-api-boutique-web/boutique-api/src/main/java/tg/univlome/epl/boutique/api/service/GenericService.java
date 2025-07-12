/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.api.service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author techwizard
 * @param <E>
 * @param <ID>
 */
public class GenericService<E, ID> {

    protected List<E> liste = new ArrayList<>();

    public void ajouter(E p) {
        liste.add(p);
    }

    public void modifier(E p) {
        liste.replaceAll(a -> a.equals(p) ? p : a);
    }

    public void supprimer(ID id) {
        //liste.removeIf(a -> a.getId() == id);
        throw new UnsupportedOperationException("Cette méthode doit être redéfinie dans la sous-classe.");

    }

    public E trouver(ID id) {
        /*return liste.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null*/
        throw new UnsupportedOperationException("Cette méthode doit être redéfinie dans la sous-classe.");

    }

    public List<E> lister() {
        //liste.forEach(p->System.out.println(p));

        return liste;
    }

    public List<E> listeperime() {
        /*return  liste.stream()
                     .filter((a)->a.estPerime())
                     .collect(Collectors.toCollection(ArrayList::new));*/
        throw new UnsupportedOperationException("Cette méthode doit être redéfinie dans la sous-classe.");
    }

}
