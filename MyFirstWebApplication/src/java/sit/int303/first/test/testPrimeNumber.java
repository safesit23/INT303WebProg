/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.first.test;

import sit.int303.first.model.PrimeNumber;

public class testPrimeNumber {
    public static void main(String[] args) {
        PrimeNumber pn = new PrimeNumber();
        pn.setNumber(2);
        System.out.printf("Is %d is Prime Number ?  %b\n",pn.getNumber(),pn.isPrimeNumber());
        pn.setNumber(3);
        System.out.printf("Is %d is Prime Number ?  %b\n",pn.getNumber(),pn.isPrimeNumber());
        pn.setNumber(4);
        System.out.printf("Is %d is Prime Number ?  %b\n",pn.getNumber(),pn.isPrimeNumber());
    }
}