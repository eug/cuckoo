
package cuckoo.common;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * This class is a abstraction of a array of <tt>Symbol</tt>.
 * This class doesn't provide any special behaviour, just a better
 * representation of the "word concept";
 * @author eugf
 */
public class Word extends ArrayList<Symbol> {
    
    public Word(Symbol... symbols) {
        super(Arrays.asList(symbols));
    }
}
