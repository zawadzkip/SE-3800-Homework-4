package operation;

import computation.Computation;

import java.util.Arrays;
import java.util.List;

/**
 * Created by gillh on 12/22/2015.
 *
 * This class is equipped to handle square of a number
 */
public class SqrOperation implements Operation{

    /**
     * This method will take a list of numbers and return a computation object of the result
     * @param numbers list of numbers to add
     * @return Computation object
     */
    @Override
    public Computation calc(List<Double> numbers) throws Exception {
        if(numbers != null) {
            double square;
            if (numbers.size() == 1) {                              // if size of list is 1
                square = numbers.get(0) * numbers.get(0);             // multiply number by itself
                return new Computation("SQR", Arrays.toString(numbers.toArray()), square);
            } else {
                throw new IllegalArgumentException("Number list has invalid number of numbers.  Can only have one number");
            }
        }
        else{
            throw new IllegalArgumentException("Number list cannot be NULL");
        }
    }
}
