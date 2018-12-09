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
public class Student {
    private int id;
    private String name;
    private int score;
    
    public Student(){
        
    }
    
    public Student(int id, String name, int score){
        this.id = id;
        this.name = name;
        this.score = score;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setScore(int score){
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", score=" + score + '}';
    }
    
    
    
}
