

package cuckoo.common;

/**
 *
 * @author eugf
 */
public interface Transition<S> {
    
    public S getNext();
    
    public void goTo(S t);
    
}
