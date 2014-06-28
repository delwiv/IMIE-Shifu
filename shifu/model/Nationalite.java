package shifu.model;

import shifu.core.AbstractShifuModel;

public class Nationalite extends AbstractShifuModel {

    private int ID = 0;
    private String lib;
    private String shortLib;

    public Nationalite() {
    }

    public Nationalite( int ID, String lib, String shortLib ) {
        this.ID = ID;
        this.lib = lib;
        this.shortLib = shortLib;
    }

    public Nationalite( String lib, String shortLib ) {
        this( 0, lib, shortLib );
    }

    public Nationalite( String lib ) {
        this( lib, "" );
    }

    public int getID() {
        return ID;
    }

    public void setID( int ID ) {
        this.ID = ID;
    }

    public String getLib() {
        return lib;
    }

    public void setLib( String value ) {
        lib = value;
    }

    public String getShortLib() {
        return shortLib;
    }

    public void setShortLib( String value ) {
        shortLib = value;
    }

    @Override
    public String toString() {
        return "Nationalite{" + "ID=" + ID + ", lib=" + lib + ", shortLib=" + shortLib + '}';
    }
    
    
}
