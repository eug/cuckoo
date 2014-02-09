
package cuckoo.turing.runner;

import cuckoo.common.Result;
import cuckoo.common.ResultType;
import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.turing.common.TState;
import cuckoo.turing.common.TTransition;

/**
 *
 * @author eugf
 */
public class DTMRunner {

    private final TState initial;
    private final Word word;
    private final int offset;
    private Result result;
    
    public DTMRunner(TState initial, Word word) {
        this(initial, word, 0);
    }
    
    public DTMRunner(TState initial, Word word, int offset) {
        this.initial = initial;
        this.word = word;
        this.offset = offset;
        this.result = new Result(null, ResultType.REJECTED);
    }
    
    public void compute() {
        int i = offset;
        TState current = initial;
        
        while (i < word.size() && !current.isFinalState()) {
            
            Symbol symbol = word.get(i);
            
            // select wich transition will be executed
            TTransition t = current.getTransition(symbol);
            
            // only execute the next instructions
            // iff the expected symbol it's readed
            if (t.getRead().equals(symbol)) {
                
                // write on the tape
                word.set(i, t.getWrite());
                
                // goes to the next state
                current = t.getNext();
                
                // move the head
                i += t.getMove();
            }
            
        }
        
        setResult(current);
    }
    
    private void setResult(TState state) {
        if (state.isFinalState()) {
            result = new Result(null, ResultType.ACCECPTED);
        } else {
            result = new Result(null, ResultType.REJECTED);
        }
    }
    
    public Result getResult() {
        return result;
    }
    
}
