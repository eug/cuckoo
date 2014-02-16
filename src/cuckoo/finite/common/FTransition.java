
package cuckoo.finite.common;

import cuckoo.common.Symbol;
import cuckoo.common.Transition;
import cuckoo.utils.DefaultSymbol;
import java.util.HashSet;
import java.util.Objects;
import java.util.LinkedHashSet;

public class FTransition implements Transition<FState> {
    
    private final LinkedHashSet<Symbol> knwonSymbols;
    
    private FState next;

    /**
     * Initializes a newly created {@code FTransition}.
     */
    public FTransition() {
        this.knwonSymbols = new LinkedHashSet<>();
        this.next = new FState("Dead State", false);
    }
    
    
    /**
     * Create a trigger for the current {@code FTransition}.
     * When the specified symbol is read from the Word, this
     * transition will be executed. Internally the specified {@code String}
     * will be converted into a {@code Symbol} object.
     * @param symbol 
     * @return This transition.
     */
    public FTransition when(String symbol) {
        return when(new DefaultSymbol(symbol));
    }
    
    
    /**
     * Create a trigger for the current {@code FTransition}.
     * When the specified symbol is read from the Word, this
     * transition will be executed.
     * @param symbol 
     * @return This transition.
     */
    public FTransition when(Symbol symbol) {
        knwonSymbols.add(symbol);
        return this;
    }
    
    /**
     * Returns a <tt>Set</tt> of Symbols associated with
     * current <tt>Transition</tt>.
     * @return Returns a {@code HashSet} of Symbols that are used to
     * trigger the current transition, a empty {@code HashSet} can be
     * returned if no Symbol was associated.
     */
    public HashSet<Symbol> getKnownSymbols() {
        return knwonSymbols;
    }
    
    
    @Override
    public void goTo(FState state) {
        next = state;
    }
    

    @Override
    public FState getNext() {
        return next;
    }
    
    
    @Override
    public boolean isValid() {
        return next != null && !knwonSymbols.isEmpty();
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
