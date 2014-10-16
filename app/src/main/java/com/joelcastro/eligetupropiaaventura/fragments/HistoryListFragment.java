package com.joelcastro.eligetupropiaaventura.fragments;

/**
 * Created by alu03009 on 15/01/14.
 */

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.joelcastro.eligetupropiaaventura.R;
import com.joelcastro.eligetupropiaaventura.daos.AdventureDAO;
import com.joelcastro.eligetupropiaaventura.daos.DAOFactory;
import com.joelcastro.eligetupropiaaventura.utils.ArrayAdapterAdventureList;
import com.joelcastro.eligetupropiaaventura.utils.MyPrefs;
import com.joelcastro.eligetupropiaaventura.utils.MyPrefs_;
import com.joelcastro.eligetupropiaaventura.utils.ServiceHandler;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Fragment that appears in the "content_frame", shows a planet
 */
@EFragment
public class HistoryListFragment extends ListFragment {

    public HistoryListFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Pref
    MyPrefs_ myPrefs;

    @Bean
    DAOFactory daoFactory;
    AdventureDAO adventureDAO;
    ArrayAdapterAdventureList adapter;

    @AfterInject
    void initDAO(){
        adventureDAO = daoFactory.getAdventureDAO();
    }

    @AfterViews
    void fillAdapterList(){
        adapter = new ArrayAdapterAdventureList(getView().getContext(), R.layout.history_list_item,R.id.historiaItemTitulo, adventureDAO.getAdventureListFromPlayer(myPrefs.user().get()));
        this.setListAdapter(adapter);
    }

    @Override
    public void onViewCreated (View rootView,
                               Bundle savedInstanceState) {
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String idHistoria = adventureDAO.getAdventureListFromPlayer(myPrefs.user().get()).get(position).getNombre();
                myPrefs.adventureName().put(idHistoria);
                HistoryNodeFragment fragment = new HistoryNodeFragment_();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putString(HistoryNodeFragment.ARG_ID, idHistoria);
                fragment.setArguments(args);
                transaction.replace(R.id.content_frame, fragment);
                transaction.commit();


            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public  boolean onItemLongClick(AdapterView<?> parent, View view,
            int position, long id) {
                adventureDAO.delAdventureFromPlayer(adventureDAO.getAdventureListFromPlayer(myPrefs.user().get()).get(position).getNombre());
                String idHistoria = adventureDAO.getAdventureListFromPlayer(myPrefs.user().get()).get(0).getNombre();
                myPrefs.adventureName().put(idHistoria);
                HistoryListFragment fragment = new HistoryListFragment_();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                transaction.replace(R.id.content_frame, fragment);
                transaction.commit();

                return true;
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }
}
