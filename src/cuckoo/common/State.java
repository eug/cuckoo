
package cuckoo.common;


/**
 *
 * @author eugf
 */
public abstract class State {

    protected final String label;
    
    protected final boolean finalState;
    
    public State(String label) {
        this(label, false);
    }
    
    public State(String label, boolean finalState) {
        this.label = label;
        this.finalState = finalState;
    }

    public boolean isFinalState() {
        return finalState;
    }
    
    public String getLabel() {
        return label;
    }
}
