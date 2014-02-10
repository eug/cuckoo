
package cuckoo.common;

import cuckoo.utils.DefaultSymbol;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a abstraction of a array of <tt>Symbol</tt>.
 * This class doesn't provide any special behaviour, just a better
 * representation of the "word concept";
 * @author eugf
 */
public class Word extends ArrayList<Symbol> {
    
    public Word(String string) {
        super();
        addAll(toSymbols(string));
    }
    
    public Word(Symbol... symbols) {
        super(Arrays.asList(symbols));
    }
    
    private List<Symbol> toSymbols(String string) {
        ArrayList<Symbol> symbols = new ArrayList<>();
        for(char c : string.toCharArray()) {
            symbols.add(new DefaultSymbol(c + ""));
        }
        return symbols;
    }
}
