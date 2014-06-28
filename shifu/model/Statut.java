/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shifu.model;

/**
 *
 * @author antoine
 */
public class Statut {
    private int ID;
    private String lib_statut;

    public Statut(int ID, String lib_statut) {
        this.ID = ID;
        this.lib_statut = lib_statut;
    }

        
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLib_statut() {
        return lib_statut;
    }

    public void setLib_statut(String lib_statut) {
        this.lib_statut = lib_statut;
    }
    
    
}
