/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view.panel;

import javax.swing.JButton;

/**
 *
 * @author user
 */
public class PanelMenu extends javax.swing.JPanel {

//    private MainFrame2 parent;

    /**
     * Creates new form PanelMenu
     */
    public PanelMenu() {
        initComponents();

    }

//    public void setParent( MainFrame2 parent ) {
//        this.parent = parent;
//    }
//
//    public MainFrame getParent() {
//
//        return parent;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        btnGestionAdherent = new javax.swing.JButton();
        btnGestionEmprunt = new javax.swing.JButton();
        btnGestionOeuvre = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
        setMaximumSize(new java.awt.Dimension(433, 109));
        setMinimumSize(new java.awt.Dimension(433, 109));
        setName(""); // NOI18N

        btnGestionAdherent.setText("Gestion Adhérent");

        btnGestionEmprunt.setText("Gestion Emprunts");

        btnGestionOeuvre.setText("Gestion Oeuvres");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Clés d'Asie - Gestion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGestionAdherent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGestionEmprunt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGestionOeuvre)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGestionAdherent)
                    .addComponent(btnGestionEmprunt)
                    .addComponent(btnGestionOeuvre))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnGestionAdherent() {
        return btnGestionAdherent;
    }

    public JButton getBtnGestionEmprunt() {
        return btnGestionEmprunt;
    }

    public JButton getBtnGestionOeuvre() {
        return btnGestionOeuvre;
    }

    public JButton getBtnImportBookin() {
//        return btnImportBookin;
        return new JButton();
    }

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGestionAdherent;
    private javax.swing.JButton btnGestionEmprunt;
    private javax.swing.JButton btnGestionOeuvre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}