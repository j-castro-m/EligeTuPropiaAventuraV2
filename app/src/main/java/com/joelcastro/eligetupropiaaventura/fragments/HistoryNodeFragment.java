package com.joelcastro.eligetupropiaaventura.fragments;

import android.app.Activity;
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
import android.widget.TextView;

import com.joelcastro.eligetupropiaaventura.R;
import com.joelcastro.eligetupropiaaventura.activities.LoginActivity;
import com.joelcastro.eligetupropiaaventura.utils.PreferencesHelper;

import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_history_node)
public class HistoryNodeFragment extends Fragment {

    private static final String TAG_ID ="id";
    private static final String TAG_TITULO="titulo";
    private static final String TAG_TEXTO="texto";
    private static final String TAG_OPCION1NAME="opcion1Nombre";
    private static final String TAG_OPCION1TEXTO="opcion1Texto";
    private static final String TAG_OPCION1GPS="opcion1GPS";
    private static final String TAG_OPCION2NAME="opcion2Nombre";
    private static final String TAG_OPCION2TEXTO="opcion2Texto";
    private static final String TAG_OPCION2GPS="opcion2GPS";
    private static final String TAG_OPCION_ELEGIDA="opcionElegida";
    private static final String TAG_OPCION_ELEGIDADESCRIPCION="opcionElegidaDescripcion";
    private static final String TAG_OPCION_ELEGIDAGPS="opcionElegidaGPS";

    public static final String ARG_ID = "idHistoria";
    private String idHistoria;

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            idHistoria = getArguments().getString(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_node, container, false);
    }

    @Override
    public void onViewCreated (View rootView,
                               Bundle savedInstanceState){
        final PreferencesHelper ph = new PreferencesHelper(rootView.getContext());
        final TextView title = (TextView) rootView.findViewById(R.id.nodeTitle);
        final TextView text = (TextView) rootView.findViewById(R.id.nodeTexto);
        final TextView selectedOption = (TextView) rootView.findViewById(R.id.nodeOpcionElegida);
        final TextView selectedOptionDescription = (TextView) rootView.findViewById(R.id.nodeDescripcionOpcionElegida);
        final Button getMapa = (Button) rootView.findViewById(R.id.nodeButtonMapa);



        final String historyTitle = ph.GetPreferences(idHistoria+TAG_TITULO);
        final String historyTexto = ph.GetPreferences(idHistoria+TAG_TEXTO);
        final String historyOpcion1Name = ph.GetPreferences(idHistoria+TAG_OPCION1NAME);
        final String historyOpcion1Texto = ph.GetPreferences(idHistoria+TAG_OPCION1TEXTO);
        final String historyOpcion1GPS = ph.GetPreferences(idHistoria+TAG_OPCION1GPS);
        final String historyOpcion2Name = ph.GetPreferences(idHistoria+TAG_OPCION2NAME);
        final String historyOpcion2Texto = ph.GetPreferences(idHistoria+TAG_OPCION2TEXTO);
        final String historyOpcion2GPS = ph.GetPreferences(idHistoria+TAG_OPCION2GPS);

        if(ph.GetPreferences(idHistoria+TAG_OPCION_ELEGIDA)==""){
            AlertDialog.Builder builder = new AlertDialog.Builder(rootView.getContext());
            builder.setMessage(historyTexto)
                    .setCancelable(false)
                    .setPositiveButton(historyOpcion1Name, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ph.SavePreferences(idHistoria+TAG_OPCION_ELEGIDA,historyOpcion1Name);
                            ph.SavePreferences(idHistoria + TAG_OPCION_ELEGIDAGPS, historyOpcion1GPS);
                            ph.SavePreferences(idHistoria + TAG_OPCION_ELEGIDADESCRIPCION, historyOpcion1Texto);
                            selectedOption.setText(historyOpcion1Name);
                            selectedOptionDescription.setText(historyOpcion1Texto);
                        }
                    })
                    .setNegativeButton(historyOpcion2Name, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ph.SavePreferences(idHistoria + TAG_OPCION_ELEGIDA, historyOpcion2Name);
                            ph.SavePreferences(idHistoria + TAG_OPCION_ELEGIDAGPS, historyOpcion2GPS);
                            ph.SavePreferences(idHistoria + TAG_OPCION_ELEGIDADESCRIPCION, historyOpcion2Texto);
                            selectedOption.setText(historyOpcion2Name);
                            selectedOptionDescription.setText(historyOpcion2Texto);

                        }
                    });



            AlertDialog alert = builder.create();
            alert.show();

        }


        getMapa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String position[] = ph.GetPreferences(idHistoria + TAG_OPCION_ELEGIDAGPS).split(",");
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=" + position[0] + "," + position[1]));
                startActivity(intent);
            }
        });

        title.setText(historyTitle);
        text.setText(historyTexto);
        selectedOption.setText(ph.GetPreferences(idHistoria+TAG_OPCION_ELEGIDA));
        selectedOptionDescription.setText(ph.GetPreferences(idHistoria + TAG_OPCION_ELEGIDADESCRIPCION));

    }






}
