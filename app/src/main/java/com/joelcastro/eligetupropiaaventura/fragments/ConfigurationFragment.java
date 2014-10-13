package com.joelcastro.eligetupropiaaventura.fragments;



import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joelcastro.eligetupropiaaventura.R;

import org.androidannotations.annotations.EFragment;


@EFragment
public class ConfigurationFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.fragment_configuration);
    }

}
