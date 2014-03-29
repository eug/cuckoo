
package cuckoo.finite.common;

import cuckoo.common.Symbol;
import cuckoo.common.AbstractState;
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
    

    @Override
    public List<FTransition> getTransitions() {
        return transition;
    }
    
    
    @Override
    public FTransition getTransition(Symbol symbol) {
        for (FTransition t : transition) {
            if (t.getKnownSymbols().contains(symbol)) {
                return t;
            }
        }
        return new FTransition();
    }
    
    
    /**
     * Get all ϵ-transitions.
     * @see #getTransitions(cuckoo.common.Symbol) 
     * @return List of ϵ-transitions.
     */
    public List<FTransition> getEpsilonTransitions() {
        return getTransitions(new Epsilon());
    }
    
    
    @Override
    public List<FTransition> getTransitions(Symbol symbol) {
        List<FTransition> trans = new LinkedList<>();
        
        transition.stream().filter(
            (t) -> t.getKnownSymbols().contains(symbol)
        ).forEach(
            (t) -> { trans.add(t); }
        );

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
