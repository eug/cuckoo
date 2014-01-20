package cuckoo.turnstile;


import cuckoo.common.Symbol;

/**
 *
 * @author eugf
 */
public class Coin extends Symbol {

    public Coin() {
        super("Coin");
    }

    @Override
    public boolean compute() {
        return true;
    }
    
}
