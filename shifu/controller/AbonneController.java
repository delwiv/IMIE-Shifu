/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shifu.controller;

import java.util.Collection;
import shifu.core.parameters.SearchParameters;
import shifu.model.Adherent;

/**
 *
 * @author delwiv
 */
public class AbonneController implements ISearchController {
    private Collection<Adherent> abonnes;
    private Adherent abonne;

    @Override
    public void doSearch( SearchParameters params ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
}
