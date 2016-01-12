package operation;

import computation.Computation;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gillh on 12/20/2015.
 *
 * This class is equipped to handle subtraction of numbers
 */
public class SubOperation implements Operation {

    /**
     * This method will take a list of numbers and return a computation object of the result
     * @param numbers list of numbers to add
     * @return Computation object
     */
    @Override
    public Computation calc(List<Double> numbers) throws Exception {
        if(numbers != null) {
            double dif;
            if (numbers.size() > 0) {           // if list is not empty
                dif = numbers.get(0);        // start with diff being first number in list
                for (int i = 1; i < numbers.size();i++) {          // for rest of numbers in list
                    dif -= numbers.get(i);                           // subtract number from difference
                }
                return new Computation("SUB", Arrays.toString(numbers.toArray()), dif);
            } else {
                throw new IllegalArgumentException("Number list must contain at least one number");
            }
        }
        else{
            throw new IllegalArgumentException("Number list cannot be NULL");
        }
    }
}
