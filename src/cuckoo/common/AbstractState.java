
package cuckoo.common;

/**
 * This class provides a skeletal implementation of a generic State.
 * The primary purpose of this abstract class is to allow
 * implementations extend the functionality of this ordinary state.
 * @author eugf
 */
public abstract class AbstractState {

    protected final String label;
    
    protected final boolean finalState;
    
    /**
     * Constructs a State with the specified label.
     * By default, the state created by this constructor will not be a Final State.
     * @param label String wich describes what this states means.
     */
    public AbstractState(String label) {
        this(label, false);
    }
    
    /**
     * Constructs a State with the label and the if is a Final State.
     * @param label String wich describes what this states means.
     * @param finalState Define if this State is Final State;
     */
    public AbstractState(String label, boolean finalState) {
        this.label = label;
        this.finalState = finalState;
    }

    /**
     * Returns <tt>true</tt> if the State is a Final State.
     * @return {@code true} if this AbstractState is a Final State,
     * {@code false} otherwise.
     */
    public boolean isFinalState() {
        return finalState;
    }
    
    /**
     * Returns the Label given to this AbstractState.
     * @return Label of this object.
     */
    public String getLabel() {
        return label;
    }
}
