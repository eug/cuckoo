package cuckoo.finite.runner;

import cuckoo.common.Result;
import cuckoo.common.ResultType;
import cuckoo.common.Word;
import cuckoo.common.Symbol;
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
            
            // if false, is the end of the word
            if (isEndOfWord()) {
                if (current.state.isFinalState()) {
                    break;
                } else {
                    continue;
                }
            }

            // TODO: Enqueue Epsilon Transitions
            
            Symbol symbol  = word.get(current.index);

            for (FTransition t : current.state.getTransitions(symbol)) {
                FState state = t.getNext();
                int index = current.index + 1;

                queue.offer(new Tuple<>(state, index));
            }
            
        } while (!queue.isEmpty());

    }

    private boolean isEndOfWord() {
        return current.index >= word.size();
    }

    public Result<FState> getResult() {
        if (current.state.isFinalState()) {
            return new Result<>(current.state, ResultType.ACCECPTED);
        } else {
            return new Result<>(current.state, ResultType.REJECTED);
        }
    }
}
