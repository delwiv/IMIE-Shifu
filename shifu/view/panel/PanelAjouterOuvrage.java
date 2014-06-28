/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view.panel;

import java.util.List;
import java.util.ListIterator;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import shifu.core.Singleton;
import shifu.model.Auteur;
import shifu.model.Genre;
import shifu.model.Livre;
import shifu.model.Mediatheque;
import shifu.model.Ouvrage;

/**
 *
 * @author user
 */
public class PanelAjouterOuvrage extends javax.swing.JPanel {

    private Ouvrage livre;

    /**
     * Creates new form PanelAjoutOeuvre
     */
    public PanelAjouterOuvrage() {
        initComponents();
        this.progressBarGoogle.setVisible( false );

        List<Genre> listAllGenres = Mediatheque.getInstance().getListGenre();

        //listAllGenres.sort( null );
        cbbGenre.removeAllItems();

        ListIterator it = listAllGenres.listIterator( 0 );

        while ( it.hasNext() ) {
            Genre genre = ( Genre ) it.next();
            cbbGenre.addItem( genre.getLib() );

        }

    }

    public Livre getLivre() {
        return new Livre();
    }

    public void setLivre( Ouvrage livre ) {
        this.livre = livre;
        this.updatePanel();
    }

    private void updatePanel() {
        try {
            this.textIsbn.setText( livre.getIsbn() );
        } catch ( Exception e ) {
        }
        try {
            this.textCodeInterne.setText( livre.getCodeInterne() );
        } catch ( Exception e ) {
        }

        this.textTitre.setText( livre.getTitre() );
        this.comboboxNote.setSelectedItem( livre.getNote() );
        this.textTitreOriginal.setText( livre.getTitreOriginal() );
        try {
            this.textDatePublication.setText( livre.getDatePublication().toString() );
        } catch ( Exception ex ) {
        }
        this.textSousTitre.setText( livre.getSousTitre() );
//        this.textFormat.setText( livre.getFormat() );
        this.textLangue.setText( livre.getLangue() );
//        this.textNbPage.setText( String.valueOf( livre.getNbPages() ) );
//        this.textCollection.setText( livre.getCollection() );
//        this.textMaisonEdition.setText( livre.getMaisonEdition() );
//        this.textareaResume.setText( livre.getResume() );
        DefaultTableModel model = ( DefaultTableModel ) this.tableAuteurOeuvre.getModel();
        for ( ListIterator it = livre.getAuteurs().listIterator( 0 ) ; it.hasNext() ; ) {
            Auteur auteur = ( Auteur ) it.next();
            model.addRow( new Object[]{
                auteur.getNom(),
                auteur.getPrenom(),
                auteur.getSurnom(),
                auteur.getNationalite().getLib()
            } );

        }
        this.tableAuteurOeuvre = new JTable( model );

    }

    public JCheckBox getCbPretable() {
        return cbPretable;
    }

    public JTextField getTextCommentaireEtat() {
        return textCommentaireEtat;
    }

    public JComboBox getCbbEtatOuvrage() {
        return cbbEtatOuvrage;
    }

    public JButton getBtnValider() {
        return btnValider;
    }

    public JButton getBtnAnuler() {
        return btnAnuler;
    }

    public JButton getBtnRecherche() {
        return btnRecherche;
    }

    public JButton getBtnSelectionAuteur() {
        return btnSelectionAuteur;
    }

    public JComboBox getCbbGenre() {
        return cbbGenre;
    }

    public JComboBox getComboboxNote() {
        return comboboxNote;
    }

    public JTextField getTextDatePublication() {
        return textDatePublication;
    }

    public JTable getTableAuteurOeuvre() {
        return tableAuteurOeuvre;
    }

    public void setTableAuteurOeuvre( JTable tableAuteurOeuvre ) {
        this.tableAuteurOeuvre = tableAuteurOeuvre;
    }

    public JTextField getTextCodeInterne() {
        return textCodeInterne;
    }

    public JTextField getTextCollection() {
        return textCollection;
    }

    public JTextField getTextFormat() {
        return textFormat;
    }

    public JTextField getTextIsbn() {
        return textIsbn;
    }

    public JTextField getTextLangue() {
        return textLangue;
    }

    public JTextField getTextMaisonEdition() {
        return textMaisonEdition;
    }

    public JTextField getTextNbPage() {
        return textNbPage;
    }

    public JTextField getTextRecherche() {
        return textRecherche;
    }

    public JTextField getTextSousTitre() {
        return textSousTitre;
    }

    public JTextField getTextTitre() {
        return textTitre;
    }

    public JTextField getTextTitreOriginal() {
        return textTitreOriginal;
    }

    public JTextArea getTextareaResume() {
        return textareaResume;
    }

