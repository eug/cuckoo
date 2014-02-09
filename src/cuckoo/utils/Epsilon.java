
package cuckoo.utils;

import cuckoo.common.Symbol;

public class Epsilon extends Symbol {

    public Epsilon() {
        super("ϵ");
    }
    
    @Override
    public boolean compute() {
        return true;
    }
    
}
