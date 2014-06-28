package shifu.core;

import java.util.*;

public interface IObservable {

    public void addObserver( IObserver observer );

    public void removeObserver( IObserver observer );

    public void notifyObservers() throws Exception;

    public List<IObserver> getObservers();

    public void setObservers( List<IObserver> observers );

}
