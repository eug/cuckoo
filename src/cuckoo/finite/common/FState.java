
package cuckoo.finite.common;

import cuckoo.common.AbstractState;
import cuckoo.common.Symbol;
import cuckoo.common.Transition;
import cuckoo.utils.Epsilon;
import java.util.List;
import java.util.Objects;
import java.util.LinkedList;

public class FState extends AbstractState {

    private final LinkedList<FTransition> transition;

    /**
     * Constructs a Finite State.
     * This State can be used to build NFA and DFA automatons.
     * @param label A label for the state.
     */
    public FState(String label) {
        this(label, false);
    }

    /**
     * Constructs a Finite State.
     * This State can be usesd to build NFA and DFA automatons.
     * @param label A label for this state.
     * @param finalState If {@code True} the State is setted
     * as final state, otherwise a non-final state.
     */
    public FState(String label, boolean finalState) {
        super(label, finalState);
        this.transition = new LinkedList<>();
    }
    
    /**
     * Creates a new transition.
     * @return Last transition created.
     */
    public FTransition trans() {
        transition.addLast(new FTransition());
        return transition.getLast();
    }
    
    /**
     * Get a list of all transitions for this State.
     * @return List of transitions.
     */
    public List<FTransition> getTransitions() {
        return transition;
    }
    
    
    /**
     * Get the related transition for the given Symbol.
     * Return the transition object if there is at least one
     * defined transitions, otherwise a empty list will be returned.
     * In order to avoid bad behaviours see {@link Transition#isValid()}
     * @param symbol
     * @return Returns a empty transition if no transition was found,
     * otherwise returns a valid transition.
     */
    public FTransition getTransition(Symbol symbol) {
        for (FTransition t : transition) {
            if (t.getKnownSymbols().contains(symbol)) {
                return t;
            }
        }
        return new FTransition();
    }
    
    public List<FTransition> getEpsilonTransitions() {
        return getTransitions(new Epsilon());
    }
    
    public List<FTransition> getTransitions(Symbol symbol) {
        List<FTransition> trans = new LinkedList<>();
        
        for (FTransition t : transition) {
            if (t.getKnownSymbols().contains(symbol)) {
                trans.add(t);
            }
        }
        
        return trans;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.finalState ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.label);
        hash = 89 * hash + Objects.hashCode(this.transition);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FState other = (FState) obj;
        if (this.finalState != other.finalState) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (!Objects.equals(this.transition, other.transition)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FState{" + "label=" + label + ", finalState=" + finalState + '}';
    }

}
