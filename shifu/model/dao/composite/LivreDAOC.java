/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao.composite;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import shifu.model.Livre;
import shifu.model.Ouvrage;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;
import shifu.model.dao.ShifuDAOFactory;
import shifu.model.dao.tables.TableLivre;

/**
 *
 * @author delwiv
 */
public class LivreDAOC extends DAO<Livre> {

    @Override
    public Livre create( Livre livre ) throws SQLException {
        Ouvrage ouvrage = ShifuDAOFactory.getOuvrageDAOC().create( livre );
        livre.setIdOeuvre( ouvrage.getIdOeuvre() );

        livre.update( ShifuDAOFactory.getLivreDAO().create( new TableLivre( livre ) ) );
        
        return livre;
    }

    @Override
    public Livre getByID( int ID ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Livre update( Livre object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Livre> getByEqualsCustomArgument( CustomArgument argument ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Livre> getByLikeCustomArgument( CustomArgument argument ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<Livre> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Livre> findAll() {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete( Livre object ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

}
