
package cuckoo.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class State {
    
    // if true, the state is defined as Final State
    private boolean isFinal;
    
    // define a label (friendly name) for this state
    private String label;

    // store all possible symbols which can be consumed by this state
    private ArrayList<Symbol> definedSymbols;
    
    // store all known states (adjacents)
    private ArrayList<State> nextStates;

    public State(String label) {
        this(label, false);
    }
    
    public State(String label, boolean isFinal) {
        this.label = label;
        this.isFinal = isFinal;
        this.definedSymbols = new ArrayList<>();
        this.nextStates = new ArrayList<>();
    }

    public boolean isFinal() {
        return isFinal;
    }

    public String getLabel() {
        return label;
    }

    public void addTransition(Symbol symbol, State nextState) {
        this.definedSymbols.add(symbol);
        this.nextStates.add(nextState);
    }
    
    public List<State> compute(Symbol symbol) {
        List<State> output = new ArrayList<>();
        
        for (int i = 0; i < definedSymbols.size(); i++) {
            
            Symbol definedSymbol = definedSymbols.get(i);
            
            if (symbol.equals(definedSymbol)) {
                if (definedSymbol.compute()) {
                    output.add(nextStates.get(i));
                }
            }
        }

        if (output.isEmpty()) {
            output.add(new DeadState());
        }
        
        return output;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.isFinal ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.label);
        hash = 97 * hash + Objects.hashCode(this.definedSymbols);
        hash = 97 * hash + Objects.hashCode(this.nextStates);
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
        if (!Objects.equals(this.definedSymbols, other.definedSymbols)) {
            return false;
        }
        if (!Objects.equals(this.nextStates, other.nextStates)) {
            return false;
        }
        return true;
    }
    
    
}
