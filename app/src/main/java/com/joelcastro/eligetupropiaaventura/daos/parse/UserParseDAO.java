package com.joelcastro.eligetupropiaaventura.daos.parse;

import com.joelcastro.eligetupropiaaventura.daos.UserDAO;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.androidannotations.annotations.EBean;

/**
 * Created by joel on 15/10/14.
 */
@EBean
public class UserParseDAO implements UserDAO{
    @Override
    public boolean checkUser(String user, String pass) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("usuarios");
        query.whereMatches("user",user);
        try {
            ParseObject usuario = query.getFirst();
            return true;
        } catch (ParseException e) {
            return false;
        }

    }


    @Override
    public void changePass(String user, String pass, String newPass) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("usuarios");
        query.whereMatches("user",user);
        try {
            ParseObject usuario = query.getFirst();
            usuario.put("user", usuario);
            usuario.put("password", newPass);

            usuario.saveInBackground();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
