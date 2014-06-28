/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.core.tools.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author louis
 */
public class HTTPTools {

    public static final String MAX_ITEM = "20";

    public String buildQuery( String sCritere ) {
        String sGoogleWS = "https://www.googleapis.com/books/v1/volumes";
        String sGoogleApiKey = "AIzaSyAIW1nDHsKbX8zPGmrszVXlqf87WnUEYSU";
        String sCriteresAdditionnels = "&langRestrict=fr&maxResults=" + MAX_ITEM + "&fields=items%2FvolumeInfo%2CtotalItems";
        String result = sGoogleWS.concat( "?q=" + sCritere + sCriteresAdditionnels + "&key=" + sGoogleApiKey );
        return result;
    }

    // HTTP GET request
    public void sendGet( String sCritere, StringBuffer sbResponse ) throws Exception {

        String builtQuery = buildQuery( sCritere );
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet( builtQuery );

        request.addHeader( "User-Agent", "Mozilla/5.0" );

//        System.setProperty("https.proxySet", "true");
//        System.getProperties().put("https.proxyHost", "127.0.0.1");
//        System.getProperties().put("https.proxyPort", "3127");
        
//        System.setProperty("java.net.useSystemProxies", "true");
//        System.setProperty( "https.proxyHost", "127.0.0.1" );
//        System.setProperty( "https.proxyPort", "3127" );
//        ProxyConf proxy = new ProxyConf();

        System.out.println( request.getURI().toString() );

        HttpResponse response = client.execute( request );
//        System.out.println("Sending 'GET' request to URL : " + builtQuery);
        System.out.println( "Response Code : "
                + response.getStatusLine().getStatusCode() );

        BufferedReader rd = new BufferedReader(
                new InputStreamReader( response.getEntity().getContent() ) );

        StringBuffer result = new StringBuffer();
        String line = "";
        while ( ( line = rd.readLine() ) != null ) {
            result.append( line );
        }

//        System.out.println( result.toString() );
        sbResponse.append( result.toString() );

    }
}
