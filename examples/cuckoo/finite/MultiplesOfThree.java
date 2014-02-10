
package cuckoo.finite;

import cuckoo.common.Symbol;
import cuckoo.common.Word;
import cuckoo.finite.common.FState;
import cuckoo.finite.runner.DFARunner;
import cuckoo.utils.DefaultSymbol;

public class MultiplesOfThree {
    public static void main(String[] args) {
        // create all symbols
        Symbol one  = new DefaultSymbol("1");
        Symbol zero = new DefaultSymbol("0");
        
        // create all states
        FState s0 = new FState("S0", true);
        FState s1 = new FState("S1");
        FState s2 = new FState("S2");
        
        // create all transitions
        s0.trans().when(zero).goTo(s0);
        s0.trans().when(one).goTo(s1);
        s1.trans().when(zero).goTo(s2);
        s1.trans().when(one).goTo(s0);
        s2.trans().when(zero).goTo(s1);
        s2.trans().when(one).goTo(s2);
        
        // define all words
        Word word0 = new Word(zero, one, one); // three
        Word word1 = new Word(one, one, zero); // six
        Word word2 = new Word(one, zero, zero, one); // nine
        Word word3 = new Word(zero); // zero (must be accepted)
        Word word4 = new Word(one); // one
        
        // run
        DFARunner runner0 = new DFARunner(word0, s0);
        DFARunner runner1 = new DFARunner(word1, s0);
        DFARunner runner2 = new DFARunner(word2, s0);
        DFARunner runner3 = new DFARunner(word3, s0);
        DFARunner runner4 = new DFARunner(word4, s0);
        
        // interpret the result
        runner0.compute();
        runner1.compute();
        runner2.compute();
        runner3.compute();
        runner4.compute();
        
        System.out.println("Word: " + word0 + "\t\t was " + runner0.getResult().getResultType());
        System.out.println("Word: " + word1 + "\t\t was " + runner1.getResult().getResultType());
        System.out.println("Word: " + word2 + "\t was " + runner2.getResult().getResultType());
        System.out.println("Word: " + word3 + "\t\t\t was " + runner3.getResult().getResultType());
        System.out.println("Word: " + word4 + "\t\t\t was " + runner4.getResult().getResultType());
    }
}
