package com.joelcastro.eligetupropiaaventura.daos.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.joelcastro.eligetupropiaaventura.daos.AdventureHistoryDAO;
import com.joelcastro.eligetupropiaaventura.models.AdventureNode;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joel on 15/10/14.
 */
@EBean
public class AdventureHistorySQLiteDAO implements AdventureHistoryDAO {
    AdventureSQLiteOpenHelper openHelper;


    @RootContext
    Context context;


    @AfterInject
    void initOpenHelper(){
        openHelper = new AdventureSQLiteOpenHelper(context);
    }

    @Override
    public void addAdventureNodeToHistory(String player, int idNode, String nameAdventure,String status) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        db.execSQL("INSERT INTO "+AdventureSQLiteOpenHelper.HISTORY_TABLE_NAME+" VALUES('"+nameAdventure+"','"+player+"','"+idNode+"','"+status+"')");
    }



    @Override
    public void changeStatus(String player, String nameAdventure) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("status","Found");

        String[] args = new String[]{nameAdventure, player};
        db.update(AdventureSQLiteOpenHelper.HISTORY_TABLE_NAME , valores, "adventurename=? AND player=?", args);

    }

    @Override
    public String checkStatus(String player, String nameAdventure) {

        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor query = db.rawQuery("SELECT * FROM " + AdventureSQLiteOpenHelper.HISTORY_TABLE_NAME + " WHERE adventurename='" + nameAdventure+"' AND player='"+player+"'", null);
        query.moveToLast();
        String result;
        result = query.getString(query.getColumnIndex("status"));
        query.close();
        return result;


    }

    @Override
    public List<AdventureNode> getNodesFromAdventure(String player, String nameAdventure) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor query = db.rawQuery("SELECT * FROM " + AdventureSQLiteOpenHelper.HISTORY_TABLE_NAME + " WHERE adventurename='" + nameAdventure+"' AND player='"+player+"'", null);
        List<AdventureNode> nodes = new ArrayList<AdventureNode>();
        query.moveToFirst();
        for(int i=0;i<(query.getCount());i++){
            Integer nodeId = query.getInt(query.getColumnIndex("idnode"));

            Log.d("QUERY B",String.valueOf(nodeId));
            AdventureNode node = getNodeFromId(nodeId);
            Log.d("QUERY C",node.getTitulo());
            nodes.add(node);
            query.moveToNext();
        }
        query.close();
        //db.close();
        return nodes;
    }

    private AdventureNode getNodeFromId(int idNode) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Log.d("QUERYSTRING", "SELECT * FROM " + openHelper.NODES_TABLE_NAME + " WHERE id=" + idNode);
        Cursor query = db.rawQuery("SELECT * FROM "+openHelper.NODES_TABLE_NAME+" WHERE id="+idNode,null);
        query.moveToFirst();
        AdventureNode node = buildNodeFromCursor(query);
        query.close();
        //db.close();
        return node;
    }

    private AdventureNode buildNodeFromCursor(Cursor query) {
        AdventureNode node = new AdventureNode();

        node.setId(query.getInt(query.getColumnIndex("id")));
        node.setTexto(query.getString(query.getColumnIndex("texto")));
        node.setGPS(query.getString(query.getColumnIndex("gps")));
        node.setTitulo(query.getString(query.getColumnIndex("titulo")));
        node.setSiguienteNodoId1(query.getInt(query.getColumnIndex("idsiguiente1")));
        node.setSiguienteNodoId2(query.getInt(query.getColumnIndex("idsiguiente2")));

        return node;
    }
}
