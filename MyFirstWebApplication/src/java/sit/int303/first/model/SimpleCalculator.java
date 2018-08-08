/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.first.model;

public class SimpleCalculator {
    private int x;
    private int y;
    private String operator;

    public SimpleCalculator() {
    }

    public SimpleCalculator(int x, int y, String operator) {
        this.x = x;
        this.y = y;
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public int getresult() {
        switch (operator) {
            case "+": return x + y;
            case "-": return x - y;
            case "*": return x * y;
            case "/": return x / y;
            default:    return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("x = %d , y = %d <br> %d %s %d = %d", x,y,x,operator,y,getresult());
    }
    

}
