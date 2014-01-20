
package cuckoo.common;

/**
 *
 * @author eugf
 */
public class DefaultSymbol extends Symbol {

    public DefaultSymbol(String label) {
        super(label);
    }
    
    @Override
    public boolean compute() {
        return true;
    }
    
}
