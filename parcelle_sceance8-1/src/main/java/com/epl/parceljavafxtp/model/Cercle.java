/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epl.parceljavafxtp.model;

/**
 *
 * @author BELL
 */
public class Cercle extends CropModel {

    private double rayon;
    private static final String SHAPE_TYPE = "CERCLE";

    public Cercle(double rayon, String cropName) {
        super(cropName);
        this.rayon = rayon;
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    @Override
    public double area() {
        double area = Math.PI * rayon * rayon;
        return Math.round(area * 100.0) / 100.0;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * rayon;
    }

    @Override
    public String getShapeName() {
        return SHAPE_TYPE;
    }

   
    
}
