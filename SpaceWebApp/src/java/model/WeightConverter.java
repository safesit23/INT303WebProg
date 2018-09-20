/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jatawatsafe
 */
public class WeightConverter {
    private double weight;
    private String planet;

    public WeightConverter() {
    }

    public WeightConverter(double weight, String planet) {
        this.weight = weight;
        this.planet = planet;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }
    
    public double converter(){
        double newWeight;
        switch(this.planet){
            case "1":   newWeight=this.weight*1;    break;
            case "2":   newWeight=this.weight*2;    break;
            case "3":   newWeight=this.weight*3;    break;
            case "4":   newWeight=this.weight*4;    break;
            default: newWeight=this.weight;
        }
        return newWeight;
    }
}
