
package model;

public class Astronomer {
    private String name;
    private double weight;

    public Astronomer(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public double getResult(){
        return weight*3;
    }
}
