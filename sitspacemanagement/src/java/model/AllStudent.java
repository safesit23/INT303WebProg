/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jatawatsafe
 */
public class AllStudent {
    private Map<String, Student> allData;
    
    public AllStudent(){
        allData = new HashMap();
    }
    
    public boolean add(Student std){
        Student stdInMap = allData.get(std.getId());
        if(stdInMap == null){
            allData.put(std.getId(), std);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean remove(Student std){
        Student stdInMap = allData.get(std.getId());
        if(stdInMap!=null){
            allData.remove(std.getId());
            return true;
        }else{
            return false;
        }
    }
    
    public List<Student> getAllData(){
        return new ArrayList(allData.values());
    }
    
}
