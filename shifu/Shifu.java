/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu;

import shifu.controller.MainController;
import shifu.core.Singleton;

/**
 *
 * @author delwiv
 */
public class Shifu {

    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) {
        Singleton.getInstance( MainController.class ).start();
    }

}
