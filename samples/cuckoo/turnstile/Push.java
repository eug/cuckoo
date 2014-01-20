package cuckoo.turnstile;

import cuckoo.common.Symbol;

/**
 *
 * @author eugf
 */
public class Push extends Symbol {

    public Push() {
        super("Push");
    }

    @Override
    public boolean compute() {
        return true;
    }
    
}