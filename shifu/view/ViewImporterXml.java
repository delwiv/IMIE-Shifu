/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.view;

import shifu.view.panel.PanelImporterFichier;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.jdom2.Element;
import shifu.core.tools.fileReader.FileReader;
import shifu.core.tools.xml.XmlParser;
import shifu.model.Livre;
import shifu.model.dao.ShifuDAOFactory;
import shifu.thirdparty.JSONException;
import shifu.thirdparty.JSONObject;

/**
 *
 * @author delwiv
 */
public class ViewImporterXml {

    private JFrame frame;
    private PanelImporterFichier panelImport;
    private List<Livre> livresBookin;

    public ViewImporterXml() {
        frame = new JFrame( "Import XML" );
        panelImport = new PanelImporterFichier();
        panelImport.setVisible( true );
        frame.setContentPane( panelImport );
        frame.setMinimumSize( new Dimension( 600, 400 ) );

        panelImport.getBtnChoisirFichier().addActionListener( new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent e ) {
                JFileChooser fileChooser = new JFileChooser();
                int responseVal = fileChooser.showOpenDialog( frame );

                if ( responseVal == JFileChooser.APPROVE_OPTION ) {
                    livresBookin = getLivresFromXml( fileChooser.getSelectedFile().getAbsolutePath() );

//                    livresBookin = parseFile( fileChooser.getSelectedFile().getAbsolutePath() );
                    ListIterator it = livresBookin.listIterator( 0 );
                    while ( it.hasNext() ) {
                        Livre cur = ( Livre ) it.next();
//                        System.out.println( cur.toString() );
                        ShifuDAOFactory.getLivreDAOC().create( cur );
                    }
                }
            }
        } );
        frame.setVisible( true );

    }

    private List<Livre> getLivresFromXml( String absolutePath ) {
        List<Livre> listLivre = new ArrayList();

        XmlParser xmlParser = new XmlParser( absolutePath );

        List<Element> listElement = xmlParser.getRacine().getChildren( "Book" );
        ListIterator it = listElement.listIterator( 0 );

        while ( it.hasNext() ) {
            try {
                listLivre.add( new Livre( ( Element ) it.next() ) );
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }
        return listLivre;

    }

    private List<Livre> parseFile( String pathToFile ) {
        //read json file
        JSONObject jsResponse;
        String sResponse;
        FileReader fReader = new FileReader();
        sResponse = fReader.getStringFromFile( pathToFile );

        jsResponse = new JSONObject( sResponse );
        List<Livre> livresFetched = new ArrayList();

        System.out.println( "Appel instanciation Livre de masse " );
        System.out.println( jsResponse.toString( 2 ) );
        try {
            livresFetched = Livre.getLivresFromJSON( 1, jsResponse.getJSONObject( "Bookin" ) );

        } catch ( JSONException e ) {
            System.out.println( e.toString() );
        }

        return livresFetched;

    }
}
