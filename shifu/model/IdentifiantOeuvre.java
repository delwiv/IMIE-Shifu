/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shifu.model;

import shifu.core.AbstractShifuModel;

/**
 *
 * @author delwiv
 */
public class IdentifiantOeuvre extends AbstractShifuModel {
    private int id;
    private String lib;
    private String value;
    private int idOeuvre;

    public IdentifiantOeuvre( int id, String lib, String value, int idOeuvre ) {
        this.id = id;
        this.lib = lib;
        this.value = value;
        this.idOeuvre = idOeuvre;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getLib() {
        return lib;
    }

    public void setLib( String lib ) {
        this.lib = lib;
    }

    public String getValue() {
        return value;
    }

    public void setValue( String value ) {
        this.value = value;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre( int idOeuvre ) {
        this.idOeuvre = idOeuvre;
    }
    
    
}
