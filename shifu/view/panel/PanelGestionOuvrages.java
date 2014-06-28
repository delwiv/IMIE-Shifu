/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shifu.model.Mediatheque;
import shifu.model.Ouvrage;
import shifu.model.dao.ShifuDAOFactory;
import shifu.view.DialogException;

/**
 *
 * @author user
 */
public class PanelGestionOuvrages extends javax.swing.JPanel {

    Ouvrage selectedOuvrage;
    List<Ouvrage> listOuvrages;
    Thread threadSearchOuvrage;

    /**
     * Creates new form PanelOeuvre
     *
     * @param listOuvrage
     */
    public PanelGestionOuvrages( List<Ouvrage> listOuvrage ) {
        initComponents();
        this.listOuvrages = listOuvrage;
        this.panelSearch1.getBtnRecherche().setVisible( false );
        refreshTableOeuvre( listOuvrages );
        this.panelSearch1.getTextSearch().addKeyListener( new KeyAdapter() {

            @Override
            public void keyTyped( KeyEvent e ) {
                btnConsultOuvrage.setEnabled( false );
                btnSupprimerOuvrage.setEnabled( false );

                if ( panelSearch1.getSearchArgs().length() > 0 ) {
                    threadSearchOuvrage = new Thread(
                            new SearchOuvrageRunnable( threadSearchOuvrage )
                    );
                    threadSearchOuvrage.start();
                } else if ( e.getKeyCode() == KeyEvent.VK_BACK_SPACE
                        && panelSearch1.getSearchArgs().length() == 1 ) {
                    //ici on vient d'effacer le dernier caractère
                    refreshTableOeuvre();
                }
            }
        } );
    }

    public final void refreshTableOeuvre() {
        refreshTableOeuvre( null );
    }

    public final void refreshTableOeuvre( List<Ouvrage> listOuvrage ) {

        DefaultTableModel model = ( DefaultTableModel ) tableOeuvre.getModel();

        model.setRowCount( 0 );

        if ( null == listOuvrage ) {
            listOuvrage = this.listOuvrages;
        }

        //on insere les oeuvre dans la table oeuvre
        ListIterator it = listOuvrage.listIterator( 0 );
        int i = 0;
        while ( it.hasNext() ) {
            Ouvrage currentOuvrage = ( Ouvrage ) it.next();
            model.addRow( new Object[]{
                currentOuvrage.getTitre(),
                currentOuvrage.getSousTitre(),
                currentOuvrage.getTitreOriginal(),
                currentOuvrage.getStrAuteurs(),
                currentOuvrage.getGenre().getLib(),
                currentOuvrage.getDatePublication(),
                currentOuvrage.getLangue(),
                currentOuvrage.getNationalite().getLib()
            } );
        }
        this.tableOeuvre = new JTable( model );
    }

    public String unAccent( String s ) {

        String temp = Normalizer.normalize( s, Normalizer.Form.NFD );
        Pattern pattern = Pattern.compile( "\\p{InCombiningDiacriticalMarks}+" );
        return pattern.matcher( temp ).replaceAll( "" );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableOeuvre = new javax.swing.JTable();
        btnAjoutOuvrage = new javax.swing.JButton();
        panelSearch1 = new shifu.view.panel.PanelSearch();
        jLabel1 = new javax.swing.JLabel();
        btnConsultOuvrage = new javax.swing.JButton();
        btnSupprimerOuvrage = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        tableOeuvre.setAutoCreateRowSorter(true);
        tableOeuvre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titre", "Sous-titre", "Titre original", "Auteur", "Genre", "Date de publication", "Langue", "Nationalité"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableOeuvre.setEditingColumn(0);
        tableOeuvre.setEditingRow(0);
        tableOeuvre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableOeuvreMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableOeuvre);

        btnAjoutOuvrage.setText("Ajouter un ouvrage");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Gestion des ouvrages");

