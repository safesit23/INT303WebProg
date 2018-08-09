/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.first.test;

import java.util.List;
import sit.int303.first.model.LineItem;
import sit.int303.first.model.ShoppingCart;
import sit.int303.mockup.model.Product;

/**
 *
 * @author INT303
 */
public class TestShoppingCart {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Product p = new Product();
        p.setProductCode("S10_001");
        p.setProductName("Harley");
        p.setMsrp(100.0);
        cart.add(p);
        cart.add(p);
        
        System.out.println("----ADD Product 1-----");
        System.out.println("Total price: "+cart.getTotalPrice());
        System.out.println("Total quantity:"+cart.getTotalQuantity());
        System.out.println("\n");
        
        p = new Product();
        p.setProductCode("S10_002");
        p.setProductName("Honda");
        p.setMsrp(15.0);
        cart.add(p);
        cart.add(p);
        cart.add(p);
        
        System.out.println("----ADD Product 2-----");
        System.out.println("Total price: "+cart.getTotalPrice());
        System.out.println("Total quantity:"+cart.getTotalQuantity());
        System.out.println("\n");
        
        System.out.println("<<<<<<<< SHOW LINEITEMS >>>>>>>>>>");
        List<LineItem> lines = cart.getLineItems();
        for (LineItem line : lines) {
            System.out.printf("%-8s %-15s %8.2f %2d %10.2f\n",
                    line.getProduct().getProductCode(),
                    line.getProduct().getProductName(),
                    line.getSalePrice(),
                    line.getQuantity(),
                    line.getTotalPrice());
        }
        System.out.println("<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>\n");
        
        cart.remove(p);
        System.out.println("----remove product 2-----");
        System.out.println("Total price: "+cart.getTotalPrice());
        System.out.println("Total quantity:"+cart.getTotalQuantity());
        System.out.println("\n");
        
        p = new Product();
        p.setProductCode("S10_003");
        p.setProductName("Toyota");
        p.setMsrp(40.0);
        cart.add(p);
        System.out.println("----ADD Product 3-----");
        System.out.println("Total price: "+cart.getTotalPrice());
        System.out.println("Total quantity:"+cart.getTotalQuantity());
        System.out.println("\n");
        
        cart.remove("S10_003");
        System.out.println("----remove product 3-----");
        System.out.println("Total price: "+cart.getTotalPrice());
        System.out.println("Total quantity:"+cart.getTotalQuantity());
        System.out.println("\n");
    }
}
