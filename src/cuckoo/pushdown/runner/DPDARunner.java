
package cuckoo.pushdown.runner;

import cuckoo.common.Result;
import cuckoo.common.ResultType;
import cuckoo.common.Word;
import cuckoo.common.Symbol;
import cuckoo.pushdown.common.PState;
import cuckoo.pushdown.common.PTransition;
import java.util.Stack;

public class DPDARunner {
    
    private final Stack<Symbol> stack;
    private PState current;
    private final Word word;
    
    public DPDARunner(Word word, PState initial) {
        this.current = initial;
        this.stack = new Stack();
        this.word = word;
    }

    /**
     * Compute!.
     * 
     * for each symbol of word do
     *      1) compute the given symbol, and return all possible transitions
     *      2) first, pop symbols from stack; second, push symbols to stack
     *      3) move to next state if something changed 
     */
    public void compute() {
        
        for (Symbol symbol : word) {
            
            computeTransition(current.getTransition(symbol));
            
            for (PTransition t : current.getEpsilonTransitions()) {
                // if more than one epsilon transition is given,
                // the automaton will change the current state to
                // the first valid epsilon transition.
                computeTransition(t);
            }
        }

    }

    private void computeTransition(PTransition trans) {
        boolean changed = false;
        
        for (Symbol s : trans.toPop()) {
            if (!stack.isEmpty() && s.equals(stack.peek())) {
                stack.pop();
                changed = true;
            }
        }
        
        // after we pop out some symbols, now we can push some new ones
        for (Symbol s : trans.toPush()) {
            stack.push(s);
            changed = true;
        }
        
        // update the cursor to the next state
        // we only transfer to another state,
        // iff it was possible to pop or push a symbol
        if (changed) {
            current = trans.getNext();
        }
    }
    
    public Result<PState> getResult() {
        return new Result<>(current);
    }
    
}
