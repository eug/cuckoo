
package cuckoo.common;

import java.util.List;

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
    
    
    /**
     * Get a list of all transitions for this State.
     * @return List of transitions.
     */
    public abstract List getTransitions();
    
    
    /**
     * Get the related transition for the given Symbol.
     * Return the transition object if there is at least one
     * defined transition, otherwise a empty list will be returned.
     * In order to avoid bad behaviours see {@link Transition#isValid()}
     * @param symbol Input symbol
     * @return Returns a empty transition if no transition was found,
     * otherwise returns a valid transition.
     */
    public abstract Transition getTransition(Symbol symbol);
    
    
    /**
     * Get all related transitions for the given Symbol.
     * Return all transitions objects if there is at least one
     * defined transition, otherwise a empty list will be returned.
     * In order to avoid bad behaviours see {@link Transition#isValid()}
     * @param symbol Input symbol
     * @return Returns a empty transition list if no transition was found,
     * otherwise returns a list of transitions.
     */
    public abstract List getTransitions(Symbol symbol);
}
