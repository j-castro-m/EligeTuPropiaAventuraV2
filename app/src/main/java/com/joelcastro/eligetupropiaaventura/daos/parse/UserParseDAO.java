package com.joelcastro.eligetupropiaaventura.daos.parse;

import android.util.Log;

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
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Usuarios");
        query.whereMatches("user",user);
        try {
            ParseObject usuario = query.getFirst();
            if(usuario.getString("password").equals(pass))
            {
                return true;
            }else
            {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

    }


    @Override
    public void changePass(String user, String pass, String newPass) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Usuarios");
        query.whereMatches("user",user);

        try {
            ParseObject usuario = query.getFirst();
            usuario.put("user", user);
            usuario.put("password", newPass);

            usuario.saveInBackground();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
