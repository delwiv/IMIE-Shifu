/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shifu.model.dao.tables;

import shifu.model.Livre;

/**
 *
 * @author delwiv
 */
public class TableLivre  {
    private String collection;
    private String format;
    private String maisonEdition;
    private String resume;
    private int nbPages;
    private int idOeuvre;

    public TableLivre( String collection, String format, String maisonEdition, String resume, int nbPages ) {
        this.collection = collection;
        this.format = format;
        this.maisonEdition = maisonEdition;
        this.resume = resume;
        this.nbPages = nbPages;
    }
    
    public TableLivre(Livre livre){
        this.collection = livre.getCollection();
        this.format = livre.getFormat();
        this.maisonEdition = livre.getMaisonEdition();
        this.resume = livre.getResume();
        this.nbPages = livre.getNbPages();
        this.idOeuvre = livre.getIdOeuvre();
    }

    public TableLivre( String collection, String format, String maisonEdition, String resume, int nbPages, int idOeuvre ) {
        this.collection = collection;
        this.format = format;
        this.maisonEdition = maisonEdition;
        this.resume = resume;
        this.nbPages = nbPages;
        this.idOeuvre = idOeuvre;
    }
    
    

    public String getCollection() {
        return collection;
    }

    public void setCollection( String collection ) {
        this.collection = collection;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat( String format ) {
        this.format = format;
    }

    public String getMaisonEdition() {
        return maisonEdition;
    }

    public void setMaisonEdition( String maisonEdition ) {
        this.maisonEdition = maisonEdition;
    }

    public String getResume() {
        return resume;
    }

    public void setResume( String resume ) {
        this.resume = resume;
    }

    public int getNbPages() {
        return nbPages;
    }

    public void setNbPages( int nbPages ) {
        this.nbPages = nbPages;
    }

    public int getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre( int idOeuvre ) {
        this.idOeuvre = idOeuvre;
    }
    
    
 
    
}
