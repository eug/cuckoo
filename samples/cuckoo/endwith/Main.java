
package cuckoo.endwith;

import cuckoo.common.DefaultSymbol;
import cuckoo.common.State;
import cuckoo.common.Symbol;
import cuckoo.common.Word;
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
        
        q0.addTransition(a, q0);
        q0.addTransition(b, q0);
        q0.addTransition(c, q0);
        q0.addTransition(a, p3);
        q0.addTransition(b, p1);
        q0.addTransition(c, p5);
        
        p1.addTransition(c, p2);
        p3.addTransition(b, p4);
        p5.addTransition(a, p6);
        
        Word word0 = new Word(a, b, c, b, c); // true
        Word word1 = new Word(a, b, c, c, a); // true
        Word word2 = new Word(b, b, c, a, b); // true
        
        Word word3 = new Word(a, b, c, c, b); // false
        Word word4 = new Word(a, b, c, a, c); // false
        Word word5 = new Word(b, b, c, b, a); // false       
        
        NFARunner runner0 = new NFARunner(q0, word0);
        NFARunner runner1 = new NFARunner(q0, word1);
        NFARunner runner2 = new NFARunner(q0, word2);
        NFARunner runner3 = new NFARunner(q0, word3);
        NFARunner runner4 = new NFARunner(q0, word4);
        NFARunner runner5 = new NFARunner(q0, word5);
        
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
