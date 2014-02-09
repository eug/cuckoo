
package cuckoo.pushdown;

import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.utils.Epsilon;
import cuckoo.utils.DefaultSymbol;
import cuckoo.pushdown.common.PState;
import cuckoo.pushdown.runner.DPDARunner;

/**
 *
 * @author eugf
 */
public class DPDAClassic {
    public static void main(String[] args) {
        Symbol zero    = new DefaultSymbol("0");
        Symbol one     = new DefaultSymbol("1");
        Symbol dollar  = new DefaultSymbol("$");
        Symbol epsilon = new Epsilon();
        
        PState q0 = new PState("q0", true);
        PState q1 = new PState("q1");
        PState q2 = new PState("q2");
        PState q3 = new PState("q3", true);
        
        q0.trans().when(zero).push(dollar).push(zero).goTo(q1);
        
        q1.trans().when(zero).pop(zero).push(zero).push(zero).goTo(q1);
        q1.trans().when(one).pop(zero).goTo(q2);
        
        q2.trans().when(epsilon).pop(dollar).goTo(q3);
        q2.trans().when(one).pop(zero).goTo(q2);
        
        Word word0 = new Word(zero, zero, zero, one, one, one);
        Word word1 = new Word(zero, zero, zero, one, one);
        
        DPDARunner runner0 = new DPDARunner(word0, q0);
        DPDARunner runner1 = new DPDARunner(word1, q0);
        
        runner0.compute();
        runner1.compute();
        
        System.out.println("Word: " + word0 + ",\tResult:" + runner0.getResult().getResultType());
        System.out.println("Word: " + word1 + ",\tResult:" + runner1.getResult().getResultType());
    }
}
