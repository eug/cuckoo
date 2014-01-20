
package cuckoo.common;

import java.util.HashSet;
import java.util.Set;

public class Transition {
    
    // store all possible symbols which can be consumed by this state
    private final Set<Symbol> knownSymbols;
    
    private final Set<Symbol> pushable;
    
    private final Set<Symbol> popable;
    
    private State nextState;

    public Transition() {
        knownSymbols = new HashSet<>();
        pushable = new HashSet<>();
        popable = new HashSet<>();
    }

    Set<Symbol> getKnownSymbols() {
        return knownSymbols;
    }

    State getNextState() {
        return nextState;
    }

    Set<Symbol> getPushable() {
        return pushable;
    }

    Set<Symbol> getPopable() {
        return popable;
    }
    
    public Transition when(Symbol symbol) {
        knownSymbols.add(symbol);
        return this;
    }
    
    public Transition goTo(State state) {
        this.nextState = state;
        return this;
    }
    
    public Transition push(Symbol symbol) {
        pushable.add(symbol);
        return this;
    }
    
    public Transition pop(Symbol symbol) {
        popable.add(symbol);
        return this;
    }
    
}
