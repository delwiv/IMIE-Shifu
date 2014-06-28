/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model;

import java.util.List;

/**
 *
 * @author delwiv
 */
public class Genre {

    private int idGenre;
    private String lib;
    private int niveau;
    private Genre genreParent;
    private List<Genre> genresFils;
    private int IdGenresFils;

    public Genre( String lib, int niveau, Genre genreParent, List<Genre> genresFils ) {
        this.lib = lib;
        this.niveau = niveau;
        this.genreParent = genreParent;
        this.genresFils = genresFils;
    }

    public Genre( int idGenre, String lib, int niveau, Genre genreParent, List<Genre> genresFils ) {
        this( lib, niveau, genreParent, genresFils );
        this.idGenre = idGenre;
    }

    public Genre( int idGenre, String lib, int niveau ) {
        this.idGenre = idGenre;
        this.lib = lib;
        this.niveau = niveau;
    }

    public Genre( int idGenre, String lib, int niveau, int IdGenresFils ) {
        this.idGenre = idGenre;
        this.lib = lib;
        this.niveau = niveau;
        this.IdGenresFils = IdGenresFils;
    }
    
    
    

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre( int idGenre ) {
        this.idGenre = idGenre;
    }

    public String getLib() {
        return lib;
    }

    public void setLib( String lib ) {
        this.lib = lib;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau( int niveau ) {
        this.niveau = niveau;
    }

    public Genre getGenreParent() {
        return genreParent;
    }

    public void setGenreParent( Genre genreParent ) {
        this.genreParent = genreParent;
    }

    public List<Genre> getGenresFils() {
        return genresFils;
    }

    public void setGenresFils( List<Genre> genresFils ) {
        this.genresFils = genresFils;
    }

    public int getIdGenresFils() {
        return IdGenresFils;
    }

    public void setIdGenresFils( int IdGenresFils ) {
        this.IdGenresFils = IdGenresFils;
    }
    
    

    @Override
    public String toString() {
        return "Genre{" + "idGenre=" + idGenre + ", lib=" + lib + ", niveau=" + niveau + ", genreParent=" + genreParent + ", genresFils=" + genresFils + '}';
    }

}
