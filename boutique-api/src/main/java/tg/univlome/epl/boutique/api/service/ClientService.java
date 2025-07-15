/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.api.service;

import tg.univlome.epl.boutique.api.entites.Client;

/**
 *
 * @author techwizard
 */
public class ClientService extends GenericService<Client, Long> {
    private static ClientService instance;
    
    private ClientService() {}
    
    public static synchronized ClientService getInstance() {
        if (instance == null) {
            instance = new ClientService();
        }
        return instance;
    }
    
    @Override
    public void supprimer(Long id) {
        liste.removeIf(a-> a.getId() == id);
    }
    
    @Override
    public Client trouver(Long id) {
        return liste.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
