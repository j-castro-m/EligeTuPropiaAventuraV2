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
        Parse.initialize(this, "7RLzgjWr0hCQi2d7bvzOn5vUuOkvXGpZU4Dp6fOs", "NsiqMAoie3lC0TtfxYF5F5Sj0H4HZFVJNmayGSC0");
    }
}