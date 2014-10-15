package com.joelcastro.eligetupropiaaventura.daos.fake;

import com.joelcastro.eligetupropiaaventura.daos.UserDAO;

import org.androidannotations.annotations.EBean;

/**
 * Created by joel on 15/10/14.
 */
@EBean
public class UserFakeDAO implements UserDAO {
    @Override
    public boolean checkUser(String user, String pass) {

        if(user.equals("user")&&pass.equals("pass"))
        {
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void changePass(String user, String pass, String newPass) {

    }
}
