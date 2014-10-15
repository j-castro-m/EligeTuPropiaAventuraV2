package com.joelcastro.eligetupropiaaventura.daos.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joelcastro.eligetupropiaaventura.daos.AdventureDAO;
import com.joelcastro.eligetupropiaaventura.models.Adventure;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joel on 12/10/14.
 */
@EBean
public class AdventureSQLiteDAO implements AdventureDAO {
    AdventureSQLiteOpenHelper openHelper;

    @RootContext
    Context context;

    @AfterInject
    void initOpenHelper(){
        openHelper = new AdventureSQLiteOpenHelper(context);
    }

    @Override
    public List<Adventure> getAllAdventures() {

        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor query = db.rawQuery("SELECT * FROM " + AdventureSQLiteOpenHelper.ADVENTURES_TABLE_NAME, null);
        List<Adventure> adventures = new ArrayList<Adventure>();
        query.moveToFirst();
        for(int i=0;i<query.getCount();i++){
            Adventure adventure = buildAdventureFromCursor(query);
            adventures.add(adventure);
            query.moveToNext();
        }
        query.close();
        //db.close();
        return adventures;


    }

    @Override
    public List<Adventure> getAdventureListFromPlayer(String idPlayer) {

        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor query = db.rawQuery("SELECT * FROM " + AdventureSQLiteOpenHelper.ADVENTURES_AND_PLAYER_TABLE_NAME+" WHERE player='"+idPlayer+"'", null);
        List<Adventure> adventures = new ArrayList<Adventure>();
        query.moveToFirst();
        for(int i=0;i<query.getCount();i++){
            Adventure adventure = getAdventureFromName(query.getString(query.getColumnIndex("adventurename")));
            adventures.add(adventure);
            query.moveToNext();
        }
        query.close();
        //db.close();
        return adventures;

    }

    @Override
    public Adventure getAdventureFromName(String adventureName) {


        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor query = db.rawQuery("SELECT * FROM "+openHelper.ADVENTURES_TABLE_NAME+" WHERE adventurename='"+adventureName+"'",null);
        query.moveToFirst();
        Adventure adventure = buildAdventureFromCursor(query);
        query.close();
        //db.close();
        return adventure;
    }

    @Override
    public void delAdventureFromPlayer(String adventureName) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        try{
            db.execSQL("DELETE FROM "+AdventureSQLiteOpenHelper.ADVENTURES_AND_PLAYER_TABLE_NAME+" WHERE adventurename='"+adventureName+"'");
        }catch(Exception ex){

        }

    }

    protected Adventure buildAdventureFromCursor(Cursor query) {
        Adventure adventure = new Adventure();

        adventure.setNombre(query.getString(query.getColumnIndex("adventurename")));
        adventure.setDescripcion(query.getString(query.getColumnIndex("description")));
        adventure.setIdNodoInicial(query.getInt(query.getColumnIndex("firstnode")));
        adventure.setIdNodoActual(query.getInt(query.getColumnIndex("actualnode")));

        return adventure;
    }

}
