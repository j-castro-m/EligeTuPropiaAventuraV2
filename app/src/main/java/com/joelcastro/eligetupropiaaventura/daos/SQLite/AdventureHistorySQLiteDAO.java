package com.joelcastro.eligetupropiaaventura.daos.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    AdventureNodeSQLiteDAO nodeDAO;

    @RootContext
    Context context;

    @AfterInject
    void initOpenHelper(){
        openHelper = new AdventureSQLiteOpenHelper(context);
    }

    @Override
    public void addAdventureNodeToHistory(String player, int idNode, String nameAdventure) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        db.execSQL("INSERT INTO "+AdventureSQLiteOpenHelper.HISTORY_TABLE_NAME+" VALUES('"+nameAdventure+"','"+player+"',"+idNode+")");
    }

    @Override
    public List<AdventureNode> getNodesFromAdventure(String player, String nameAdventure) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor query = db.rawQuery("SELECT * FROM " + AdventureSQLiteOpenHelper.HISTORY_TABLE_NAME + "WHERE adventurename='" + nameAdventure+"'", null);
        List<AdventureNode> nodes = new ArrayList<AdventureNode>();
        query.moveToFirst();
        for(int i=0;i<query.getCount();i++){
            AdventureNode node = nodeDAO.getNodeFromId(query.getInt(query.getColumnIndex("idnode")));
            nodes.add(node);
            query.moveToNext();
        }
        query.close();
        //db.close();
        return nodes;
    }
}
