
package cuckoo.finite;

import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.utils.DefaultSymbol;
import cuckoo.finite.common.FState;
import cuckoo.finite.runner.DFARunner;

/**
 *
 * @author eugf
 */
public class Turnstile {
    public static void main(String[] args) {
        // create all symbols
        Symbol coin = new DefaultSymbol("Coin");
        Symbol push = new DefaultSymbol("Push");
        
        // create all states
        FState locked   = new FState("Locked");
        FState unlocked = new FState("Unlocked");
        
        // create all transitions
        locked.trans().when(coin).goTo(unlocked);
        locked.trans().when(push).goTo(locked);
        unlocked.trans().when(coin).goTo(unlocked);
        unlocked.trans().when(push).goTo(locked);
        
        // define a simple word
        Word word = new Word(push, push, coin, push);
        
        // run
        DFARunner runner = new DFARunner(word, locked);
        runner.compute();
        
        // interpret the result
        if (runner.getResult().getState().equals(locked)) {
            System.out.println("Turnstile is working fine!");
        } else {
            System.out.println("Need to fix!");
        }
    }
}
