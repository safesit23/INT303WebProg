/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.first.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import sit.int303.mockup.model.Product;

/**
 *
 * @author INT303
 */
public class ShoppingCart implements Serializable{
    private Map<String, LineItem> cart;

    public ShoppingCart() {
        cart = new HashMap();
        
    }
    public void add(Product p){
        LineItem line= cart.get(p.getProductCode());
        //ถ้าสินค้านั้นไม่เคยมีอยู่
        if(line==null){
            cart.put(p.getProductCode(), new LineItem(p));
        //ถ้าสินค้านั้นมีอยู่ในตระกร้าแต่ต้องการเพิ่มจำนวน
        }else{
            line.setQuantity(line.getQuantity()+1);
        }
    }
    
    public void remove(Product p){
        this.remove(p.getProductCode());
    }
    
    public void remove(String productCode){
        cart.remove(productCode);
    }
    
}
