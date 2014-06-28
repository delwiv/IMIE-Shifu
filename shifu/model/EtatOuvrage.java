package shifu.model;

import shifu.core.AbstractShifuModel;

public class EtatOuvrage extends AbstractShifuModel {

    private int idEtatOuvrage;
    private String commentaire;
    private String etatOuvrage;
    private boolean isLendable;

    
    
    public EtatOuvrage(int idEtatOuvrage, String commentaire, String etatOuvrage, boolean isLendable) {
        this.idEtatOuvrage = idEtatOuvrage;
        this.commentaire = commentaire;
        this.etatOuvrage = etatOuvrage;
        this.isLendable = isLendable;
    }
    
    public int getIdEtatOuvrage() {
        return idEtatOuvrage;
    }

    public void setIdEtatOuvrage(int idEtatOuvrage) {
        this.idEtatOuvrage = idEtatOuvrage;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getEtatOuvrage() {
        return etatOuvrage;
    }

    public void setEtatOuvrage(String etatOuvrage) {
        this.etatOuvrage = etatOuvrage;
    }

    public boolean isLendable() {
        return isLendable;
    }

    public void setIsLendable(boolean isLendable) {
        this.isLendable = isLendable;
    }

    
}
