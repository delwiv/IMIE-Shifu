package shifu.core.parameters;

import shifu.core.AbstractShifuModel;


abstract public class UpdateParameters extends ShifuParameters {
    
    public static final short ADD_MODEL = 1;
    public static final short EDIT_MODEL = 2;
    public static final short DELETE_MODEL = 3;
    
    private short updateType;
    private AbstractShifuModel model;
    
    public UpdateParameters( short updateType ) {
        super( ShifuParameters.UPDATE_PARAMS );
        this.updateType = updateType;
    }

    /**
     * Set the value of updateType
     *
     * @param newVar the new value of updateType
     */
    public void setUpdateType( short newVar ) {
        updateType = newVar;
    }

    /**
     * Get the value of updateType
     *
     * @return the value of updateType
     */
    public short getUpdateType() {
        return updateType;
    }

    /**
     * Set the value of model
     *
     * @param newVar the new value of model
     */
    public void setModel( AbstractShifuModel newVar ) {
        model = newVar;
    }

    /**
     * Get the value of model
     *
     * @return the value of model
     */
    public AbstractShifuModel getModel() {
        return model;
    }
    
}
