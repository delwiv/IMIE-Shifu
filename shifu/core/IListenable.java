package shifu.core;

import shifu.core.parameters.ShifuParameters;

public interface IListenable {

    /**
     * @param listener
     */
    public void addListener( IListener listener );

    /**
     * @param listener
     */
    public void removeListener( IListener listener );

    /**
     * @param params
     * @throws java.lang.Exception
     */
    public void notifyListeners( ShifuParameters params ) throws Exception;

}
