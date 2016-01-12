package calculator;

import msoe.CalculatorUserInput;
import msoe.Main;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by zawadzkip on 1/6/16.
 */
public class MenuTest {

    public InputStream sysin = null;
    private ByteArrayOutputStream sysout;
    //public CalculatorUserInput ci = null;
    @BeforeMethod
    public void setup() throws UnsupportedEncodingException {
        System.setIn(System.in);
        System.setOut(System.out);
        sysin = System.in;
        sysout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(sysout,true,"UTF8"));
    }

    @Test
    public void testInput() throws IOException {
        sysin = new ByteArrayInputStream("EXIT".getBytes());
        System.setIn(sysin);
        Main.main(null);
        String output = sysout.toString();
        String expected = "Welcome to Hardip's and Patrick's Calculator" +
                "Use command 'ADD' to add a list of numbers" +
                "Use command 'SUB' to subtract a list of numbers" +
                "Use command 'MULT' to multiply a list of numbers" +
                "Use command 'DIV' to divide a list of numbers" +
                "Use command 'SQR' to square a list of numbers" +
                "Use command 'HIS' to view history" +
                "Use command 'DEL' to delete history" +
                "Use placeholder '!#' to reference a previous result (# being the result number in history" +
                "Goodbye!";
        assertEquals(output.trim().replace("\n", "").replace("\r", ""),expected);
    }


}
