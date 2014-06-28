package shifu.core;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import shifu.core.parameters.ShifuParameters;
import shifu.view.panel.PanelMenu;

abstract public class AbstractShifuView implements IObserver, IListenable, ActionListener {

    protected List<IListener> listeners = null;
    protected AbstractShifuModel model;
    protected JFrame frame;
    protected PanelMenu panelMenu;

    public AbstractShifuView() {
    }

    /**
     * On supprime tous les panels à l'exception du PanelMenu
     */
    protected void cleanPanel() {
        Component[] panels = frame.getContentPane().getComponents();

        for ( Component pan : panels ) {
            // on ne supprime que les JPanel
            if ( pan.getParent().getClass() == JPanel.class ) {
                // mais pas le panelMenu
                if ( pan.getClass() != PanelMenu.class ) {
                    try {
//                        System.out.println( "removing " + pan.getClass().toString() );
                        frame.remove( pan );
                    } catch ( Exception e ) {
                        e.printStackTrace();
                        // panel présent dans la liste mais pas dans la frame, pas d'erreur
                    }
                }
            }
            frame.pack();

        }
    }

    @Override
    public abstract void update() throws Exception;

    @Override
    public void notifyListeners( ShifuParameters params ) throws Exception {
        ListIterator it = listeners.listIterator( 0 );
        IListener listener = null;
        while ( it.hasNext() ) {
            listener = ( IListener ) it.next();
            listener.update( params );
        }
    }

    @Override
    public void removeListener( IListener listener ) {
        listeners.remove( listener );
    }

    @Override
    public void addListener( IListener listener ) {
        if ( null == this.listeners ) {
            listeners = new LinkedList();
        }
        listeners.add( listener );
    }

    public AbstractShifuModel getModel() {
        return model;
    }

    public void setModel( AbstractShifuModel model ) {
        this.model = model;
    }

    public List<IListener> getListeners() {
        return listeners;
    }

    public void setListeners( List<IListener> listeners ) {
        this.listeners = listeners;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame( JFrame frame ) {
        this.frame = frame;
    }

}
