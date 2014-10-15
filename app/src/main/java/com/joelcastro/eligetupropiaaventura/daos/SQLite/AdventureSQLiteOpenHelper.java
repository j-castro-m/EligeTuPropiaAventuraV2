package com.joelcastro.eligetupropiaaventura.daos.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by joel on 15/10/14.
 */
public class AdventureSQLiteOpenHelper extends SQLiteOpenHelper {


    public static final String NODES_TABLE_NAME = "nodes";
    public static final String ADVENTURES_TABLE_NAME = "adventures";
    public static final String HISTORY_TABLE_NAME = "history";
    public static final String ADVENTURES_AND_PLAYER_TABLE_NAME = "adventuresplayer";

    // Crear constantes con los nombres de las columnas

    public AdventureSQLiteOpenHelper(Context context){
        super(context,"adventure.sqlite",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+NODES_TABLE_NAME+"(id INT, texto TEXT,gps TEXT,titulo TEXT,idsiguiente1 INT,idsiguiente2 INT)");
        db.execSQL("CREATE TABLE "+ADVENTURES_TABLE_NAME+"(id_material TEXT, id_deposito TEXT)");
        db.execSQL("CREATE TABLE "+HISTORY_TABLE_NAME+"(adventurename TEXT, player TEXT, idnode INT)");
        db.execSQL("CREATE TABLE "+ADVENTURES_AND_PLAYER_TABLE_NAME+"(id_deposito TEXT,"
                +" id_ecoparque TEXT,"
                +" depositanteid TEXT,"
                +" fecha TEXT,"
                +" peso TEXT,"
                +" company BOOLEAN,"
                +" nombre TEXT,"
                +" sector TEXT,"
                +" telefono TEXT,"
                +" email TEXT,"
                +" web TEXT"
                +")");


        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES ('1', 'Material informático')");
        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES ('2', 'Neveras')");
        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES ('3', 'Aceites usados')");

        db.execSQL("INSERT INTO "+ADVENTURES_TABLE_NAME+" VALUES ('1','San Jose - Las Fuentes','http://www.restauranteateneo.es/sites/all/themes/ateneo/images/bus.png')");
        db.execSQL("INSERT INTO "+ADVENTURES_TABLE_NAME+" VALUES ('2','Cogullada','http://www.restauranteateneo.es/sites/all/themes/ateneo/images/bus.png')");
        db.execSQL("INSERT INTO "+ADVENTURES_TABLE_NAME+" VALUES ('3','Universidad - Delicias','http://www.restauranteateneo.es/sites/all/themes/ateneo/images/bus.png')");
        db.execSQL("INSERT INTO "+ADVENTURES_TABLE_NAME+" VALUES ('4','Valdespartera','http://www.restauranteateneo.es/sites/all/themes/ateneo/images/bus.png')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}