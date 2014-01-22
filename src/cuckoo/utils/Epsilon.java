
package cuckoo.utils;

import cuckoo.common.Symbol;
import cuckoo.common.Symbol;

/**
 *
 * @author eugf
 */
public class Epsilon extends Symbol {

    public Epsilon() {
        super("ϵ");
    }
    
    @Override
    public boolean compute() {
        return true;
    }
    
}
