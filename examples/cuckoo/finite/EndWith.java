
package cuckoo.finite;

import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.finite.common.FState;
import cuckoo.finite.runner.NFARunner;
import cuckoo.utils.DefaultSymbol;

public class EndWith {
    public static void main(String[] args) {
        // create all symbols
        Symbol a = new DefaultSymbol("a");
        Symbol b = new DefaultSymbol("b");
        Symbol c = new DefaultSymbol("c");
        
        // create all states
        FState q0 = new FState("q0");
        FState p1 = new FState("p1");
        FState p2 = new FState("p2", true);
        FState p3 = new FState("p3");
        FState p4 = new FState("p4", true);
        FState p5 = new FState("p5");
        FState p6 = new FState("p6", true);
        
        // create all transitions
        q0.trans().when(a).when(b).when(c).goTo(q0);
        q0.trans().when(a).goTo(p3);
        q0.trans().when(b).goTo(p1);
        q0.trans().when(c).goTo(p5);

        p1.trans().when(c).goTo(p2);
        p3.trans().when(b).goTo(p4);
        p5.trans().when(a).goTo(p6);

        // define all words
        Word word0 = new Word(a, b, c, b, c);
        Word word1 = new Word(a, b, c, c, a);
        Word word2 = new Word(b, b, c, a, b);
        Word word3 = new Word(a, b, c, c, b);
        Word word4 = new Word(a, b, c, a, c);
        Word word5 = new Word(b, b, c, b, a);      
        
        // run
        NFARunner runner0 = new NFARunner(word0, q0);
        NFARunner runner1 = new NFARunner(word1, q0);
        NFARunner runner2 = new NFARunner(word2, q0);
        NFARunner runner3 = new NFARunner(word3, q0);
        NFARunner runner4 = new NFARunner(word4, q0);
        NFARunner runner5 = new NFARunner(word5, q0);
        
        runner0.compute();
        runner1.compute();
        runner2.compute();
        runner3.compute();
        runner4.compute();
        runner5.compute();
        
        // interpret the result
        System.out.println("Word: " + word0 + "\twas " + runner0.getResult().getResultType());
        System.out.println("Word: " + word1 + "\twas " + runner1.getResult().getResultType());
        System.out.println("Word: " + word2 + "\twas " + runner2.getResult().getResultType());
        System.out.println("Word: " + word3 + "\twas " + runner3.getResult().getResultType());
        System.out.println("Word: " + word4 + "\twas " + runner4.getResult().getResultType());
        System.out.println("Word: " + word5 + "\twas " + runner5.getResult().getResultType());
    }
}
