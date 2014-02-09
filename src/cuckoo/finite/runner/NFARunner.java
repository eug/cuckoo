package cuckoo.finite.runner;

import cuckoo.common.Word;
import cuckoo.common.Result;
import cuckoo.common.Symbol;
import cuckoo.common.ResultType;
import cuckoo.finite.common.FState;
import cuckoo.finite.common.FTransition;
import java.util.Queue;
import java.util.LinkedList;

public class NFARunner {
    
    private class Tuple<A,B> {
        public final A state;
        public final B index;
        public Tuple(A a, B b) {
            this.state = a;
            this.index = b;
        }
    }
    
    private final Word word;
    private final FState[] initialStates;
    private Tuple<FState, Integer> current;
    private final Queue<Tuple<FState, Integer>> queue;
    
    public NFARunner(Word word, FState... initial) {
        this.word = word;
        this.queue = new LinkedList<>();
        this.initialStates = initial;
    }

    public void compute() {
        
        for (FState state : initialStates) {
            queue.offer(new Tuple<>(state, 0));
        }
        
        do {
            current = queue.poll();

            if (isEndOfWord()) {
                if (current.state.isFinalState()) {
                    break;
                } else {
                    continue;
                }
            }

            Symbol symbol  = word.get(current.index);
            
            for (FTransition trans : current.state.getEpsilonTransitions()) {
                enqueueTransition(trans, true);            
            }
            
            for (FTransition trans : current.state.getTransitions(symbol)) {
                enqueueTransition(trans, false);
            }
            
        } while (!queue.isEmpty());

    }
    
    private void enqueueTransition(FTransition trans, boolean isEpsilon) {
        FState state = trans.getNext();
        int index = current.index + ((isEpsilon) ? 0 : 1);
        queue.offer(new Tuple<>(state, index));
    }
    
    private boolean isEndOfWord() {
        return current.index >= word.size();
    }

    public Result<FState> getResult() {
        return new Result<>(current.state);
    }
}
