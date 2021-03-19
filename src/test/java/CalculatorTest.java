import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void sumTest(){
        double actualSum = calculator.sum(5,2.6);
        double expectedSum = 7.6;
        Assert.assertEquals(actualSum,expectedSum);
    }

    @Test
    public void subtractionTest(){
        double actualSubtraction = calculator.subtraction(13,2.5);
        double expectedSubtraction = 10.5;
        Assert.assertEquals(actualSubtraction,expectedSubtraction);
    }

    @Test
    public void subtractionBooleanTest(){
        double actualSubtraction = calculator.subtraction(13,2.5);
        double expectedSubtraction = 10.5;
        Assert.assertTrue(actualSubtraction == expectedSubtraction);
    }

    @Test
    public void divisionTest(){
        double actualDivision = calculator.divide(50,2.5);
        double expectedDivision = 20;
        Assert.assertEquals(actualDivision,expectedDivision);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void divisionByZeroTest(){
        calculator.divide(50,0);
    }

    @Test
    public void multiplicationTest(){
        double actualMultiplication = calculator.multiplication(13,2.5);
        double expectedMultiplication = 32.5;
        Assert.assertEquals(actualMultiplication,expectedMultiplication);
    }


}
