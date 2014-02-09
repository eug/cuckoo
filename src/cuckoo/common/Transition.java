
package cuckoo.common;

public interface Transition<S> {
    
    public S getNext();
    
    public void goTo(S t);
    
}
