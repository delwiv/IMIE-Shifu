/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view.panel;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import shifu.core.Singleton;
import shifu.model.Emprunt;
import shifu.model.Mediatheque;
import shifu.model.dao.ShifuDAOFactory;
import shifu.view.DialogException;

/**
 *
 * @author user
 */
public class PanelGestionEmprunts extends javax.swing.JPanel {

    List<Emprunt> listEmprunts;
    Emprunt empruntSelect;

    /**
     * Creates new form PanelEmprunt
     */
    public PanelGestionEmprunts() {
        initComponents();
        this.btnRetourEmprunt.setEnabled( false );
    }

    public JButton getBtnAjouterEmprunt() {
        return btnAjouterEmprunt;
    }

    public JButton getBtnRetourEmprunt() {
        return btnRetourEmprunt;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSearchEmprunt = new shifu.view.panel.PanelSearch();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmprunt = new javax.swing.JTable();
        btnAjouterEmprunt = new javax.swing.JButton();
        btnRetourEmprunt = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        tableEmprunt.setAutoCreateRowSorter(true);
        tableEmprunt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Titre oeuvre emprunter", "Nom Emprunteur", "Prénom Emprunteur", "Téléphone Emprunteur", "Date de debut d'emprunt", "Renouvelé", "Date de fin au plus tard", "Retard"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableEmprunt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableEmpruntMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableEmprunt);
        if (tableEmprunt.getColumnModel().getColumnCount() > 0) {
            tableEmprunt.getColumnModel().getColumn(0).setResizable(false);
            tableEmprunt.getColumnModel().getColumn(1).setResizable(false);
            tableEmprunt.getColumnModel().getColumn(2).setResizable(false);
            tableEmprunt.getColumnModel().getColumn(3).setResizable(false);
            tableEmprunt.getColumnModel().getColumn(4).setResizable(false);
            tableEmprunt.getColumnModel().getColumn(5).setResizable(false);
            tableEmprunt.getColumnModel().getColumn(6).setResizable(false);
            tableEmprunt.getColumnModel().getColumn(7).setResizable(false);
        }

        btnAjouterEmprunt.setText("Ajouter un emprunt");
        btnAjouterEmprunt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterEmpruntActionPerformed(evt);
            }
        });

        btnRetourEmprunt.setText("Valider un retour");
        btnRetourEmprunt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetourEmpruntActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Gestion des Emprunts");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAjouterEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 367, Short.MAX_VALUE)
                        .addComponent(btnRetourEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(panelSearchEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelSearchEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAjouterEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRetourEmprunt, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRetourEmpruntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetourEmpruntActionPerformed
        ShifuDAOFactory.getEmpruntDAO().delete( empruntSelect );
        try {
            Mediatheque.getInstance().setListAdherents(
                    ShifuDAOFactory.getAdherentDAOC().findAll()
            );
            listEmprunts = ShifuDAOFactory.getEmpruntDAO().findAll();

            refreshTableEmprunts();
        } catch ( Exception ex ) {
            new DialogException(ex );
        }


    }//GEN-LAST:event_btnRetourEmpruntActionPerformed

    private void btnAjouterEmpruntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterEmpruntActionPerformed


    }//GEN-LAST:event_btnAjouterEmpruntActionPerformed

    private void tableEmpruntMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableEmpruntMouseClicked
        int selectedRow = this.tableEmprunt.rowAtPoint( evt.getPoint() );
        this.empruntSelect = listEmprunts.get( selectedRow );
        this.btnRetourEmprunt.setEnabled( true );
    }//GEN-LAST:event_tableEmpruntMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAjouterEmprunt;
    private javax.swing.JButton btnRetourEmprunt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private shifu.view.panel.PanelSearch panelSearchEmprunt;
    private javax.swing.JTable tableEmprunt;
    // End of variables declaration//GEN-END:variables

    public void setEmprunts( List<Emprunt> listEmprunts ) {
        this.listEmprunts = listEmprunts;
        refreshTableEmprunts();
    }

    private void refreshTableEmprunts() {
        DefaultTableModel model = ( DefaultTableModel ) this.tableEmprunt.getModel();
        model.setRowCount( 0 );
        Calendar c = Calendar.getInstance();
        for ( Emprunt e : listEmprunts ) {
            boolean retard = false;
            c.setTime( e.getDateEmprunt() );
            c.add( Calendar.DATE, 14 );
            Date dateRetour = new Date( c.getTimeInMillis() );
            if ( Date.valueOf( LocalDate.now() ).after( dateRetour ) ) {
                retard = true;
            }

            model.addRow( new Object[]{
                e.getOuvrage().getTitre(),
                e.getAbonne().getNom(),
                e.getAbonne().getPrenom(),
                e.getAbonne().getTel(),
                e.getDateEmprunt(),
                e.isIsRenouvele(),
                dateRetour,
                retard
            } );
        }
        this.btnRetourEmprunt.setEnabled( false );
        this.tableEmprunt = new JTable( model );
    }

    public List<Emprunt> getListEmprunts() {
        return listEmprunts;
    }

}
