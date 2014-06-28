/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shifu.model.dao.tables;

/**
 *
 * @author antoine
 */
public class TableStatutIndividu {
    private int idIndividu;
    private int idStatut;

    public TableStatutIndividu(int idIndividu, int idStatut) {
        this.idIndividu = idIndividu;
        this.idStatut = idStatut;
    }

    public int getIdIndividu() {
        return idIndividu;
    }

    public void setIdIndividu(int idIndividu) {
        this.idIndividu = idIndividu;
    }

    public int getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(int idStatut) {
        this.idStatut = idStatut;
    }
    
}
