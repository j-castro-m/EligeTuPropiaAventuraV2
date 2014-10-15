package com.joelcastro.eligetupropiaaventura.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.joelcastro.eligetupropiaaventura.R;
import com.joelcastro.eligetupropiaaventura.fragments.HistoryListFragment;
import com.joelcastro.eligetupropiaaventura.models.Adventure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joel on 15/10/14.
 */
public class ArrayAdapterAdventureList extends ArrayAdapter<Adventure> {
    /*public ArrayAdapterAdventureList(HistoryListFragment historyListFragment, int history_list_item, List<Adventure> allAdventures) {
        super(historyListFragment, history_list_item, allAdventures);
    }*/
    Context mContext;
    int layoutResourceId;
    List<Adventure> data = new ArrayList<Adventure>();

    public ArrayAdapterAdventureList(Context mContext, int layoutResourceId, int layoutResourceFieldID,List<Adventure> data) {

        super(mContext, layoutResourceId,layoutResourceFieldID, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null){
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }


        Adventure objectItem = data.get(position);


        TextView textViewItem = (TextView) convertView.findViewById(R.id.historiaItemTitulo);
        textViewItem.setText(objectItem.getNombre());
        textViewItem.setTag(objectItem.getNombre());
        return convertView;

    }
}
