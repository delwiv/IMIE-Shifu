/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shifu.core.Controller;
import shifu.core.Singleton;
import shifu.core.parameters.ShifuParameters;
import shifu.model.Adherent;
import shifu.model.Mediatheque;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.ShifuDAOFactory;

/**
 *
 * @author antoine
 */
public class AdherentController extends Controller<Adherent> {

    @Override
    public void addModel( Adherent object ) throws SQLException, Exception {
        object = ShifuDAOFactory.getAdherentDAOC().create( object );
        Mediatheque.getInstance().addAdherent( object );
    }

    @Override
    public void updateModel( Adherent object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Adherent> getListModel( CustomArgument args ) {
        List<Adherent> lAdherents = new ArrayList();
        
        try {
            lAdherents = ShifuDAOFactory.getAdherentDAOC().getByEqualsCustomArgument( new CustomArgument(null,  "%" + args.getValue() + "%"));
        } catch ( Exception e ) {
        }
        
        
        return lAdherents;
    }

    @Override
    public Adherent getModel( CustomArgument args ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update( ShifuParameters params ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
