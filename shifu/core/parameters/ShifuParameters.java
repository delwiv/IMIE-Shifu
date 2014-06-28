package shifu.core.parameters;

public abstract class ShifuParameters {

    public static final short SEARCH_PARAMS = 1;
    public static final short UPDATE_PARAMS = 2;
    public static final short LEAVE_PARAMS = 3;

    private short ParamsType;

    public ShifuParameters( short ParamsType ) {
        this.ParamsType = ParamsType;
    }

    /**
     * Set the value of ParamsType
     *
     * @param newVar the new value of ParamsType
     */
    public void setParamsType( short newVar ) {
        ParamsType = newVar;
    }

    /**
     * Get the value of ParamsType
     *
     * @return the value of ParamsType
     */
    public short getParamsType() {
        return ParamsType;
    }

}
