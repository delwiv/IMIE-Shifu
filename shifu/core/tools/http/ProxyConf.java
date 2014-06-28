/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.core.tools.http;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 *
 * @author delwiv
 */
public class ProxyConf extends Authenticator {

    private String username, password;

    public ProxyConf( /*String username, String password*/ ) {
//        this.username = username;
//        this.password = password;
        System.setProperty( "https.proxyHost", "127.0.0.1" );	//l'adresse du proxy
        System.setProperty( "https.proxyPort", "3127" );	//le port du proxy
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication( username, password.toCharArray() );
    }
}
