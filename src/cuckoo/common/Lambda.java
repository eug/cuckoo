
package cuckoo.common;

import cuckoo.common.Symbol;

/**
 *
 * @author eugf
 */
public class Lambda extends Symbol {

    public Lambda() {
        super("λ");
    }
    
    @Override
    public boolean compute() {
        return true;
    }
    
}
