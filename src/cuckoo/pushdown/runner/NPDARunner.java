package cuckoo.pushdown.runner;

import cuckoo.common.Word;
import cuckoo.common.Result;
import cuckoo.common.Symbol;
import cuckoo.common.ResultType;
import cuckoo.pushdown.common.PState;
import cuckoo.pushdown.common.PTransition;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class NPDARunner {

    private class ThreeUple<A, B, C> {
        public final A state;
        public final B stack;
        public final C index;
        public ThreeUple(A a, B b, C c) {
            this.state = a;
            this.stack = b;
            this.index = c;
        }
    }
    
    private final Word word;
    private final PState[] initial;
    private ThreeUple<PState, Stack<Symbol>, Integer> current;
    private final Queue<ThreeUple<PState, Stack<Symbol>, Integer>> queue;

    public NPDARunner(Word word, PState... initial) {
        this.word = word;
        this.queue = new LinkedList();
        this.initial = initial;
    }

    /**
     * Compute!.
     * enqueue all initial states while queue is not empty do
     * 1) dequeue one state
     * 2) process all epsilon transitions
     * 3) get a symbol
     * 4) compute the given symbol
     * 5) enqueue all other possible transitions
     */
    public void compute() {

        for (PState s : initial) {
            queue.offer(new ThreeUple<>(s, new Stack<Symbol>(), 0));
        }

        do {
            current = queue.poll();
            
            for (PTransition trans : current.state.getEpsilonTransitions()) {
                computeTransition(trans, true);            
            }
            
            if (isEndOfWord()) { continue; }
            
            Symbol symbol = word.get(current.index);

            for (PTransition t : current.state.getTransitions(symbol)) {
                computeTransition(t, false);
            }

            // the current state doesn't have any transition associated?
            if (current.state.getTransitions().isEmpty()) {
                PState deadState = new PState("Dead State", false);
                current = new ThreeUple<>(deadState, null, -1);
                break;                    
            }
            
        } while (!queue.isEmpty());
        
    }

    private boolean isEndOfWord() {
        return current.index == word.size();
    }

    private void computeTransition(PTransition t, boolean isEpsilon) {
        boolean changed = false;
        
        for (Symbol s : t.toPop()) {
            if (!current.stack.isEmpty() && s.equals(current.stack.peek())) {
                current.stack.pop();
                changed = true;
            }
        }
        
        // after we pop out some symbols, now we can push some new ones
        for (Symbol s : t.toPush()) {
            current.stack.push(s);
            changed = true;
        }
        
        // update the cursor to the next state
        // we only transfer to another state,
        // iff it was possible to pop or push a symbol
        if (changed) {
            PState next = t.getNext();
            Stack<Symbol> stack = (Stack<Symbol>) current.stack.clone();
            int index = current.index + ((isEpsilon) ? 0 : 1);
            
            queue.offer(new ThreeUple<>(next, stack, index));
        }
    }
    
    public Result<PState> getResult() {
        if (current.state.isFinalState()) {
            return new Result<>(current.state, ResultType.ACCECPTED);
        } else {
            return new Result<>(current.state, ResultType.REJECTED);
        }
    }
}
