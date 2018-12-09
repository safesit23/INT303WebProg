/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Map;

/**
 *
 * @author jatawatsafe
 */
public class AllStudent {
    private Map<String, Student> allData;
    
    public boolean add(Student std){
        String keyId = ""+std.getId();
        Student stdInMap = allData.get(keyId);
        if(std!=null){
            return false;
        }else{
            allData.put(keyId, stdInMap);
            return true;
        }
    }
    
    public boolean remove(Student std){
        
    }
    
}
