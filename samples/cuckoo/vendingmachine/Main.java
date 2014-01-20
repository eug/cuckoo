
package cuckoo.vendingmachine;

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
        Symbol coin025 = new Coin025();
        Symbol coin100 = new Coin100();
        Symbol select  = new Select();
        
        // create all states
        State coins000 = new State("$0.00", false);
        State coins025 = new State("$0.25", false);
        State coins050 = new State("$0.50", false);
        State coins075 = new State("$0.75", false);
        State coins100 = new State("$1.00", false);
        State coins125 = new State("$1.25", true);
        State coins150 = new State("$1.50", true);
        State coins175 = new State("$1.75", true);
        State coins200 = new State("$2.00", true);
        
        
        // create all transitions
        coins000.addTransition(select,  coins000);
        coins000.addTransition(coin025, coins025);
        coins000.addTransition(coin100, coins100);
        
        coins025.addTransition(select,  coins025);
        coins025.addTransition(coin025, coins050);
        coins025.addTransition(coin100, coins125);
        
        coins050.addTransition(select,  coins050);
        coins050.addTransition(coin025, coins075);
        coins050.addTransition(coin100, coins150);

        coins075.addTransition(select,  coins075);
        coins075.addTransition(coin025, coins100);
        coins075.addTransition(coin100, coins175);
        
        coins100.addTransition(select,  coins100);
        coins100.addTransition(coin025, coins125);
        coins100.addTransition(coin100, coins200);
        
        coins125.addTransition(select,  coins000);
        coins125.addTransition(coin025, coins125);
        coins125.addTransition(coin100, coins125);
        
        coins150.addTransition(select,  coins000);
        coins150.addTransition(coin025, coins150);
        coins150.addTransition(coin100, coins150);
        
        coins175.addTransition(select,  coins000);
        coins175.addTransition(coin025, coins175);
        coins175.addTransition(coin100, coins175);
        
        coins200.addTransition(select,  coins000);
        coins200.addTransition(coin025, coins200);
        coins200.addTransition(coin100, coins200);
        
        Word word = new Word(coin025, coin025, coin025, coin025, coin025, select);
        
        // run
        DFARunner runner = new DFARunner(coins000, word);
        runner.compute();
        
        // interpret the result
        if (runner.getResult().getState().isFinal()) {
            System.out.println("Here is your Coke!");
        } else {
            System.out.println("Insert more coins");
        }
        
    }
}
