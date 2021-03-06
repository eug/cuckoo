package cuckoo.pushdown.runner;

import cuckoo.common.Word;
import cuckoo.common.Result;
import cuckoo.common.Symbol;
import cuckoo.common.IRunner;
import cuckoo.pushdown.common.PState;
import cuckoo.pushdown.common.PTransition;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

/**
 * Implements a Runner for Non Deterministic Pushdown Automata.
 * @author eugf
 */
public class NPDARunner implements IRunner<PState> {

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
     * enqueue all initial states
     * while queue is not empty do
     * 1) dequeue one state
     * 2) process all epsilon transitions
     * 3) get a symbol
     * 4) compute the given symbol
     * 5) enqueue all other possible transitions
     */
    @Override
    public void compute() {

        for (PState s : initial) {
            queue.offer(new ThreeUple<>(s, new Stack<Symbol>(), 0));
        }

        do {
            current = queue.poll();
            current.state.getEpsilonTransitions().stream().forEach((trans) -> {
                computeTransition(trans, true);
            });
            
            if (isEndOfWord()) { continue; }
            
            Symbol symbol = word.get(current.index);
            current.state.getTransitions(symbol).stream().forEach((t) -> {
                computeTransition(t, false);
            });

            // Doesn't have any transition associated?
            if (current.state.getTransitions().isEmpty()) {
                PState deadState = new PState("Dead State", false);
                current = new ThreeUple<>(deadState, null, -1);
                break;                    
            }
            
        } while (!queue.isEmpty());
        
    }

    private boolean isEndOfWord() {
        return current.index >= word.size();
    }

    private void computeTransition(PTransition t, boolean isEpsilon) {
        boolean changed = false;
        
        for (Symbol s : t.toPop()) {
            if (!current.stack.isEmpty() && s.equals(current.stack.peek())) {
                current.stack.pop();
                changed = true;
            }
        }
        
        for (Symbol s : t.toPush()) {
            current.stack.push(s);
            changed = true;
        }
        
        // update the head to the next state
        // we only transfer to another state,
        // iff it was possible to pop or push a symbol
        if (changed) {
            PState next = t.getNext();
            Stack<Symbol> stack = (Stack<Symbol>) current.stack.clone();
            int index = current.index + ((isEpsilon) ? 0 : 1);
            
            queue.offer(new ThreeUple<>(next, stack, index));
        }
    }
    
    @Override
    public Result<PState> getResult() {
        return new Result<>(current.state);
    }
}
