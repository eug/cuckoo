
package cuckoo.turing.common;

import cuckoo.common.Symbol;
import cuckoo.common.Transition;
import java.util.Objects;
import cuckoo.utils.Epsilon;


public class TTransition implements Transition<TState> {
    
    private Symbol write;
    
    private Symbol read;
    
    private TState next;
    
    private int move;

    public TTransition() {
        this.write = new Epsilon();
        this.read = new Epsilon();
        this.next = new TState("Dead State", false);
        this.move = Move.NONE;
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
    
    public TTransition reads(Symbol symbol) {
        read = symbol;
        return this;
    }
    
    public TTransition write(Symbol symbol) {
        write = symbol;
        return this;
    }
    
    public TTransition right() {
        move = Move.RIGHT;
        return this;
    }
    
    public TTransition left() {
        move = Move.LEFT;
        return this;
    }
    
    public TTransition stay() {
        move = Move.NONE;
        return this;
    }
    
    @Override
    public void goTo(TState state) {
        next = state;
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
