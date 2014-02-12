
package cuckoo.finite.runner;

import cuckoo.common.IRunner;
import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.common.Result;
import cuckoo.finite.common.FState;
import cuckoo.finite.common.FTransition;

public class DFARunner implements IRunner<FState> {
    
    private final Word word;
    private FState current;
    
    public DFARunner(Word word, FState initial) {
        this.word = word;
        this.current = initial;
    }
    
    @Override
    public void compute() {
        for (Symbol symbol : word) {
            FTransition trans = current.getTransition(symbol);
            current = trans.getNext();
        }
    }
    
    @Override
    public Result<FState> getResult() {
        return new Result<>(current);
    }

}
