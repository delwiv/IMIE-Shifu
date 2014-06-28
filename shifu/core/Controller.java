package shifu.core;

import java.util.List;
import shifu.model.dao.CustomArgument;

public abstract class Controller<T> implements IListener {

    protected T model;
    protected AbstractShifuView view;

    public Controller() {

    }

    /**
     * Cette méthode générique impose aux héritières de permettre la crétion
     * d'un objet
     *
     * @param object l'object à mettre en base
     * @throws java.lang.Exception
     */
    public abstract void addModel( T object ) throws Exception;

    /**
     * Cette méthode générique impose aux héritières de permettre la MAJ d'un
     * objet
     *
     * @param object
     * @throws java.lang.Exception
     */
    public abstract void updateModel( T object ) throws Exception;

    /**
     * Methode permettant d'effectuer des recherches.
     *
     * @param args arguments de recherche (typiquement le contenu du TextField
     * de recherche)
     * @return list d'objets correspondant à divers critères
     * @throws java.lang.Exception
     */
    public abstract List<T> getListModel( CustomArgument args ) throws Exception;

    /**
     * Recherche ne retournant qu'une réponse (souvent recherche par ID mais pas
     * que)
     *
     * @param args
     * @return
     * @throws java.lang.Exception
     */
    public abstract T getModel( CustomArgument args ) throws Exception;

}
