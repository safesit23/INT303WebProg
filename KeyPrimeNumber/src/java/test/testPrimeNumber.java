package test;

import model.PrimeNumber;

public class testPrimeNumber {
    public static void main(String[] args) {
        PrimeNumber pn = new PrimeNumber();
        pn.setNumber(2);
        System.out.printf("Is %d is Prime Number ?  %b\n",pn.getNumber(),pn.isPrimeNumber());
        pn.setNumber(3);
        System.out.printf("Is %d is Prime Number ?  %b\n",pn.getNumber(),pn.isPrimeNumber());
    }
}
