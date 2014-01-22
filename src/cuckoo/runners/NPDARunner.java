
package cuckoo.runners;

import cuckoo.common.State;
import cuckoo.common.Symbol;
import cuckoo.common.Word;
import java.util.List;
import java.util.Stack;

public class NPDARunner {

    
    private Stack stack;
    private final Word word;
    private State current;
    
    
    public NPDARunner(Word word, State... initial) {
        this.word = word;
//        this.current = initial;
    }
//
    public void compute() {
//        
//        for (Symbol symbol : word) {
//            
//            List<State> nextState = current.compute(symbol);
//            current.
//            if (!nextState.isEmpty()) {
//                current = nextState.get(0);
//            }
//        }
    }
    
}
