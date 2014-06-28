/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shifu.core.IListener;
import shifu.core.Singleton;
import shifu.core.parameters.SearchParameters;
import shifu.core.parameters.ShifuParameters;
import shifu.model.Adherent;
import shifu.model.Auteur;
import shifu.model.Mediatheque;
import shifu.model.Ouvrage;
import shifu.model.dao.CustomArgument;

/**
 *
 * @author delwiv
 */
public class SearchController implements ISearchController, IListener {

    @Override
    public void doSearch( SearchParameters searchParameters ) {
        try {

            switch ( searchParameters.getSearchType() ) {
                case SearchParameters.SEARCH_ADHERENT:
                    List<Adherent> adherents = Singleton.getInstance( AdherentController.class )
                            .getListModel( new CustomArgument( null, searchParameters.getArgs() ) );
                    Mediatheque.getInstance().setListAdherentsRecherche( adherents );
                    break;
                case SearchParameters.SEARCH_AUTEUR:
                    List<Auteur> auteurs = Singleton.getInstance( AuteurController.class )
                            .getListModel( new CustomArgument( null, searchParameters.getArgs() ) );
                    Mediatheque.getInstance().setListAuteursRecherche( auteurs );
                    break;
                case SearchParameters.SEARCH_OUVRAGE:
                    List<Ouvrage> ouvrages = Singleton.getInstance( OuvrageController.class )
                            .getListModel( new CustomArgument( null, searchParameters.getArgs() ) );
                    Mediatheque.getInstance().setListOuvrageRecherche( ouvrages );
                    break;
            }
        } catch ( Exception ex ) {
            Logger.getLogger( SearchController.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    @Override
    public void update( ShifuParameters params ) {
        SearchParameters searchParams = ( SearchParameters ) params;
        String searchArgs = searchParams.getArgs();
        String table = "";

    }

}
