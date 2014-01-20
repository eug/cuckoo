package cuckoo.runners;

import cuckoo.common.Word;
import cuckoo.common.State;
import cuckoo.common.Result;
import cuckoo.common.Symbol;
import cuckoo.common.DeadState;
import cuckoo.common.ResultType;
import java.util.LinkedList;
import java.util.Queue;

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
    private Result result;
    private final State initial;
    private final Queue<Tuple<State, Integer>> queue;
    
    public NFARunner(State initial, Word word) {
        this.word = word;
        this.initial = initial;
        this.queue = new LinkedList<>();
        this.result = new Result(new DeadState(), ResultType.DEAD_STATE);
    }

    public void compute() {
        Symbol symbol;
        Tuple<State, Integer> current;
        
        queue.offer(new Tuple<>(initial, 0));
        
        // to evaluate all transitions we use breadth-first-search
        while (!queue.isEmpty() && !isAccepted()) {
            
            current = queue.poll();
            
            // if true, is the end of the word
            if (current.index < word.size()) {
                symbol  = word.get(current.index);
                for (State next : current.state.compute(symbol)) {
                    queue.offer(new Tuple<>(next, current.index + 1));
                }
            } else {
                setResult(current.state);
            }
        }

    }

    private void setResult(State current) {
        if (!current.equals(new DeadState())) {
            if (current.isFinal()) {
                result = new Result(current, ResultType.ACCECPTED);
            } else {
                result = new Result(current, ResultType.REJECTED);
            }
        }
    }

    private boolean isAccepted() {
        return result.getResult() == ResultType.ACCECPTED;
    }
    
    public Result getResult() {
        return result;
    }

}
