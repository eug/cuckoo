
package cuckoo.pushdown.common;

import cuckoo.common.Symbol;
import cuckoo.common.Transition;
import cuckoo.utils.DefaultSymbol;
import java.util.List;
import java.util.Objects;
import java.util.LinkedList;
import java.util.LinkedHashSet;

public class PTransition implements Transition<PState> {
    
    private final LinkedHashSet<Symbol> knwonSymbols;
    
    private final List<Symbol> pushable;
    
    private final List<Symbol> popable;
    
    private PState next;

    public PTransition() {
        this.knwonSymbols = new LinkedHashSet<>();
        this.pushable = new LinkedList<>();
        this.popable = new LinkedList<>();
        this.next = new PState("Dead State", false);
    }
    
    public PTransition when(String symbol) {
        return when(new DefaultSymbol(symbol));
    }
    
    public PTransition when(Symbol symbol) {
        knwonSymbols.add(symbol);
        return this;
    }
    
    public PTransition pop(String symbol) {
        return pop(new DefaultSymbol(symbol));
    }
    
    public PTransition pop(Symbol symbol) {
        popable.add(symbol);
        return this;
    }
    
    public PTransition push(String symbol) {
        return push(new DefaultSymbol(symbol));
    }
    
    public PTransition push(Symbol symbol) {
        pushable.add(symbol);
        return this;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public void goTo(PState state) {
        next = state;
    }
    
    public LinkedHashSet<Symbol> getKnownSymbols() {
        return knwonSymbols;
    }
    
    public List<Symbol> toPop() {
        return popable;
    }
    
    public List<Symbol> toPush() {
        return pushable;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public PState getNext() {
        return next;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isValid() {
        return next != null && !knwonSymbols.isEmpty();
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.knwonSymbols);
        hash = 71 * hash + Objects.hashCode(this.pushable);
        hash = 71 * hash + Objects.hashCode(this.popable);
        hash = 71 * hash + Objects.hashCode(this.next);
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
        final PTransition other = (PTransition) obj;
        if (!Objects.equals(this.knwonSymbols, other.knwonSymbols)) {
            return false;
        }
        if (!Objects.equals(this.pushable, other.pushable)) {
            return false;
        }
        if (!Objects.equals(this.popable, other.popable)) {
            return false;
        }
        if (!Objects.equals(this.next, other.next)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PTransition{" + "knwonSymbols=" + knwonSymbols + ", pushable=" + pushable + ", popable=" + popable + ", nextState=" + next + '}';
    }
    
}
