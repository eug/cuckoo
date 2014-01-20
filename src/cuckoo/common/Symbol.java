
package cuckoo.common;

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
    
}
