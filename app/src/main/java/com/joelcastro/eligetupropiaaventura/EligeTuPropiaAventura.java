package com.joelcastro.eligetupropiaaventura;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by joel on 12/10/14.
 */
public class EligeTuPropiaAventura extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Parse.initialize(this, "odRjmri5FhKQFi2dQyh6hR8t4dyzRpRbEpTEae62", "NbaLF6n1NVUu01Zp4jnmRFumjMejXao5d92QQTCA");
    }
}