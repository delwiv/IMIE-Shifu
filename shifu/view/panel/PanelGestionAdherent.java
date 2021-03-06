/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view.panel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shifu.core.Singleton;
import shifu.model.Adherent;
import shifu.model.Mediatheque;

/**
 *
 * @author user
 */
public class PanelGestionAdherent extends javax.swing.JPanel {

    private JDialog dialogAdherent;
    private PanelAjouterAdherent panelNvAdherent;
    private int row;
    /**
     * Creates new form PanelAbonne
     */
    public PanelGestionAdherent() {
        initComponents();

        
        this.panelSearchAdh.getBtnRecherche().setVisible( false);
        
       
        
        
        this.panelSearchAdh.getTextSearch().addKeyListener( new KeyAdapter() {

            @Override
            public void keyTyped( KeyEvent e ) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        searchAdherent(panelSearchAdh.getSearchArgs().toLowerCase( ));
                    }
                }).start();
                
            }

            

            
        });
//        AdherentDAOC a = ShifuDAOFactory.getAdherentDAOC();
//        this.listAdherents = a.getAllAdherentFromDB();
    }
    
    private void searchAdherent(String searchArgs){
        List<Adherent> lAdherent = new ArrayList();
        ListIterator<Adherent> it = Singleton.getInstance( Mediatheque.class).getListAdherents().listIterator( 0);
        
        while(it.hasNext()){
            Adherent adherent = it.next();
            
            if(unAccent(adherent.getNom()).toLowerCase().contains( searchArgs )
            || unAccent( adherent.getPrenom()).toLowerCase().contains( searchArgs )){
                lAdherent.add( adherent );
            }
        }
        
        refreshJtable( lAdherent );
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        btnAjoutAdherent = new javax.swing.JButton();
        btnValiderAdherent = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAbonne = new javax.swing.JTable();
        panelSearchAdh = new shifu.view.panel.PanelSearch();
        jLabel1 = new javax.swing.JLabel();
        btnSupprimerAdherent = new javax.swing.JButton();
        btnModifierAdherent = new javax.swing.JButton();

        jButton3.setText("jButton3");

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        btnAjoutAdherent.setText("Ajouter adhérent");
        btnAjoutAdherent.setMaximumSize(new java.awt.Dimension(130, 28));
        btnAjoutAdherent.setMinimumSize(new java.awt.Dimension(130, 28));
        btnAjoutAdherent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjoutAdherentActionPerformed(evt);
            }
        });

        btnValiderAdherent.setText("Valider adhérents en attente");
        btnValiderAdherent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnValiderAdherentActionPerformed(evt);
            }
        });

        tableAbonne.setAutoCreateRowSorter(true);
        tableAbonne.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "", "Title 3", "Title 4", "Title 5", "Title 6", "null", "null", "Title 9"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableAbonne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAbonneMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableAbonne);
        if (tableAbonne.getColumnModel().getColumnCount() > 0) {
            tableAbonne.getColumnModel().getColumn(0).setResizable(false);
            tableAbonne.getColumnModel().getColumn(1).setResizable(false);
            tableAbonne.getColumnModel().getColumn(2).setResizable(false);
            tableAbonne.getColumnModel().getColumn(3).setResizable(false);
            tableAbonne.getColumnModel().getColumn(4).setResizable(false);
            tableAbonne.getColumnModel().getColumn(5).setResizable(false);
            tableAbonne.getColumnModel().getColumn(6).setResizable(false);
            tableAbonne.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Gestion des adhérents");

        btnSupprimerAdherent.setText("Supprimer adhérent");
        btnSupprimerAdherent.setMaximumSize(new java.awt.Dimension(130, 28));
        btnSupprimerAdherent.setMinimumSize(new java.awt.Dimension(130, 28));
        btnSupprimerAdherent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupprimerAdherentActionPerformed(evt);
            }
        });

        btnModifierAdherent.setText("Modifier adhérent");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAjoutAdherent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnModifierAdherent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSupprimerAdherent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btnValiderAdherent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelSearchAdh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAjoutAdherent, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSupprimerAdherent, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnValiderAdherent, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModifierAdherent, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelSearchAdh, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnValiderAdherentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnValiderAdherentActionPerformed


    }//GEN-LAST:event_btnValiderAdherentActionPerformed

    private void btnAjoutAdherentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjoutAdherentActionPerformed

    }//GEN-LAST:event_btnAjoutAdherentActionPerformed

    private void tableAbonneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAbonneMouseClicked
             
        row = tableAbonne.rowAtPoint( evt.getPoint() );
      
                
    }//GEN-LAST:event_tableAbonneMouseClicked

    private void btnSupprimerAdherentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupprimerAdherentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSupprimerAdherentActionPerformed

    public int getRow() {
        return row;
    }

    
    
    public JButton getBtnAjoutAdherent() {
        return btnAjoutAdherent;
    }

    public JButton getBtnValiderAdherent() {
        return btnValiderAdherent;
    }

   

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1( JScrollPane jScrollPane1 ) {
        this.jScrollPane1 = jScrollPane1;
    }

    public PanelSearch getPanelSearchAdh() {
        return panelSearchAdh;
    }

    public void setPanelSearchAdh( PanelSearch panelSearchAdh ) {
        this.panelSearchAdh = panelSearchAdh;
    }

    public JButton getBtnModifierAdherent() {
        return btnModifierAdherent;
    }

    public void setBtnModifierAdherent(JButton btnModifierAdherent) {
        this.btnModifierAdherent = btnModifierAdherent;
    }

    public JButton getBtnSupprimerAdherent() {
        return btnSupprimerAdherent;
    }

    public void setBtnSupprimerAdherent(JButton btnSupprimerAdherent) {
        this.btnSupprimerAdherent = btnSupprimerAdherent;
    }

    
    
    
    public JTable getTableAdherent() {
        return tableAbonne;
    }
    
   
    public void setTableAbonne( JTable tableAbonne ) {
        this.tableAbonne = tableAbonne;
    }

    
    
    
