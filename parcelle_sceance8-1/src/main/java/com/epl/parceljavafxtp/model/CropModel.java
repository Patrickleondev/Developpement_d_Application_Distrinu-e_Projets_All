/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epl.parceljavafxtp.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author BELL
 */
public abstract class CropModel {
    private static int compteur = 0;
    
    private final int id;
    private final String cropName;

    protected CropModel(String cropName) {
        this.id = ++compteur;
        this.cropName = cropName;
    }

    public int getId() {
        return id;
    }

    public String getCropName() {
        return cropName;
    }

    public abstract double area();
    public abstract double perimeter();
    public abstract String getShapeName();

    @Override
    public String toString() {
        return cropName + " #" + id + " (Périmètre: " + perimeter() + ", Surface: " + area() + ")";
    }

    

    public static int getCompteur() {
        return compteur;
    }
    
    
}
