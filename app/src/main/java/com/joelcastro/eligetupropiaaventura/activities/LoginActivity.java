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
import android.widget.TextView;
import android.widget.Toast;

import com.joelcastro.eligetupropiaaventura.R;
import com.joelcastro.eligetupropiaaventura.utils.MyPrefs_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@NoTitle
@Fullscreen
@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    @Pref
    MyPrefs_ myPrefs;
    @ViewById(R.id.textUserMain) EditText tusuario;
    @ViewById(R.id.textPassMain) EditText tpass;
    @ViewById(R.id.buttonEnterMain) Button button;

    @AfterViews
    void addTextValues() {
        tusuario.setText(myPrefs.user().get());
        tpass.setText(myPrefs.pass().get());
    }

    @TextChange({R.id.textUserMain,R.id.textPassMain})
    void onUserTextChange(TextView tv, CharSequence text) {
        if((tpass.getText().length()>0)&&(tusuario.getText().length()>0))
        {
            button.setEnabled(true);
        }
        else
        {
            button.setEnabled(false);
        }

    }

    @Click(value = R.id.buttonEnterMain)
    void doButtonEnter() {

        if((tusuario.getText().toString().equals("user"))&&(tpass.getText().toString().equals("pass")))
        {
            myPrefs.user().put( tusuario.getText().toString());
            myPrefs.pass().put( tpass.getText().toString());

            Intent intent = new Intent(LoginActivity.this, DrawerMenuActivity_.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.bad_login);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            tusuario.setText("");
            tpass.setText("");
        }



    }


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_login);
        final Button button = (Button) findViewById(R.id.buttonEnterMain);
        final EditText tusuario = (EditText) findViewById(R.id.textUserMain);
        final EditText tpass = (EditText) findViewById(R.id.textPassMain);

        final SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        String user = prefs.getString("usuario", "");
        String pass = prefs.getString("pass", "");

        if(user!=""&&pass!="")
        {
            Intent intent = new Intent(LoginActivity.this, DrawerMenuActivity.class);
            intent.putExtra("usuario",user);
            intent.putExtra("pass",pass);
            startActivity(intent);
        }

        tusuario.setText(user);
        tpass.setText(pass);



        }*/



    }

