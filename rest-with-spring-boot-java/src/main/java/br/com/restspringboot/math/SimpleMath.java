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

    public Double division(Double numberOne,Double numberTwo){
        Double division = numberOne / numberTwo;
        return division;
    }

    public Double mean(Double numberOne, Double numberTwo){
        Double mean = (numberOne + numberTwo) / 2 ;
        return mean;
    }

    public Double squareRoot(Double numberOne){
        Double squareRoot = Math.sqrt(numberOne) ;
        return squareRoot;
    }

}
