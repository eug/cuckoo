
package cuckoo.common;

import java.util.Objects;

/**
 * Define an abstract Symbol object.
 * All {@code Symbol} implementation should extend this class. To implement a
 * {@code Symbol} object, the programmer must implement the {@link #compute()}
 * method, which is used to decide whenever the current {@code Symbol} must be
 * computed or not (read as, ignored or not).<p>
 * Internally all the Symbols comparations are based on {@code label} attribute,
 * then if the label isn't specified correctly the application may crash.
 * @author eugf
 * @see cuckoo.utils.DefaultSymbol
 */
public abstract class Symbol {
    
    private final String label;

    /**
     * Constructs a Symbol with the specified label.
     * @param label Label of this symbol.
     * @throws IllegalArgumentException If {@code null} or
     * empty string is passed as parameter
     */
    public Symbol(String label) {
        if (label == null || label.isEmpty()) {
            throw new IllegalArgumentException("A Symbol object cannot be an empty String.");
        }
        this.label = label;
    }
    
    /**
     * Returns the label.
     * @return Returns the label of the current Symbol.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Compute a specific implementation of the Symbol.
     * If the symbol has no specific implementation,
     * this method should return True as default.
     * @return Return {@code False} if this symbol should be ignored,
     * return {@code True} if is a valid symbol.
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
        return '{' + label + '}';
    }
    
}
