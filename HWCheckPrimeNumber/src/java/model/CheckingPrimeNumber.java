package model;

public class CheckingPrimeNumber {
    private int number;

    public CheckingPrimeNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public String getResultPrimeNumber(){
        int LimitNum=2;
        boolean PrimeNum=true;
        if(number>1){
            while(number>LimitNum){
                if(number%LimitNum==0){
                    PrimeNum=false;break;
                }
                LimitNum++;
            }
            if(PrimeNum){
                return "Prime Number";
            }else{
                return "not a Prime Number";
            }
        }else{
            return "not a Prime Number";
        }
    }

    @Override
    public String toString() {
        return String.format("%d is %s", number, getResultPrimeNumber());
    }
    
}
