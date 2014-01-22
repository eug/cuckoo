
package cuckoo.common;

import java.util.Objects;

public abstract class Symbol {
    
    private final String label;

    public Symbol(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }

    /**
     * Compute a specific implementation of the Symbol.
     * If the symbol has no specific implementation,
     * this method should return True as default.
     * @return 
     */
    public abstract boolean compute();

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.label);
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
        final Symbol other = (Symbol) obj;
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + label + '}';
    }
    
}
