package cuckoo.vendingmachine;

import cuckoo.common.Symbol;

/**
 *
 * @author eugf
 */
public class Coin025 extends Symbol {

    public Coin025() {
        super("$0.25");
    }

    @Override
    public boolean compute() {
        return true;
    }
    
}
