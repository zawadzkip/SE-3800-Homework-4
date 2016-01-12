package operation;

import computation.Computation;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gillh on 12/20/2015.
 *
 * This class is equipped to handle addition of numbers
 */
public class AddOperation implements Operation {

    /**
     * This method will take a list of numbers and return a computation object of the result
     * @param numbers list of numbers to add
     * @return Computation object
     */
    @Override
    public Computation calc(List<Double> numbers) {
        if(numbers != null) {
            double sum;
            if (numbers.size() > 0) {                  // if list is not empty
                sum = 0;
                for (Double num : numbers) {       // for every number in list
                    sum += num;                         // add them together
                }
                return new Computation("ADD", Arrays.toString(numbers.toArray()), sum); // return a computation object
            } else {
                throw new IllegalArgumentException("Number list must contain at least one number");
            }
        }
        else{
            throw new IllegalArgumentException("Number list cannot be NULL");
        }
    }
}
