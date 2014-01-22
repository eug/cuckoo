
package cuckoo.classicaldpda;

import cuckoo.common.Word;
import cuckoo.common.State;
import cuckoo.common.Symbol;
import cuckoo.utils.Epsilon;
import cuckoo.utils.DefaultSymbol;
import cuckoo.runners.DPDARunner;

/**
 *
 * @author eugf
 */
public class Main {
    public static void main(String[] args) {
        Symbol zero    = new DefaultSymbol("0");
        Symbol one     = new DefaultSymbol("1");
        Symbol dollar  = new DefaultSymbol("$");
        Symbol epsilon = new Epsilon();
        
        State q0 = new State("q0", true);
        State q1 = new State("q1");
        State q2 = new State("q2");
        State q3 = new State("q3", true);
        
        q0.trans().when(zero).push(dollar).push(zero).goTo(q1);
        q1.trans().when(zero).pop(zero).push(zero).push(zero).goTo(q1);
        q1.trans().when(one).pop(zero).goTo(q2);
        q2.trans().when(one).pop(zero).goTo(q2);
        q2.trans().when(epsilon).pop(dollar).goTo(q3);
        
        Word word0 = new Word(zero, zero, zero, one, one, one);
        Word word1 = new Word(zero, zero, zero, one, one);
        
        DPDARunner runner0 = new DPDARunner(word0, q0);
        DPDARunner runner1 = new DPDARunner(word1, q0);
        
        runner0.compute();
        runner1.compute();
        
        if (!runner0.getResult().getState().isFinal()) System.out.println("ERROR");
        if (runner1.getResult().getState().isFinal())  System.out.println("ERROR");
    }
}
