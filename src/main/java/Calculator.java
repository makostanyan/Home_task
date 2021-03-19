public class Calculator {

    public double sum(double a, double b){
        return a + b;
    }

    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0){
            throw  new ArithmeticException();
        }
        return  a / b;
    }

    public double subtraction(double a, double b){
        return a - b;
    }

    public double multiplication(double a, double b){
        return a * b;
    }

}
