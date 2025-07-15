/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tg.univlome.epl.boutique.api.entites;

import java.io.Serializable;


/**
 *
 * @author techwizard
 */
public class Client extends Personne  implements Serializable{
    public Client() {}

    public Client(Long id, String nom, String prenom) {
        super(id, nom, prenom);
    }

   
}