package calculator;

import org.testng.annotations.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import static org.testng.Assert.*;

/**
 * Created by zawadzkip on 1/4/16.
 * This test runs to cover the Calculator class and check for expected functionality.
 */
public class CalculatorTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();  // this object will hold the text that is printed to console
    private final String INSTRUCTIONS_TEXT = "Welcome to Hardip's and Patrick's Calculator\n" +
            "\n" +
            "Use command 'ADD' to add a list of numbers\n" +
            "Use command 'SUB' to subtract a list of numbers\n" +
            "Use command 'MULT' to multiply a list of numbers\n" +
            "Use command 'DIV' to divide a list of numbers\n" +
            "Use command 'SQR' to square a list of numbers\n" +
            "Use command 'HIS' to view history\n" +
            "Use command 'DEL' to delete history\n" +
            "\n" +
            "Use placeholder '!#' to reference a previous result (# being the result number in history\n";
    private Calculator calculator;
    //It's important to reset the Calculator and print stream before every test.
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception{
        System.setOut(new PrintStream(outContent));
        calculator = new Calculator();
    }
    //reset the print stream and calculator after every test.
    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception{
        outContent = new ByteArrayOutputStream();
        System.setOut(null);
        calculator = null;
    }

    //Set of Commands to be run and checked in the calculator class.
    private static String[] testOperations(){
        return new String[]{
                "ADD 1 2 3",//6
                "SUB 3 2 1",//0
                "MULT 3 2 2",//12
                "DIV 6 2 3",//1
                "SQR 4",//16
                "HIS",
                "DEL",
                "HIS"

        };
    }
    //Associated responses with corresponding commands from above.
    private static String[] testOperationsToString(){
        return new String[]{
                "ADD [1.0, 2.0, 3.0] Answer: 6.0",
                "SUB [3.0, 2.0, 1.0] Answer: 0.0",
                "MULT [3.0, 2.0, 2.0] Answer: 12.0",
                "DIV [6.0, 2.0, 3.0] Answer: 1.0",
                "SQR [4.0] Answer: 16.0",
                "ADD [1.0, 2.0, 3.0] Answer: 6.0\n" +
                        "SUB [3.0, 2.0, 1.0] Answer: 0.0\n" +
                        "MULT [3.0, 2.0, 2.0] Answer: 12.0\n" +
                        "DIV [6.0, 2.0, 3.0] Answer: 1.0\n" +
                "SQR [4.0] Answer: 16.0",
                "\n",
                "No Logs to Display"
        };
    }

    /**
     * A dataprovider utilizing the two arrays above in order to check for certain output after
     * certain commands have been run.
     * @return An object array containing, the array of commands, the array of appropriate respones for said command,
     * and the index for which to access in both arrays.
     */
    @DataProvider(name = "testOperations")
    private static Object[][] testOperationResults(){
        return new Object[][]{
                {testOperations(),testOperationsToString(),0},
                {testOperations(),testOperationsToString(),1},
                {testOperations(),testOperationsToString(),2},
                {testOperations(),testOperationsToString(),3},
                {testOperations(),testOperationsToString(),4},
                {testOperations(),testOperationsToString(),5},
                {testOperations(),testOperationsToString(),6},
                {testOperations(),testOperationsToString(),7},

        };
    }

    /**
     * The tests the standard output of each command. It is VERY IMPORTANT that this test runs first. Otherwise their
     * tends to be situations where the logger will contain commands run from other tests.
     * @param commands Array of commands
     * @param commandResponses Array of command responses respective to commands
     * @param index the index to access in both arrays
     */
    @Test(priority = 0, dataProvider = "testOperations")
    public void testStandardOperations(String[] commands, String[] commandResponses, int index)
    {
        calculator.executeCommand(commands[index]);
        String output = outContent.toString().replace(INSTRUCTIONS_TEXT,"").trim();//remove the instructions text.
        assertEquals(output.trim().replace("\n", "").replace("\r", ""),commandResponses[index].trim().trim().replace("\n", "").replace("\r", ""));
    }

    /**
     * This test will check the ! operator to substitute a result from a previous computation, into a new computation.
     */
    @Test(priority = 1)
    public void testSubstitutionOperations() {
       String add = "ADD 1 2 3";
       String sub = "SUB !1 2.0";
       String result = "SUB [6.0, 2.0] Answer: 4.0";
       calculator.executeCommand(add);
       outContent.reset();
       calculator.executeCommand(sub);
       String output = outContent.toString().replace(INSTRUCTIONS_TEXT,"").trim();
       assertEquals(output,result);
    }

    /**
     * This test will check for the appropriate response from a command that is not recognized by the Calculator.
     */
    @Test(priority = 1)
    public void testBadCommand(){
        calculator.executeCommand("NOTCOMMAND");
        String output = outContent.toString().replace(INSTRUCTIONS_TEXT,"").trim();
        assertEquals(output,"Command Not Recognized");
    }

    @Test(priority = 1)
    public void testExit(){
        calculator.executeCommand("EXIT");
        String output = outContent.toString().replace(INSTRUCTIONS_TEXT,"").trim();
        assertEquals(output,"");
    }

    /**
     * This will check for a valid response when
     * invalid user input is issued. A user issues a valid command, but provides an invalid parameter.
     * For example: A letter instead of a number.
     */
    @Test(priority = 1)
    public void testNonInteger(){
        calculator.executeCommand("SUB A 2");
        String expected = "A is not a valid integer.  Skipping...SUB [2.0] Answer: 2.0";
        String output = outContent.toString().replace(INSTRUCTIONS_TEXT,"").trim();
        assertEquals(output.trim().replace("\n", "").replace("\r", ""),expected);
    }

    /**
     * This test will check for a response when a user attempts to reference a past command that is not
     * valid, in this case a letter instead of an expected integer.
     */
    @Test(priority = 1)
    public void testNonIntegerSubstitution(){
        calculator.executeCommand("ADD 1 2");
        outContent.reset();
        calculator.executeCommand("SUB !A 2");
        String output = outContent.toString().replace(INSTRUCTIONS_TEXT,"").trim();
        String expected = "A is not a valid integerSUB [2.0] Answer: 2.0";
        assertEquals(output.trim().replace("\n", "").replace("\r", ""),expected);
    }

    /**
     * This test will check for a response when a user provides an integer index that is invalid. This is different
     * from the above test because it is still a valid integer, however it would be out of bounds.
     */
    @Test(priority =  1)
    public void testInvalidIntegerSubtitution(){
        calculator.executeCommand("ADD 1 2 3");
        outContent.reset();
        calculator.executeCommand("SUB !-1 2");
        String output = outContent.toString().replace(INSTRUCTIONS_TEXT,"").trim();
        String expected = "-1 is not a valid index.  Skipping..." +
                "SUB [2.0] Answer: 2.0";
        assertEquals(output.trim().replace("\n", "").replace("\r", ""),expected);
    }

    /**
     * This test will check for an out of bounds situation in which the user attempts to reference a past calculation
     * that also does not exist, but instead of being a negative number, it is a very large position number.
     */
    @Test(priority = 1)
    public void testLargeIntegerSubstitution(){
        calculator.executeCommand("SUB !100000000 2");
        String output = outContent.toString().replace(INSTRUCTIONS_TEXT,"").trim();
        String expected = "100000000 is not a valid index.  Skipping..." +
                "SUB [2.0] Answer: 2.0";
        assertEquals(output.trim().replace("\n", "").replace("\r", ""),expected);
    }









}