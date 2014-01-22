
package cuckoo.endwith;

import cuckoo.common.Word;
import cuckoo.common.State;
import cuckoo.common.Symbol;
import cuckoo.utils.DefaultSymbol;
import cuckoo.runners.NFARunner;

public class Main {
    // An NFA for the language of all strings over {a, b, c}
    // that end with one of ab, bc, and ca
    public static void main(String[] args) {
        Symbol a = new DefaultSymbol("a");
        Symbol b = new DefaultSymbol("b");
        Symbol c = new DefaultSymbol("c");
        
        State q0 = new State("q0");
        State p1 = new State("p1");
        State p2 = new State("p2", true);
        State p3 = new State("p3");
        State p4 = new State("p4", true);
        State p5 = new State("p5");
        State p6 = new State("p6", true);
        
        q0.trans().when(a).when(b).when(c).goTo(q0);
        q0.trans().when(a).goTo(p3);
        q0.trans().when(b).goTo(p1);
        q0.trans().when(c).goTo(p5);

        p1.trans().when(c).goTo(p2);
        p3.trans().when(b).goTo(p4);
        p5.trans().when(a).goTo(p6);

        Word word0 = new Word(a, b, c, b, c); // true
        Word word1 = new Word(a, b, c, c, a); // true
        Word word2 = new Word(b, b, c, a, b); // true
        
        Word word3 = new Word(a, b, c, c, b); // false
        Word word4 = new Word(a, b, c, a, c); // false
        Word word5 = new Word(b, b, c, b, a); // false       
        
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
        
        System.out.println(runner0.getResult().getState().getLabel().equals("p2"));
        System.out.println(runner1.getResult().getState().getLabel().equals("p6"));
        System.out.println(runner2.getResult().getState().getLabel().equals("p4"));
        System.out.println(runner3.getResult().getState().getLabel().equals("p1"));
        System.out.println(runner4.getResult().getState().getLabel().equals("p5"));
        System.out.println(runner5.getResult().getState().getLabel().equals("p3"));
    }
}
