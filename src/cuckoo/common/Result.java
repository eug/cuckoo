
package cuckoo.common;

/**
 * A object representing the output after compute a given word.
 * Is usually generated by #getResult() method after execute all computations.
 * @param <S> type of the State in this Result object. The specified type, must
 * be a implementations of {@link AbstractState}.
 * @author eugf
 */
public final class Result<S extends AbstractState> {

    private final S state;
    
    public Result(S state) {
        this.state = state;
    }

    /**
     * Returns if the word was accepted or not.
     * More formally, if the specified state is a final state,
     * this method will return {@code ACCEPTED}, otherwise {@code REJECTED}.<p>
     * <b>WARNING</b>: Sometimes the computation must be interpreted,
     * wich means that, even if the result isn't a final state, the word is accepted.<p>
     * @return {@code ACCEPTED} if the last is state is final, {@code REJECTED} otherwise.
     */
    public ResultType getResultType() {
        return state.isFinalState() ? ResultType.ACCECPTED : ResultType.REJECTED;
    }

    /**
     * Returns the last state of the computation.
     * The State returned will be a {@link AbstractState} implementation.
     * @return Last State of the computation.
     */
    public S getState() {
        return state;
    }
    
}
