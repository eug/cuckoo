
package cuckoo.finite.runner;

import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.common.Result;
import cuckoo.common.ResultType;
import cuckoo.finite.common.FState;
import cuckoo.finite.common.FTransition;

public class DFARunner {
    
    private final Word word;
    private FState current;
    
    public DFARunner(Word word, FState initial) {
        this.word = word;
        this.current = initial;
    }
    
    public void compute() {
        for (Symbol symbol : word) {
            FTransition trans = current.getTransition(symbol);
            current = trans.getNext();
        }
    }
    
    public Result<FState> getResult() {
        if (current.isFinalState()) {
            return new Result<>(current, ResultType.ACCECPTED);
        } else {
            return new Result<>(current, ResultType.REJECTED);
        }
    }

}
