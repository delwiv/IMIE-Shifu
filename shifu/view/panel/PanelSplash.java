/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view.panel;

import java.awt.GridLayout;
import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.plaf.ProgressBarUI;

/**
 *
 * @author delwiv
 */
public class PanelSplash extends JPanel {

    private JLabel label;
    private JProgressBar progressBar;

    public PanelSplash() {
        this.setLayout( new GridLayout( 2, 1 ) );
        this.label = new JLabel( "Démarrage de l'application..." );
        this.progressBar = new JProgressBar(new DefaultBoundedRangeModel(0, 0, 0, 100));
        this.add( label );
        this.add( progressBar );

    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel( JLabel label ) {
        this.label = label;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

}
