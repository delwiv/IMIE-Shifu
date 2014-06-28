package shifu.core.parameters;

import shifu.core.AbstractShifuModel;


public class EditModelParameters extends UpdateParameters {
    
    private AbstractShifuModel originalModel;    
    
    public EditModelParameters() {        
        super( UpdateParameters.EDIT_MODEL );
    }

    /**
     * Set the value of originalModel
     *
     * @param newVar the new value of originalModel
     */
    public void setOriginalModel( AbstractShifuModel newVar ) {
        originalModel = newVar;
    }

    /**
     * Get the value of originalModel
     *
     * @return the value of originalModel
     */
    public AbstractShifuModel getOriginalModel() {
        return originalModel;
    }
    
}
