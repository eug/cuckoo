package cuckoo.testsuite;

import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.common.ResultType;
import cuckoo.pushdown.common.PState;
import cuckoo.pushdown.runner.DPDARunner;
import cuckoo.pushdown.runner.NPDARunner;
import cuckoo.utils.Epsilon;
import cuckoo.utils.DefaultSymbol;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExamplesPushdown {

    @Test
    public void dpdaExample() {
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
        
        Word word0 = new Word(one, one, zero, zero);
        Word word1 = new Word(zero, zero, zero, one, one);
        Word word2 = new Word(zero, zero, zero, one, one, one);
        Word word3 = new Word(zero, zero, one, one);
        Word word4 = new Word(zero, one);
        
        DPDARunner runner0 = new DPDARunner(word0, q0);
        DPDARunner runner1 = new DPDARunner(word1, q0);
        DPDARunner runner2 = new DPDARunner(word2, q0);
        DPDARunner runner3 = new DPDARunner(word3, q0);
        DPDARunner runner4 = new DPDARunner(word4, q0);
        
        runner0.compute();
        runner1.compute();
        runner2.compute();
        runner3.compute();
        runner4.compute();
        
        assertEquals(runner0.getResult().getResultType(), ResultType.REJECTED);
        assertEquals(runner1.getResult().getResultType(), ResultType.REJECTED);
        assertEquals(runner2.getResult().getResultType(), ResultType.ACCECPTED);
        assertEquals(runner3.getResult().getResultType(), ResultType.ACCECPTED);
        assertEquals(runner4.getResult().getResultType(), ResultType.ACCECPTED);
    }
    
    @Test
    public void npdaExample() {
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
        
        runner0.compute();
        runner1.compute();
        runner2.compute();
        runner3.compute();
        
        assertEquals(runner0.getResult().getResultType(), ResultType.ACCECPTED);
        assertEquals(runner1.getResult().getResultType(), ResultType.REJECTED);
        assertEquals(runner2.getResult().getResultType(), ResultType.REJECTED);
        assertEquals(runner3.getResult().getResultType(), ResultType.REJECTED);
    }
}
