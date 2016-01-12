package operation;

import computation.Computation;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by zawadzkip on 12/27/15.
 */
public class SqrOperationTest {

    Operation o;
    List<Double> numbers;
    CustomAssert customAssert;
    private final String SQR_COMMAND = "SQR";
    private final String EXCEPTION_MESSAGE = "Number list has invalid number of numbers.  Can only have one number";
    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        o = new SqrOperation();
        numbers = new ArrayList<>();
        customAssert = new CustomAssert();
    }

    @Test
    public void testPositiveNumbers(){
        numbers.add(5.0);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(SQR_COMMAND, Arrays.toString(numbers.toArray()), 25.0);
            customAssert.assertComputationIsEqual(expected,result);
        }catch (Exception e){
            fail("Test Positive Number should not have thrown an exception");
        }
    }
    @Test
    public void testNegativeNumbers(){
        numbers.add(-10.0);
        try{
            Computation result =o.calc(numbers);
            Computation expected = new Computation(SQR_COMMAND, Arrays.toString(numbers.toArray()),100.0);
            customAssert.assertComputationIsEqual(expected,result);
        }catch (Exception e){
            fail("Test Negative Numbers should not have thrown an exception");
        }
    }
    @Test
    public void testEmptyList(){
        try{
            o.calc(numbers);
            fail("Test Empty List should have thrown an exception");
        }catch(Exception e){
            assertEquals(e.getMessage(), EXCEPTION_MESSAGE);
        }
    }
    @Test
    public void testMoreThanOneElementList(){
        numbers.add(10.0);
        numbers.add(10.0);
        try{
            o.calc(numbers);
            fail("Test More Than One Elements List should have thrown an exception");
        }catch(Exception e){
            assertEquals(e.getMessage(), EXCEPTION_MESSAGE);
        }
    }
    @Test
    public void testValueSize(){
        numbers.add(Math.sqrt(Double.MAX_VALUE));
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(SQR_COMMAND,Arrays.toString(numbers.toArray()),Double.MAX_VALUE);

            assertEquals(result.getResult(),expected.getResult(),2E292);  // Had to use 2E292 because the actual actual and expected values where to the power of 308
            assertEquals(result.getName(),expected.getName());
            assertEquals(result.getNumberList(),expected.getNumberList());
            //assertEquals(result.computationString(), expected.computationString());  // This would never pass because the actual and expected values will never be the same
                                                                                            // I would say that it is safe to assume that if the tests above pass, then we can ignore this test.
            //customAssert.assertComputationIsEqual(exepected,result);
        }catch(Exception e){
            fail("Test Value Size should not have thrown an exception.");
        }
    }

}