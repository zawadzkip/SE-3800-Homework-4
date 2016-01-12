package logger;

import computation.Computation;
import logging.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.*;

/**
 * Created by gillh on 12/23/2015.
 *
 * This class is responsible for testing the functionality of the Logger Class
 */
public class LoggerTest {

    public LoggerTest(){}

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();  // this object will hold the text that is printed to console

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
     * Error messages
     */
    private static String PRINT_N_LOGS_ERROR_INVALID_INDEX = "Unable to print log: Please request at least one log";
    private static String PRINT_N_LOGS_ERROR_NO_LOGS = "No Logs to Display";

    @BeforeMethod
    public void setUp() throws Exception {
        Logger.getInstance().clearLogHistory();         // clear out logger
        System.setOut(new PrintStream(outContent));     // set the outContent as holding the printed text
    }

    @AfterMethod
    public void tearDown() throws Exception {
        outContent.reset();                             // clear contents of outContent
        System.setOut(null);                            // reset System.out
    }

    /**
     * This method will test the singleton functionality of the Logger class
     * @throws Exception
     */
    @Test
    public void testGetInstance() throws Exception {
        Logger loggerInst1 = Logger.getInstance();          // make one reference to class
        Logger loggerInst2 = Logger.getInstance();          // mak another referenct to class

        assertEquals(true, loggerInst1 == loggerInst2, "Logger instances should be the same instance");  // both references should point to same object
    }

    /**
     * This method will test the functionality of the log() method
     * @throws Exception
     */
    @Test
    public void testLog() throws Exception {
        Computation[] comps = computations();
        Logger logger = Logger.getInstance();

        logger.log(comps[0]);

        assertEquals(logger.getNumLogs(),1, "Should be "+1+" was "+logger.getNumLogs());

        logger.log(comps[1]);
        logger.log(comps[2]);

        assertEquals(logger.getNumLogs(),3,"Should be "+3+" was "+logger.getNumLogs());
    }

    /**
     * This is a data provider for the testGetComputation method.
     * It holds the {array of computations to log, index of computation to get, expected computation output}
     * @return
     */
    @DataProvider(name = "testGetComputation")
    private static Object[][] testGetComputationExpectedResults(){
        return new Object [][]{
                {new Computation[0],0,true,""},
                {new Computation[0],1,true,""},
                {new Computation[0],-1,true,""},
                {computations(),0,false,computationsToString()[0]},
                {computations(),1,false,computationsToString()[1]},
                {computations(),2,false,computationsToString()[2]},
                {computations(),3,false,computationsToString()[3]},
                {computations(),4,false,computationsToString()[4]},
                {computations(),-1,true,""},
                {computations(),5,true,""},
        };
    }

    /**
     * This method will test the getComputation() method
     * @param comps             computation array
     * @param indexToGet        index of computation that is to be retrieved
     * @param expectedResult    expected computation string
     * @throws Exception
     */
    @Test(dataProvider = "testGetComputation")
    public void testGetComputation(Computation[] comps, int indexToGet, boolean shouldBeNull, String expectedResult) throws Exception{
        Logger logger = Logger.getInstance();

        for(Computation comp: comps){
            logger.log(comp);
        }
        if(shouldBeNull){
            System.out.println(indexToGet);
            assertNull(logger.getComputation(indexToGet));
        }
        else {
            assertEquals(logger.getComputation(indexToGet).computationString(), expectedResult, "Should be " + expectedResult + " was " + logger.getNumLogs());
        }
    }

    /**
     * This method will test the clearLogHistory() method
     * @throws Exception
     */
    @Test
    public void testClearLogHistory() throws Exception{
        Logger logger = Logger.getInstance();

        for (Computation comp: computations()){         // add all computations
            logger.log(comp);
        }

        assertEquals(logger.getNumLogs(),computations().length);

        logger.clearLogHistory();       // clear history

        assertEquals(logger.getNumLogs(),0);  // getNumLogs should be 0 now
    }

    /**
     * This method will test the functionality of printMostRecentLog() method.
     * @throws Exception
     */
    @Test
    public void testPrintMostRecentLog() throws Exception {

        Computation[] comps = computations();               // make reference to computations object
        String[] compsToString = computationsToString();        // make reference to computationsToString object

        Logger logger = Logger.getInstance();                   // get instance of Logger
        logger.log(comps[0]);                               // log one computation
        logger.printMostRecentLog();

        assertEquals(outContent.toString().trim(), compsToString[0], "Should be " + compsToString[0] + " was " + outContent.toString());
        outContent.reset(); // reset outContent stream

        logger.log(comps[1]);
        logger.log(comps[2]);

        logger.printMostRecentLog();

        assertEquals(outContent.toString().trim(), compsToString[2], "Should be " + compsToString[2] + " was " + outContent.toString());
        //outContent.reset();
    }

    /**
     * This is a data provider for the testPrintNLogs method.
     * It holds the {value of N, array of computations to log, expected output}
     * @return
     */
    @DataProvider(name = "printNLogsExpectedResults")
    private static Object [][] printNLogsExpectedResults(){
        return new Object [][]{
                {1,new Computation[0],PRINT_N_LOGS_ERROR_NO_LOGS},
                {1,computations(),computationsToString()[4]},               // get last computation
                {2,computations(),computationsToString()[3]+computationsToString()[4]}, // get last two computations
                {3,computations(),computationsToString()[2]+computationsToString()[3]+computationsToString()[4]}, // get last three computations
                {4,computations(),computationsToString()[1]+computationsToString()[2]+computationsToString()[3]+computationsToString()[4]}, // get last four computations
                {5,computations(),computationsToString()[0]+computationsToString()[1]+computationsToString()[2]+computationsToString()[3]+computationsToString()[4]}, // get last five computations
                {6,computations(),computationsToString()[0]+computationsToString()[1]+computationsToString()[2]+computationsToString()[3]+computationsToString()[4]}, // get last six computations
                {0,computations(),PRINT_N_LOGS_ERROR_INVALID_INDEX},
        };
    }

    /**
     * This method will test the printNLogs() method using the data provider as indicated
     * It tests what the output should be once the printNLogs() method is invoked in various situations
     * @param numLogs
     * @param comps
     * @param expectedResult
     * @throws Exception
     */
    @Test(dataProvider = "printNLogsExpectedResults")
    public void testPrintNLogs(int numLogs, Computation[] comps, String expectedResult) throws Exception {
        Logger logger = Logger.getInstance();

        for(Computation comp: comps){  // log all computations
            logger.log(comp);
        }

        logger.printNLogs(numLogs);  // print logs based on numLogs

        assertEquals(
                outContent.toString().trim().replace("\n", "").replace("\r", ""),
                expectedResult.trim(),
                "Should be " +
                        expectedResult +
                " was " + outContent.toString()
        );

    }
}