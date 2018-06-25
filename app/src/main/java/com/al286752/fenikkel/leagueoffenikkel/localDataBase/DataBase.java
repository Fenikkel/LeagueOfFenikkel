package com.al286752.fenikkel.leagueoffenikkel.localDataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Currency;


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

    public ArrayList<String> getCurrentSummoner(){
        SQLiteDatabase db = getReadableDatabase();

        //(indica si son necesarios los valores unicos, Tabla que queremos, columnas que queremos, filas que queremos, parametros para seleccion de filas, grouping, grouping, orden, limite de numero de filas)
        // El primer parametro se usa si hay datos duplicados (supongo que foreing keys)?
        Cursor cursor = db.query(false, START, null,null, null, null, null, null, null);

        ArrayList<String> lista = new ArrayList<>();

        if(cursor.getCount()==0){

            lista.add("N/A");//no value

            return lista;

        }

        while (cursor.moveToNext()){// el primer move te porta a la primera fila? es com si estiguera fora de la tabla

            lista.add(String.valueOf(cursor.getInt(0))); //ho pase a String pero despres necessitare int a lo millor
            lista.add(cursor.getString(1)); //LASTVERSION
            lista.add(cursor.getString(2)); //REGION

        }

        cursor.close();//important

        return lista;
    }

    public boolean deleteCurrentSummoner(){

        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(START,null,null); //eliminara totes les files

        return rowsDeleted==1;

    }

}
