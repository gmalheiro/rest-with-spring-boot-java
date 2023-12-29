package br.com.restspringboot.math;

public class SimpleMath {
    
    public Double sum(Double numberOne,Double numberTwo) {
        Double sum = numberOne + numberTwo;
        return sum;
    }

    public Double subtraction(Double numberOne, Double numberTwo){
        Double subtraction = numberOne - numberTwo;
        return subtraction;
    }

    public Double multiplication(Double numberOne, Double numberTwo){
        Double multiplication = numberOne * numberTwo;
        return multiplication;
    }

}
