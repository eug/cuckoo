package cuckoo.vendingmachine;

import cuckoo.common.Symbol;

/**
 *
 * @author eugf
 */
public class Select extends Symbol {

    public Select() {
        super("select");
    }

    @Override
    public boolean compute() {
        return true;
    }
    
}
