
package cuckoo.common;

import cuckoo.utils.DeadState;
import java.util.List;
import java.util.Set;

public abstract class AbstractDeterministicRunner {
    
    protected State current;
    protected Result result;
    protected final Word word;
    
    public AbstractDeterministicRunner(Word word, State intial) {
        this.word = word;
        this.current = intial;
        this.result = new Result(new DeadState(), ResultType.DEAD_STATE);
    }
 
    public abstract void compute();
    
    protected Set<Symbol> getKnownSymbols(Transition transition) {
        return transition.getKnownSymbols();
    }

    protected List<Symbol> getPushable(Transition transition) {
        return transition.getPushable();
    }

    protected List<Symbol> getPopable(Transition transition) {
        return transition.getPopable();
    }
    
    protected State getNextState(Transition transition) {
        return transition.getNextState();
    }
    
    protected void setResult() {
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
