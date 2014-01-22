
package cuckoo.common;

import cuckoo.utils.DeadState;

public abstract class AbstractDeterministicRunner extends AbstractRunner {
    
    protected Result result;
    protected State current;
    protected final Word word;
    
    public AbstractDeterministicRunner(Word word, State intial) {
        this.word = word;
        this.current = intial;
        this.result = new Result(new DeadState(), ResultType.DEAD_STATE);
    }
    
    @Override
    public Result getResult() {
        return defineResult(current);
    }
    
}
