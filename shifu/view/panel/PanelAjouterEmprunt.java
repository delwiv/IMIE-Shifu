/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view.panel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shifu.core.Singleton;
import shifu.model.Adherent;
import shifu.model.Emprunt;
import shifu.model.Mediatheque;
import shifu.model.Ouvrage;
import shifu.model.dao.ShifuDAOFactory;

/**
 *
 * @author user
 */
public class PanelAjouterEmprunt extends javax.swing.JPanel {

    List<Adherent> listAdherents;
    Adherent adherentChoisi;
    List<Ouvrage> listOuvrages;
    Ouvrage ouvrageChoisi;
    List<Emprunt> empruntsCrees = new ArrayList();
    List<Emprunt> empruntsSupprimes = new ArrayList();
    Emprunt empruntSelect;

    /**
     * Creates new form PanelAjouterEmprunt
     */
    public PanelAjouterEmprunt() {
        initComponents();

        this.tableAdherents.addMouseListener( new MouseAdapter() {

            @Override
            public void mouseClicked( MouseEvent mouseEvent ) {
                super.mouseClicked( mouseEvent ); //To change body of generated methods, choose Tools | Templates.
                final int selectedRowIndex = tableAdherents.rowAtPoint( mouseEvent.getPoint() );
                adherentChoisi = Singleton.getInstance( Mediatheque.class).getListAdherents().get( selectedRowIndex );
                refreshTableEmprunts( adherentChoisi );
            }

        } );

        this.panelSearchOeuvre.getBtnRecherche().setVisible( false );
        this.panelSearchOeuvre.getTextSearch().addKeyListener( new KeyAdapter() {

            @Override
            public void keyTyped( KeyEvent e ) {
                if ( panelSearchOeuvre.getTextSearch().getText().length() > 1 ) {
                    new Thread( new Runnable() {

                        @Override
                        public void run() {
                            searchOuvrage( panelSearchOeuvre.getSearchArgs().toLowerCase() );
                        }
                    } ).start();
                }
            }
        } );

        this.panelSearchAbonne.getBtnRecherche().setVisible( false );
        this.panelSearchAbonne.getTextSearch().addKeyListener( new KeyAdapter() {
            @Override
            public void keyTyped( KeyEvent e ) {
                searchAdherent( panelSearchAbonne.getSearchArgs().toLowerCase() );
            }
        } );

        this.tableOeuvre.addMouseListener( new MouseAdapter() {

            @Override
            public void mouseClicked( MouseEvent mouseEvent ) {
                super.mouseClicked( mouseEvent ); //To change body of generated methods, choose Tools | Templates.
                final int selectedRowIndex = tableOeuvre.rowAtPoint( mouseEvent.getPoint() );
                ouvrageChoisi = listOuvrages.get( selectedRowIndex );
                btnValidOeuvre.setEnabled( true );

            }
        } );

        this.tableEmprunt.addMouseListener( new MouseAdapter() {

            @Override
            public void mouseClicked( MouseEvent mouseEvent ) {
                super.mouseClicked( mouseEvent ); //To change body of generated methods, choose Tools | Templates.
                final int selectedRowIndex = tableEmprunt.rowAtPoint( mouseEvent.getPoint() );
                empruntSelect = adherentChoisi.getEmprunts().get( selectedRowIndex );
            }
        } );

        btnValidOeuvre.addActionListener( ( ActionEvent e ) -> {
            Emprunt emprunt = new Emprunt( Date.valueOf( LocalDate.now() ), adherentChoisi, ouvrageChoisi );
            empruntsCrees.add( emprunt );
            adherentChoisi.getEmprunts().add( emprunt );
            refreshTableEmprunts( adherentChoisi );
        } );

        btnValider.addActionListener( ( ActionEvent e ) -> {
            empruntsCrees.stream().forEach( ( emprunt ) -> {
                ShifuDAOFactory.getEmpruntDAO().create( emprunt );
            } );
            empruntsCrees.clear();

            this.setVisible( false );
        } );
    }

    public void refreshAll() {
        this.listAdherents = Mediatheque.getInstance().getListAdherents();
        this.listOuvrages = Mediatheque.getInstance().getListOuvrage();
    }

