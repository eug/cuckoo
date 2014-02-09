
package cuckoo.pushdown.common;

import cuckoo.common.Symbol;
import cuckoo.utils.Epsilon;
import java.util.LinkedList;
import java.util.Objects;
import java.util.List;

/**
 *
 * @author eugf
 */
public class PState {
    
    private final String label;
    
    private final boolean finalState;

    private final LinkedList<PTransition> transition;
    
    public PState(String label) {
        this(label, false);
    }
    
    public PState(String label, boolean isFinal) {
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
    
    public PTransition trans() {
        transition.addLast(new PTransition());
        return transition.getLast();
    }

    public List<PTransition> getTransitions() {
        return transition;
    }
    
    public PTransition getTransition(Symbol symbol) {
        for (PTransition t : transition) {
            if (t.getKnownSymbols().contains(symbol)) {
                return t;
            }
        }
        return new PTransition();
    }
    
    public List<PTransition> getEpsilonTransitions() {
        return getTransitions(new Epsilon());
    }
    
    public List<PTransition> getTransitions(Symbol symbol) {
        List<PTransition> trans = new LinkedList<>();
        for (PTransition t : transition) {
            if (t.getKnownSymbols().contains(symbol)) {
                trans.add(t);
            }
        }
        return trans;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.label);
        hash = 23 * hash + (this.finalState ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.transition);
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
        final PState other = (PState) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        if (this.finalState != other.finalState) {
            return false;
        }
        if (!Objects.equals(this.transition, other.transition)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PState{" + "label=" + label + ", finalState=" + finalState + '}';
    }

}