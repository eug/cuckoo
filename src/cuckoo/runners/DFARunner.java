
package cuckoo.runners;

import cuckoo.common.Word;
import cuckoo.common.State;
import cuckoo.common.Symbol;
import cuckoo.common.AbstractDeterministicRunner;
import java.util.List;

public class DFARunner extends AbstractDeterministicRunner {
    
    public DFARunner(Word word, State initial) {
        super(word, initial);
    }
    
    @Override
    public void compute() {
        for (Symbol symbol : word) {
            // FIXME: by definition, DFA's states only return one next state
            List<State> nextState = current.compute(symbol);
            if (!nextState.isEmpty()) {
                current = nextState.get(0);
            }
        }
    }

}
