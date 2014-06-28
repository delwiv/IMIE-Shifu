/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import shifu.core.AbstractShifuModel;
import shifu.core.AbstractShifuView;
import shifu.core.Controller;
import shifu.core.Singleton;
import shifu.core.parameters.SearchParameters;
import shifu.core.parameters.ShifuParameters;
import shifu.core.parameters.UpdateParameters;
import shifu.model.Adherent;
import shifu.model.Auteur;
import shifu.model.Emprunt;
import shifu.model.Mediatheque;
import shifu.model.Ouvrage;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.ShifuDAOFactory;
import shifu.model.database.MySQLConnection;
import shifu.view.DialogException;
import shifu.view.MainFrame;
import shifu.view.ShifuSplashScreen;

/**
 *
 * @author delwiv
 */
public class MainController extends Controller<AbstractShifuModel> {

    private AbstractShifuModel object;
    private AbstractShifuView view;

    public MainController() {

    }

    @Override
    public void update( ShifuParameters params ) throws Exception {
        try {
            switch ( params.getParamsType() ) {
                case ShifuParameters.SEARCH_PARAMS:
                    Singleton.getInstance( SearchController.class ).doSearch( ( SearchParameters ) params );
                    break;

                case ShifuParameters.UPDATE_PARAMS:
                    UpdateParameters updateParams = ( UpdateParameters ) params;
                    if ( updateParams.getUpdateType() == UpdateParameters.ADD_MODEL ) {
                        addModel( updateParams.getModel() );

                    } else if ( updateParams.getUpdateType() == UpdateParameters.EDIT_MODEL ) {
                        updateModel( updateParams.getModel() );

                    } else if ( updateParams.getUpdateType() == UpdateParameters.DELETE_MODEL ) {
                        deleteModel( updateParams.getModel() );
                    }
                    break;
                case ShifuParameters.LEAVE_PARAMS:
                    MySQLConnection.getInstance().close();
                    System.exit( 0 );
            }

        } catch ( SQLException ex ) {
            Logger.getLogger( MainController.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    @Override
    public void addModel( AbstractShifuModel object ) {
        try {
            if ( object instanceof Auteur ) {
                Singleton.getInstance( AuteurController.class ).addModel( ( Auteur ) object );
            } else if ( object instanceof Ouvrage ) {
                Singleton.getInstance( OuvrageController.class ).addModel( ( Ouvrage ) object );
            } else if ( object instanceof Adherent ) {
                Singleton.getInstance( AdherentController.class ).addModel( ( Adherent ) object );
            }

        } catch ( Exception ex ) {
            Logger.getLogger( MainController.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    public void start() {
        try {
//            Splash
            ShifuSplashScreen splashScreen = new ShifuSplashScreen();

            try {
                splashScreen.setText( "Vérification de la base de données" );
                splashScreen.setProgress( 20 );

                Mediatheque.getInstance().setListAdherents(
                        ShifuDAOFactory.getAdherentDAOC().getAllAdherentFromDB()
                );

                splashScreen.setText( "Récupération des genres" );
                splashScreen.setProgress( 30 );

                Mediatheque.getInstance().setListGenre(
                        ShifuDAOFactory.getGenreDAO().findAll() );

                splashScreen.setText( "Récupération des ouvrages" );
                splashScreen.setProgress( 50 );

                Mediatheque.getInstance().setListOuvrage(
                        ShifuDAOFactory.getOuvrageDAOC().getAllFromDB() );

                splashScreen.setProgress( 80 );
                splashScreen.setText( "Création de la fenetre principale" );
                MainFrame frame = new MainFrame( this );

                splashScreen.setProgress( 100 );
                splashScreen.setText( "Démarrage ;)" );
                splashScreen.setVisible( false );

                frame.displayMainScreen();

            } catch ( Exception e ) {
                new DialogException( e );
            }

        } catch ( Exception e ) {
            new DialogException( e );

        }

    }

    @Override
    public void updateModel( AbstractShifuModel object ) {
        try {
            if ( object instanceof Adherent ) {
                Adherent adherent = ( Adherent ) object;
                ShifuDAOFactory.getAdherentDAOC().update( adherent );
                Singleton.getInstance( Mediatheque.class ).setListAdherents( ShifuDAOFactory.getAdherentDAOC().getAllAdherentFromDB());
            }
        } catch ( Exception e ) {
        }

    }

    @Override
    public List<AbstractShifuModel> getListModel( CustomArgument args ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractShifuModel getModel( CustomArgument args ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteModel( AbstractShifuModel model ) throws Exception {
        try {
            if ( object instanceof Emprunt ) {
                Emprunt emprunt = ( Emprunt ) model;
                ShifuDAOFactory.getEmpruntDAO().delete( emprunt );
                emprunt.getAbonne().getEmprunts().remove( emprunt );
                Mediatheque.getInstance().notifyObservers();

            } else if ( object instanceof Ouvrage ) {
                Ouvrage ouvrage = ( Ouvrage ) object;
                ShifuDAOFactory.getOuvrageDAOC().delete( ouvrage );
                Mediatheque.getInstance().getListOuvrage().remove( ouvrage );
            }

        } catch ( Exception ex ) {
            throw ex;
        }
    }

}
