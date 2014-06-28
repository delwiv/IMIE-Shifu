package shifu.core;

import shifu.core.parameters.ShifuParameters;

public interface IListener {

    /**
     * @param params
     */
    public void update( ShifuParameters params ) throws Exception;

}
