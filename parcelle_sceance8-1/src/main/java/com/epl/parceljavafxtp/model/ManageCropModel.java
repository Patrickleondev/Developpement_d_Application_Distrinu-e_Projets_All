/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.epl.parceljavafxtp.model;

import com.epl.parceljavafxtp.utils.AppUtils;
import java.util.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author BELL
 */
public class ManageCropModel {
    
    private static ManageCropModel instance;
    private final ObservableList<CropModel> crops = FXCollections.observableArrayList();
    private SimpleStringProperty globalArea;
    private SimpleStringProperty resultmsg;
    private SimpleDoubleProperty areaGap;
    private SimpleIntegerProperty cropsLength;
    
    private ManageCropModel(){
       
        cropsLength = new SimpleIntegerProperty(0);
        globalArea = new SimpleStringProperty("0.0");
        resultmsg = new SimpleStringProperty("Terminer la configuration pour voir le résultat");
        areaGap = new SimpleDoubleProperty(0.0);
    }

    public void setAreaGap() {
        Double totalArea = crops.stream()
        .mapToDouble(CropModel::area)
        .sum();
        Double result;
        result = Double.valueOf(globalArea.getValue()) - totalArea;
        this.areaGap.setValue(result);
    }

    public SimpleDoubleProperty getAreaGap() {
        setAreaGap();
        return areaGap;
    }
    
    public void setCropsLength(){
        this.cropsLength.setValue(crops.size());
        AppUtils.printToLog("CALLED LLT");
    }
    public SimpleIntegerProperty getCropsLength() {
        setCropsLength();
        return cropsLength;
    }
    
    public  void setGlobalArea(String newGlobalArea) {
        globalArea.setValue(newGlobalArea);
        setResultmsg();
    }

    public  SimpleStringProperty getGlobalArea() {
        return this.globalArea;
    }
    
   
    public SimpleStringProperty getResultmsg() {
        setResultmsg();
        return resultmsg;
    }
    
    public void setResultmsg() {
        if ("0.0".equals(globalArea.getValue())) {
            this.resultmsg.setValue("Mettez à jour la superficie de votre parcelle");
            return;
        }
        
        if (crops.isEmpty()) {
            this.resultmsg.setValue("Vous n'avez pas renseigner les cultures");
            AppUtils.printToLog("CALLED EMPTY");
            return;
        }
        if (getAreaGap().greaterThan(0).getValue()) {
            this.resultmsg.setValue("Il vous restera : " + getAreaGap().getValue().toString() + " m²");
        } else if(getAreaGap().isEqualTo(0).getValue()) {
            this.resultmsg.setValue("Il ne vous reste plus d'espace");
        }else {
            this.resultmsg.setValue("Il vous faudra encore : " + getAreaGap().getValue().toString() + " m² pour ");
        }
        AppUtils.printToLog("CALLED");
    }
    
     
    
    public static ManageCropModel getInstance(){
        if (instance == null) {
            instance = new ManageCropModel();
        }
        
        return instance;
    }
    
    public void addCrop(CropModel f) {
        
        crops.add(f);
    }
    
    public void addCrop(String cropName, String shapeType, double dimension1, double dimension2){
    
           switch (shapeType) {
                case "TRIANGLE":
                    addCrop(CropFactory.createTriangle(dimension1, cropName));
                    break;
                case "CERCLE":
                    addCrop(CropFactory.createCercle(dimension1, cropName));
                    break;
                case "RECTANGLE":
                    addCrop(CropFactory.createRectangle(dimension1,dimension2, cropName));
                    break;
                default:
                    
            }
    }

    public CropModel getCropById(int id) {
        return crops.stream()
                      .filter(f -> f.getId() == id)
                      .findFirst()
                      .orElse(null);
    }

    public ObservableList<CropModel> getCrops() {
        crops.forEach(text -> AppUtils.printToLog(text.toString()));
        return crops;
    }

    public void sortByArea() {
        crops.sort(Comparator.comparingDouble(CropModel::area));
    }

    public void sortByPerimeter() {
        crops.sort(Comparator.comparingDouble(CropModel::perimeter));
    }
    
    public void remove(CropModel c) {
        crops.remove(c);
    }
    
    // Supprimer toute la liste de culture
    public void clearCrops() {
        crops.clear();
    }
}
