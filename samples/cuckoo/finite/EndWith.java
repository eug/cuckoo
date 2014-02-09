
package cuckoo.finite;

import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.finite.common.FState;
import cuckoo.finite.runner.NFARunner;
import cuckoo.utils.DefaultSymbol;

public class EndWith {
    // An NFA for the language of all strings over {a, b, c}
    // that end with one of ab, bc, and ca
    public static void main(String[] args) {
        Symbol a = new DefaultSymbol("a");
        Symbol b = new DefaultSymbol("b");
        Symbol c = new DefaultSymbol("c");
        
        FState q0 = new FState("q0");
        FState p1 = new FState("p1");
        FState p2 = new FState("p2", true);
        FState p3 = new FState("p3");
        FState p4 = new FState("p4", true);
        FState p5 = new FState("p5");
        FState p6 = new FState("p6", true);
       
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

        System.out.println(runner0.getResult().getState().getLabel());
        System.out.println(runner1.getResult().getState().getLabel());
        System.out.println(runner2.getResult().getState().getLabel());
//        Assert(runner0.getResult(), ResultType.ACCECPTED, "p2");
//        Assert(runner1.getResult(), ResultType.ACCECPTED, "p6");
//        Assert(runner2.getResult(), ResultType.ACCECPTED, "p4");
        
//        Assert(runner3.getResult(), ResultType.DEAD_STATE, "Dead State");
//        Assert(runner4.getResult(), ResultType.DEAD_STATE, "Dead State");
//        Assert(runner5.getResult(), ResultType.DEAD_STATE, "Dead State");
    }
    
//    private static void Assert(Result rt, ResultType rtExpected, String strExpected) {
//        if (rt.getResultType() != rtExpected) {
//            throw new RuntimeException("Assertion error : " + rt + " should be " + rtExpected);
//        }
//        if (rt.getState().getLabel().compareTo(strExpected) != 0) {
//            throw new RuntimeException("Assertion error : " + rt + " should be " + strExpected);
//        }
//    }
}
