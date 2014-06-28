/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shifu.model;

/**
 *
 * @author antoine
 */
public class Login {
    private int ID;
    private String login;
    private byte password[];
    private int ID_individu;

    public Login(int ID, String login, byte[] password, int ID_individu) {
        this.ID = ID;
        this.login = login;
        this.password = password;
        this.ID_individu = ID_individu;
    }

    
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public int getID_individu() {
        return ID_individu;
    }

    public void setID_individu(int ID_individu) {
        this.ID_individu = ID_individu;
    }
    
    
    
}
