package msoe;

import calculator.Calculator;

import java.util.Scanner;

/**
 * Created by zawadzkip on 1/4/16.
 */
public class CalculatorUserInput {
    private Calculator c = new Calculator();
    
    public CalculatorUserInput(){
        System.out.println("Welcome to Hardip's and Patrick's Calculator");
        boolean cont = true;
        Scanner s = new Scanner(System.in);
        while(cont){
            showInstructions();
            cont = c.executeCommand(s.nextLine());
        }
        System.out.println("Goodbye!");
    }

    private void showInstructions(){
        System.out.println("");
        System.out.println("Use command 'ADD' to add a list of numbers");
        System.out.println("Use command 'SUB' to subtract a list of numbers");
        System.out.println("Use command 'MULT' to multiply a list of numbers");
        System.out.println("Use command 'DIV' to divide a list of numbers");
        System.out.println("Use command 'SQR' to square a list of numbers");
        System.out.println("Use command 'HIS' to view history");
        System.out.println("Use command 'DEL' to delete history");
        System.out.println("");
        System.out.println("Use placeholder '!#' to reference a previous result (# being the result number in history");

    }
}
