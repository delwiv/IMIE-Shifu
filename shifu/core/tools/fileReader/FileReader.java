package shifu.core.tools.fileReader;

import java.io.*;

public class FileReader {

    public String getStringFromFile(String fileName) {
        String chaine = "";

        //lecture du fichier texte	
        try {
            InputStream ips = new FileInputStream(fileName);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
//				System.out.println(ligne);
                    chaine += ligne + "\n";
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return chaine;
    }
}
