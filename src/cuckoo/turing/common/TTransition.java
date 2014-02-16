
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

    
    public Symbol getRead() {
        return read;
    }

    
    public Symbol getWrite() {
        return write;
    }

    
    public int getMove() {
        return move;
    }
    
    
    public TTransition reads(String symbol) {
        return reads(new DefaultSymbol(symbol));
    }
    
    
    public TTransition reads(Symbol symbol) {
        read = symbol;
        return this;
    }
    
    
    public TTransition write(String symbol) {
        return write(new DefaultSymbol(symbol));
    }
    
    
    public TTransition write(Symbol symbol) {
        write = symbol;
        return this;
    }
    
    
    public TTransition right() {
        move = EMove.RIGHT;
        return this;
    }
    
    
    public TTransition left() {
        move = EMove.LEFT;
        return this;
    }
    
    
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
