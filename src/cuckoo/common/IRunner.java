
package cuckoo.common;

import cuckoo.finite.runner.DFARunner;
import cuckoo.finite.runner.NFARunner;
import cuckoo.pushdown.runner.DPDARunner;
import cuckoo.pushdown.runner.NPDARunner;
import cuckoo.turing.runner.DTMRunner;

/**
 * A <code>IRunner</code> is an interface for automaton runner.
 * This class provides uniform way to implement an automaton runner.
 * @see NFARunner
 * @see DFARunner
 * @see NPDARunner
 * @see DPDARunner
 * @see DTMRunner
 * @author eugf
 * @param <S> the type of {@code State} object that this class will use.
 */
public interface IRunner<S extends AbstractState> {
    
    /**
     * Execute each transition of the automaton.
     * The initial state and the {@code Word} object
     * must be specified before execute this method.
     * <tt>WARNING</tt>: Bear in mind that if the given automaton
     * can't compute the given {@code Word}, this method will never
     * reach the end of the computation.
     */
    public void compute();
    
    /**
     * Retuns if the word was accepted or not.
     * @return The {@code Result} object must contain the last
     * state of the computation and if it is a final state.
     */
    public Result<S> getResult();
}
