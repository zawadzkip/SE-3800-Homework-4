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
public class MultOperationTest {

    Operation o;
    List<Double> numbers;
    CustomAssert customAssert;
    private final String MULT_COMMAND = "MULT";
    private final String EMPTY_LIST_EXCEPTION_MESSAGE = "Number list must contain at least one number";
    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        o = new MultOperation();
        numbers = new ArrayList<>();
        customAssert = new CustomAssert();
    }
    @Test
    public void testMultiplyPositive(){
        numbers.add(5.0);
        numbers.add(6.0);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(MULT_COMMAND, Arrays.toString(numbers.toArray()),30.0);
            customAssert.assertComputationIsEqual(expected,result);
        }catch (Exception e){
            fail("Test Multiply Positive should have not thrown an exception");
        }

    }
    @Test
    public void testMultiplyNegative(){
        numbers.add(-10.0);
        numbers.add(-2.0);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(MULT_COMMAND, Arrays.toString(numbers.toArray()),20.0);
            customAssert.assertComputationIsEqual(expected,result);
        }catch (Exception e){
            fail("Test Multiply Negatives should not have thrown an exception");
        }
    }
    @Test
    public void testMultiplyBoth(){
        numbers.add(-5.0);
        numbers.add(10.0);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(MULT_COMMAND,Arrays.toString(numbers.toArray()),-50.0);
            customAssert.assertComputationIsEqual(expected,result);
        }catch (Exception e) {
            fail("Test Multiply Both should not have thrown an exception");
        }
    }
    @Test
    public void testEmptyList(){
        try{
            o.calc(numbers);
            fail("Exception should have been thrown.");
        }catch (Exception e){
            assertEquals(e.getMessage(),EMPTY_LIST_EXCEPTION_MESSAGE);
        }
    }

    //TODO need to find a better to do this. the floating point precison loss is causing false positives
    //FIXME
    @Test
    public void testValueSize(){
        numbers.add(Double.MAX_VALUE);
        numbers.add(1.0);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(MULT_COMMAND,Arrays.toString(numbers.toArray()),Double.MAX_VALUE);
            customAssert.assertComputationIsEqual(expected,result);
        }catch (Exception e){
            fail("Test Value Size should not have thrown an exception.");
        }
    }

}