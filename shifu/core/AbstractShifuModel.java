package shifu.core;

import java.util.*;

abstract public class AbstractShifuModel implements IObservable {

    private List<IObserver> observers;

    public AbstractShifuModel() {
        observers = new LinkedList();
    }

    @Override
    public void addObserver( IObserver observer ) {
        observers.add( observer );
    }

    @Override
    public void removeObserver( IObserver observer ) {
        observers.remove( observer );
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
        return observers;
    }

    @Override
    public void setObservers( List<IObserver> observers ) {
        this.observers = observers;
    }
    
    

}
