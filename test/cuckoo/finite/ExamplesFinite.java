

package cuckoo.finite;

import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.common.ResultType;
import cuckoo.finite.common.FState;
import cuckoo.finite.runner.DFARunner;
import cuckoo.finite.runner.NFARunner;
import cuckoo.utils.DefaultSymbol;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import junit.framework.Assert;

/**
 *
 * @author eugf
 */
public class ExamplesFinite {
    
    public ExamplesFinite() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

     @Test
     public void endwithExample() {
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
        
        Assert.assertEquals(runner0.getResult().getResultType(), ResultType.ACCECPTED);
        Assert.assertEquals(runner0.getResult().getState(), p2);
        
        Assert.assertEquals(runner1.getResult().getResultType(), ResultType.ACCECPTED);
        Assert.assertEquals(runner1.getResult().getState(), p6);
        
        Assert.assertEquals(runner2.getResult().getResultType(), ResultType.ACCECPTED);
        Assert.assertEquals(runner2.getResult().getState(), p4);
        
        Assert.assertEquals(runner3.getResult().getResultType(), ResultType.REJECTED);
        Assert.assertEquals(runner4.getResult().getResultType(), ResultType.REJECTED);
        Assert.assertEquals(runner5.getResult().getResultType(), ResultType.REJECTED);
     }
     
    @Test
    public void turnstileExample() {
        Symbol coin = new DefaultSymbol("Coin");
        Symbol push = new DefaultSymbol("Push");
        
        FState locked   = new FState("Locked");
        FState unlocked = new FState("Unlocked");
        
        locked.trans().when(coin).goTo(unlocked);
        locked.trans().when(push).goTo(locked);
        unlocked.trans().when(coin).goTo(unlocked);
        unlocked.trans().when(push).goTo(locked);
        
        Word word0 = new Word(push);
        Word word1 = new Word(coin);
        Word word2 = new Word(push, coin);
        Word word3 = new Word(coin, push);
        Word word4 = new Word(coin, push, coin);
        Word word5 = new Word(push, push, coin, push);
        
        DFARunner runner0 = new DFARunner(word0, locked);
        DFARunner runner1 = new DFARunner(word1, locked);
        DFARunner runner2 = new DFARunner(word2, locked);
        DFARunner runner3 = new DFARunner(word3, locked);
        DFARunner runner4 = new DFARunner(word4, locked);
        DFARunner runner5 = new DFARunner(word5, locked);
        
        runner0.compute();
        runner1.compute();
        runner2.compute();
        runner3.compute();
        runner4.compute();
        runner5.compute();
        
        Assert.assertEquals(runner0.getResult().getState(), locked);
        Assert.assertEquals(runner1.getResult().getState(), unlocked);
        Assert.assertEquals(runner2.getResult().getState(), unlocked);
        Assert.assertEquals(runner3.getResult().getState(), locked);
        Assert.assertEquals(runner4.getResult().getState(), unlocked);
        Assert.assertEquals(runner5.getResult().getState(), locked);
    }
    
    @Test
    public void vendingMachineExample() {
        // create all symbols
        Symbol coin025 = new DefaultSymbol("$0.25");
        Symbol coin100 = new DefaultSymbol("$1.00");
        Symbol select  = new DefaultSymbol("Select");
        
        // create all states
        FState coins000 = new FState("$0.00", false);
        FState coins025 = new FState("$0.25", false);
        FState coins050 = new FState("$0.50", false);
        FState coins075 = new FState("$0.75", false);
        FState coins100 = new FState("$1.00", false);
        FState coins125 = new FState("$1.25", true);
        FState coins150 = new FState("$1.50", true);
        FState coins175 = new FState("$1.75", true);
        FState coins200 = new FState("$2.00", true);
        
        // create all transitions
        coins000.trans().when(select).goTo(coins000);
        coins000.trans().when(coin025).goTo(coins025);
        coins000.trans().when(coin100).goTo(coins100);
        
        coins025.trans().when(select).goTo(coins025);
        coins025.trans().when(coin025).goTo(coins050);
        coins025.trans().when(coin100).goTo(coins125);
        
        coins050.trans().when(select).goTo(coins050);
        coins050.trans().when(coin025).goTo(coins075);
        coins050.trans().when(coin100).goTo(coins150);

        coins075.trans().when(select).goTo(coins075);
        coins075.trans().when(coin025).goTo(coins100);
        coins075.trans().when(coin100).goTo(coins175);
        
        coins100.trans().when(select).goTo(coins100);
        coins100.trans().when(coin025).goTo(coins125);
        coins100.trans().when(coin100).goTo(coins200);
        
        coins125.trans().when(select).goTo(coins000);
        coins125.trans().when(coin025).goTo(coins125);
        coins125.trans().when(coin100).goTo(coins125);
        
        coins150.trans().when(select).goTo(coins000);
        coins150.trans().when(coin025).goTo(coins150);
        coins150.trans().when(coin100).goTo(coins150);
        
        coins175.trans().when(select).goTo(coins000);
        coins175.trans().when(coin025).goTo(coins175);
        coins175.trans().when(coin100).goTo(coins175);
        
        coins200.trans().when(select).goTo(coins000);
        coins200.trans().when(coin025).goTo(coins200);
        coins200.trans().when(coin100).goTo(coins200);
        
        Word word0 = new Word(coin025);
        Word word1 = new Word(coin100);
        Word word2 = new Word(coin100, coin025);
        Word word3 = new Word(coin025, coin100);
//        Word word0 = new Word(coin025, coin025, coin025, coin025, coin025);
        
        DFARunner runner0 = new DFARunner(word0, coins000);
        DFARunner runner1 = new DFARunner(word1, coins000);
        DFARunner runner2 = new DFARunner(word2, coins000);
        DFARunner runner3 = new DFARunner(word3, coins000);
        
        Assert.assertEquals(runner0.getResult().getResultType(), ResultType.REJECTED);
        Assert.assertEquals(runner1.getResult().getResultType(), ResultType.REJECTED);
        
        System.out.println(runner1.getResult().getState());
        Assert.assertEquals(runner2.getResult().getResultType(), ResultType.ACCECPTED);
        Assert.assertEquals(runner3.getResult().getResultType(), ResultType.ACCECPTED);
        
        
    }
}
