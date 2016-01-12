package operation;

import computation.Computation;
import org.testng.Assert;

/**
 * Created by zawadzkip on 12/27/15.
 */
public class CustomAssert extends Assert {

    public void assertComputationIsEqual(Computation expected, Computation result){
        assertEquals(result.getResult(),expected.getResult(),1E-8);
        assertEquals(result.getName(),expected.getName());
        assertEquals(result.getNumberList(),expected.getNumberList());
        assertEquals(result.computationString(), expected.computationString());
    }
}
