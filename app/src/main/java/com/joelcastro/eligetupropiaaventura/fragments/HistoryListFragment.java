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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.joelcastro.eligetupropiaaventura.R;
import com.joelcastro.eligetupropiaaventura.utils.PreferencesHelper;
import com.joelcastro.eligetupropiaaventura.utils.ServiceHandler;

import org.androidannotations.annotations.EFragment;
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
    private static final String TAG_USER ="user";
    private static final String TAG_ID ="id";
    private static final String TAG_TITULO="titulo";
    private static final String TAG_TEXTO="texto";
    private static final String TAG_OPCION1NAME="opcion1Nombre";
    private static final String TAG_OPCION1TEXTO="opcion1Texto";
    private static final String TAG_OPCION1GPS="opcion1GPS";
    private static final String TAG_OPCION2NAME="opcion2Nombre";
    private static final String TAG_OPCION2TEXTO="opcion2Texto";
    private static final String TAG_OPCION2GPS="opcion2GPS";
    private static final String TAG_SUMMARY="historico";
    private static final String TAG_SUMMARY_ITEM="itemHistorico";
    private static final String TAG_SUMMARY_TEXT="textoHistorico";
    private static final String TAG_SUMMARY_OPTION="opcionElegida";



    private ProgressDialog pDialog;
    private static String url = "https://raw2.github.com/vlad29/vlad29.github.io/master/cyoagps.json";
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

    // contacts JSONArray
    JSONArray items = null;
    // Hashmap for ListView
    ArrayList<HashMap<String, String>> historyList;



    public HistoryListFragment() {
        // Empty constructor required for fragment subclasses
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated (View rootView,
                               Bundle savedInstanceState){




        PreferencesHelper ph = new PreferencesHelper(rootView.getContext());
        nameValuePairs.add(new BasicNameValuePair("usuario", ph.GetPreferences("usuario")));


        historyList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
            int position, long id) {
                PreferencesHelper ph = new PreferencesHelper(view.getContext());
            // getting values from selected ListItem
            String idHistoria = ((TextView) view.findViewById(R.id.historiaItemID)).getText().toString();
                ph.SavePreferences("lastHistory",idHistoria);
                HistoryNodeFragment fragment = new HistoryNodeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putString(HistoryNodeFragment.ARG_ID, idHistoria);
                fragment.setArguments(args);
                transaction.replace(R.id.content_frame, fragment);
                transaction.commit();


            }
        });

        GetData dataFromURL = new GetData();
        dataFromURL.setContext(rootView.getContext());
        dataFromURL.execute();

    }


private class GetData extends AsyncTask<Void, Void, Void> {


    Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Cargando Historias...");
        pDialog.setCancelable(false);
        pDialog.show();

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        // Creating service handler class instance
        ServiceHandler sh = new ServiceHandler();
        PreferencesHelper ph = new PreferencesHelper(context);
        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET,nameValuePairs);

        if (jsonStr != null) {
            try {

                // Getting JSON Array node
                JSONObject jsonObject = new JSONObject(jsonStr);
                items = jsonObject.getJSONArray("historias");

                // looping through All Items
                for (int i = 0; i < items.length(); i++) {
                    JSONObject c = items.getJSONObject(i);


                    String  historiaId = c.getString(TAG_ID);
                    String  historiaTitulo = c.getString(TAG_TITULO);
                    String  historiaTexto =c.getString(TAG_TEXTO);
                    String  historiaOpcion1Name =c.getString(TAG_OPCION1NAME);
                    String  historiaOpcion1Texto =c.getString(TAG_OPCION1TEXTO);
                    String  historiaOpcion1GPS =c.getString(TAG_OPCION1GPS);
                    String  historiaOpcion2Name =c.getString(TAG_OPCION2NAME);
                    String  historiaOpcion2Texto =c.getString(TAG_OPCION2TEXTO);
                    String  historiaOpcion2GPS =c.getString(TAG_OPCION2GPS);


                    // tmp hashmap for the item
                    HashMap<String, String> historiaListadoHash = new HashMap<String, String>();

                    JSONArray summaryJSON = c.getJSONArray(TAG_SUMMARY);
                    for (int j = 1; j <= items.length(); j++) {
                       JSONObject itemHistorico = summaryJSON.getJSONObject(j-1);
                        ph.SavePreferences(historiaId+TAG_SUMMARY_TEXT+j,itemHistorico.getString(TAG_SUMMARY_TEXT));
                        ph.SavePreferences(historiaId+TAG_SUMMARY_OPTION+j,itemHistorico.getString(TAG_SUMMARY_OPTION));
                    }



                    historiaListadoHash.put(TAG_ID,historiaId);
                    historiaListadoHash.put(TAG_TITULO, historiaTitulo);


                    ph.SavePreferences(historiaId+TAG_ID,historiaId);
                    ph.SavePreferences(historiaId+TAG_TITULO,historiaTitulo);
                    ph.SavePreferences(historiaId+TAG_TEXTO,historiaTexto);
                    ph.SavePreferences(historiaId+TAG_OPCION1NAME,historiaOpcion1Name);
                    ph.SavePreferences(historiaId+TAG_OPCION1TEXTO,historiaOpcion1Texto);
                    ph.SavePreferences(historiaId+TAG_OPCION1GPS,historiaOpcion1GPS);
                    ph.SavePreferences(historiaId+TAG_OPCION2NAME,historiaOpcion2Name);
                    ph.SavePreferences(historiaId+TAG_OPCION2TEXTO,historiaOpcion2Texto);
                    ph.SavePreferences(historiaId+TAG_OPCION2GPS,historiaOpcion2GPS);



                    // adding contact to contact list
                    historyList.add(historiaListadoHash);
                    if(ph.GetPreferences("lastHistoriId")==""){
                        ph.SavePreferences("lastHistoryId",historiaId);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing())
            pDialog.dismiss();
        /**
         * Updating parsed JSON data into ListView
         * */
        ListAdapter adapter = new SimpleAdapter(
                context, historyList,
                R.layout.history_list_item,
                new String[] {
                        TAG_ID,
                        TAG_TITULO},
                new int[] {
                        R.id.historiaItemID,
                        R.id.historiaItemTitulo});

         setListAdapter(adapter);
    }

}

}
