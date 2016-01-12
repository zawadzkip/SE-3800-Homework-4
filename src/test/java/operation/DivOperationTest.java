package operation;


import computation.Computation;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zawadzkip on 12/27/15.
 */
public class DivOperationTest {

    Operation o;
    List<Double> numbers;
    CustomAssert customAssert;
    private final String DIV_COMMAND = "DIV";
    private final String EMPTY_LIST_EXCEPTION_MESSAGE = "Number list must contain at least one number";
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        o = new DivOperation();
        numbers = new ArrayList<>();
        customAssert  = new CustomAssert();
    }
    @Test
    public void testDividePositives(){
        numbers.add(12.0);
        numbers.add(2.0);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(DIV_COMMAND, Arrays.toString(numbers.toArray()),6.0);
            customAssert.assertComputationIsEqual(expected,result);
        }catch (Exception e){
            fail("Test Divide Positives should not have thrown an exception");
        }
    }
    @Test
    public void testDivideNegatives(){
        numbers.add(-14.0);
        numbers.add(-7.0);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(DIV_COMMAND, Arrays.toString(numbers.toArray()),2.0);
            customAssert.assertComputationIsEqual(expected,result);
        }catch (Exception e){
            fail("Test Divide Negatives should not have thrown an exception");

        }
    }
    @Test
    public void testDivideBoth(){
        numbers.add(-21.0);
        numbers.add(3.0);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(DIV_COMMAND, Arrays.toString(numbers.toArray()),-7.0);
            customAssert.assertComputationIsEqual(expected,result);
        }catch (Exception e){
            fail("Test Divide Both should not have thrown an exception");
        }
    }
    @Test
    public void testEmptyList(){
        try{
            o.calc(numbers);
            fail("Test Empty List should have thrown an exception");
        }catch (Exception e){
            assertEquals(e.getMessage(),EMPTY_LIST_EXCEPTION_MESSAGE);
        }
    }
    @Test
    public void testValueSize(){
        numbers.add(Double.MAX_VALUE);
        numbers.add(1.0);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(DIV_COMMAND, Arrays.toString(numbers.toArray()),Double.MAX_VALUE);
            customAssert.assertComputationIsEqual(expected,result);
        }catch (Exception e){
            fail("Test Value Size should have thrown an exception.");
        }
    }
}