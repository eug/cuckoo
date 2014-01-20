
package cuckoo.runners;

import cuckoo.common.Result;
import cuckoo.common.ResultType;
import cuckoo.common.State;
import cuckoo.common.Symbol;
import cuckoo.common.Word;
import java.util.List;
import java.util.concurrent.RecursiveAction;


public class NFARunner extends RecursiveAction {

    private Word word;
    private int offset;
    private Result result;
    private State currentState;
    
    public NFARunner(State startState, Word word) {
        this(startState, word, 0);
    }
    
    private NFARunner(State startState, Word word, int offset) {
        this.result = new Result(null, ResultType.DEAD_STATE);
        this.currentState = startState;
        this.offset = offset;
        this.word = word;
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void compute() {
        
        for (int i = offset; currentState != null && i < word.size(); i++) {
            Symbol symbol = word.get(i);
            
            List<State> states = currentState.compute(symbol);
            
            if (states != null) {
                
                if (states.size() == 1) {
                    currentState = states.get(0);
                    setResult(currentState);
                } else {
                    
                    // at this point we duplicate your machine,
                    // executing it as a depth-first-search algo
                    for (State state : states) {
                        NFARunner runner = new NFARunner(state, word, i);
                        runner.compute();
                        result = runner.getResult();
                    }
                }
            }
        }
        
        setResult(currentState);        
    }

    private void setResult(State currentState) {
        if (currentState != null) {
            if (currentState.isFinal()) {
                result = new Result(currentState, ResultType.ACCECPTED);
            } else {
                result = new Result(currentState, ResultType.REJECTED);
            }
        }
        // else
        // the currentState is null, so we don't need to change anything
        /// since we contruct the object as a null State and DEAD_STATE;
    }
    
    public Result getResult() {
        return result;
    }

}
