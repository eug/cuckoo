
package cuckoo.classicalnpda;

import cuckoo.utils.DefaultSymbol;
import cuckoo.utils.Epsilon;
import cuckoo.common.State;
import cuckoo.common.Symbol;
import cuckoo.common.Word;
//import cuckoo.runners.NPDARunner;

public class Main {
    public static void main(String[] args) {
        Symbol zero = new DefaultSymbol("0");
        Symbol one  = new DefaultSymbol("1");
        Symbol dollar  = new DefaultSymbol("$");
        Symbol epsilon = new Epsilon();
        
        State q1 = new State("q1", true);
        State q2 = new State("q2");
        State q3 = new State("q3");
        State q4 = new State("q4", true);
        
        q1.trans().when(epsilon).push(dollar).goTo(q2);
        q2.trans().when(zero).push(zero).goTo(q2);
        q2.trans().when(one).pop(zero).goTo(q3);
        q3.trans().when(one).pop(zero).goTo(q3);
        q4.trans().when(epsilon).pop(dollar).goTo(q4);
        
        Word word = new Word(zero, zero, one, one, zero, zero);
        
//        NPDARunner runner = new NPDARunner(word, q1);
//        runner.compute();
    }
}
