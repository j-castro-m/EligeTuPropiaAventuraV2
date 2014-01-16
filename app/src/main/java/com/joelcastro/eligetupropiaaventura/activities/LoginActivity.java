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

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
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


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if((tusuario.getText().toString().equals("user"))&&(tpass.getText().toString().equals("pass")))
                {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("usuario", tusuario.getText().toString());
                    editor.putString("pass", tpass.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, DrawerMenuActivity.class);
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
        });



    }
}
