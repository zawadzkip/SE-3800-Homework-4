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
public class SubOperationTest {

    Operation o;
    List<Double> numbers;
    CustomAssert customAssert;
    private final String SUB_COMMAND = "SUB";
    private final String EXCEPTION_MESSAGE = "Number list must contain at least one number";
    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        o = new SubOperation();
        numbers = new ArrayList<>();
        customAssert = new CustomAssert();
    }

    @Test
    public void testPositiveNumbers(){
        numbers.add(12.0);
        numbers.add(5.0);
        numbers.add(1.0);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(SUB_COMMAND, Arrays.toString(numbers.toArray()),6.0);
            customAssert.assertComputationIsEqual(expected,result);
        }catch(Exception e){
            fail("Test Positive Numbers should not have thrown an exception");
        }
    }
    @Test
    public void testNegativeNumbers(){
        numbers.add(-10.0);
        numbers.add(-2.0);
        numbers.add(-8.0);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(SUB_COMMAND,Arrays.toString(numbers.toArray()),0.0);
            customAssert.assertComputationIsEqual(expected,result);
        }catch(Exception e){
            fail("Test Negative Numbers should not have thrown an exception");
        }
    }
    @Test
    public void testBoth(){
        numbers.add(10.0);
        numbers.add(-5.0);
        numbers.add(2.0);
        numbers.add(-3.0);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(SUB_COMMAND,Arrays.toString(numbers.toArray()),16.0);
            customAssert.assertComputationIsEqual(expected,result);
        }catch(Exception e){
            fail("TestBoth should not have thrown an exception");
        }
    }
    @Test
    public void testEmptyList(){
        try{
            o.calc(numbers);
            fail("Test EmptyList should have failed");
        }catch(Exception e){
            assertEquals(e.getMessage(),EXCEPTION_MESSAGE);
        }
    }
    @Test
    public void testValueSize(){
        numbers.add(Double.MAX_VALUE);
        try{
            Computation result = o.calc(numbers);
            Computation expected = new Computation(SUB_COMMAND,Arrays.toString(numbers.toArray()),Double.MAX_VALUE);
            customAssert.assertComputationIsEqual(expected,result);
        }catch(Exception e){
            fail("Test Value Size should not have thrown an exception.");
        }
    }


}