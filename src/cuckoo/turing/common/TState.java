
package cuckoo.turing.common;

import cuckoo.common.AbstractState;
import cuckoo.common.Symbol;
import java.util.LinkedList;
import java.util.List;

public class TState extends AbstractState {
    
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

    
    @Override
    public List<TTransition> getTransitions() {
        return transitions;
    }
    
    
    @Override
    public TTransition getTransition(Symbol symbol) {
        for (TTransition t: transitions) {
            if (t.getRead().equals(symbol)) {
                return t;
            }
        }
        return new TTransition();
    }

    
    @Override
    public List getTransitions(Symbol symbol) {
        List trans = new LinkedList();
        TTransition t = getTransition(symbol);
        if (t.isValid()) {
            trans.add(t);
        }
        return trans;
    }
    
}
