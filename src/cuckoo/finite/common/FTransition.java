
package cuckoo.finite.common;

import cuckoo.common.Symbol;
import cuckoo.common.Transition;
import java.util.Objects;
import java.util.LinkedHashSet;

public class FTransition implements Transition<FState> {
    
    private final LinkedHashSet<Symbol> knwonSymbols;
    
    private FState next;

    public FTransition() {
        this.knwonSymbols = new LinkedHashSet<>();
        this.next = new FState("Dead State", false);
    }
    
    public FTransition when(Symbol symbol) {
        knwonSymbols.add(symbol);
        return this;
    }
    
    @Override
    public void goTo(FState state) {
        next = state;
    }
    
    public LinkedHashSet<Symbol> getKnownSymbols() {
        return knwonSymbols;
    }

    @Override
    public FState getNext() {
        return next;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.knwonSymbols);
        hash = 53 * hash + Objects.hashCode(this.next);
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
        final FTransition other = (FTransition) obj;
        if (!Objects.equals(this.knwonSymbols, other.knwonSymbols)) {
            return false;
        }
        if (!Objects.equals(this.next, other.next)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FTransition{" + "knwonSymbols=" + knwonSymbols + ", next=" + next + '}';
    }

}
