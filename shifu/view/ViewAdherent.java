/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import shifu.core.AbstractShifuView;
import shifu.core.Singleton;
import shifu.core.parameters.AddModelParameters;
import shifu.core.parameters.EditModelParameters;
import shifu.model.Adherent;
import shifu.model.Adresse;
import shifu.model.Emprunt;
import shifu.model.Mediatheque;
import shifu.model.dao.ShifuDAOFactory;
import shifu.view.panel.PanelAjouterAdherent;
import shifu.view.panel.PanelConsulterAdherent;
import shifu.view.panel.PanelGestionAdherent;
import shifu.view.panel.PanelValiderAdherentAttente;

/**
 *
 * @author user
 */
public class ViewAdherent extends AbstractShifuView {

    private PanelGestionAdherent panelAdherent;
    private PanelAjouterAdherent panelAjoutAdherent;
    private PanelValiderAdherentAttente panelValiderAdherentAttente;
    private PanelConsulterAdherent panelConsulterAdherent;
    private Adherent adherent;
    private JDialog dialog;
    private Adresse adresse;
    private List<Adherent> listadherent;
    private boolean alreadyAdded;

    public ViewAdherent() {
        super();
        //on affiche la liste des adhérent
    }

    public PanelGestionAdherent getPanelGestionAdherent() {
        if ( null == this.panelAdherent ) {
            panelAdherent = new PanelGestionAdherent();
        }

        panelAdherent.refreshJtable( Mediatheque.getInstance().getListAdherents() );

        return panelAdherent;
    }

    @Override
    public void update() {
        panelAdherent.refreshJtable( Mediatheque.getInstance().getListAdherents() );
    }

    public PanelAjouterAdherent getPanelAjouterAdherent() {
        if ( null == this.panelAjoutAdherent ) {
            panelAjoutAdherent = new PanelAjouterAdherent();
        }
        return panelAjoutAdherent;
    }

    public PanelValiderAdherentAttente getPanelValiderAdherent() {
        if ( null == this.panelValiderAdherentAttente ) {
            panelValiderAdherentAttente = new PanelValiderAdherentAttente();
        }
        return panelValiderAdherentAttente;
    }

    public PanelConsulterAdherent getPanelConsulterAdherent() {
        if ( null == this.panelConsulterAdherent ) {
            panelConsulterAdherent = new PanelConsulterAdherent();
        }
        return panelConsulterAdherent;
    }

    public JDialog getDialog() {
        if ( null == dialog ) {
            dialog = new JDialog();
        }
        return dialog;
    }

