/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shifu.core.AbstractShifuView;
import shifu.core.IListener;
import shifu.core.Singleton;
import shifu.core.parameters.AddModelParameters;
import shifu.core.tools.http.HTTPTools;
import shifu.core.tools.http.RunnableHttp;
import shifu.thirdparty.JSONObject;
import shifu.model.Auteur;
import shifu.model.EtatOuvrage;
import shifu.model.Genre;
import shifu.model.IdentifiantOeuvre;
import shifu.model.Livre;
import shifu.model.Mediatheque;
import shifu.model.Nationalite;
import shifu.model.Ouvrage;
import shifu.view.panel.PanelAjouterOuvrage;
import shifu.view.panel.PanelGestionOuvrages;
import shifu.view.panel.PanelGoogleResponse;

/**
 *
 * @author user
 */
public class ViewOuvrage extends AbstractShifuView {

    //private PanelAjouterOuvrage panelAjouterOuvrage;
    private PanelGestionOuvrages panelGestionOuvrages;
    private List<Auteur> listAuteurs;
    private List<Ouvrage> listOuvrages = Mediatheque.getInstance().getListOuvrage();
    private Livre livre;
    private JDialog jdialog;
    private boolean isWaitingForGoogle = false;
    Thread googleSearchThread;
    private boolean isWaintingforOuvrageSearch = false;

    public ViewOuvrage() {
        super();
        this.listAuteurs = new ArrayList();
    }

    public void displayGestionOeuvre() {
        cleanPanel();
        if ( null == panelGestionOuvrages ) {
            panelGestionOuvrages = new PanelGestionOuvrages( listOuvrages );
        }

//        cleanPanel();
        //Ajout du trigger pour ouvrir la view Ajout d'Oeuvre
        panelGestionOuvrages.getBtnAjoutOuvrage().addActionListener( this );

//        panels.add( panelOeuvre );
        frame.add( panelGestionOuvrages );
        panelGestionOuvrages.setVisible( true );
//        panelGestionOuvrages.getPanelSearch1().getBtnRecherche().addActionListener( this );

//        panVisible = 3;
        frame.pack();
    }

    private void addAuteursToOeuvre() {

        ListIterator it = listAuteurs.listIterator( 0 );
        // On met a jour la table
        DefaultTableModel model = ( DefaultTableModel ) getPanelAjouterOuvrage().getTableAuteurOeuvre().getModel();
        model.setRowCount( 0 );
        while ( it.hasNext() ) {
            Auteur currentAuteur = ( Auteur ) it.next();
            String[] row = {
                currentAuteur.getNom(),
                currentAuteur.getPrenom(),
                currentAuteur.getSurnom(),
                currentAuteur.getNationalite().getLib()
            };
            model.addRow( row );
        }
        getPanelAjouterOuvrage().setTableAuteurOeuvre( new JTable( model ) );
    }

    @Override
    public void update() {
        if ( isWaitingForGoogle ) {
            try {
                isWaitingForGoogle = false;
                jdialog.setVisible( false );
                getPanelAjouterOuvrage().getProgressBarGoogle().setVisible( false );
                jdialog.dispose();
                StringBuffer response = Singleton.getInstance( RunnableHttp.class ).getResponse();

                List<Ouvrage> listLivre = parseGoogleResponse( new JSONObject( response.toString() ) );
                for ( ListIterator<Ouvrage> it = listLivre.listIterator( 0 ) ; it.hasNext() ; ) {
                    System.out.println( it.next().toString() );

                }
                JDialog dialog = new JDialog();

                PanelGoogleResponse panelGoogleResponse = new PanelGoogleResponse( listLivre );
                panelGoogleResponse.getBtnCancel().addActionListener( new ActionListener() {
                    @Override
                    public void actionPerformed( ActionEvent e ) {
                        dialog.dispose();
                    }
                } );
                panelGoogleResponse.getBtnValid().addActionListener( new ActionListener() {

                    @Override
                    public void actionPerformed( ActionEvent e ) {
                        getPanelAjouterOuvrage().setLivre( panelGoogleResponse.getLivre() );
                    }
                } );

                dialog.setTitle( "Resultat de la recherche google" );
                dialog.add( panelGoogleResponse );
                dialog.setSize( panelGoogleResponse.getPreferredSize() );
                dialog.setVisible( true );

            } catch ( Exception ex ) {
                Logger.getLogger( ViewOuvrage.class.getName() ).log( Level.SEVERE, null, ex );
            }

        } else if ( isWaintingforOuvrageSearch ) {
            isWaintingforOuvrageSearch = false;
            List<Ouvrage> listOuvrages = null;
            try {
                listOuvrages = Mediatheque.getInstance().getListOuvrageRecherche();
            } catch ( Exception ex ) {
                Logger.getLogger( ViewOuvrage.class.getName() ).log( Level.SEVERE, null, ex );
            }

            try {
                getPanelGestionOuvrages().refreshTableOeuvre( listOuvrages );

            } catch ( Exception ex ) {
                Logger.getLogger( ViewOuvrage.class.getName() ).log( Level.SEVERE, null, ex );
            }

        }
    }

