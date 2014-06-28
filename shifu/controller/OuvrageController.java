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
import shifu.core.parameters.ShifuParameters;
import shifu.model.Mediatheque;
import shifu.model.Ouvrage;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.ShifuDAOFactory;

/**
 *
 * @author delwiv
 */
public class OuvrageController extends Controller<Ouvrage> {
    
    @Override
    public void addModel( Ouvrage object ) throws SQLException {
        ShifuDAOFactory.getOuvrageDAOC().create( object );
        Mediatheque.getInstance().getListOuvrage().add( 0, object );
    }
    
    @Override
    public void updateModel( Ouvrage object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * La recherche d'ouvrage peut correspondre à differents champs
     *
     * @param args
     * @return
     */
    @Override
    public List<Ouvrage> getListModel( CustomArgument args ) {
        List<Ouvrage> listOuvrages = new ArrayList();
        List<Ouvrage> listBuffer = new ArrayList();
        try {
            listOuvrages = ShifuDAOFactory.getOuvrageDAOC().getByLikeCustomArgument( new CustomArgument( null, "%" + args.getValue() + "%" ) );

//            for ( Iterator<Ouvrage> it = listOuvrages.iterator() ; it.hasNext() ; ) {
//                Ouvrage ouvrage = it.next();
//                if ( !listBuffer.contains( ouvrage ) ) {
//                    listBuffer.add( ouvrage );
//                }
//            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        
        return listOuvrages;
    }
    
    @Override
    public Ouvrage getModel( CustomArgument args ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update( ShifuParameters params ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
    
}
