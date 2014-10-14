package com.joelcastro.eligetupropiaaventura.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.joelcastro.eligetupropiaaventura.R;
import com.joelcastro.eligetupropiaaventura.utils.PreferencesHelper;

import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.HashMap;

@EFragment(R.layout.fragment_list)
public class HistorySummaryFragment extends ListFragment {
    public static final String LAST_HISTORY_ID = "idHistoria";

    private static final String TAG_TEXTO="textoHistorico";
    private static final String TAG_OPCION="opcionElegida";

    private String idHistoria;




    public static HistorySummaryFragment newInstance(String param1) {

        HistorySummaryFragment fragment = new HistorySummaryFragment();
        Bundle args = new Bundle();
        args.putString(LAST_HISTORY_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }
    public HistorySummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idHistoria = getArguments().getString(LAST_HISTORY_ID);
        }
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated (View rootView,
                               Bundle savedInstanceState){
        ListView lv = getListView();
        PreferencesHelper ph = new PreferencesHelper(rootView.getContext());
        ArrayList<HashMap<String, String>> summaryList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> summaryListadoHash = new HashMap<String, String>();
        int i = 1;
        while((ph.GetPreferences(idHistoria+TAG_OPCION+i)=="")){
            summaryListadoHash.put(TAG_TEXTO,ph.GetPreferences(idHistoria+TAG_TEXTO+i));
            summaryListadoHash.put(TAG_OPCION,ph.GetPreferences(idHistoria+TAG_OPCION+i));
            summaryList.add(summaryListadoHash);
            Log.d("LISTA",summaryList.toString());
        }

        ListAdapter adapter = new SimpleAdapter(
                rootView.getContext(), summaryList,
                R.layout.history_summary_item,
                new String[] {
                        TAG_TEXTO,
                        TAG_OPCION},
                new int[] {
                        R.id.summaryText,
                        R.id.summaryElection});

        setListAdapter(adapter);



    }
}
