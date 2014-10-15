package com.joelcastro.eligetupropiaaventura.daos.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.joelcastro.eligetupropiaaventura.daos.UserDAO;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by joel on 15/10/14.
 */
@EBean
public class UserSQLiteDAO implements UserDAO {
    AdventureSQLiteOpenHelper openHelper;

    @RootContext
    Context context;

    @AfterInject
    void initOpenHelper(){
        openHelper = new AdventureSQLiteOpenHelper(context);
    }

    @Override
    public boolean checkUser(String user, String pass) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        Cursor query = db.rawQuery("SELECT * FROM "+openHelper.USER_TABLE_NAME+" WHERE user='"+user+"' AND password='"+pass+"'",null);
        query.moveToFirst();
        if(query.getCount()>1)
        {
            query.close();
            return true;
        }else{
            query.close();
            return false;
        }

    }

    @Override
    public void changePass(String user, String pass, String newPass) {
        SQLiteDatabase db = openHelper.getReadableDatabase();
        db.execSQL("DELETE FROM "+AdventureSQLiteOpenHelper.USER_TABLE_NAME+" WHERE user ='"+user+"'");
        db.execSQL("INSERT INTO "+AdventureSQLiteOpenHelper.USER_TABLE_NAME+" VALUES ('"+user+"','"+newPass+"')");
        db.close();
    }
}
