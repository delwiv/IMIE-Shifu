/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import shifu.core.Controller;
import shifu.core.parameters.ShifuParameters;
import shifu.model.Auteur;
import shifu.model.Mediatheque;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.ShifuDAOFactory;

/**
 *
 * @author delwiv
 */
public class AuteurController extends Controller<Auteur> {

    private Auteur auteur;
    private List<Auteur> listAuteur;

    public AuteurController() {
        listAuteur = new ArrayList();
    }

    @Override
    public void update( ShifuParameters params ) {

    }

    @Override
    public void addModel( Auteur object ) throws SQLException {
        // creation nationalité
        System.out.println( object.toString() );
        object.setNationnalite( ShifuDAOFactory.getNationaliteDAO().create( object.getNationalite() ) );
        ShifuDAOFactory.getAuteurDAO().create( object );
    }

    @Override
    public void updateModel( Auteur object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Auteur> getListModel( CustomArgument args ) throws SQLException {
        List<Auteur> listAuteurs = new ArrayList();

        ListIterator it;

        String[] searchArgs = ( ( String ) args.getValue() ).split( " " );
        for ( int i = 0 ; i < searchArgs.length ; i++ ) {

            // recherche sur le nom
            List<Auteur> listBuffer = ShifuDAOFactory.getAuteurDAO()
                    .getByLikeCustomArgument( new CustomArgument( "nom_auteur", searchArgs[i] ) );
            it = listBuffer.listIterator( 0 );
            while ( it.hasNext() ) {
                Auteur auteur = ( Auteur ) it.next();
                if ( !listAuteurs.contains( auteur ) ) {
                    listAuteurs.add( auteur );
                }
            }

            // puis sur le prénom
            listBuffer = ShifuDAOFactory.getAuteurDAO()
                    .getByLikeCustomArgument( new CustomArgument( "prenom_auteur", searchArgs[i] ) );
            it = listBuffer.listIterator( 0 );
            while ( it.hasNext() ) {
                Auteur auteur = ( Auteur ) it.next();
                if ( !listAuteurs.contains( auteur ) ) {
                    listAuteurs.add( auteur );
                }
            }
            // enfin sur le surnom
            listBuffer = ShifuDAOFactory.getAuteurDAO()
                    .getByLikeCustomArgument( new CustomArgument( "surnom_auteur", searchArgs[i] ) );
            it = listBuffer.listIterator( 0 );
            while ( it.hasNext() ) {
                Auteur auteur = ( Auteur ) it.next();
                if ( !listAuteurs.contains( auteur ) ) {
                    listAuteurs.add( auteur );
                }
            }
        }

        return listAuteurs;
    }

    @Override
    public Auteur getModel( CustomArgument args ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
}
