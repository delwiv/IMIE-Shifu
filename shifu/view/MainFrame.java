
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.jdom2.Element;
import shifu.core.AbstractShifuView;
import shifu.core.IListener;
import shifu.core.Singleton;
import shifu.core.parameters.LeaveParameters;
import shifu.core.tools.xml.XmlParser;
import shifu.model.Livre;
import shifu.model.dao.ShifuDAOFactory;
import shifu.view.panel.PanelMenu;

/**
 *
 * @author delwiv
 */
public class MainFrame extends AbstractShifuView {

    /**
     * Creates new form MainFrame
     */
    public MainFrame( IListener listener ) {
        addListener( listener );
        frame = new JFrame();
        
        frame.setLayout( new BorderLayout() );

        //envoi de la jframe
        frame.setName( "Gestion Clés d'Asie" );
        frame.setTitle( "Gestion Clés d'Asie" );
        
        frame.setJMenuBar( getMenuBar() );
        
        panelMenu = new PanelMenu();
        this.panelMenu.setVisible( true );

        //Ajout du trigger pour ouvrir la view adhérents
        panelMenu.getBtnGestionAdherent().addActionListener( this );

        //Ajout du trigger pour ouvrir la view Emprunts
        panelMenu.getBtnGestionEmprunt().addActionListener( this );

        //Ajout du trigger pour ouvrir la view Oeuvre
        panelMenu.getBtnGestionOeuvre().addActionListener( this );
        
        panelMenu.getBtnImportBookin().addActionListener( this );
        
        frame.add( this.panelMenu, BorderLayout.NORTH );
//        this.panelMenu.setParent( this );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setPreferredSize( new Dimension( 1400, 1100 ) );
        frame.setLocationRelativeTo( null );
        
        frame.addWindowListener( new WindowAdapter() {
            
            @Override
            public void windowClosed( WindowEvent e ) {
                try {
                    leave();
                } catch ( Exception ex ) {
                    Logger.getLogger( this.getClass().getName() ).log( Level.SEVERE, null, ex );
                }
                
            }
            
        } );
        
        frame.pack();
    }
    
    public void displayMainScreen() {
        
        frame.setVisible( true );
    }
    
    public static void setListeners( AbstractShifuView source, AbstractShifuView destination ) {
        destination.setListeners( source.getListeners() );
    }
    
    public void addPanelOeuvre() {
        cleanPanel();
        
    }
    
    @Override
    public void update() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void actionPerformed( ActionEvent event ) {
        cleanPanel();
        try {
            
            if ( event.getSource() == panelMenu.getBtnGestionAdherent() ) {
                launchGestionAdherents();
                
            } else if ( event.getSource() == panelMenu.getBtnGestionEmprunt() ) {
                launchGestionEmprunts();
                
            } else if ( event.getSource() == panelMenu.getBtnGestionOeuvre() ) {
                launchGestionOeuvres();
                
            }
//            else if ( event.getSource() == panelMenu.getBtnImportBookin() ) {
//                ViewImporterXml viewXml = new ViewImporterXml();
//            }
        } catch ( Exception e ) {
            System.out.println( e.toString() );
        }
    }
    
    private void launchGestionAdherents() {
        ViewAdherent viewAdherent = Singleton.getInstance( ViewAdherent.class );
        setListeners( this, viewAdherent );
        viewAdherent.setFrame( frame );
        viewAdherent.displayGestionAdherent();
    }
    
    private void launchGestionEmprunts() {
        ViewEmprunt viewEmprunt = Singleton.getInstance( ViewEmprunt.class );
        setListeners( this, viewEmprunt );
        viewEmprunt.setFrame( frame );
        viewEmprunt.displayGestionEmprunts();
    }
    
    private void launchGestionOeuvres() {
        ViewOuvrage viewOuvrage = Singleton.getInstance( ViewOuvrage.class );
        setListeners( this, viewOuvrage );
        viewOuvrage.setFrame( frame );
        viewOuvrage.displayGestionOeuvre();
    }
    
    public void leave() throws Exception {
        notifyListeners( new LeaveParameters() );
    }
    
