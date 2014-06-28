package shifu.core.parameters;

public class SearchParameters extends ShifuParameters {

    public static final short SEARCH_ADHERENT = 1;
    public static final short SEARCH_AUTEUR = 2;
    public static final short SEARCH_EMPRUNT = 3;
    public static final short SEARCH_OUVRAGE = 4;

    public static final String TABLE_ABONNE = "Abonne";
    public static final String TABLE_AUTEUR = "Auteur";
    public static final String TABLE_EMPRUNT = "Emprunt";
    public static final String TABLE_OUVRAGE = "Ouvrage";

    private String args;
    private short searchType;

    public SearchParameters() {
        super( ShifuParameters.SEARCH_PARAMS );
    }

    public SearchParameters( short searchType ) {
        this();
        this.searchType = searchType;
    }
    
    

    /**
     * Set the value of searchType
     *
     * @param newVar the new value of searchType
     */
    public void setSearchType( short newVar ) {
        searchType = newVar;
    }

    /**
     * Get the value of searchType
     *
     * @return the value of searchType
     */
    public short getSearchType() {
        return searchType;
    }

    /**
     * Set the value of args
     *
     * @param newVar the new value of args
     */
    public void setArgs( String newVar ) {
        args = newVar;
    }

    /**
     * Get the value of args
     *
     * @return the value of args
     */
    public String getArgs() {
        return args;
    }

}
