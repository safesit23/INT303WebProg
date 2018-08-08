package test;

import model.PrimeNumber;

public class testPrimeNumber {
    public static void main(String[] args) {
        PrimeNumber pn = new PrimeNumber(19);
        System.out.println("CHECK: "+pn.isPrimeNumber());
    }
}