    private JMenuBar getMenuBar() {
        JMenu jMenu1;
        JMenu jMenu3;
        JMenu jMenu4;
        JMenuBar jMenuBar1;
        JMenuItem jMenuItem1;
        JMenuItem jMenuItem2;
        JMenuItem miAddAdherent;
        JMenu miAddOuvrage;
        JMenuItem miConsultOuvrage;
        JMenuItem miGestionAdherents;
        JMenuItem miGestionOuvrages;
        JMenuItem miQuit;
        JMenuItem miVoirAdherentsAValider;
        
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miQuit = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        miAddOuvrage = new javax.swing.JMenu();
        miGestionOuvrages = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        miConsultOuvrage = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        miGestionAdherents = new javax.swing.JMenuItem();
        miAddAdherent = new javax.swing.JMenuItem();
        miVoirAdherentsAValider = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        
        jMenuBar1.setName( "jMenuBar1" ); // NOI18N

        jMenu1.setText( "Fichier" );
        jMenu1.setName( "jMenu1" ); // NOI18N

        miQuit.setAccelerator( javax.swing.KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK ) );
        miQuit.setText( "Quitter" );
        miQuit.setName( "miQuit" ); // NOI18N
        miQuit.addActionListener( new ActionListener() {
            
            @Override
            public void actionPerformed( ActionEvent e ) {
                try {
                    notifyListeners( new LeaveParameters() );
                } catch ( Exception ex ) {
//                    Logger.getLogger( MainFrame.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
        } );
        jMenu1.add( miQuit );
        
        jMenuItem1.setAccelerator( javax.swing.KeyStroke.getKeyStroke( java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK ) );
        jMenuItem1.setText( "Importer depuis Bookin" );
        jMenuItem1.setName( "jMenuItem1" ); // NOI18N
        jMenuItem1.addActionListener( new java.awt.event.ActionListener() {
            
            public void actionPerformed( java.awt.event.ActionEvent evt ) {
                try {
                    jMenuItem1ActionPerformed( evt );
                } catch ( SQLException ex ) {
//                    Logger.getLogger( MainFrame.class.getName() ).log( Level.SEVERE, null, ex );
                }
            }
        }
        );
        jMenu1.add( jMenuItem1 );
        
        jMenuBar1.add( jMenu1 );
        
        miAddOuvrage.setText( "Ouvrages" );
        miAddOuvrage.setName( "miAddOuvrage" ); // NOI18N

        miGestionOuvrages.setText( "Gestion des Ouvrages" );
        miGestionOuvrages.setName( "miGestionOuvrages" ); // NOI18N
        miAddOuvrage.add( miGestionOuvrages );
        
        jMenuItem2.setText( "Ajouter un ouvrage" );
        jMenuItem2.setName( "jMenuItem2" ); // NOI18N
        miAddOuvrage.add( jMenuItem2 );
        
        miConsultOuvrage.setText( "Consulter un ouvrage" );
        miConsultOuvrage.setName( "miConsultOuvrage" ); // NOI18N
        miAddOuvrage.add( miConsultOuvrage );
        
        jMenuBar1.add( miAddOuvrage );
        
        jMenu3.setText( "Adhérents" );
        jMenu3.setName( "jMenu3" ); // NOI18N

        miGestionAdherents.setText( "Gestion des adhérents" );
        miGestionAdherents.setName( "miGestionAdherents" ); // NOI18N
        jMenu3.add( miGestionAdherents );
        
        miAddAdherent.setText( "Ajouter un adhérent" );
        miAddAdherent.setName( "miAddAdherent" ); // NOI18N
        jMenu3.add( miAddAdherent );
        
        miVoirAdherentsAValider.setText( "Voir adhérents à valider" );
        miVoirAdherentsAValider.setName( "miVoirAdherentsAValider" ); // NOI18N
        jMenu3.add( miVoirAdherentsAValider );
        
        jMenuBar1.add( jMenu3 );
        
        jMenu4.setText( "Emprunts" );
        jMenu4.setName( "jMenu4" ); // NOI18N
        jMenuBar1.add( jMenu4 );
        
        return jMenuBar1;
    }
    
    private void jMenuItem1ActionPerformed( java.awt.event.ActionEvent evt ) throws SQLException {
        List<Livre> listLivres = new ArrayList();
        JFileChooser fileChooser = new JFileChooser();
        int responseVal = fileChooser.showOpenDialog( new JFrame() );
        
        if ( responseVal == JFileChooser.APPROVE_OPTION ) {
            listLivres = getLivresFromXml( fileChooser.getSelectedFile().getAbsolutePath() );

//                    livresBookin = parseFile( fileChooser.getSelectedFile().getAbsolutePath() );
            ListIterator it = listLivres.listIterator( 0 );
            while ( it.hasNext() ) {
                Livre cur = ( Livre ) it.next();
//                        System.out.println( cur.toString() );
                ShifuDAOFactory.getLivreDAOC().create( cur );
                
            }
        }
    }
    
    private List<Livre> getLivresFromXml( String absolutePath ) {
        List<Livre> listLivre = new ArrayList();
        
        XmlParser xmlParser = new XmlParser( absolutePath );
        
        List<Element> listElement = xmlParser.getRacine().getChildren( "Book" );
        ListIterator it = listElement.listIterator( 0 );
        
        while ( it.hasNext() ) {
            try {
                listLivre.add( new Livre( ( Element ) it.next() ) );
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }
        return listLivre;
        
    }
}
