package com.joelcastro.eligetupropiaaventura.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.joelcastro.eligetupropiaaventura.R;
import com.joelcastro.eligetupropiaaventura.daos.AdventureHistoryDAO;
import com.joelcastro.eligetupropiaaventura.daos.AdventureNodeDAO;
import com.joelcastro.eligetupropiaaventura.daos.DAOFactory;
import com.joelcastro.eligetupropiaaventura.models.AdventureNode;
import com.joelcastro.eligetupropiaaventura.utils.MyPrefs_;


import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.List;

@EFragment(R.layout.fragment_history_node)
public class HistoryNodeFragment extends Fragment {


    public static final String ARG_ID = "idHistoria";
    private String idHistoria;
    AdventureNode nodo;

    @Pref
    MyPrefs_ myPrefs;

    @Bean
    DAOFactory daoFactory;
    AdventureHistoryDAO adventureHistoryDAO;
    AdventureNodeDAO adventureNodeDAO;

    @AfterInject
    void initDAO(){
        adventureHistoryDAO = daoFactory.getAdventureHistoryDAO();
        adventureNodeDAO = daoFactory.getAdventureNodeDAO();
        List<AdventureNode> listaNodos = adventureHistoryDAO.getNodesFromAdventure(myPrefs.user().get(),myPrefs.adventureName().get());
        nodo = listaNodos.get(listaNodos.size()-1);
    }


    @ViewById(R.id.nodeTitle) TextView title;
    @ViewById(R.id.nodeTexto) TextView text;
    @ViewById(R.id.nodeOpcionElegida) TextView selectedOption;
    @ViewById(R.id.nodeDescripcionOpcionElegida) TextView selectedOptionDescription;
    @ViewById(R.id.nodeButtonMapa) Button getMapa;

    public HistoryNodeFragment() {
        // Required empty public constructor
    }

    public static HistoryNodeFragment newInstance(String param1) {
        HistoryNodeFragment fragment = new HistoryNodeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ID, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @AfterViews
    void addTextValues() {
        if((adventureHistoryDAO.checkStatus(
                myPrefs.user().get(),
                myPrefs.adventureName().get())
        ).equals("Found")){
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(nodo.getTexto())
                    .setCancelable(false)
                    .setPositiveButton(adventureNodeDAO.getNodeFromId(nodo.getSiguienteNodoId1()).getTitulo(), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            adventureHistoryDAO.changeStatus(myPrefs.user().get(),myPrefs.adventureName().get());
                            adventureHistoryDAO.addAdventureNodeToHistory(myPrefs.user().get(),nodo.getSiguienteNodoId1(),myPrefs.adventureName().get(),"Searching");
                            title.setText(adventureNodeDAO.getNodeFromId(nodo.getSiguienteNodoId1()).getTitulo());
                            text.setText(adventureNodeDAO.getNodeFromId(nodo.getSiguienteNodoId1()).getTexto());

                        }
                    })
                    .setNegativeButton(adventureNodeDAO.getNodeFromId(nodo.getSiguienteNodoId2()).getTitulo(), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            adventureHistoryDAO.changeStatus(myPrefs.user().get(),myPrefs.adventureName().get());
                            adventureHistoryDAO.addAdventureNodeToHistory(myPrefs.user().get(),nodo.getSiguienteNodoId2(),myPrefs.adventureName().get(),"Searching");
                            title.setText(adventureNodeDAO.getNodeFromId(nodo.getSiguienteNodoId2()).getTitulo());
                            text.setText(adventureNodeDAO.getNodeFromId(nodo.getSiguienteNodoId2()).getTexto());

                        }
                    });



            AlertDialog alert = builder.create();
            alert.show();

        }else{
            title.setText(nodo.getTitulo());
            text.setText(nodo.getTexto());
        }

        if (getArguments() != null) {
            idHistoria = getArguments().getString(ARG_ID);
        }
        title.setText(nodo.getTitulo());//title.setText(historyTitle);
        text.setText(nodo.getTexto());//text.setText(historyTexto);
    }






    @Click(value = R.id.nodeButtonMapa)
    void doButtonEnter() {
                String position[] = nodo.getGPS().split(",");
                Log.d("GPSSS",nodo.getGPS());
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + position[0] + "," + position[1]+"&daddr=" + position[0] + "," + position[1]));

                startActivity(intent);
            }


    }







