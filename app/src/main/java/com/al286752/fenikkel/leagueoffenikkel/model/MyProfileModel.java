package com.al286752.fenikkel.leagueoffenikkel.model;

import android.content.Context;

import com.al286752.fenikkel.leagueoffenikkel.localDataBase.DataBase;
import com.al286752.fenikkel.leagueoffenikkel.localDataBase.IDataBase;
import com.al286752.fenikkel.leagueoffenikkel.server.ILeagueServer;
import com.al286752.fenikkel.leagueoffenikkel.server.LeagueServer;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by fenikkel on 17/05/18.
 */

public class MyProfileModel implements IMyProfileModel, IDataBase {

    private ILeagueServer leagueServer ;

    private IDataBase dataBase;

    private static MyProfileModel instance = null;

    public static MyProfileModel getInstance(){
        return instance;
    }

    public static MyProfileModel getInstance(Context context){
        if(instance ==null)
            instance = new MyProfileModel(new LeagueServer(context), new DataBase(context));
        return instance;
    }
    private MyProfileModel (ILeagueServer riotServer, IDataBase dataBase){

        this.leagueServer = riotServer;
        this.dataBase = dataBase;
    }

    /*public MyProfileModel(Context context){

        leagueServer=new LeagueServer(context);

    }*/
    public void findSummoner(String nickname, ResponseReceiver<JSONObject> reciever) {
        leagueServer.findSummoner(nickname,reciever);
    }


    @Override
    public String getUrlIcon(int idIcon) {
        return leagueServer.getUrlIcon(idIcon);
    }

    @Override
    public void insertCurrentSummoner(int summonerID, String lastVersion, String region) {
        dataBase.insertCurrentSummoner(summonerID,lastVersion,region);
    }

    @Override
    public ArrayList<String> getCurrentSummoner() {
        return dataBase.getCurrentSummoner();
    }

    @Override
    public boolean deleteCurrentSummoner() {
        return dataBase.deleteCurrentSummoner();
    }

    @Override
    public void findSummonerByID(String summonerID, ResponseReceiver<JSONObject> responseReceiver) {
        leagueServer.findSummonerByID(summonerID,responseReceiver);
    }


}
