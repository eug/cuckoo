
package cuckoo.common;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

public class Transition {
    
    // store all possible symbols which can be consumed by this state
    private final Set<Symbol> knownSymbols;
    
    private final List<Symbol> pushable;
    
    private final List<Symbol> popable;
    
    private State nextState;

    public Transition() {
        knownSymbols = new HashSet<>();
        pushable = new ArrayList<>();
        popable = new ArrayList<>();
    }

    Set<Symbol> getKnownSymbols() {
        return knownSymbols;
    }

    List<Symbol> getPushable() {
        return pushable;
    }

    List<Symbol> getPopable() {
        return popable;
    }
    
    State getNextState() {
        return nextState;
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
    
//    public Transition write(Symbol symbol) {
//        return this;
//    }
//    
//    public Transition rigth() {
//        return this;
//    }
//    
//    public Transition left() {
//        return this;
//    }
    
}
