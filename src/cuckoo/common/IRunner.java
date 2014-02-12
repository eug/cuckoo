
package cuckoo.common;

/**
 *
 * @author eugf
 * @param <S>
 */
public interface IRunner<S extends AbstractState> {
    
    public void compute();
    
    public Result<S> getResult();
}
