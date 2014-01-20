
package cuckoo.runners;

import cuckoo.common.Word;
import cuckoo.common.State;
import cuckoo.common.Symbol;
import cuckoo.common.Result;
import cuckoo.common.ResultType;
import cuckoo.common.DeadState;
import java.util.List;

public class DFARunner {
    
    private Result result;
    private final Word word;
    private State current;
    
    public DFARunner(State initial, Word word) {
        this.word = word;
        this.current = initial;
        this.result = new Result(new DeadState(), ResultType.DEAD_STATE);
    }
    
    public void compute() {
        
        for (Symbol symbol : word) {
            // FIXME: by definition, DFA's states only return one next state
            List<State> nextState = current.compute(symbol);
            if (!nextState.isEmpty()) {
                current = nextState.get(0);
            }
        }
        
        if (current.equals(new DeadState())) {
            result = new Result(current, ResultType.DEAD_STATE);
        } else if (current.isFinal()) {
            result = new Result(current, ResultType.ACCECPTED);
        } else {
            result = new Result(current, ResultType.REJECTED);
        }
    }
    
    public Result getResult() {
        return result;
    }
}
