package computation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.util.IllegalFormatException;

import static org.testng.Assert.*;

/**
 * Created by gillh on 12/27/2015.
 */
public class ComputationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();  // this object will hold the text that is printed to console

    private static final String NULL_PARAM_ERROR_MSG = "Computation parameters cannot be null";

    public ComputationTest(){}

    /**
     * This data provider contains data to be used to test the constructor method
     * @return
     */
    @DataProvider(name = "testConstructor")
    private Object[][] testConstructorData(){
        return new Object[][]{
                {null,  null,       0,   true,      NULL_PARAM_ERROR_MSG},
                {"ADD", null,       0,   true,      NULL_PARAM_ERROR_MSG},
                {null,  "[1,2,3]",  0,   true,      NULL_PARAM_ERROR_MSG},
                {null,  null,       1,   true,      NULL_PARAM_ERROR_MSG},
                {"ADD",  "[1,2,3]",  1,   false,   ""},
        };
    }

    /**
     * This method will test the Constructor method to ensure that it throws Illegal Argument exceptions when
     * a valid value is not provided for an argument.  Otherwise it should create the objects with no problem
     * @param name                  name of operation
     * @param numberList            list of numbers to operate
     * @param result                result of operation
     * @param exceptionExpected     is an exception expected?
     * @param exceptionString           if so, what is the exception string
     * @throws Exception
     */
    @Test(dataProvider = "testConstructor")
    public void testConstructor(String name,String numberList,double result, boolean exceptionExpected, String exceptionString) throws Exception{
        if(exceptionExpected) {
            try {
                new Computation(name, numberList, result);
                fail("Should have failed");
            } catch (IllegalArgumentException e) {
                assertEquals(e.getMessage(),exceptionString);
            }
        }
        else{
            try{
                new Computation(name,numberList,result);
            }
            catch(IllegalFormatException e){
                fail("Should not have failed");
            }
        }
    }

    /**
     * This Computation array holds a list of computation objects will be used in the tests located below
     * @return
     */
    private static Computation[] computations(){
        return new Computation[]{
                new Computation("ADD","[1,2,3]",6.0),
                new Computation("SUB","[1,2,3]",-4.0),
                new Computation("MULT","[1,2,3]",6.0),
                new Computation("DIV","[1,2,4]",0.125),
                new Computation("SQR","[2]",4.0)
        };
    }

    /**
     * The String array holds the output string values for each of the computations in the "computations" object.
     * The indexes of each array match respectively.
     * @return
     */
    private static String[] computationsToString(){
        return new String[]{
                "ADD [1,2,3] Answer: 6.0",
                "SUB [1,2,3] Answer: -4.0",
                "MULT [1,2,3] Answer: 6.0",
                "DIV [1,2,4] Answer: 0.125",
                "SQR [2] Answer: 4.0"
        };
    }

    /**
     * The String array holds the name string values for each of the computations in the "computations" object.
     * The indexes of each array match respectively.
     * @return
     */
    private static String[] computationNames(){
        return new String[]{
                "ADD",
                "SUB",
                "MULT",
                "DIV",
                "SQR"
        };
    }

    /**
     * The String array holds the Number List string values for each of the computations in the "computations" object.
     * The indexes of each array match respectively.
     * @return
     */
    private static String[] computationNumLists(){
        return new String[]{
                "[1,2,3]",
                "[1,2,3]",
                "[1,2,3]",
                "[1,2,4]",
                "[2]"
        };
    }

    /**
     * The Double array holds the Result values for each of the computations in the "computations" object.
     * The indexes of each array match respectively.
     * @return
     */
    private static Double[] computationResults(){
        return new Double[]{
                6.0,
                -4.0,
                6.0,
                0.125,
                4.0
        };
    }

    /**
     * This method will test the getName() method
     * @throws Exception
     */
    @Test
    public void testGetName() throws Exception {
        Computation[] comps = computations();
        String[] names = computationNames();

        Computation comp0 = comps[0];
        Computation comp1 = comps[1];
        Computation comp2 = comps[2];
        Computation comp3 = comps[3];
        Computation comp4 = comps[4];

        assertEquals(comp0.getName(),names[0]);
        assertEquals(comp1.getName(),names[1]);
        assertEquals(comp2.getName(),names[2]);
        assertEquals(comp3.getName(),names[3]);
        assertEquals(comp4.getName(),names[4]);
    }

//    @Test
//    public void testSetName() throws Exception {
//
//    }

    /**
     * This method will test the getNumberList() method
     * @throws Exception
     */
    @Test
    public void testGetNumberList() throws Exception {
        Computation[] comps = computations();
        String[] numLists = computationNumLists();

        Computation comp0 = comps[0];
        Computation comp1 = comps[1];
        Computation comp2 = comps[2];
        Computation comp3 = comps[3];
        Computation comp4 = comps[4];

        assertEquals(comp0.getNumberList(),numLists[0]);
        assertEquals(comp1.getNumberList(),numLists[1]);
        assertEquals(comp2.getNumberList(),numLists[2]);
        assertEquals(comp3.getNumberList(),numLists[3]);
        assertEquals(comp4.getNumberList(),numLists[4]);
    }

//    @Test
//    public void testSetNumberList() throws Exception {
//
//    }

    /**
     * This method will test the getResult() method
     * @throws Exception
     */
    @Test
    public void testGetResult() throws Exception {
        Computation[] comps = computations();
        Double[] results = computationResults();

        Computation comp0 = comps[0];
        Computation comp1 = comps[1];
        Computation comp2 = comps[2];
        Computation comp3 = comps[3];
        Computation comp4 = comps[4];

        assertEquals(comp0.getResult(),results[0]);
        assertEquals(comp1.getResult(),results[1]);
        assertEquals(comp2.getResult(),results[2]);
        assertEquals(comp3.getResult(),results[3]);
        assertEquals(comp4.getResult(),results[4]);
    }

//    @Test
//    public void testSetResult() throws Exception {
//
//    }

    /**
     * This method will test the computationString() method
     * @throws Exception
     */
    @Test
    public void testComputationString() throws Exception {
        Computation[] comps = computations();
        String[] compStrings = computationsToString();

        Computation comp0 = comps[0];
        Computation comp1 = comps[1];
        Computation comp2 = comps[2];
        Computation comp3 = comps[3];
        Computation comp4 = comps[4];

        assertEquals(comp0.computationString(),compStrings[0]);
        assertEquals(comp1.computationString(),compStrings[1]);
        assertEquals(comp2.computationString(),compStrings[2]);
        assertEquals(comp3.computationString(),compStrings[3]);
        assertEquals(comp4.computationString(),compStrings[4]);
    }
}