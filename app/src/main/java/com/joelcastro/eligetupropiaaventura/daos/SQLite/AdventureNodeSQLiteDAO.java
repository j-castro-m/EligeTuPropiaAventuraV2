package com.joelcastro.eligetupropiaaventura.daos.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.joelcastro.eligetupropiaaventura.daos.AdventureNodeDAO;
import com.joelcastro.eligetupropiaaventura.models.AdventureNode;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by joel on 12/10/14.
 */
@EBean
public class AdventureNodeSQLiteDAO implements AdventureNodeDAO {

    AdventureSQLiteOpenHelper openHelper;

    @RootContext
    Context context;

    @AfterInject
    void initOpenHelper(){
        openHelper = new AdventureSQLiteOpenHelper(context);
    }
    @Override
    public AdventureNode getNodeFromId(int idNode) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Log.d("QUERYSTRING", "SELECT * FROM " + openHelper.NODES_TABLE_NAME + " WHERE id=" + idNode);
        Cursor query = db.rawQuery("SELECT * FROM "+openHelper.NODES_TABLE_NAME+" WHERE id="+idNode,null);
        query.moveToFirst();
        AdventureNode node = buildNodeFromCursor(query);
        query.close();
        //db.close();
        return node;
    }

    public AdventureNode buildNodeFromCursor(Cursor query) {
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
