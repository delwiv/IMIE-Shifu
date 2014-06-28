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
import shifu.core.tools.fileReader.FileReader;
import shifu.model.Livre;
import shifu.thirdparty.JSONException;
import shifu.thirdparty.JSONObject;

/**
 *
 * @author delwiv
 */
public class ViewImporterJSON {

    private JFrame frame;
    private PanelImporterFichier panelJson;
    private List<Livre> livresBookin;

    public ViewImporterJSON() {
        frame = new JFrame( "Import JSON" );
        panelJson = new PanelImporterFichier();
        panelJson.setVisible( true );
        frame.setContentPane( panelJson );
        frame.setVisible( true );
        frame.setMinimumSize( new Dimension( 600, 400 ) );

        panelJson.getBtnChoisirFichier().addActionListener( new ActionListener() {

            @Override
            public void actionPerformed( ActionEvent e ) {
                JFileChooser fileChooser = new JFileChooser( "~/dev/svn/shifu/old" );
                int responseVal = fileChooser.showOpenDialog( frame );

                if ( responseVal == JFileChooser.APPROVE_OPTION ) {
                    livresBookin = new ArrayList();
                    livresBookin = parseFile( fileChooser.getSelectedFile().getAbsolutePath() );
                    ListIterator it = livresBookin.listIterator( 0 );
                    while ( it.hasNext() ) {
                        Livre cur = ( Livre ) it.next();
                        System.out.println( cur.toString() );
                    }
                }
            }
        } );
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