    private void searchOuvrage( String searchArgs ) {
        List<Ouvrage> listOuvrages = new ArrayList();
        ListIterator<Ouvrage> it = Mediatheque.getInstance().getListOuvrage().listIterator( 0 );
        while ( it.hasNext() ) {
            Ouvrage ouvrage = it.next();
            if ( unAccent( ouvrage.getStrAuteurs() ).toLowerCase().contains( searchArgs )
                    || unAccent( ouvrage.getTitre() ).toLowerCase().contains( searchArgs )
                    || unAccent( ouvrage.getTitreOriginal() ).toLowerCase().contains( searchArgs )
                    || unAccent( ouvrage.getSousTitre() ).toLowerCase().contains( searchArgs )
                    || unAccent( ouvrage.getGenre().getLib() ).toLowerCase().contains( searchArgs )
                    || unAccent( ouvrage.getIsbn() ).toLowerCase().contains( searchArgs )
                    || unAccent( ouvrage.getCodeInterne() ).toLowerCase().contains( searchArgs ) ) {
                listOuvrages.add( ouvrage );

            }
        }
        refreshTableOeuvre( listOuvrages );
    }

    private void searchAdherent( String searchArgs ) {
        List<Adherent> lAdherent = new ArrayList();
        ListIterator<Adherent> it = Mediatheque.getInstance().getListAdherents().listIterator( 0 );

        while ( it.hasNext() ) {
            Adherent adherent = it.next();

            if ( unAccent( adherent.getNom() ).toLowerCase().contains( searchArgs )
                    || unAccent( adherent.getPrenom() ).toLowerCase().contains( searchArgs ) ) {
                lAdherent.add( adherent );
            }
        }
        refreshTableAdherent( lAdherent );

    }

    private void refreshTableEmprunts( Adherent adherentChoisi ) {

        List<Emprunt> listEmprunts = adherentChoisi.getEmprunts();
        DefaultTableModel model = ( DefaultTableModel ) this.tableEmprunt.getModel();
        btnValidOeuvre.setEnabled( false );
        model.setRowCount( 0 );
        for ( ListIterator it = listEmprunts.listIterator( 0 ) ; it.hasNext() ; ) {
            JButton btnSupprimerEmprunt = new JButton();
            btnSupprimerEmprunt.setText( "Supprimer" );
            btnSupprimerEmprunt.addActionListener( ( ActionEvent e ) -> {
                deleteEmprunt( listEmprunts.get( tableEmprunt.getSelectedRow() ) );
            } );
            Emprunt emprunt = ( Emprunt ) it.next();
            model.addRow( new Object[]{
                emprunt.getOuvrage().getTitre(),
                emprunt.getDateEmprunt(),
                emprunt.isIsRenouvele(),
                "", } );
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOeuvre = new javax.swing.JTable();
        panelSearchOeuvre = new shifu.view.panel.PanelSearch();
        jLabel1 = new javax.swing.JLabel();
        btnValidOeuvre = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        panelSearchAbonne = new shifu.view.panel.PanelSearch();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableEmprunt = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAdherents = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnValider = new javax.swing.JButton();
        btnAnnuler = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        jPanel1.setBackground(java.awt.SystemColor.controlHighlight);
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        tableOeuvre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titre", "Sous-titre", "Auteurs", "Genre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableOeuvre.setOpaque(false);
        tableOeuvre.setPreferredSize(null);
        jScrollPane1.setViewportView(tableOeuvre);
        if (tableOeuvre.getColumnModel().getColumnCount() > 0) {
            tableOeuvre.getColumnModel().getColumn(0).setResizable(false);
        }

        panelSearchOeuvre.setBackground(new java.awt.Color(204, 204, 204));
        panelSearchOeuvre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jLabel1.setText("Liste des ouvrages empruntables");

        btnValidOeuvre.setText("Ajouter cet ouvrage");
        btnValidOeuvre.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelSearchOeuvre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnValidOeuvre)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSearchOeuvre, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnValidOeuvre)
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        panelSearchAbonne.setBackground(new java.awt.Color(204, 204, 204));
        panelSearchAbonne.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        tableEmprunt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titre oeuvre", "Date début", "Renouvelé", "Date retour"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableEmprunt.setMaximumSize(new java.awt.Dimension(300, 2000));
        jScrollPane2.setViewportView(tableEmprunt);

