
package cuckoo.finite;

import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.finite.common.FState;
import cuckoo.utils.DefaultSymbol;
import cuckoo.finite.runner.DFARunner;

public class VendingMachine {
    public static void main(String[] args) {
        // create all symbols
        Symbol coin025 = new DefaultSymbol("$0.25");
        Symbol coin100 = new DefaultSymbol("$1.00");
        Symbol select  = new DefaultSymbol("Select");
        
        // create all states
        FState coins000 = new FState("$0.00", false);
        FState coins025 = new FState("$0.25", false);
        FState coins050 = new FState("$0.50", false);
        FState coins075 = new FState("$0.75", false);
        FState coins100 = new FState("$1.00", false);
        FState coins125 = new FState("$1.25", true);
        FState coins150 = new FState("$1.50", true);
        FState coins175 = new FState("$1.75", true);
        FState coins200 = new FState("$2.00", true);
        
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
        if (runner.getResult().getState().isFinalState()) {
            System.out.println("Here is your Coke!");
        } else {
            System.out.println("Insert more coins");
        }
        
    }
}

