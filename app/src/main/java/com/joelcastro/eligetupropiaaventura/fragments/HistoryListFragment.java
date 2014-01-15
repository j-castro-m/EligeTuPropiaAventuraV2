package com.joelcastro.eligetupropiaaventura.fragments;

/**
 * Created by alu03009 on 15/01/14.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joelcastro.eligetupropiaaventura.R;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
public class HistoryListFragment extends Fragment {
    public static final String DRAWER_ITEM_NUMBER = "drawer_item_number";

    public HistoryListFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history_list, container, false);
        int i = getArguments().getInt(DRAWER_ITEM_NUMBER);
        String drawer_selection = getResources().getStringArray(R.array.drawer_actions)[i];


        return rootView;
    }
}
