package calculator;

import computation.Computation;
import logging.Logger;
import operation.*;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by gillh on 12/21/2015.
 *
 * This Calculator class will be responsible for executing commands and operations.
 */
public class Calculator {

    private Logger logger = null;
    private static char HIS_REF_SYMBOL = '!';       // Declaring symbol to be used when referencing past computation results in history

    /**
     * Constructor method
     * Welcomes user
     * Initiates calculator functionality
     */
    public Calculator(){
       logger = Logger.getInstance();
        

    }
    /**
     * The helper method is used to determine which commands to execute based on user input
     * @param input user input
     * @return whether to continue or exit
     */
    public boolean executeCommand(String input){
        boolean cont = true;
        String[] inputArray = input.trim().split(" ");      // split input string into array by a space delimeter
        String typeOfCommand = inputArray[0];               // the type of command will be the first element of input array
        switch(typeOfCommand) {
            case "ADD":
                conductOperation(new AddOperation(),inputArray);
                break;
            case "SUB":
                conductOperation(new SubOperation(),inputArray);
                break;
            case "MULT":
                conductOperation(new MultOperation(),inputArray);
                break;
            case "DIV":
                conductOperation(new DivOperation(),inputArray);
                break;
            case "SQR":
                conductOperation(new SqrOperation(),inputArray);
                break;
            case "HIS":
                logger.printNLogs(logger.getNumLogs());
                break;
            case "DEL":
                logger.clearLogHistory();
                break;
            case "EXIT":
                cont = false;
                break;
            default:
                System.out.println("Command Not Recognized");
                break;
        }
        return cont;
    }

    /**
     * This method is used when an operation command is executed.
     * It executes the operation while logging and printing the result
     * @param op Operation type
     * @param inputArray input parameters
     */
    private void conductOperation(Operation op, String[] inputArray){
        try {
            Computation comp = op.calc(cleanInputParameters(inputArray));  // calculate operation with integer input parameters
            logger.log(comp);                       // log computation
            System.out.println(comp.computationString()); // print result
        } catch (Exception e) {
            e.printStackTrace();   // Not sure what to do if we reach here
        }
    }

    /**
     * This helper method will convert an array of String values into Integers if they are integers
     * @param inputArray Array of string numbers
     * @return List of integers
     */
    private List<Double> cleanInputParameters(String[] inputArray){
        List<Double> numList = new LinkedList<Double>();
        for(int i = 1; i< inputArray.length;i++){                   // for every element in array
            char firstChar = inputArray[i].charAt(0);
            if(firstChar == HIS_REF_SYMBOL){                                   // if first character is a "!"
                subHistoryReference(inputArray[i],numList);             // then initiate history subsitution
            }
            else{                                                   // else
                try {
                    numList.add(Double.parseDouble(inputArray[i]));           // add the integer equivalent of string
                }
                catch (NumberFormatException e){                            // if string is not an integer
                    System.out.println(inputArray[i]+" is not a valid integer.  Skipping...");  // print error message and skip it
                }
            }

        }
        return numList;  // return list of integers
    }

    /**
     * This helper method will use the logger to reference previous computations
     * and subsitute the value of the result into the integer list
     * @param hisRef history reference string
     * @param numList integer list to add value to
     */
    private void subHistoryReference(String hisRef, List<Double> numList){
        String prevCompIndexStr = hisRef.replace(""+HIS_REF_SYMBOL,"").trim();                  // remove History Reference Symbol
        int prevCompIndex;
        try{
            prevCompIndex = Integer.parseInt(prevCompIndexStr);  // convert string value to integer
            if(prevCompIndex>=1 && prevCompIndex <= logger.getNumLogs()){  // if index is a valid index for the log items
                numList.add(logger.getComputation(logger.getNumLogs() - prevCompIndex).getResult());            // add referenced result to number list
            }
            else{
                System.out.println(prevCompIndex+" is not a valid index.  Skipping...");
            }
        }
        catch (NumberFormatException e) {                                   // if not possible
            System.out.println(prevCompIndexStr + " is not a valid integer");      // print error and skip
        }

    }
}
