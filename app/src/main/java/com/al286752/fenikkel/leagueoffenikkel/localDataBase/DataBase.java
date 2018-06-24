package com.al286752.fenikkel.leagueoffenikkel.localDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fenikkel on 23/06/18.
 */

public class DataBase extends SQLiteOpenHelper implements IDataBase {

    private static final String CREATE_TABLE = "create table";
    private static final String START = "START";
    private static final String PRIMARY_KEY_NOT_NULL = "primary key not null";
    private static final String INTEGER = "integer";
    private static final String LAST_VERSION = "LASTVERSION";
    private static final String REGION = "REGION";
    private static final String TEXT = "text";
    private static final String NOT_NULL = "not null";
    private static final String SUMMONER_ID = "SUMMONERID";



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
        createCurrentSummoner(db);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists "+ START); //  MOLT CUTRE PALERO, AÇO HI HA QUE REVISAR-HO
        onCreate(db);

    }


    private void createCurrentSummoner(SQLiteDatabase db) {

        String create = CREATE_TABLE + " " + START + " ("
                        + SUMMONER_ID + " " + INTEGER+ " " + PRIMARY_KEY_NOT_NULL+ ", "
                        + LAST_VERSION + " " + TEXT + " " + NOT_NULL + ", "
                        + REGION + " " + TEXT + " " + NOT_NULL + ");";

        db.execSQL(create);
    }
}
