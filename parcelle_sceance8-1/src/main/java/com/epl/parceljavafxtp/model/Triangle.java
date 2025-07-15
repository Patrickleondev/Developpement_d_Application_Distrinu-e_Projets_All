/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epl.parceljavafxtp.model;

/**
 *
 * @author BELL
 */
public class Triangle extends CropModel {
    
    private double dimension;
    private static final String SHAPE_TYPE = "RECTANGLE";
    @Override
    public String getShapeName() {
        return SHAPE_TYPE;
    }
    
    public Triangle(double dimension, String cropName) {
        super(cropName);
        this.dimension = dimension;
    }

    @Override
    public double area() {
        double area = (Math.sqrt(3) / 4) * Math.pow(dimension, 2);
        return Math.round(area*100.0)/100.0;
    }

    @Override
    public double perimeter() {
        return dimension*2;
    }
    
  
    
}
