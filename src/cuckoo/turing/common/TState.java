
package cuckoo.turing.common;

import cuckoo.common.Symbol;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author eugf
 */
public class TState {
    
    private final String label;
    
    private final boolean finalState;
    
    private final LinkedList<TTransition> transitions;

    public TState(String label) {
        this(label, false);
    }

    public TState(String label, boolean finalState) {
        this.label = label;
        this.finalState = finalState;
        this.transitions = new LinkedList<>();
    }
    
    public String getLabel() {
        return label;
    }
    
    public boolean isFinalState() {
        return finalState;
    }
    
    public TTransition trans() {
        transitions.addLast(new TTransition());
        return transitions.getLast();
    }

    public List<TTransition> getTransitions() {
        return transitions;
    }
    
    public TTransition getTransition(Symbol symbol) {
        for (TTransition t: transitions) {
            if (t.getRead().equals(symbol)) {
                return t;
            }
        }
        return new TTransition();
    }
    
}
