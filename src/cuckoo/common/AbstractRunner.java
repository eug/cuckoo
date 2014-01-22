
package cuckoo.common;

import cuckoo.utils.DeadState;
import java.util.List;
import java.util.Set;

public abstract class AbstractRunner {
    
    public abstract void compute();
    
    public abstract Result getResult();
    
    // convenience methods to access package protected methods
    
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
    
    protected Result defineResult(State state) {
        if (state.equals(new DeadState())) {
            return new Result(state, ResultType.DEAD_STATE);
        } else if (state.isFinal()) {
            return new Result(state, ResultType.ACCECPTED);
        } else {
            return new Result(state, ResultType.REJECTED);
        }
    }
}