    @Override
    public void actionPerformed( ActionEvent event ) {

        if ( event.getSource() == getPanelGestionAdherent().getBtnAjoutAdherent() ) {

            alreadyAdded = false;
            //panel Ajout adherent
            getDialog().getContentPane().removeAll();
            getDialog().setTitle( "Ajout adhérent" );
            getPanelAjouterAdherent().clearPanel();
            getDialog().add( getPanelAjouterAdherent() );
            getPanelAjouterAdherent().getBtnValiderAjoutAdherent().addActionListener( this );
            getPanelAjouterAdherent().getBtnAnnuler().addActionListener( this );
            getDialog().setSize( panelAjoutAdherent.getMinimumSize() );
            getDialog().setLocationRelativeTo( null );
            getDialog().setVisible( true );

        } else if ( event.getSource() == getPanelAjouterAdherent().getBtnValiderAjoutAdherent() ) {
            try {
                //Bouton Valider PanelAjouterAdherent

                createAdherent();
            } catch ( Exception ex ) {
                new DialogException(ex );
            }

            getDialog().dispose();

        } else if ( event.getSource()
                == getPanelAjouterAdherent().getBtnAnnuler() ) {
            //bouton annuler PanelAjouterAdherent
            getDialog().dispose();

        } else if ( event.getSource()
                == getPanelGestionAdherent().getBtnValiderAdherent() ) {
            //panel valider adherents
            getDialog().getContentPane().removeAll();
            getDialog().setTitle( "Valider des adhésions" );
            getDialog().add( getPanelValiderAdherent() );
            getPanelValiderAdherent().getBtnFermer().addActionListener( this );

            getDialog().setSize( 800, 700 );
            getDialog().setLocationRelativeTo( null );
            getDialog().setVisible( true );

        } else if ( event.getSource()
                == getPanelValiderAdherent().getBtnFermer() ) {

            //bouton fermer PanelValiderAdherent
            getDialog().dispose();

        } else if ( event.getSource()
                == getPanelGestionAdherent().getBtnModifierAdherent() ) {

            //panel modifier/consulter adhérent
            getDialog().getContentPane().removeAll();
            getDialog().add( getPanelConsulterAdherent() );
            getDialog().setTitle( "Modifier un adhérent" );
            getPanelConsulterAdherent().getBtnAnnuler().addActionListener( this );
            getPanelConsulterAdherent().getBtnValider().addActionListener( this );
            getDialog().setSize( panelConsulterAdherent.getMinimumSize() );
            try {
                adherent = getAdherentByID( ( Integer ) panelAdherent.getTableAdherent().getValueAt( panelAdherent.getRow(), 0 ) );

                ListIterator itAdhEmprunt = adherent.getEmprunts().listIterator( 0 );

                List<Emprunt> listEmprunts = ShifuDAOFactory.getEmpruntDAO().findAll();

                DefaultTableModel model = ( DefaultTableModel ) panelConsulterAdherent.getTableActifEmprunt().getModel();
                model.setRowCount( 0 );
                Calendar c = Calendar.getInstance();
                for ( ListIterator<Emprunt> it = listEmprunts.listIterator( 0 ) ; it.hasNext() ; ) {
                    Emprunt emprunt = it.next();

                    //date retour
                    c.setTime( emprunt.getDateEmprunt() );
                    c.add( Calendar.DATE, 14 );
                    //Retard
                    boolean retard = false;
                    Date dateRetour = new Date( c.getTimeInMillis() );
                    if ( Date.valueOf( LocalDate.now() ).after( dateRetour ) ) {
                        retard = true;
                    }

                    if ( emprunt.getIdIndividu() == adherent.getID() ) {
                        model.addRow( new Object[]{
                            emprunt.getOuvrage().getTitre(),
                            emprunt.getDateEmprunt(),
                            dateRetour,
                            retard

                        } );
                    }
                }

                System.out.println( panelAdherent.getRow() );
                loadAdherent( adherent );
                getDialog().setLocationRelativeTo( null );
                getDialog().setVisible( true );
            } catch ( SQLException ex ) {
                new DialogException(ex );
            }

       
            loadAdherent( adherent );
            getDialog().setLocationRelativeTo( null );
            getDialog().setVisible( true );
        } else if ( event.getSource()
                == getPanelGestionAdherent().getBtnSupprimerAdherent() ) {
            Adherent adherent = Mediatheque.getInstance().getListAdherents().get( getPanelGestionAdherent().getRow() );
            try {
                ShifuDAOFactory.getAdherentDAOC().delete( adherent );

                Mediatheque.getInstance().getListAdherents().remove( adherent );
                this.getPanelGestionAdherent().refreshJtable( Mediatheque.getInstance().getListAdherents() );
            } catch ( SQLException ex ) {
                new DialogException(ex );
            }
        } else if ( event.getSource()
                == getPanelConsulterAdherent().getBtnValider() ) {

            try {
                //bouton Valider PanelConsulterAdherent
                updateAdherent(adherent.getID());
            } catch ( Exception ex ) {
                new DialogException(ex);
            }
            getDialog().dispose();

        } else if ( event.getSource()
                == getPanelConsulterAdherent().getBtnAnnuler() ) {

            //bouton Annuler PanelConsulterAdherent
            getDialog().dispose();

        } else if ( event.getSource()
                == getPanelGestionAdherent().getPanelSearchAdh().getBtnRecherche() ) {

            //MVC
//            SearchParameters searchParam = new SearchParameters( SearchParameters.SEARCH_ADHERENT );
//
//            searchParam.setArgs( getPanelGestionAdherent().getPanelSearchAdh().getTextSearch().getText() );
//
//            try {
//                Mediatheque.getInstance().addObserver( this );
//            } catch ( Exception e ) {
//            }
//
//            isWaitingForSearchAdherent = true;
//            notifyListeners( searchParam );
            //Local view
            //on recupère le model de la jTable Adherent
            DefaultTableModel model = ( DefaultTableModel ) getPanelGestionAdherent().getTableAdherent().getModel();

            //on recupère le texte a rechercher
            String textToSearch = getPanelGestionAdherent().getPanelSearchAdh().getSearchArgs();

            List<Adherent> bufferListAdherent = new ArrayList<Adherent>();
            List<Adherent> ListAdherents = Mediatheque.getInstance().getListAdherents();

            for ( Adherent a : ListAdherents ) {
                if ( a.getNom().toLowerCase().contains( textToSearch.toLowerCase() ) || a.getPrenom().toLowerCase().contains( textToSearch.toLowerCase() ) ) {
                    bufferListAdherent.add( a );
                }
            }

//                
//            SearchParameters searchParam = new SearchParameters( SearchParameters.SEARCH_ADHERENT );
//
//            String searchArg = getPanelGestionAdherent().getPanelSearchAdh().getSearchArgs();
//            if ( searchArg.length() > 1 ) {
//
//                searchParam.setArgs( searchArg );
//
//                try {
//                    Mediatheque.getInstance().addObserver( this );
//                } catch ( Exception e ) {
//                }
//
//                isWaitingForSearchAdherent = true;
//                notifyListeners( searchParam );
            panelAdherent.refreshJtable( bufferListAdherent );

        }

    }

    void displayGestionAdherent() {
        cleanPanel();

        frame.add( getPanelGestionAdherent(), BorderLayout.CENTER );
        panelAdherent.setVisible( true );

        //evenement des bouton du panel gestion adherent
        panelAdherent.getBtnValiderAdherent().addActionListener( this );
        panelAdherent.getBtnAjoutAdherent().addActionListener( this );
        panelAdherent.getBtnModifierAdherent().addActionListener( this );
        panelAdherent.getBtnSupprimerAdherent().addActionListener( this );

        panelAdherent.getPanelSearchAdh().getBtnRecherche().addActionListener( this );

        frame.pack();
    }

