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
    private String id;
    private String name;
    private int score;
    
    public Student(){
        
    }
    
    public Student(String id, String name, int score){
        this.id = id;
        this.name = name;
        this.score = score;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setScore(int score){
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
    public String getCalculateGrade(){
        if(score>=80){
            return "A";
        }else if(score>=70){
            return "B";
        }else if(score>=60){
            return "C";
        }else if(score>=50){
            return "D";
        }else{
            return "F";
        }
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", score=" + score + '}';
    }
    
    
    
}
