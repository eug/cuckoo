
package cuckoo.utils;

import cuckoo.common.State;
import cuckoo.common.Symbol;
import java.util.List;
import java.util.ArrayList;

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