    public void createAdherent() throws Exception {

        System.out.println( "Déja créé : " + alreadyAdded );
        if ( !alreadyAdded ) {

            Adherent adherentPanel = new Adherent();
            adherentPanel.setNom( panelAjoutAdherent.getNom() );
            adherentPanel.setPrenom( panelAjoutAdherent.getPrenom() );
            adherentPanel.setTitre( panelAjoutAdherent.getTitre() );
            adherentPanel.setEmail( panelAjoutAdherent.getEmail() );
            adherentPanel.setTel( panelAjoutAdherent.getTel() );
            adresse = new Adresse(
                    panelAjoutAdherent.getligne1(),
                    panelAjoutAdherent.getligne2(),
                    panelAjoutAdherent.getligne3(),
                    panelAjoutAdherent.getCp(),
                    panelAjoutAdherent.getVille() );
            adherentPanel.setAdresse( adresse );

            //date adhésion = date création
            adherentPanel.setDateAdhesion( Date.valueOf( LocalDate.now() ) );

            adherentPanel.setDateNaissance( panelAjoutAdherent.getDateNaissance() );

            alreadyAdded = true;
            AddModelParameters addparam = new AddModelParameters();
            Singleton
                    .getInstance( Mediatheque.class
                    ).addObserver( this );
            addparam.setModel( adherentPanel );

            notifyListeners( addparam );
        }
    }

    public Adherent getAdherent() {
        if ( null == adherent ) {
            adherent = new Adherent();
        }
        return adherent;
    }

    public void updateAdherent(int ID ) throws Exception {
        Adherent adherentUpdate = new Adherent();
        adherentUpdate.setID( ID );
        adherentUpdate.setNom( panelConsulterAdherent.getPanelFormulaireAdhérent1().getNom() );
        adherentUpdate.setPrenom( panelConsulterAdherent.getPanelFormulaireAdhérent1().getPrenom() );
        adherentUpdate.setTitre( panelConsulterAdherent.getPanelFormulaireAdhérent1().getTitre() );
        adherentUpdate.setTel( panelConsulterAdherent.getPanelFormulaireAdhérent1().getTel() );
        adherentUpdate.setDateNaissance( panelConsulterAdherent.getPanelFormulaireAdhérent1().getDateNaissance() );
        adherentUpdate.setEmail( panelConsulterAdherent.getPanelFormulaireAdhérent1().getEmail() );

        Adresse adresseUpdate = new Adresse(
                panelConsulterAdherent.getPanelFormulaireAdhérent1().getTextAdresse(),
                panelConsulterAdherent.getPanelFormulaireAdhérent1().getTextBatiment(),
                panelConsulterAdherent.getPanelFormulaireAdhérent1().getTextAppart(),
                panelConsulterAdherent.getPanelFormulaireAdhérent1().getTextCp(),
                panelConsulterAdherent.getPanelFormulaireAdhérent1().getVille() );
        
        
        
        adresseUpdate.setID_adresse( adherent.getAdresse().getID_adresse());
        
        adherentUpdate.setAdresse( adresseUpdate );
        
            EditModelParameters editModelParam = new EditModelParameters();
            Singleton.getInstance( Mediatheque.class ).addObserver( this );
            editModelParam.setModel(adherentUpdate);
            notifyListeners( editModelParam );
        
    }

    public Adherent getAdherentByID( int id ) throws SQLException {

        adherent = ShifuDAOFactory.getAdherentDAOC().getByID( id );

        return adherent;
    }

    public void loadAdherent( Adherent adherent ) {

        panelConsulterAdherent.getPanelFormulaireAdhérent1().setNom( adherent.getNom() );
        panelConsulterAdherent.getPanelFormulaireAdhérent1().setPrenom( adherent.getPrenom() );
        panelConsulterAdherent.getPanelFormulaireAdhérent1().setTitre( adherent.getTitre() );
        panelConsulterAdherent.getPanelFormulaireAdhérent1().setEmail( adherent.getEmail() );
        panelConsulterAdherent.getPanelFormulaireAdhérent1().setTel( adherent.getTel() );
        panelConsulterAdherent.getPanelFormulaireAdhérent1().setTextAdresse( adherent.getAdresse().getLigne1_adresse() );
        panelConsulterAdherent.getPanelFormulaireAdhérent1().setTextBatiment( adherent.getAdresse().getLigne2_adresse() );
        panelConsulterAdherent.getPanelFormulaireAdhérent1().setTextAppart( adherent.getAdresse().getLigne3_adresse() );
        panelConsulterAdherent.getPanelFormulaireAdhérent1().setTextVille( adherent.getAdresse().getVille_adresse() );
        panelConsulterAdherent.getPanelFormulaireAdhérent1().setTextCp( adherent.getAdresse().getCP_adresse() );
        panelConsulterAdherent.getPanelFormulaireAdhérent1().setDateChooserNaissance( adherent.getDateNaissance() );
    }

}
