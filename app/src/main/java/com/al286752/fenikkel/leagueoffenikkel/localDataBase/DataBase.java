package com.al286752.fenikkel.leagueoffenikkel.localDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fenikkel on 23/06/18.
 */

public class DataBase extends SQLiteOpenHelper implements IDataBase {


    private static final String DB_NAME = "pickparalisis.db";
    private static final int DB_VERSION = 1;//para comprobar si ha habido cambios

    public DataBase(Context context){
        super(context, DB_NAME, null, DB_VERSION);

    }

    //este no crec que el necessite
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) { //ESTE ES EL QUE ESTA EN LA CLASE SQLiteOpenHelper
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
