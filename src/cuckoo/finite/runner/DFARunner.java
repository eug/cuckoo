
package cuckoo.finite.runner;

import cuckoo.common.IRunner;
import cuckoo.common.Word;
import cuckoo.common.Result;
import cuckoo.finite.common.FState;

/**
 * Implements a Runner for Deterministic Finite Automata.
 * For each Symbol of the given Word, one transition
 * is selected from the current state, until it reaches the
 * end of the {@code Word}.
 * If the current state doesn't recongnizes the current symbol
 * the automaton will reach a dead state;
 * @author eugf
 */
public class DFARunner implements IRunner<FState> {
    
    private final Word word;
    private FState current;
    
    /**
     * Initializes a newly created {@code DFARunner}. 
     * @param word Word of {@code Symbol}.
     * @param initial Set the initial state of the automaton.
     */
    public DFARunner(Word word, FState initial) {
        this.word = word;
        this.current = initial;
    }
    
    @Override
    public void compute() {
        word.stream().map(
            (symbol) -> current.getTransition(symbol)
        ).forEach(
            (trans) -> { current = trans.getNext(); }
        );
    }
    
    @Override
    public Result<FState> getResult() {
        return new Result<>(current);
    }

}
