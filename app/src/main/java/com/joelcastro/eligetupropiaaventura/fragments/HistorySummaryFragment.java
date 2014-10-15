package com.joelcastro.eligetupropiaaventura.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.joelcastro.eligetupropiaaventura.R;
import com.joelcastro.eligetupropiaaventura.daos.AdventureDAO;
import com.joelcastro.eligetupropiaaventura.daos.AdventureHistoryDAO;
import com.joelcastro.eligetupropiaaventura.daos.DAOFactory;
import com.joelcastro.eligetupropiaaventura.models.AdventureNode;
import com.joelcastro.eligetupropiaaventura.utils.ArrayAdapterAdventureList;
import com.joelcastro.eligetupropiaaventura.utils.MyPrefs_;


import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

@EFragment(R.layout.fragment_list)
public class HistorySummaryFragment extends ListFragment {
    public static final String LAST_HISTORY_ID = "idHistoria";
    private static final String TAG_TEXTO="textoHistorico";
    private static final String TAG_OPCION="opcionElegida";

    private String idHistoria;

    @Pref
    MyPrefs_ myPrefs;

    @Bean
    DAOFactory daoFactory;
    AdventureHistoryDAO adventureHistoryDAO;

    @AfterInject
    void initDAO(){
        adventureHistoryDAO = daoFactory.getAdventureHistoryDAO();
    }

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
        //PreferencesHelperBK ph = new PreferencesHelperBK(rootView.getContext());
        ArrayList<HashMap<String, String>> summaryList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> summaryListadoHash;
        int i = 1;

        List<AdventureNode> lista = adventureHistoryDAO.getNodesFromAdventure(myPrefs.user().get(),myPrefs.adventureName().get());

        if(lista.size()>1) {
            ListIterator<AdventureNode> listIterator = lista.listIterator();
            ListIterator<AdventureNode> listIteratorForNextNode = lista.listIterator(1);
            Log.d("LISTA", lista.toString());


            for (int iteratorList = 0; iteratorList < lista.size() - 1; iteratorList++) {
                summaryListadoHash = new HashMap<String, String>();
                summaryListadoHash.put(TAG_TEXTO, lista.get(iteratorList).getTexto());
                summaryListadoHash.put(TAG_OPCION, lista.get(iteratorList + 1).getTitulo());
                summaryList.add(summaryListadoHash);
                Log.d("HASH", summaryListadoHash.toString());
            }
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
