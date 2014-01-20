package cuckoo.vendingmachine;

import cuckoo.common.Symbol;

/**
 *
 * @author eugf
 */
public class Coin100 extends Symbol {

    public Coin100() {
        super("$1.00");
    }

    @Override
    public boolean compute() {
        return true;
    }
    
}
