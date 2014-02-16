
package cuckoo.turing.common;

import cuckoo.utils.Epsilon;
import cuckoo.common.Symbol;
import cuckoo.common.Transition;
import cuckoo.utils.DefaultSymbol;
import java.util.Objects;


public class TTransition implements Transition<TState> {
    
    private Symbol write;
    
    private Symbol read;
    
    private TState next;
    
    private int move;
    
    /**
     * Initializes a newly created {@code TTransition}.
     */
    public TTransition() {
        this.write = new Epsilon();
        this.read = new Epsilon();
        this.next = new TState("Dead State");
        this.move = EMove.NONE;
    }
    

    @Override
    public TState getNext() {
        return next;
    }

    
    /**
     * Returns which {@code Symbol} will be read from the {@code Tape}.
     * @return 
     */
    public Symbol getRead() {
        return read;
    }

    
    /**
     * Returns which {@code Symbol} will be written in the {@code Tape}.
     * @return 
     */
    public Symbol getWrite() {
        return write;
    }

    
    /**
     * Returns which movement will be executed.
     * @see EMove
     * @return Returns a value representing the movement.
     */
    public int getMove() {
        return move;
    }
    
    
    /**
     * Reads the specified {@code String} from the {@code Tape}.
     * Internally the specified {@code String} will be converted
     * into a {@code Symbol} object.
     * @param symbol String to be read
     * @return This transition.
     */
    public TTransition reads(String symbol) {
        return reads(new DefaultSymbol(symbol));
    }
    
    
    /**
     * Reads the specified {@code Symbol} from the {@code Tape}.
     * @param symbol Symbol to be read
     * @return This transition.
     */
    public TTransition reads(Symbol symbol) {
        read = symbol;
        return this;
    }

    
    /**
     * Writes the specified {@code String} in the {@code Tape}.
     * Internally the specified {@code String} will be converted
     * into a {@code Symbol} object.
     * @param symbol String to be written
     * @return This transition.
     */    
    public TTransition write(String symbol) {
        return write(new DefaultSymbol(symbol));
    }
    
    
    /**
     * Writes the specified {@code Symbol} in the {@code Tape}.
     * @param symbol Symbol to be written
     * @return This transition.
     */
    public TTransition write(Symbol symbol) {
        write = symbol;
        return this;
    }
    
    
    /**
     * Moves the head to the right.
     * @return This transition.
     */
    public TTransition right() {
        move = EMove.RIGHT;
        return this;
    }
    
    
    /**
     * Moves the head to the left.
     * @return This transition.
     */
    public TTransition left() {
        move = EMove.LEFT;
        return this;
    }
    
    
    /**
     * Do not move the head.
     * @return This transition.
     */
    public TTransition stay() {
        move = EMove.NONE;
        return this;
    }
    

    @Override
    public void goTo(TState state) {
        next = state;
    }
    

    @Override
    public boolean isValid() {
        return next != null && write != null && read != null;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.write);
        hash = 41 * hash + Objects.hashCode(this.read);
        hash = 41 * hash + Objects.hashCode(this.next);
        hash = 41 * hash + this.move;
        return hash;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TTransition other = (TTransition) obj;
        if (!Objects.equals(this.write, other.write)) {
            return false;
        }
        if (!Objects.equals(this.read, other.read)) {
            return false;
        }
        if (!Objects.equals(this.next, other.next)) {
            return false;
        }
        if (this.move != other.move) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "TTransition{" + "write=" + write + ", read=" + read + ", next=" + next + ", move=" + move + '}';
    }
    
}
