
package cuckoo.common;

/**
 * A {@code Transition} is a generic transition object.
 * This class aims to be used internally as an abstraction of all types of transition.
 * @see cuckoo.finite.common.FTransition
 * @see cuckoo.pushdown.common.PTransition
 * @see cuckoo.turing.common.TTransition
 * @author eugf
 * @param <S> The type of the next state. The object type must extend an {@link AbstractState}.
 */
public interface Transition<S extends AbstractState> {
    
    /**
     * Returns the next State.
     * <tt>WARNING:</tt> If the next state wasn't defined,
     * or is defined as {@code null} this method should return {@code null},
     * otherwise the application will crash.
     * @return Next State.
     */
    public S getNext();
    
    /**
     * Set the next State.
     * <tt>WARNING:</tt> By default there is no null-value checking,
     * if a {@code null} parameter is given, the application may crash.
     * @param t Next state.
     */
    public void goTo(S t);
    
    /**
     * Returns <tt>true</tt> if has a next state and knwon symbols defined.
     * Check if the current transition is well defined.
     * @return Returns <tt>true</tt> if has a next state and a knwon symbol defined,
     * otherwise returns <tt>false</tt>
     */
    public boolean isValid();
}
