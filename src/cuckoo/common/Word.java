
package cuckoo.common;

import java.util.ArrayList;
import java.util.Arrays;

public class Word extends ArrayList<Symbol> {
    
    public Word(Symbol... symbols) {
        super();
        addAll(Arrays.asList(symbols));
    }
}
