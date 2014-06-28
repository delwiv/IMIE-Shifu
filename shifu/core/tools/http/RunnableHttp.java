/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.core.tools.http;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import shifu.core.IListener;
import shifu.core.IObservable;
import shifu.core.IObserver;
import shifu.core.parameters.ShifuParameters;

/**
 *
 * @author delwiv
 */
public class RunnableHttp implements Runnable, IObservable {

    private List<IObserver> observers;
    private HTTPTools http;
    private String args;
    private StringBuffer response;
    private JProgressBar progressBar;

    public RunnableHttp() {

        http = new HTTPTools();
        response = new StringBuffer();
    }

    @Override
    public void run() {
        try {
            progressBar.setVisible( true );

            http.sendGet( args, response );
            progressBar.setVisible( false );
            notifyObservers();
        } catch ( Exception ex ) {
            progressBar.setVisible( false );
            
        }
    }

    public StringBuffer getResponse() {
        return response;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar( JProgressBar progressBar ) {
        this.progressBar = progressBar;
    }

    public void setArgs( String args ) {
        this.args = args.replace( " ", "%20" );
    }

//    @Override
//    public void addListener( IListener listener ) {
//       
//    }
//
//    @Override
//    public void removeListener( IListener listener ) {
//        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void notifyListeners( ShifuParameters params ) {
//        
//    }
    @Override
    public void addObserver( IObserver observer ) {
        if ( null == this.observers ) {
            this.observers = new LinkedList();
        }
        this.observers.add( observer );
    }

    @Override
    public void removeObserver( IObserver observer ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyObservers() throws Exception {
        ListIterator it = observers.listIterator( 0 );
        while ( it.hasNext() ) {
            IObserver observer = ( IObserver ) it.next();
            observer.update();
        }
    }

    @Override
    public List<IObserver> getObservers() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setObservers( List<IObserver> observers ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
