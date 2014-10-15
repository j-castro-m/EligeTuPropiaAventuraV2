package com.joelcastro.eligetupropiaaventura.daos.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.androidannotations.annotations.EBean;

/**
 * Created by joel on 15/10/14.
 */
@EBean
public class AdventureSQLiteOpenHelper extends SQLiteOpenHelper {


    public static final String NODES_TABLE_NAME = "nodes";
    public static final String ADVENTURES_TABLE_NAME = "adventures";
    public static final String HISTORY_TABLE_NAME = "history";
    public static final String ADVENTURES_AND_PLAYER_TABLE_NAME = "adventuresplayer";
    public static final String USER_TABLE_NAME = "users";

    // Crear constantes con los nombres de las columnas

    public AdventureSQLiteOpenHelper(Context context){
        super(context,"adventure.sqlite",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+NODES_TABLE_NAME+"(id INT, texto TEXT,gps TEXT,titulo TEXT,idsiguiente1 INT,idsiguiente2 INT)");
        db.execSQL("CREATE TABLE "+ADVENTURES_TABLE_NAME+"(adventurename TEXT, description TEXT, firstnode INT, actualnode INT)");
        db.execSQL("CREATE TABLE "+HISTORY_TABLE_NAME+"(adventurename TEXT, player TEXT, idnode INT)");
        db.execSQL("CREATE TABLE "+ADVENTURES_AND_PLAYER_TABLE_NAME+"(adventurename TEXT,player TEXT)");
        db.execSQL("CREATE TABLE "+USER_TABLE_NAME+"(user TEXT, password TEXT)");


        db.execSQL("INSERT INTO "+USER_TABLE_NAME+" VALUES ('user','pass')");

        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES (1, 'texto','titulo','GPS',4,5)");
        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES (4, 'texto 4','titulo 4','GPS',1,5)");
        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES (5, 'texto 5','titulo 5','GPS',4,1)");

        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES (2, 'texto','titulo','GPS',6,7)");
        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES (6, 'texto 6','titulo 6','GPS',2,7)");
        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES (7, 'texto 7','titulo 7','GPS',6,2)");

        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES (3, 'texto','titulo','GPS',8,9)");
        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES (8, 'texto 8','titulo 8','GPS',3,9)");
        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES (9, 'texto 9','titulo 9','GPS',8,3)");

        db.execSQL("INSERT INTO "+ADVENTURES_TABLE_NAME+" VALUES ('Aventura Inicial','Aventura de prueba inicial del sistema',1,1)");
        db.execSQL("INSERT INTO "+ADVENTURES_TABLE_NAME+" VALUES ('Aventura Inicial 2','Aventura de prueba inicial del sistema 2',2,2)");
        db.execSQL("INSERT INTO "+ADVENTURES_TABLE_NAME+" VALUES ('Aventura Inicial 3','Aventura de prueba inicial del sistema 3',3,3)");

        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES ('Aventura Inicial','user')");
        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES ('Aventura Inicial 2','user')");
        db.execSQL("INSERT INTO "+NODES_TABLE_NAME+" VALUES ('Aventura Inicial 3','user')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}