
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
    private State currentState;
    
    public DFARunner(State startState, Word word) {
        this.word = word;
        this.currentState = startState;
        this.result = new Result(new DeadState(), ResultType.DEAD_STATE);
    }
    
    public void compute() {
        
        for (Symbol symbol : word) {
            // FIXME: by definition, DFA's states only return one next state
            List<State> nextState = currentState.compute(symbol);
            if (!nextState.isEmpty()) {
                currentState = nextState.get(0);
            }
        }
        
        if (currentState == null) {
            result = new Result(currentState, ResultType.DEAD_STATE);
        } else if (currentState.isFinal()) {
            result = new Result(currentState, ResultType.ACCECPTED);
        } else {
            result = new Result(currentState, ResultType.REJECTED);
        }
    }
    
    public Result getResult() {
        return result;
    }
}
