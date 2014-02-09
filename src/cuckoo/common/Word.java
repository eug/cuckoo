
package cuckoo.common;

import java.util.Arrays;
import java.util.ArrayList;

public class Word extends ArrayList<Symbol> {
    
    public Word(Symbol... symbols) {
        super();
        addAll(Arrays.asList(symbols));
    }
}
