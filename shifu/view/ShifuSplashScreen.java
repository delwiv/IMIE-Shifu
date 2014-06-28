/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JWindow;
import shifu.view.panel.PanelSplash;

/**
 *
 * @author delwiv
 */
public class ShifuSplashScreen extends JWindow {
    
    private PanelSplash panelSplash;
    
    public ShifuSplashScreen() {
        panelSplash = new PanelSplash();
        panelSplash.setSize( 150, 250 );
        panelSplash.setVisible( true );
        this.getContentPane().add( panelSplash );
        int width = 450;
        int height = 115;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = ( screen.width - width ) / 2;
        int y = ( screen.height - height ) / 2;
        setBounds( x, y, width, height );
//        this.setSize( 160, 260 );
        this.setVisible( true );
        
    }
    
    public void setText( String str ) {
        this.panelSplash.getLabel().setText( str );
    }
    
    public void setProgress( int progress ) {
        this.panelSplash.getProgressBar().setValue( progress );
        this.panelSplash.getProgressBar().setString( String.valueOf( progress ) );
    }
    
}
