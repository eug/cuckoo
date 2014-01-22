
package cuckoo.common;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.LinkedList;

public class State {
    
    // if true, the state is defined as Final State
    private boolean isFinal;
    
    // define a label (friendly name) for this state
    private final String label;

    private final List<Transition> transition;
    
    private final List<State> output;

    // just a cached reference
    private final Symbol epsilon;
    
    public State(String label) {
        this(label, false);
    }
    
    public State(String label, boolean isFinal) {
        this.label = label;
        this.isFinal = isFinal;
        this.epsilon = new Epsilon();
        this.output = new ArrayList<>();
        this.transition = new ArrayList<>();
    }

    public boolean isFinal() {
        return isFinal;
    }

    public String getLabel() {
        return label;
    }
    
    public Transition trans() {
        transition.add(new Transition());
        return transition.get(transition.size() - 1);
    }
    
    public List<State> compute(Symbol symbol) {
        output.clear();
        
        // select all transitions related to this state/symbol
        List<Transition> trans = getTransitions(symbol);
        
        // get all possible states
        for (Transition t : trans) {
            output.add(t.getNextState());
        }
        
        // if there's no transition related to this state/symbol,
        // the next state MUST be a DEAD_STATE
        if (trans.isEmpty()) {
            output.add(new DeadState());
        }
        
        return output;
    }

    public List<Transition> getTransitions(Symbol symbol) {
        List<Transition> trans = new LinkedList<>();
        for (Transition t : transition) {
            for (Symbol s : t.getKnownSymbols()) {
                if (s.equals(symbol) || s.equals(epsilon)) {
                    trans.add(t);
                }
            }
        }
        return trans;
    }
    
    public List<Transition> getTransitions() {
        return transition;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.isFinal ? 1 : 0);
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
        final State other = (State) obj;
        if (this.isFinal != other.isFinal) {
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
        return "State{" + "label=" + label + ", transition=" + transition + '}';
    }
    
}
