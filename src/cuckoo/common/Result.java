
package cuckoo.common;

public class Result {

    private final ResultType result;
    private final State state;
    
    public Result(State state, ResultType result) {
        this.result = result;
        this.state = state;
    }

    public ResultType getResult() {
        return result;
    }

    public State getState() {
        return state;
    }
    
}
