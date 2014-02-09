
package cuckoo.common;

public class Result<S> {

    private final ResultType result;
    private final S state;
    
    public Result(S state, ResultType result) {
        this.result = result;
        this.state = state;
    }

    public ResultType getResultType() {
        return result;
    }

    public S getState() {
        return state;
    }
    
}
