
package cuckoo.vendingmachine;

import cuckoo.common.Word;
import cuckoo.common.State;
import cuckoo.common.Symbol;
import cuckoo.utils.DefaultSymbol;
import cuckoo.runners.DFARunner;

/**
 *
 * @author eugf
 */
public class Main {
    public static void main(String[] args) {
        // create all symbols
        Symbol coin025 = new DefaultSymbol("$0.25");
        Symbol coin100 = new DefaultSymbol("$1.00");
        Symbol select  = new DefaultSymbol("Select");
        
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
        coins000.trans().when(select).goTo(coins000);
        coins000.trans().when(coin025).goTo(coins025);
        coins000.trans().when(coin100).goTo(coins100);
        
        coins025.trans().when(select).goTo(coins025);
        coins025.trans().when(coin025).goTo(coins050);
        coins025.trans().when(coin100).goTo(coins125);
        
        coins050.trans().when(select).goTo(coins050);
        coins050.trans().when(coin025).goTo(coins075);
        coins050.trans().when(coin100).goTo(coins150);

        coins075.trans().when(select).goTo(coins075);
        coins075.trans().when(coin025).goTo(coins100);
        coins075.trans().when(coin100).goTo(coins175);
        
        coins100.trans().when(select).goTo(coins100);
        coins100.trans().when(coin025).goTo(coins125);
        coins100.trans().when(coin100).goTo(coins200);
        
        coins125.trans().when(select).goTo(coins000);
        coins125.trans().when(coin025).goTo(coins125);
        coins125.trans().when(coin100).goTo(coins125);
        
        coins150.trans().when(select).goTo(coins000);
        coins150.trans().when(coin025).goTo(coins150);
        coins150.trans().when(coin100).goTo(coins150);
        
        coins175.trans().when(select).goTo(coins000);
        coins175.trans().when(coin025).goTo(coins175);
        coins175.trans().when(coin100).goTo(coins175);
        
        coins200.trans().when(select).goTo(coins000);
        coins200.trans().when(coin025).goTo(coins200);
        coins200.trans().when(coin100).goTo(coins200);
        
        Word word = new Word(coin025, coin025, coin025, coin025, coin025, select);
        
        // run
        DFARunner runner = new DFARunner(word, coins000);
        runner.compute();
        
        // interpret the result
        if (runner.getResult().getState().isFinal()) {
            System.out.println("Here is your Coke!");
        } else {
            System.out.println("Insert more coins");
        }
        
    }}

