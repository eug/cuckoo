
package cuckoo.common;

import cuckoo.utils.DefaultSymbol;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is an abstraction of an array of <tt>Symbol</tt>.
 * This class doesn't provide any special behaviour, just a better
 * representation of "word concept";
 * @author eugf
 */
public class Word extends ArrayList<Symbol> {
    
    /**
     * Convert a given string into a Word object.
     * Internally the string will be converted into array of Symbols.
     * @param string Input string
     */
    public Word(String string) {
        super();
        addAll(toSymbols(string));
    }
    
    /**
     * Constructs a Word object for a given array of Symbols.
     * @param symbols Input symbol
     */
    public Word(Symbol... symbols) {
        super(Arrays.asList(symbols));
    }
    
    /**
     * Convert a given string to list of Symbols.
     * @param string String to be converted
     * @return A String as list of Symbol objects.
     */
    private List<Symbol> toSymbols(String string) {
        ArrayList<Symbol> symbols = new ArrayList<>();
        for (char c : string.toCharArray()) {
            symbols.add(new DefaultSymbol(c + ""));
        }
        return symbols;
    }
}
