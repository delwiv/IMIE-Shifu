/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.core.tools.xml;

import java.io.File;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author delwiv
 */
public class XmlParser {

    private Document document;
    private Element racine;

    public XmlParser( String path ) {
        //On crée une instance de SAXBuilder
        SAXBuilder sxb = new SAXBuilder();
        try {
         //On crée un nouveau document JDOM avec en argument le fichier XML
            //Le parsing est terminé ;)
            document = sxb.build( new File( path ) );
        } catch ( Exception e ) {
        }

        //On initialise un nouvel élément racine avec l'élément racine du document.
        racine = ( Element ) document.getRootElement();

        //Méthode définie dans la partie 3.2. de cet article
//      afficheALL();
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument( Document document ) {
        this.document = document;
    }

    public Element getRacine() {
        return racine;
    }

    public void setRacine( Element racine ) {
        this.racine = racine;
    }

}
