package com.joelcastro.eligetupropiaaventura.utils;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by joel on 13/10/14.
 */
@SharedPref(value=SharedPref.Scope.APPLICATION_DEFAULT)
public interface MyPrefs {

    // The field name will have default value "John"
    @DefaultString("")
    String user();

    // The field age will have default value 42
    @DefaultString("")
    String pass();


}
