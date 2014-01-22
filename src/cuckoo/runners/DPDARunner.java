
package cuckoo.runners;

import cuckoo.common.Word;
import cuckoo.common.State;
import cuckoo.common.Symbol;
import cuckoo.common.Transition;
import cuckoo.common.AbstractDeterministicRunner;
import java.util.List;
import java.util.Stack;

public class DPDARunner extends AbstractDeterministicRunner {
    
    private final Stack<Symbol> stack;
    
    public DPDARunner(Word word, State initial) {
        super(word, initial);
        this.current = initial;
        this.stack = new Stack();
    }

    @Override
    public void compute() {
        boolean changed = false;
        
        for (Symbol symbol : word) {
            
            List<State> next = current.compute(symbol);
            List<Transition> trans = current.getTransitions(symbol);
            
            if (trans.isEmpty() || next.isEmpty()) {
                continue;
            }
                
            for (Transition t : trans) {

                for (Symbol s : getPopable(t)) {
                    if (!stack.isEmpty() && s.equals(stack.peek())) {
                        stack.pop();
                        changed = true;
                    }
                }

                // after we pop out some symbols, now we can push some new ones
                for (Symbol s : getPushable(t)) {
                    stack.push(s);
                    changed = true;
                }

                // update the cursor to the next state
                if (changed) {
                    current = getNextState(t);
                    changed = false;
                }
            }
        }
        
        setResult();
    }
    
}
