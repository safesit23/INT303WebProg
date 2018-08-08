package model;

public class PrimeNumber {
    private int number;

    public PrimeNumber() {
    }

    public PrimeNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public boolean isPrimeNumber(){
        boolean isPrime = number > 1?true:false;    //ถ้าตัวเลขมากกว่า 1 ให้ตั้งต้นเป็น true
        for (int i = 2; i < number; i++) {       
            if(number%i==0){
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
    
}
