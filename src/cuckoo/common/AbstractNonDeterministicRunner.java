
package cuckoo.common;

import cuckoo.utils.DeadState;

public abstract class AbstractNonDeterministicRunner extends AbstractRunner {
    
    protected Result result;
    protected State[] initial;
    protected final Word word;
    protected State lastState;
    
    public AbstractNonDeterministicRunner(Word word, State... intial) {
        this.word = word;
        this.initial = intial;
        this.result = new Result(new DeadState(), ResultType.DEAD_STATE);
    }
    
    @Override
    public Result getResult() {
        return defineResult(lastState);
    }
}
