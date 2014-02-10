
package cuckoo.common;

/**
 * A Tape is just a better representation of the object {@link Word}.
 * There is no (and shoudn't be) special behaviour on this object.
 * @author eugf
 */
public final class Tape extends Word {

    public Tape(String string) { super(string); }

    public Tape(Symbol... symbols) { super(symbols); }
    
}
