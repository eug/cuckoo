
package cuckoo.turnstile;

import cuckoo.common.State;
import cuckoo.common.Symbol;
import cuckoo.common.Word;
import cuckoo.runners.DFARunner;

/**
 *
 * @author eugf
 */
public class Main {
    public static void main(String[] args) {
        // create all symbols
        Symbol coin = new Coin();
        Symbol push = new Push();
        
        // create all states
        State locked   = new State("Locked");
        State unlocked = new State("Unlocked");
        
        // create all transitions
        locked.addTransition(coin, unlocked);
        locked.addTransition(push, locked);
        unlocked.addTransition(coin, unlocked);
        unlocked.addTransition(push, locked);
        
        // define a simple word
        Word word = new Word(push, push, coin, push);
        
        // run
        DFARunner runner = new DFARunner(locked, word);
        runner.compute();
        
        // interpret the result
        if (runner.getResult().getState().equals(locked)) {
            System.out.println("Turnstile is working fine!");
        } else {
            System.out.println("Need to fix!");
        }
    }
}