//    public void setTableAdherent( JTable tableAdherent ) {
//        this.tableAbonne = tableAdherent;
//    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjoutAdherent;
    private javax.swing.JButton btnModifierAdherent;
    private javax.swing.JButton btnSupprimerAdherent;
    private javax.swing.JButton btnValiderAdherent;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private shifu.view.panel.PanelSearch panelSearchAdh;
    private javax.swing.JTable tableAbonne;
    // End of variables declaration//GEN-END:variables

    public void refreshJtable( List<Adherent> listAdherents ) {
        DefaultTableModel model = ( DefaultTableModel ) tableAbonne.getModel();

        model.setRowCount( 0 );
        
        if(null != listAdherents){
            //On vide la jtable(refresh)
            for ( int i = 0 ; i < model.getRowCount() ; i++ ) {
                model.removeRow( i );
            }
            
            //on insere les oeuvre dans la table oeuvre
            ListIterator it = listAdherents.listIterator( 0 );
            int i = 0;
            while ( it.hasNext() ) {
                Adherent currentAdherent = ( Adherent ) it.next();
                Object[] row = {
                    currentAdherent.getID(),
                    currentAdherent.getNom(),
                    currentAdherent.getPrenom(),
                    currentAdherent.getAdresse().getLigne1_adresse(),
                    currentAdherent.getAdresse().getCP_adresse(),
                    currentAdherent.getAdresse().getVille_adresse(),
                    currentAdherent.getEmail(),
                    currentAdherent.getTel(),
                    currentAdherent.getDateAdhesion(),
                    currentAdherent.getDateAdhesion()
                };

                model.addRow( row );
            }

            this.tableAbonne = new JTable( model );
            
        }
            
    }
    
    public  String unAccent(String s) {
    
    String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(temp).replaceAll("");
  }
}