    public JProgressBar getProgressBarGoogle() {
        return progressBarGoogle;
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        textTitre = new javax.swing.JTextField();
        textTitreOriginal = new javax.swing.JTextField();
        textSousTitre = new javax.swing.JTextField();
        textLangue = new javax.swing.JTextField();
        textCollection = new javax.swing.JTextField();
        textFormat = new javax.swing.JTextField();
        textMaisonEdition = new javax.swing.JTextField();
        textNbPage = new javax.swing.JTextField();
        comboboxNote = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        textareaResume = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        textIsbn = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        textCodeInterne = new javax.swing.JTextField();
        btnRecherche = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        textRecherche = new javax.swing.JTextField();
        textDatePublication = new javax.swing.JTextField();
        progressBarGoogle = new javax.swing.JProgressBar();
        jLabel16 = new javax.swing.JLabel();
        cbbGenre = new javax.swing.JComboBox();
        cbbEtatOuvrage = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textCommentaireEtat = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cbPretable = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        btnSelectionAuteur = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        tableAuteur = new javax.swing.JScrollPane();
        tableAuteurOeuvre = new javax.swing.JTable();
        btnValider = new javax.swing.JButton();
        btnAnuler = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
        setMaximumSize(new java.awt.Dimension(1219, 859));
        setMinimumSize(new java.awt.Dimension(1219, 859));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        jLabel1.setText("Titre");

        jLabel2.setText("Titre original");

        jLabel3.setText("Note");

        jLabel5.setText("Date de publication");

        jLabel7.setText("Sous-titre");

        jLabel9.setText("Langue");

        jLabel10.setText("Collection");

        jLabel11.setText("Format");

        jLabel12.setText("Maison d'édition");

        jLabel13.setText("Résumé");

        jLabel14.setText("Nombre de page");

        textSousTitre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSousTitreActionPerformed(evt);
            }
        });

        textCollection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCollectionActionPerformed(evt);
            }
        });

        comboboxNote.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        textareaResume.setColumns(20);
        textareaResume.setRows(5);
        jScrollPane1.setViewportView(textareaResume);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel8.setText("Oeuvre");

        jLabel15.setText("ISBN");

        jLabel19.setText("Code interne");

        textCodeInterne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCodeInterneActionPerformed(evt);
            }
        });

        btnRecherche.setText("Rechercher");

        jLabel21.setText("Google");

        jLabel16.setText("Genre");

        cbbGenre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbGenreItemStateChanged(evt);
            }
        });

        cbbEtatOuvrage.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Etat");

        jLabel6.setText("Commentaire état");

        jLabel17.setText("Prêtable");

        cbPretable.setSelected(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel8))
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel2))
                                    .addGap(36, 36, 36))
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(69, 69, 69)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textTitreOriginal, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textTitre, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textSousTitre, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textLangue, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textCollection, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textIsbn, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                                    .addComponent(textMaisonEdition)
                                    .addComponent(progressBarGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(89, 89, 89)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboboxNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbGenre, 0, 370, Short.MAX_VALUE)
                                    .addComponent(textNbPage)
                                    .addComponent(textFormat)
                                    .addComponent(textDatePublication)
                                    .addComponent(textCodeInterne)
                                    .addComponent(cbbEtatOuvrage, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textCommentaireEtat)
                                    .addComponent(cbPretable)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(textRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 129, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textRecherche, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(btnRecherche))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBarGoogle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textCodeInterne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(textIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(comboboxNote, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textTitreOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(textDatePublication, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textSousTitre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(textFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textLangue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(textNbPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textCollection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel16)
                    .addComponent(cbbGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textMaisonEdition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cbbEtatOuvrage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textCommentaireEtat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbPretable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));

        btnSelectionAuteur.setText("Selection auteur");
        btnSelectionAuteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectionAuteurActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel18.setText("Auteur");

        tableAuteurOeuvre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Prénom", "Surnom", "Nationalité"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableAuteur.setViewportView(tableAuteurOeuvre);
        if (tableAuteurOeuvre.getColumnModel().getColumnCount() > 0) {
            tableAuteurOeuvre.getColumnModel().getColumn(0).setResizable(false);
            tableAuteurOeuvre.getColumnModel().getColumn(1).setResizable(false);
            tableAuteurOeuvre.getColumnModel().getColumn(2).setResizable(false);
            tableAuteurOeuvre.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSelectionAuteur))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(tableAuteur, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(btnSelectionAuteur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableAuteur, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnValider.setText("Valider");

        btnAnuler.setText("Annuler");
        btnAnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnulerActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel22.setText("Ajouter une oeuvre");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel22)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAnuler)
                        .addGap(18, 18, 18)
                        .addComponent(btnValider))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnValider)
                    .addComponent(btnAnuler))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textCollectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCollectionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCollectionActionPerformed

    private void textSousTitreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSousTitreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textSousTitreActionPerformed

    private void btnSelectionAuteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectionAuteurActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_btnSelectionAuteurActionPerformed

    private void textCodeInterneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCodeInterneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCodeInterneActionPerformed

    private void btnAnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnulerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnulerActionPerformed

    private void cbbGenreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbGenreItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cbbGenreItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnuler;
    private javax.swing.JButton btnRecherche;
    private javax.swing.JButton btnSelectionAuteur;
    private javax.swing.JButton btnValider;
    private javax.swing.JCheckBox cbPretable;
    private javax.swing.JComboBox cbbEtatOuvrage;
    private javax.swing.JComboBox cbbGenre;
    private javax.swing.JComboBox comboboxNote;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JProgressBar progressBarGoogle;
    private javax.swing.JScrollPane tableAuteur;
    private javax.swing.JTable tableAuteurOeuvre;
    private javax.swing.JTextField textCodeInterne;
    private javax.swing.JTextField textCollection;
    private javax.swing.JTextField textCommentaireEtat;
    private javax.swing.JTextField textDatePublication;
    private javax.swing.JTextField textFormat;
    private javax.swing.JTextField textIsbn;
    private javax.swing.JTextField textLangue;
    private javax.swing.JTextField textMaisonEdition;
    private javax.swing.JTextField textNbPage;
    private javax.swing.JTextField textRecherche;
    private javax.swing.JTextField textSousTitre;
    private javax.swing.JTextField textTitre;
    private javax.swing.JTextField textTitreOriginal;
    private javax.swing.JTextArea textareaResume;
    // End of variables declaration//GEN-END:variables

}
