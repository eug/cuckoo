
package cuckoo.pushdown;

import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.utils.Epsilon;
import cuckoo.utils.DefaultSymbol;
import cuckoo.pushdown.common.PState;
import cuckoo.pushdown.runner.DPDARunner;

public class DPDAClassic {
    public static void main(String[] args) {
        // create all symbols
        Symbol zero    = new DefaultSymbol("0");
        Symbol one     = new DefaultSymbol("1");
        Symbol dollar  = new DefaultSymbol("$");
        Symbol epsilon = new Epsilon();
        
        // create all states
        PState q0 = new PState("q0", true);
        PState q1 = new PState("q1");
        PState q2 = new PState("q2");
        PState q3 = new PState("q3", true);
        
        // create all transitions
        q0.trans().when(zero).push(dollar).push(zero).goTo(q1);
        
        q1.trans().when(zero).pop(zero).push(zero).push(zero).goTo(q1);
        q1.trans().when(one).pop(zero).goTo(q2);
        
        q2.trans().when(epsilon).pop(dollar).goTo(q3);
        q2.trans().when(one).pop(zero).goTo(q2);
        
        // define all words
        Word word0 = new Word(zero, zero, zero, one, one, one);
        Word word1 = new Word(zero, zero, zero, one, one);
        Word word2 = new Word(one, one, one, zero, zero, zero);
        
        // run
        DPDARunner runner0 = new DPDARunner(word0, q0);
        DPDARunner runner1 = new DPDARunner(word1, q0);
        DPDARunner runner2 = new DPDARunner(word2, q0);
        
        runner0.compute();
        runner1.compute();
        runner2.compute();
        
        // interpret the result
        System.out.println("Word: " + word0 + " \twas " + runner0.getResult().getResultType());
        System.out.println("Word: " + word1 + " \twas " + runner1.getResult().getResultType());
        System.out.println("Word: " + word2 + " \twas " + runner2.getResult().getResultType());
    }
}
