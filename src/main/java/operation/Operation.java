package operation;

import computation.Computation;

import java.util.List;

/**
 * Created by gillh on 12/20/15.
 *
 * This Operation interface will define the methods necessary for any kind of operation
 */
public interface Operation {
    public Computation calc(List<Double> numbers) throws Exception;
}
