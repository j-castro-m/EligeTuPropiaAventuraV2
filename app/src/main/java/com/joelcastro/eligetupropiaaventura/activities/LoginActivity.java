package com.joelcastro.eligetupropiaaventura.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.joelcastro.eligetupropiaaventura.R;
import com.joelcastro.eligetupropiaaventura.daos.DAOFactory;
import com.joelcastro.eligetupropiaaventura.daos.UserDAO;
import com.joelcastro.eligetupropiaaventura.utils.MyPrefs_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@NoTitle
@Fullscreen
@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    @Bean
    DAOFactory daoFactory;
    UserDAO userDAO;

    @Pref
    MyPrefs_ myPrefs;
    @ViewById(R.id.textUserMain) EditText tusuario;
    @ViewById(R.id.textPassMain) EditText tpass;
    @ViewById(R.id.textPassNew) EditText npass;
    @ViewById(R.id.buttonEnterMain) Button button;
    @ViewById(R.id.buttonChangePass) Button buttonPass;

    @AfterViews
    void addTextValues() {
        tusuario.setText(myPrefs.user().get());
        tpass.setText(myPrefs.pass().get());
    }

    @AfterInject
    void initDAO(){
        userDAO = daoFactory.getUserDAO();
    }

    @Click(value = R.id.buttonEnterMain)
    void doButtonEnter() {

        if (userDAO.checkUser(tusuario.getText().toString(),tpass.getText().toString())) {
            myPrefs.user().put(tusuario.getText().toString());
            myPrefs.pass().put(tpass.getText().toString());

            Intent intent = new Intent(LoginActivity.this, DrawerMenuActivity_.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.bad_login);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            tusuario.setText("");
            tpass.setText("");
        }
    }

        @Click(value = R.id.buttonChangePass)
        void doChangePass() {

            if(userDAO.checkUser(tusuario.getText().toString(),tpass.getText().toString()))
            {
                String user = tusuario.getText().toString();
                String newPass = npass.getText().toString();
                userDAO.changePass(user,tpass.getText().toString(),newPass);
                Context context = getApplicationContext();
                CharSequence text = "Contraseña cambiada";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                tusuario.setText(user);
                tpass.setText(newPass);
                npass.setText("");
            }
            else
            {
                Context context = getApplicationContext();
                CharSequence text = "Contraseña no cambiada";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                tusuario.setText("");
                tpass.setText("");
            }


    }


    }