    public Livre createLivre() {
        livre = new Livre();
        livre.setTitre( getPanelAjouterOuvrage().getTextTitre().getText() );
        livre.setTitreOriginal( getPanelAjouterOuvrage().getTextTitreOriginal().getText() );
        livre.setSousTitre( getPanelAjouterOuvrage().getTextSousTitre().getText() );
        livre.setLangue( getPanelAjouterOuvrage().getTextLangue().getText() );
        livre.setCollection( getPanelAjouterOuvrage().getTextCollection().getText() );
        livre.setMaisonEdition( getPanelAjouterOuvrage().getTextMaisonEdition().getText() );
        try {
            livre.setDatePublication( Date.valueOf( LocalDate.parse( getPanelAjouterOuvrage().getTextDatePublication().getText() ) ) );
        } catch ( Exception ex ) {
        }
        livre.getIdentifiants().add( new IdentifiantOeuvre(
                0, "Code interne", getPanelAjouterOuvrage().getTextCodeInterne().getText(), 0 ) );
        livre.getIdentifiants().add( new IdentifiantOeuvre(
                0, "ISBN", getPanelAjouterOuvrage().getTextIsbn().getText(), 0 ) );
        livre.setNote( Integer.parseInt( ( String ) getPanelAjouterOuvrage().getComboboxNote().getSelectedItem() ) );
        livre.setFormat( getPanelAjouterOuvrage().getTextFormat().getText() );
        try {
            livre.setNbPages( Integer.parseInt( getPanelAjouterOuvrage().getTextNbPage().getText() ) );
        } catch ( Exception ex ) {
        }
        livre.setGenre( Mediatheque.getInstance().getListGenre().get( getPanelAjouterOuvrage().getCbbGenre().getSelectedIndex() ) );
        livre.setResume( getPanelAjouterOuvrage().getTextareaResume().getText() );
        livre.setGenre( new Genre( 2, "Divers", 2, null, null ) );

        livre.setEtat( new EtatOuvrage( 0,
                getPanelAjouterOuvrage().getTextCommentaireEtat().getText(),
                ( String ) getPanelAjouterOuvrage().getCbbEtatOuvrage().getSelectedItem(),
                getPanelAjouterOuvrage().getCbPretable().isSelected()
        ) );

        if ( listAuteurs.isEmpty() ) {
            livre.getAuteurs().add( new Auteur( "inconnu", "", "", "Auteur inconnu", new Nationalite( 0, "Inconnu", "INC" ) ) );
        } else {
            livre.setAuteurs( listAuteurs );
        }

        return livre;
    }

