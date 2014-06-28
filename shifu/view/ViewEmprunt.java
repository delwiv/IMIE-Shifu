/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import shifu.core.AbstractShifuView;
import shifu.core.Singleton;
import shifu.model.Adherent;
import shifu.model.Mediatheque;
import shifu.model.Ouvrage;
import shifu.model.dao.ShifuDAOFactory;
import shifu.view.panel.PanelAjouterEmprunt;
import shifu.view.panel.PanelGestionEmprunts;

/**
 *
 * @author delwiv
 */
public class ViewEmprunt extends AbstractShifuView {

    private PanelGestionEmprunts panelGestionEmprunts;
    private PanelAjouterEmprunt panelAjoutEmprunt;
    private JDialog dialogEmprunt;

    public ViewEmprunt() {
        super();
    }

    public PanelAjouterEmprunt getPanelAjoutEmprunt() {
        if ( null == panelAjoutEmprunt ) {
            panelAjoutEmprunt = new PanelAjouterEmprunt();
        } else {
            panelAjoutEmprunt.refreshAll();
        }

        return panelAjoutEmprunt;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        if ( e.getSource() == panelGestionEmprunts.getBtnAjouterEmprunt() ) {
            if ( null == panelAjoutEmprunt ) {
                panelAjoutEmprunt = new PanelAjouterEmprunt();
            }
            panelAjoutEmprunt.refreshAll();
            panelAjoutEmprunt.refreshTableAdherent( panelAjoutEmprunt.getListAdherents() );
            panelAjoutEmprunt.refreshTableOeuvre( panelAjoutEmprunt.getListOuvrages() );
            panelAjoutEmprunt.getPanelSearchAbonne().getBtnRecherche().addActionListener( this );
            panelAjoutEmprunt.getPanelSearchOeuvre().getBtnRecherche().addActionListener( this );

            cleanPanel();

            frame.add( panelAjoutEmprunt, BorderLayout.CENTER );

            panelAjoutEmprunt.setVisible( true );
//            panelAjoutEmprunt. ().addActionListener( this );
//            panelAjoutEmprunt. ().addActionListener( this );
            frame.pack();

        } else if ( e.getSource() == getPanelAjoutEmprunt().getPanelSearchAbonne().getBtnRecherche() ) {

            String strToComp = getPanelAjoutEmprunt().getPanelSearchAbonne().getSearchArgs();

            List<Adherent> lAdherent = Mediatheque.getInstance().getListAdherents();
            List<Adherent> lAdherentToDisplay = new ArrayList();

            for ( Adherent adherent : lAdherent ) {
                if ( adherent.getNom().toLowerCase().contains( strToComp.toLowerCase() ) ) {
                    lAdherentToDisplay.add( adherent );
                }
                if ( adherent.getPrenom().toLowerCase().contains( strToComp.toLowerCase() ) ) {
                    lAdherentToDisplay.add( adherent );
                }
            }

            panelAjoutEmprunt.refreshTableAdherent( lAdherentToDisplay );

        } else if ( e.getSource() == getPanelAjoutEmprunt().getPanelSearchOeuvre().getBtnRecherche() ) {
            String strToComp = getPanelAjoutEmprunt().getPanelSearchOeuvre().getSearchArgs();

            List<Ouvrage> lOuvrage = Mediatheque.getInstance().getListOuvrage();
            List<Ouvrage> lOuvrageToDisplay = new ArrayList();

            for ( Ouvrage ouvrage : lOuvrage ) {
                if ( ouvrage.getTitre().toLowerCase().contains( strToComp.toLowerCase() ) ) {
                    lOuvrageToDisplay.add( ouvrage );
                }
            }

            panelAjoutEmprunt.refreshTableOeuvre( lOuvrageToDisplay );
        }

    }

    void displayGestionEmprunts() {
        cleanPanel();
        if ( null == panelGestionEmprunts ) {
            panelGestionEmprunts = new PanelGestionEmprunts();
        }

        panelGestionEmprunts.setEmprunts( ShifuDAOFactory.getEmpruntDAO().findAll() );

        frame.add( panelGestionEmprunts, BorderLayout.CENTER );
        panelGestionEmprunts.setVisible( true );
        panelGestionEmprunts.getBtnAjouterEmprunt().addActionListener( this );
        panelGestionEmprunts.getBtnRetourEmprunt().addActionListener( this );
//        frame.pack();
    }

}
