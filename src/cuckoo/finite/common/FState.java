
package cuckoo.finite.common;

import cuckoo.common.Symbol;
import cuckoo.pushdown.common.PTransition;
import cuckoo.utils.Epsilon;
import java.util.List;
import java.util.Objects;
import java.util.LinkedList;

public class FState {
    
    private final String label;
    
    private final boolean finalState;

    private final LinkedList<FTransition> transition;
    
    public FState(String label) {
        this(label, false);
    }
    
    public FState(String label, boolean isFinal) {
        this.label = label;
        this.finalState = isFinal;
        this.transition = new LinkedList<>();
    }

    public boolean isFinalState() {
        return finalState;
    }
    
    public String getLabel() {
        return label;
    }
    
    public FTransition trans() {
        transition.addLast(new FTransition());
        return transition.getLast();
    }
    
    public List<FTransition> getTransitions() {
        return transition;
    }
    
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
