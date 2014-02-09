
package cuckoo.turing.common;

import cuckoo.common.State;
import cuckoo.common.Symbol;
import java.util.LinkedList;
import java.util.List;

public class TState extends State {
    
    private final LinkedList<TTransition> transitions;

    public TState(String label) {
        this(label, false);
    }

    public TState(String label, boolean finalState) {
        super(label, finalState);
        this.transitions = new LinkedList<>();
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
