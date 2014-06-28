/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao.composite;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import shifu.model.Adherent;
import shifu.model.Emprunt;
import shifu.model.Individu;
import shifu.model.dao.CustomArgument;
import shifu.model.dao.DAO;
import shifu.model.dao.ShifuDAOFactory;
import shifu.model.dao.elementaire.IndividuDAO;

/**
 *
 * @author antoine
 */
public class AdherentDAOC extends DAO<Adherent> {

    public List<Adherent> getAllAdherentFromDB() throws SQLException {
        List<Adherent> listAdherents = new ArrayList();
//        List<Individu> individus = new ArrayList();
//        
//        Adherent adherentTest = new Adherent();

//        individus = ShifuDAOFactory.getIndividuDAO().getByEqualsCustomArgument( new CustomArgument( "1", "1" ) );
//        
//        Iterator it = individus.listIterator( 0 );
//        
//        while ( it.hasNext() ) {
//            Adherent adherent =new Adherent((Individu ) it.next());
//            try {
//                adherent.setDateAdhesion( ShifuDAOFactory.getAdherentDAO().getByID( adherent.getID()).getDateAdhesion() );
//            } catch ( Exception e ) {
//                //e.printStackTrace();
//            }
//            
//            
//            adherent.setAdresse( ShifuDAOFactory.getAdresseDAO().getByID( adherent.getIdAdresse()));
//            
//            listAdherents.add(adherent);
//        }
//        listAdherents.add( adherentTest );
        IndividuDAO individuDAO = new IndividuDAO();

        listAdherents = individuDAO.getOnlyAdherent();

        for ( Adherent adherent : listAdherents ) {
            adherent.setAdresse( ShifuDAOFactory.getAdresseDAO().getByID( adherent.getIdAdresse() ) );
            List<Emprunt> listEmprunts = ShifuDAOFactory.getEmpruntDAO()
                    .getByEqualsCustomArgument( new CustomArgument( "ID_individu", adherent.getIdIndividu() ) );
            for ( Emprunt e : listEmprunts ) {
                e.setAbonne( adherent );
                e.setOuvrage( ShifuDAOFactory.getOuvrageDAOC().getByID(
                        ShifuDAOFactory.getExemplaireDAO().getByID( e.getIdExemplaire() ).getIdOeuvre()
                ) );
            }
            adherent.setEmprunts( listEmprunts );
        }

        return listAdherents;
    }

    /**
     * Cette classe permet de creer un adhérent, et donc d'ecrire dans plusieurs
     * tables. Ces tables sont les suivantes:
     *
     * -Adresse -Individu -Adherent -Login -Statut_individu -Statut
     *
     * @param adherent instanceof Adherent
     * @return Le model objet de l'adhérant ainsi que l'écriture en base de
     * données
     */
    @Override
    public Adherent create( Adherent adherent ) throws SQLException {

        //ajout des donnees dans la table Adresse
        adherent.setAdresse( ShifuDAOFactory.getAdresseDAO().create( adherent.getAdresse() ) );

        //ajout du login TODO
        //adherent.setLogin( ShifuDAOFactory.getLoginDAO().create( adherent.getLogin()));
        //ajout des donnees dans la table Individu
        adherent.setID( ShifuDAOFactory.getIndividuDAO().create( adherent ).getID() );
        //ShifuDAOFactory.getIndividuDAO().create(adherent);

        //TODO:ajouter ligne dans adherent
        ShifuDAOFactory.getAdherentDAO().create( adherent );

        return adherent;
    }

    @Override
    public Adherent getByID( int ID ) throws SQLException {
        Adherent adherent = null;
        Individu individu = ShifuDAOFactory.getIndividuDAO().getByID( ID );
        if ( null != individu ) {
            Adherent adherentTmp = ShifuDAOFactory.getAdherentDAO().getByID( ID );

            adherent = new Adherent( individu, adherentTmp.getDateAdhesion() );

            adherent.setAdresse( ShifuDAOFactory.getAdresseDAO().getByID( adherent.getIdAdresse() ) );
            adherent.setEmprunts( ShifuDAOFactory.getEmpruntDAO().getByEqualsCustomArgument( new CustomArgument( "ID_individu", ID ) ) );
        } else {
            adherent = new Adherent();
        }
        return adherent;

    }

    @Override
    public Adherent update( Adherent object ) throws SQLException {

        
        ShifuDAOFactory.getIndividuDAO().update( object );
        ShifuDAOFactory.getAdresseDAO().update( object.getAdresse() );

        return object;
    }

    @Override
    public List<Adherent> getByEqualsCustomArgument( CustomArgument argument ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Adherent> getByLikeCustomArgument( CustomArgument argument ) {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<Adherent> executeCustomSearch( PreparedStatement statement, CustomArgument argument ) throws SQLException {
        throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Adherent> findAll() throws SQLException {
        return getAllAdherentFromDB();
    }

    @Override
    public void delete( Adherent object ) throws SQLException {
        if ( object.getEmprunts().size() > 0 ) {
            preparedStatement = getPreparedStatement( "DELETE FROM Emprunt WHERE ID_individu = " + object.getID() );
            preparedStatement.executeUpdate();

        }
        preparedStatement = getPreparedStatement( "DELETE FROM Adherent WHERE ID_individu = " + object.getID() );
        preparedStatement.executeUpdate();

        preparedStatement = getPreparedStatement( "DELETE FROM Individu WHERE ID_individu = " + object.getID() );
        preparedStatement.executeUpdate();

        preparedStatement = getPreparedStatement( "DELETE FROM Adresse WHERE ID_adresse = "
                + "(SELECT ID_adresse from Individu where ID_individu = " + object.getID() + ")" );
        preparedStatement.executeUpdate();

    }

}