        tableAdherents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Prenom", "E-mail"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableAdherents);

        jLabel4.setText("Choisir un adhérent");

        jLabel5.setText("Liste des prêts en cours");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelSearchAbonne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelSearchAbonne, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnValider.setText("Valider");

        btnAnnuler.setText("Annuler");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Ajouter des emprunts");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAnnuler)
                        .addGap(30, 30, 30)
                        .addComponent(btnValider)
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnValider)
                    .addComponent(btnAnnuler))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnValidOeuvre;
    private javax.swing.JButton btnValider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private shifu.view.panel.PanelSearch panelSearchAbonne;
    private shifu.view.panel.PanelSearch panelSearchOeuvre;
    private javax.swing.JTable tableAdherents;
    private javax.swing.JTable tableEmprunt;
    private javax.swing.JTable tableOeuvre;
    // End of variables declaration//GEN-END:variables

    public void refreshTableAdherent( List<Adherent> lAdherents ) {

        this.listAdherents = lAdherents;

        DefaultTableModel model = ( DefaultTableModel ) tableAdherents.getModel();

        model.setRowCount( 0 );

        if ( null != lAdherents ) {
            //On vide la jtable(refresh)
            for ( int i = 0 ; i < model.getRowCount() ; i++ ) {
                model.removeRow( i );
            }

            //on insere les oeuvre dans la table oeuvre
            ListIterator it = lAdherents.listIterator( 0 );
            int i = 0;
            while ( it.hasNext() ) {
                Adherent currentAdherent = ( Adherent ) it.next();
                Object[] row = {
                    currentAdherent.getNom(),
                    currentAdherent.getPrenom(),
                    currentAdherent.getEmail()
                };

                model.addRow( row );
            }

            this.tableAdherents = new JTable( model );

        }

    }

    public void refreshTableOeuvre( List<Ouvrage> listOuvrage ) {
        this.listOuvrages = listOuvrage;
        DefaultTableModel model = ( DefaultTableModel ) tableOeuvre.getModel();
        JButton btnAjoutOuvrage = new JButton();

        model.setRowCount( 0 );

        if ( null != listOuvrage ) {
            //On vide la jtable(refresh)
            model.setRowCount( 0 );

            //on insere les oeuvre dans la table oeuvre
            ListIterator it = listOuvrage.listIterator( 0 );
            int i = 0;
            while ( it.hasNext() ) {
                btnAjoutOuvrage.setText( "Ajouter emprunt" );
                btnAjoutOuvrage.addActionListener( ( ActionEvent e ) -> {
                    ajoutOuvrage( listOuvrage.get( tableOeuvre.getSelectedRow() ) );
                } );
                Ouvrage currentOuvrage = ( Ouvrage ) it.next();
                Object[] row = {
                    currentOuvrage.getTitre(),
                    currentOuvrage.getSousTitre(),
                    currentOuvrage.getStrAuteurs(),
                    currentOuvrage.getGenre().getLib(),
                    btnAjoutOuvrage
                };

                model.addRow( row );
            }

            this.tableOeuvre = new JTable( model );

        }
    }

    public PanelSearch getPanelSearchAbonne() {
        return panelSearchAbonne;
    }

    public PanelSearch getPanelSearchOeuvre() {
        return panelSearchOeuvre;
    }

    public JTable getTableAdherents() {
        return tableAdherents;
    }

    public void setListAdherents( List<Adherent> listAdherents ) {
        this.listAdherents = listAdherents;
    }

    public void setListOuvrages( List<Ouvrage> listOuvrages ) {
        this.listOuvrages = listOuvrages;
    }

    public List<Adherent> getListAdherents() {
        return listAdherents;
    }

    public List<Ouvrage> getListOuvrages() {
        return listOuvrages;
    }

    private void deleteEmprunt( Emprunt get ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    private void ajoutOuvrage( Ouvrage get ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    public String unAccent( String s ) {

        String temp = Normalizer.normalize( s, Normalizer.Form.NFD );
        Pattern pattern = Pattern.compile( "\\p{InCombiningDiacriticalMarks}+" );
        return pattern.matcher( temp ).replaceAll( "" );
    }

}
