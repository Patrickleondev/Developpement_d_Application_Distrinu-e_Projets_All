/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epl.parceljavafxtp.model;

/**
 *
 * @author BELL
 */
public class CropFactory {
    
    private CropFactory(){}
    
    public static CropModel createCercle(double rayon, String cropName) {
        return new Cercle(rayon,cropName);
    }
    
    public static CropModel createTriangle(double dimension, String cropName) {
        return new Triangle(dimension,cropName);
    }

    public static CropModel createRectangle(double longueur, double largeur, String cropName) {
        return new RectangleCrop(longueur, largeur, cropName);
    }
}