        btnConsultOuvrage.setText("Consulter/modifier ouvrage");
        btnConsultOuvrage.setEnabled(false);
        btnConsultOuvrage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultOuvrageActionPerformed(evt);
            }
        });

        btnSupprimerOuvrage.setText("Supprimer ouvrage");
        btnSupprimerOuvrage.setEnabled(false);
        btnSupprimerOuvrage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerOuvrageActionPerformed(evt);
            }
        });

        jButton1.setText("Rafraichir la liste");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAjoutOuvrage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsultOuvrage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSupprimerOuvrage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(panelSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panelSearch1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAjoutOuvrage, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                        .addComponent(btnConsultOuvrage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSupprimerOuvrage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableOeuvreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableOeuvreMouseClicked
        int selectedRow = this.tableOeuvre.rowAtPoint( evt.getPoint() );
        this.selectedOuvrage = listOuvrages.get( selectedRow );
        this.btnConsultOuvrage.setEnabled( true );
        this.btnSupprimerOuvrage.setEnabled( true );
    }//GEN-LAST:event_tableOeuvreMouseClicked

    private void btnSupprimerOuvrageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerOuvrageActionPerformed
        try {
            ShifuDAOFactory.getOuvrageDAOC().delete( selectedOuvrage );
            Mediatheque.getInstance().getListOuvrage().remove( selectedOuvrage );
            refreshTableOeuvre( null );
        } catch ( Exception ex ) {
            new DialogException(ex );
        }
    }//GEN-LAST:event_btnSupprimerOuvrageActionPerformed

    private void btnConsultOuvrageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultOuvrageActionPerformed
        PanelAjouterOuvrage panel = new PanelAjouterOuvrage();
        panel.setLivre( selectedOuvrage );
        JDialog dialog = new JDialog();
        dialog.setTitle( "Consulter l'ouvrage " + selectedOuvrage.getTitre() );
        dialog.setLayout( new BorderLayout() );
        dialog.setSize( this.getWidth() + 20, this.getHeight() + 20 );
        dialog.add( panel, BorderLayout.NORTH );
        JButton btnClose = new JButton();
        btnClose.setText( "Fermer" );
        btnClose.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent e ) {
                dialog.setVisible( false );
                dialog.dispose();
            }
        } );
        JPanel panelButtons = new JPanel();
        JButton btnValid = new JButton();
        btnValid.setText( "Valider modifications" );
        btnValid.addActionListener( new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent e ) {

            }
        } );
        panelButtons.add( btnClose );
        panelButtons.add( btnValid );
        panelButtons.setVisible( true );
        dialog.add( panelButtons, BorderLayout.SOUTH );
        dialog.setVisible( true );

    }//GEN-LAST:event_btnConsultOuvrageActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.listOuvrages = Mediatheque.getInstance().getListOuvrage();
        refreshTableOeuvre();
    }//GEN-LAST:event_jButton1ActionPerformed

    public JButton getBtnAjoutOuvrage() {
        return btnAjoutOuvrage;
    }

    public PanelSearch getPanelSearch1() {
        return panelSearch1;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjoutOuvrage;
    private javax.swing.JButton btnConsultOuvrage;
    private javax.swing.JButton btnSupprimerOuvrage;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private shifu.view.panel.PanelSearch panelSearch1;
    private javax.swing.JTable tableOeuvre;
    // End of variables declaration//GEN-END:variables

    private class SearchOuvrageRunnable implements Runnable {

        Thread otherThread;

        public SearchOuvrageRunnable( Thread thread ) {
            this.otherThread = thread;
        }

        @Override
        public void run() {
            if ( null != otherThread ) {
                while ( otherThread.isAlive() ) {
                }
            }

            searchOuvrage( panelSearch1.getSearchArgs().toLowerCase() );

        }

        private void searchOuvrage( String searchArgs ) {
            List<Ouvrage> listOuvragesRecherche = new ArrayList();
            for ( Ouvrage ouvrage : listOuvrages ) {

                if ( unAccent( ouvrage.getStrAuteurs() ).toLowerCase().contains( searchArgs )
                        || unAccent( ouvrage.getTitre() ).toLowerCase().contains( searchArgs )
                        || unAccent( ouvrage.getTitreOriginal() ).toLowerCase().contains( searchArgs )
                        || unAccent( ouvrage.getSousTitre() ).toLowerCase().contains( searchArgs )
                        || unAccent( ouvrage.getGenre().getLib() ).toLowerCase().contains( searchArgs )
                        || unAccent( ouvrage.getIsbn() ).toLowerCase().contains( searchArgs )
                        || unAccent( ouvrage.getCodeInterne() ).toLowerCase().contains( searchArgs ) ) {
                    listOuvragesRecherche.add( ouvrage );
                }
            }
            refreshTableOeuvre( listOuvragesRecherche );

        }
    }

    public List<Ouvrage> getListOuvrages() {
        return listOuvrages;
    }

    public void setListOuvrages( List<Ouvrage> listOuvrages ) {
        this.listOuvrages = listOuvrages;
        refreshTableOeuvre();
    }

}
