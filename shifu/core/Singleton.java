/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.core;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe récupérée sur
 * http://neutrofoton.com/generic-singleton-pattern-in-java/ permet de gérer le
 * signleton avec généricité ;)
 *
 * @author delwiv
 */
public class Singleton {

    /**
     * On stocke l'unique instance
     */
    private static final Singleton instance = new Singleton();

    /**
     * HashMap<Class, Object> : pour chaque clé (Class) on a UN Object Il y aura
     * dans cette HashMap une instance par classe appelée
     */
    @SuppressWarnings( "rawtypes" )
    private Map<Class, Object> mapHolder = new HashMap<Class, Object>();

    private Singleton() {
    }

    /**
     * delwiv
     *
     * @param <T>
     * @param classOf la classe voulue (ClassName.class)
     * @return L'instance de cette classe stockée dans la HashMap on va vérifier
     * si notre HashMap contient une clé contenant la classe demandée. Si oui on
     * retourne la valeur stockée en face de cette clé. Si non on crée une
     * nouvelle instance, on l'ajoute à la HashMap (<Class, Object>) et on
     * retourne cet Object.
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @SuppressWarnings( "unchecked" )
    public static <T> T getInstance( Class<T> classOf ) {

        synchronized ( instance ) {
            //Si le type (classOf) de la variable instance N' existe PAS comme clé dans la HashMap
            if ( !instance.mapHolder.containsKey( classOf ) ) {
                // On crée une nouvelle instance
                T obj;
                try {
                    obj = classOf.newInstance();

                    // On ajoute le couple Class Object (type, instance) dans la HashMap
                    instance.mapHolder.put( classOf, obj );
                } catch ( InstantiationException | IllegalAccessException ex ) {
                    Logger.getLogger( Singleton.class.getName() ).log( Level.SEVERE, null, ex );
                }

            }
            // Enfin on retourne l'Objet casté en (T) qui est le type d'instance.
            return ( T ) instance.mapHolder.get( classOf );
        }
    }
}
