package com.joelcastro.eligetupropiaaventura.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by alu03009 on 15/01/14.
 */
public class PreferencesHelper {

    private SharedPreferences sharedPreferences;
    private Editor editor;

    public PreferencesHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit(); }

    public String GetPreferences(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void SavePreferences(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }
}