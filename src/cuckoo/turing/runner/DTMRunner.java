
package cuckoo.turing.runner;

import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.common.Result;
import cuckoo.common.IRunner;
import cuckoo.turing.common.TState;
import cuckoo.turing.common.TTransition;

/**
 * Implements a Runner for Deterministic Turing Machine.
 * @author eugf
 */
public class DTMRunner implements IRunner<TState> {

    private final TState initial;
    private final Word word;
    private final int offset;
    private Result result;
    
    public DTMRunner(Word word, TState initial) {
        this(word, 0, initial);
    }
    
    public DTMRunner(Word word, int offset, TState initial) {
        this.word = word;
        this.offset = offset;
        this.initial = initial;
        this.result = new Result(new TState("Dead State"));
    }
    
    @Override
    public void compute() {
        int i = offset;
        TState current = initial;
        
        while (i < word.size() && !current.isFinalState()) {
            
            Symbol symbol = word.get(i);
            
            TTransition t = current.getTransition(symbol);

            if (t.getRead().equals(symbol)) {
                
                // write on the tape
                word.set(i, t.getWrite());
                
                // goes to the next state
                current = t.getNext();
                
                // move the head
                i += t.getMove();
            }
            
        }
        
        result = new Result(current);
    }
    
    @Override
    public Result getResult() {
        return result;
    }
    
}
