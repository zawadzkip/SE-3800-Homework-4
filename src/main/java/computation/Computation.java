package computation;

/**
 * Created by Patrick Zawadzki on 12/18/2015.
 *
 * This class is used as an Object to store the computation results for Logging. When an Operation is run,
 * the Operation will pass it's results and information into the logger using this class as the means of storing
 * relevant data.
 *
 */
public class Computation {
    /**
     * The String name variable is used to store the string of the type of computation being done.
     * For example, if the user called the "Add" computation, then "Add" would be stored in this variable.
     */
    private String name;
    /**
     * The String numberList is used to essentially store the parameters that were passed into the computation initially.
     * For example, if the user called "Add 1 2 3 4 5", then "1 2 3 4 5" would be stored in this String.
     */
    private String numberList;
    /**
     * The String result is used to store the result of the computation that was completed. For example, if the user
     * called "Add 1 2 3" then the answer "6" would be stored in this String.
     */
    private double result;
    public Computation(String name, String numberList, double result){
        if(name == null || numberList == null){
            throw new IllegalArgumentException("Computation parameters cannot be null");
        }
        this.name = name;
        this.numberList = numberList;
        this.result = result;
    }

    //Appropriate Getters/Setters below
    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public String getNumberList() {
        return numberList;
    }

//    public void setNumberList(String numberList) {
//        this.numberList = numberList;
//    }

    public double getResult() {
        return result;
    }

//    public void setResult(double result) {
//        this.result = result;
//    }

    /**
     * This method is used to easily print the results of this Computation object for quick logging response.
     * @return A String containing the name, list of numbers, and result of that calculation.
     * @throws IllegalArgumentException When one of the possible Strings stored in a computation is null
     */
    public String computationString() throws IllegalArgumentException{
//        if(name == null)
//            throw new IllegalArgumentException("A computation NAME cannot be NULL");
//        if(numberList == null)
//            throw new IllegalArgumentException("A computation NUMBER LIST cannot be NULL");
        return name + " " + numberList + " Answer: " + result;
    }
}
