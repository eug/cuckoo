
package cuckoo.common;

import cuckoo.common.State;
import cuckoo.common.Symbol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eugf
 */
public class DeadState extends State {

    private final List<State> emptyList;
    
    public DeadState() {
        super("Dead State");
        emptyList = new ArrayList<>();
    }
    
    @Override
    public List<State> compute(Symbol symbol) {
        return emptyList;
    }
    
}
