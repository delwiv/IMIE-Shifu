/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import shifu.view.panel.PanelFormulaireAuteur;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import shifu.core.AbstractShifuView;
import shifu.core.Singleton;
import shifu.core.parameters.AddModelParameters;
import shifu.core.parameters.SearchParameters;
import shifu.model.Auteur;
import shifu.model.Mediatheque;
import shifu.model.Nationalite;
import shifu.view.panel.PanelAjouterAuteur;
import shifu.view.panel.PanelAjouterAuteurOeuvre;

/**
 *
 * @author user
 */
public class ViewAuteur extends AbstractShifuView {
    
    private PanelFormulaireAuteur panelAuteur;
    private PanelAjouterAuteurOeuvre panelAjouterAuteurOeuvre;
    private Auteur auteur;
    private List<Auteur> listAuteurs;
    private JDialog dialogAuteur;
    private boolean isWatingForAuteurSearch = false;
    
    public ViewAuteur() {
        super();
        this.auteur = new Auteur();
        auteur.addObserver( this );
    }

    /**
     * Update de la view auteur plusieurs actions sont possibles selon le
     * contexte On va donc déterminer le contexte ;)
     *
     */
    @Override
    public void update() throws SQLException {
        
        if ( isWatingForAuteurSearch ) {
            
            List<Auteur> auteursRecherche = Mediatheque.getInstance().getListAuteursRecherche();
            if ( null != auteursRecherche ) {
                this.listAuteurs = auteursRecherche;
                getPanelAjouterAuteurOeuvre().refreshJTable( listAuteurs );
            }
            // On vérifie si on est dans le dialog d'ajout d'auteur à une oeuvre
        } else if ( panelAjouterAuteurOeuvre.isVisible() ) {
            // si oui alors on met a jour la JTable du paneAjouterAuteurOeuvre
            // avec l'auteur fraichement créé
            panelAjouterAuteurOeuvre.addAuteur( auteur );
        } else {
            // alors on vient de valider l'ajout d'un ou plusieurs auteurs, on les 
            // rajoute à la JTable
        }
        
    }
    
    void switchDialog( JDialog dialog, Component currentComp, Component newComp ) {
        dialog.remove( currentComp );
        dialog.add( newComp );
        newComp.setVisible( true );
        dialog.revalidate();
        dialog.repaint();
    }
    
    public void displaySelectAuteur( JDialog dialog, final List<Auteur> currentAuteurs ) {
        try {
            // Creation du panel pour choisir un ou des auteurs et affichage dans un JDialog
            if ( null == panelAjouterAuteurOeuvre ) {
                panelAjouterAuteurOeuvre = new PanelAjouterAuteurOeuvre( currentAuteurs );
            }
            dialog.getContentPane().removeAll();
            dialog.add( panelAjouterAuteurOeuvre );
            dialog.setVisible( true );
            dialog.setSize( 800, 640 );

            //TRIGGER BOUTON AJOUT NOUVEL AUTEUR
            panelAjouterAuteurOeuvre.getBtnAjouterAuteur().addActionListener( new ActionListener() {
                
                @Override
                public void actionPerformed( ActionEvent e ) {
                    displayPanelChoisirAuteur( dialog );
                }
            } );
            // VALIDER LES AUTEURS CHOISIS
            panelAjouterAuteurOeuvre.getBtnValider().addActionListener( new ActionListener() {
                @Override
                public void actionPerformed( ActionEvent e ) {
                    listAuteurs = getAuteursChoisis( panelAjouterAuteurOeuvre.getTableAuteur() );
                    
                    dialog.dispose();
                }
                
            } );
            panelAjouterAuteurOeuvre.getPanelSearch().getBtnRecherche().addActionListener( this );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }
    
    private List<Auteur> getAuteursChoisis( JTable table ) {
        List<Auteur> auteurs = new ArrayList();
        for ( int i = 0 ; i < table.getRowCount() ; i++ ) {

            if ( ( Boolean ) table.getModel().getValueAt( i, 0 ) == true ) {
                // on recupere l'objet de la jtable à l'index qui vaut true
                System.out.println( "Auteur trouvé" );
                auteurs.add( panelAjouterAuteurOeuvre.getListAuteurs().get( i ) );
            }
        }
        return auteurs;
    }
    
    private void addAuteur( Auteur auteur ) throws Exception {
        auteur.addObserver( this );
        AddModelParameters addparam = new AddModelParameters();
        addparam.setModel( auteur );
        
        notifyListeners( addparam );
    }
    
    public List<Auteur> getListAuteurs() {
        return listAuteurs;
    }
    
    private void displayPanelChoisirAuteur( JDialog dialog ) {
        PanelAjouterAuteur panelAjout = new PanelAjouterAuteur();
        switchDialog( dialog, panelAjouterAuteurOeuvre, panelAjout );

        // VALIDATION NOUVEL AUTEUR (click sur OK)
        panelAjout.getBtnValider().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                createNouvelAuteur( panelAjout.getPanelFormulaireAuteur1() );
                switchDialog( dialog, panelAjout, panelAjouterAuteurOeuvre );
            }
            
        } );

        //ANNULER
        panelAjout.getBtnAnnuler().addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent e ) {
                switchDialog( dialog, panelAjout, panelAjouterAuteurOeuvre );
            }
        } );
        
    }

    /**
     * On recupere les champs du panel pour instancier un nouvel auteur
     *
     * @param dialog
     * @param panelAjout
     */
    private void createNouvelAuteur( PanelFormulaireAuteur panelFormAuteur ) {
        try {
            auteur = new Auteur(
                    panelFormAuteur.getNom(),
                    panelFormAuteur.getPrenom(),
                    panelFormAuteur.getSurnom(),
                    panelFormAuteur.getCommentaire(),
                    new Nationalite( panelFormAuteur.getNationalite() ) );
            addAuteur( auteur );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void actionPerformed( ActionEvent event ) {
        try {
            if ( event.getSource() == getPanelAjouterAuteurOeuvre().getPanelSearch().getBtnRecherche() ) {
                String args = getPanelAjouterAuteurOeuvre().getPanelSearch().getSearchArgs();
                if ( args.length() > 1 ) {
                    // on lance la recherche avec le contenu du textfield
                    SearchParameters params = new SearchParameters();
                    params.setSearchType( SearchParameters.SEARCH_AUTEUR );
                    params.setArgs( args );
                    isWatingForAuteurSearch = true;
                    Mediatheque.getInstance().addObserver( this );
                    notifyListeners( params );
                }
            }
         } catch ( Exception ex ) {
            new DialogException(ex );
        }
    }
    
    private PanelAjouterAuteurOeuvre getPanelAjouterAuteurOeuvre() throws SQLException {
        if ( null == panelAjouterAuteurOeuvre ) {
            panelAjouterAuteurOeuvre = new PanelAjouterAuteurOeuvre( listAuteurs );
        }
        return panelAjouterAuteurOeuvre;
    }
}