    @Override
    public void actionPerformed( ActionEvent event ) {

        if ( event.getSource() == panelGestionOuvrages.getBtnAjoutOuvrage() ) {
            //btnAjoutOuvrage

            cleanPanel();
//                panels.add( panelAjoutOeuvre );
            frame.add( getPanelAjouterOuvrage() );
            this.getPanelAjouterOuvrage().getProgressBarGoogle().setIndeterminate( true );
            getPanelAjouterOuvrage().setVisible( true );
            frame.pack();

            getPanelAjouterOuvrage().getBtnValider().addActionListener( this );

            // AJOUT DES AUTEURS
            getPanelAjouterOuvrage().getBtnSelectionAuteur().addActionListener( this );

            getPanelAjouterOuvrage().getBtnRecherche().addActionListener( this );

            frame.pack();
        } else if ( event.getSource() == getPanelAjouterOuvrage().getBtnValider() ) {

            AddModelParameters params = new AddModelParameters();

            params.setModel( createLivre() );
            try {
                notifyListeners( params );
            } catch ( Exception ex ) {
                new DialogException(ex );
            }
            cleanPanel();
            Singleton.getInstance( ViewOuvrage.class ).displayGestionOeuvre();
            getPanelGestionOuvrages().setListOuvrages( Mediatheque.getInstance().getListOuvrage() );
        } else if ( event.getSource() == getPanelAjouterOuvrage().getBtnSelectionAuteur() ) {

            try {
                ViewAuteur viewAuteur = Singleton.getInstance( ViewAuteur.class );
                viewAuteur.setFrame( frame );
                ListIterator it = listeners.listIterator( 0 );
                while ( it.hasNext() ) {
                    viewAuteur.addListener( ( IListener ) it.next() );
                }
                JDialog dialog = new JDialog();

                viewAuteur.displaySelectAuteur( dialog, listAuteurs );
                dialog.addWindowListener( new WindowAdapter() {

                    @Override
                    public void windowClosed( WindowEvent e ) {
                        super.windowClosed( e ); //To change body of generated methods, choose Tools | Templates.
                        listAuteurs = viewAuteur.getListAuteurs();
                        addAuteursToOeuvre();
                    }

                } );
            } catch ( Exception ex ) {
                Logger.getLogger( ViewOuvrage.class.getName() ).log( Level.SEVERE, null, ex );
            }

        } else if ( event.getSource() == getPanelAjouterOuvrage().getBtnRecherche() ) {

            try {
                RunnableHttp http = Singleton.getInstance( RunnableHttp.class );
                http.setProgressBar( this.getPanelAjouterOuvrage().getProgressBarGoogle() );
                http.addObserver( this );
                if ( null != googleSearchThread ) {
                    googleSearchThread.interrupt();
                }

                googleSearchThread = new Thread( http );

                http.setArgs( getPanelAjouterOuvrage().getTextRecherche().getText() );
                this.isWaitingForGoogle = true;
                googleSearchThread.start();

            } catch ( Exception ex ) {
                Logger.getLogger( ViewOuvrage.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
    }

    private List<Ouvrage> parseGoogleResponse( JSONObject jsonObject ) {
        List<Ouvrage> listLivres = new ArrayList();
        int nbLivres = jsonObject.getInt( "totalItems" );

        //google ne renvoie que 40 réponses
        if ( nbLivres > Integer.valueOf( HTTPTools.MAX_ITEM ) ) {
            nbLivres = Integer.valueOf( HTTPTools.MAX_ITEM );
        }

        for ( int i = 0 ; i < nbLivres ; i++ ) {
            System.out.println( "Boucle n°     " + ( i + 1 ) + "/" + nbLivres );
            try {
                listLivres.add( new Livre( jsonObject.getJSONArray( "items" )
                        .getJSONObject( i ).getJSONObject( "volumeInfo" ) ) );

            } catch ( Exception e ) {
                System.out.println( e.toString() );
            }

        }
        return listLivres;
    }

    public PanelAjouterOuvrage getPanelAjouterOuvrage() {
        PanelAjouterOuvrage panel = null;
        try {
            panel = Singleton.getInstance( PanelAjouterOuvrage.class );
        } catch ( Exception ex ) {
            Logger.getLogger( ViewOuvrage.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return panel;
    }

    public PanelGestionOuvrages getPanelGestionOuvrages() {
        return panelGestionOuvrages;
    }

}
