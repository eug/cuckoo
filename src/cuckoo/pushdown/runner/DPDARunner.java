
package cuckoo.pushdown.runner;

import cuckoo.common.IRunner;
import cuckoo.common.Word;
import cuckoo.common.Result;
import cuckoo.common.Symbol;
import cuckoo.pushdown.common.PState;
import cuckoo.pushdown.common.PTransition;
import java.util.Stack;


/**
 * Implements a Runner for Deterministic Pushdown Automata.
 * For each symbol of the Word, get all possible transitions for the given Symbol, 
 * for each transition, pop specified Symbols, push new Symbols and move the head
 * to the next state.
 * @author eugf
 */
public class DPDARunner implements IRunner<PState> {

    private final Stack<Symbol> stack;
    private PState current;
    private final Word word;
    
    public DPDARunner(Word word, PState initial) {
        this.word = word;
        this.current = initial;
        this.stack = new Stack();
    }

    @Override
    public void compute() {
        word.stream().forEach( (symbol) -> {

            computeTransition(current.getTransition(symbol));

            // if more than one epsilon transition is given,
            // the automaton will change the current state to
            // the first valid epsilon transition.
            current.getEpsilonTransitions().stream().forEach((t) -> {
                computeTransition(t);
            });
        });

    }

    private void computeTransition(PTransition trans) {
        boolean changed = false;
        
        for (Symbol s : trans.toPop()) {
            if (!stack.isEmpty() && s.equals(stack.peek())) {
                stack.pop();
                changed = true;
            }
        }
        
        // after we pop out some symbols, now we can push new ones
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
    
    @Override
    public Result<PState> getResult() {
        return new Result<>(current);
    }
    
}
