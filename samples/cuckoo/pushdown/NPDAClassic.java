
package cuckoo.pushdown;

import cuckoo.utils.DefaultSymbol;
import cuckoo.utils.Epsilon;
import cuckoo.common.Symbol;
import cuckoo.common.Word;
import cuckoo.pushdown.common.PState;
import cuckoo.pushdown.runner.NPDARunner;

public class NPDAClassic {
    public static void main(String[] args) {
        Symbol zero    = new DefaultSymbol("0");
        Symbol one     = new DefaultSymbol("1");
        Symbol dollar  = new DefaultSymbol("$");
        Symbol epsilon = new Epsilon();
        
        PState q1 = new PState("q1", true);
        PState q2 = new PState("q2");
        PState q3 = new PState("q3");
        PState q4 = new PState("q4", true);
        
        q1.trans().when(epsilon).push(dollar).goTo(q2);
        
        q2.trans().when(zero).push(zero).goTo(q2);
        q2.trans().when(one).pop(zero).goTo(q3);
        
        q3.trans().when(epsilon).pop(dollar).goTo(q4);
        q3.trans().when(one).pop(zero).goTo(q3);
        
        Word word0 = new Word(zero, one);
        Word word1 = new Word(zero, one, one);
        Word word2 = new Word(zero, zero, one);
        Word word3 = new Word(zero, one, zero);
        
        NPDARunner runner0 = new NPDARunner(word0, q1);
        NPDARunner runner1 = new NPDARunner(word1, q1);
        NPDARunner runner2 = new NPDARunner(word2, q1);
        NPDARunner runner3 = new NPDARunner(word3, q1);
        
//        runner0.compute();
        runner1.compute();
//        runner2.compute();
//        runner3.compute();
        
//        System.out.println("Word: " + word0 + ",\tResult:" + runner0.getResult().getResultType());
        System.out.println("Word: " + word1 + ",\tResult:" + runner1.getResult().getResultType());
//        System.out.println("Word: " + word2 + ",\tResult:" + runner2.getResult().getResultType());
//        System.out.println("Word: " + word3 + ",\tResult:" + runner3.getResult().getResultType());
    }
}
