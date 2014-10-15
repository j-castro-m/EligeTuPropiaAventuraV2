package com.joelcastro.eligetupropiaaventura.daos;

/**
 * Created by joel on 15/10/14.
 */
public interface UserDAO {

    public boolean checkUser(String user,String pass);
    public void changePass(String user,String pass,String newPass);
}
