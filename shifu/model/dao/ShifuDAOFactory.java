/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shifu.model.dao;

import shifu.model.Adresse;
import shifu.model.EtatOuvrage;
import shifu.model.IdentifiantOeuvre;
import shifu.model.Login;
import shifu.model.Nationalite;
import shifu.model.dao.composite.AdherentDAOC;
import shifu.model.dao.composite.LivreDAOC;
import shifu.model.dao.composite.OuvrageDAOC;
import shifu.model.dao.elementaire.AdherentDAO;
import shifu.model.dao.elementaire.AdresseDAO;
import shifu.model.dao.elementaire.AuteurDAO;
import shifu.model.dao.elementaire.AuteurOeuvreDAO;
import shifu.model.dao.elementaire.EmpruntDAO;
import shifu.model.dao.elementaire.EtatDAO;
import shifu.model.dao.elementaire.ExemplaireDAO;
import shifu.model.dao.elementaire.GenreDAO;
import shifu.model.dao.elementaire.IdentifiantOeuvreDAO;
import shifu.model.dao.elementaire.IndividuDAO;
import shifu.model.dao.elementaire.LivreDAO;
import shifu.model.dao.elementaire.LoginDAO;
import shifu.model.dao.elementaire.NationaliteDAO;
import shifu.model.dao.elementaire.OeuvreDAO;
import shifu.model.dao.tables.TableAuteurOeuvre;
import shifu.model.dao.tables.TableExemplaire;
import shifu.model.dao.tables.TableLivre;
import shifu.model.dao.tables.TableOeuvre;

/**
 * Cette factory nous donne des méthodes static pour 
 * accédes aux différents DAO disponibles
 * @author delwiv
 */
public class ShifuDAOFactory {

    // DAO Elementaires
    public static AuteurDAO getAuteurDAO() {
        return new AuteurDAO();
    }

    public static AdherentDAO getAdherentDAO() {
        return new AdherentDAO();
    }

    public static DAO<TableOeuvre> getOeuvreDAO() {
        return new OeuvreDAO();
    }

    public static DAO<Adresse> getAdresseDAO() {
        return new AdresseDAO();
    }
    public static EmpruntDAO getEmpruntDAO() {
        return new EmpruntDAO();
    }
    
    public static DAO<TableAuteurOeuvre> getAuteurOeuvreDAO() {
        return new AuteurOeuvreDAO();
    }

    public static DAO<TableExemplaire> getExemplaireDAO() {
        return new ExemplaireDAO();
    }

    public static IdentifiantOeuvreDAO getIdentifiantOeuvreDAO() {
        return new IdentifiantOeuvreDAO();
    }

    public static IndividuDAO getIndividuDAO() {
        return new IndividuDAO();
    }

    public static DAO<TableLivre> getLivreDAO() {
        return new LivreDAO();
    }

    public static DAO<Login> getLoginDAO() {
        return new LoginDAO();
    }

    public static DAO<Nationalite> getNationaliteDAO() {
        return new NationaliteDAO();
    }
    public static GenreDAO getGenreDAO() {
        return new GenreDAO();
    }
    public static DAO<EtatOuvrage> getEtatOuvrageDAO() {
        return new EtatDAO();
    }
    // DAO Composites

    public static OuvrageDAOC getOuvrageDAOC() {
        return new OuvrageDAOC();
    }
    
    public static AdherentDAOC getAdherentDAOC(){
        return new AdherentDAOC();
    }

    public static LivreDAOC getLivreDAOC(){
        return new LivreDAOC();
    }
}
