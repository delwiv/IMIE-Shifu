/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao.elementaire;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import shifu.model.Genre;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;

/**
 *
 * @author delwiv
 */
public class GenreDAO extends DAO<Genre> {

    private static Genre genreInconnu;

    public Genre getGenreInconnu() {
        if ( null == genreInconnu ) {
            Genre genre = null;
            try {
                resultSet = getPreparedStatement( "SELECT ID_genre, lib_genre FROM Genre WHERE lib_genre = 'Inconnu'" ).executeQuery();
                if ( resultSet.first() ) {
                    genreInconnu = new Genre(
                            resultSet.getInt( "ID_genre" ),
                            resultSet.getString( "lib_genre" ),
                            0,
                            null,
                            null );
                }
            } catch ( SQLException ex ) {
//            Logger.getLogger( GenreDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
        return genreInconnu;
    }

    @Override
    public Genre create( Genre genre ) {
        try {
            preparedStatement = getPreparedStatement( "INSERT INTO Genre (lib_genre, niveau_genre, ID_genre_1) VALUES (?, ?, ?);" );

            preparedStatement.setString( 1, genre.getLib() );
            preparedStatement.setInt( 2, genre.getNiveau() );
            preparedStatement.setInt( 3, genre.getGenreParent().getIdGenre() );

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if ( resultSet.first() ) {
                genre.setIdGenre( resultSet.getInt( "ID_genre" ) );
            }

        } catch ( SQLException ex ) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch ( SQLException e ) {
                e.printStackTrace();
            }
        }
        return genre;
    }

    @Override
    public Genre getByID( int ID ) {
        Genre genre = null;

        try {
            resultSet = this.connection.createStatement().executeQuery( "SELECT * FROM Genre WHERE ID_genre = " + ID );

            if ( resultSet.first() ) {
                genre = new Genre(
                        resultSet.getInt( "ID_genre" ),
                        resultSet.getString( "lib_genre" ),
                        resultSet.getInt( "niveau_genre" )
                );

                int idSousGenre = resultSet.getInt( "ID_genre_1" );
                genre.setGenresFils( null );
            }
        } catch ( SQLException ex ) {
            Logger.getLogger( GenreDAO.class.getName() ).log( Level.SEVERE, null, ex );
        } finally {
            try {
                resultSet.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( GenreDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }

        return genre;
    }

    @Override
    public Genre update( Genre object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Genre> getByEqualsCustomArgument( CustomArgument argument ) {
        List<Genre> listGenres = null;
        try {
            preparedStatement = getPreparedStatement( "SELECT * FROM Genre WHERE "
                    + argument.getFieldName() + " = ? " );
            Object value = argument.getValue();

            if ( value instanceof String ) {
                preparedStatement.setString( 1, ( String ) value );
            } else if ( value instanceof Integer ) {
                preparedStatement.setInt( 1, ( Integer ) argument.getValue() );
            }

            listGenres = executeCustomSearch( preparedStatement, argument );
        } catch ( SQLException ex ) {
            Logger.getLogger( GenreDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }
        if ( listGenres.isEmpty() ) {
            listGenres.add( getGenreInconnu() );
        }
        return listGenres;
    }

    @Override
    public List<Genre> getByLikeCustomArgument( CustomArgument argument ) {
        List<Genre> listGenres = null;
        try {
            preparedStatement = getPreparedStatement( "SELECT * FROM Genre WHERE "
                    + argument.getFieldName() + " LIKE ? " );
            Object value = argument.getValue();

            if ( value instanceof String ) {
                preparedStatement.setString( 1, ( String ) value );
            } else if ( value instanceof Integer ) {
                preparedStatement.setInt( 1, ( Integer ) argument.getValue() );
            }

            listGenres = executeCustomSearch( preparedStatement, argument );
        } catch ( SQLException ex ) {
            Logger.getLogger( GenreDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }
        if ( listGenres.isEmpty() ) {
            listGenres.add( getGenreInconnu() );
        }
        return listGenres;
    }

    @Override
    protected List<Genre> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        List<Genre> listGenres = new ArrayList();
        try {

            resultSet = statement.executeQuery();

            try {
                if ( resultSet.first() ) {
                    Genre currentGenre;

                    int idGenre = resultSet.getInt( "ID_genre" );
                    String libGenre = resultSet.getString( "lib_genre" );
                    int niveauGenre = resultSet.getInt( "niveau_genre" );

                    currentGenre = new Genre( idGenre, libGenre, niveauGenre, null, null );

                    listGenres.add( currentGenre );

                    while ( resultSet.next() ) {

//                        System.out.println( resultSet.getInt( "ID_genre" ) );
//                        System.out.println( resultSet.getString( "lib_genre" ) );
//                        System.out.println( resultSet.getInt( "niveau_genre" ) );
                        currentGenre = new Genre(
                                resultSet.getInt( "ID_genre" ),
                                resultSet.getString( "lib_genre" ),
                                resultSet.getInt( "niveau_genre" ),
                                null,
                                null );
                        try {
                            listGenres.add( currentGenre );
                        } catch ( Exception e ) {
                            e.printStackTrace();
                        }

                    }
                }
            } catch ( Exception e ) {
                e.printStackTrace();
            }

        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return listGenres;
    }

    @Override
    public List<Genre> findAll() {
        List<Genre> lGenres = new ArrayList();
        
        try {
            resultSet = connection.createStatement().executeQuery( "SELECT * FROM Genre ORDER BY lib_genre");
            
            if(resultSet.first()){
                Genre currentGenre;

                    int idGenre = resultSet.getInt( "ID_genre" );
                    String libGenre = resultSet.getString( "lib_genre" );
                    int niveauGenre = resultSet.getInt( "niveau_genre" );
                    int idGenreFils = resultSet.getInt( "ID_genre_1" );

                    currentGenre = new Genre( idGenre, libGenre, niveauGenre, idGenreFils );

                    lGenres.add( currentGenre );
                    
                    while ( resultSet.next() ) {

                        
                        
                        currentGenre = new Genre(
                                resultSet.getInt( "ID_genre" ),
                                resultSet.getString( "lib_genre" ),
                                resultSet.getInt( "niveau_genre" ),
                                resultSet.getInt( "ID_genre_1") );
                        try {
                            lGenres.add( currentGenre );
                        } catch ( Exception e ) {
                            e.printStackTrace();
                        }
                        
//                        
//                        
//                        System.out.println( currentGenre.getLib() );
//                        System.out.println( currentGenre.getNiveau() );
//                        System.out.println( currentGenre.getIdGenresFils() );

                    }
            }
            
        } catch ( SQLException ex ) {
            Logger.getLogger( GenreDAO.class.getName() ).log( Level.SEVERE, null, ex );
        }
        finally{
            try {
                resultSet.close();
            } catch ( SQLException ex ) {
                Logger.getLogger( GenreDAO.class.getName() ).log( Level.SEVERE, null, ex );
            }
        }
        
        return lGenres;
    }

    @Override
    public void delete( Genre object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
