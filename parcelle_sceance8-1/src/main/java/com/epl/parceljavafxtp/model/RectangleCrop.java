/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epl.parceljavafxtp.model;

/**
 *
 * @author BELL
 */
public class RectangleCrop extends CropModel {
    private double longueur, largeur;
    private static final String SHAPE_TYPE = "RECTANGLE";
    @Override
    public String getShapeName() {
        return SHAPE_TYPE;
    }
    public RectangleCrop(double longueur, double largeur, String cropName) {
        super(cropName);
        this.largeur = largeur;
        this.longueur = longueur;
    }

    @Override
    public double area() {
        double area = largeur * longueur;
        return Math.round(area*100.0)/100.0;
    }

    @Override
    public double perimeter() {
        return 2 * (largeur + longueur);
    }

    
}
