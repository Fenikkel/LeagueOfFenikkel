package com.al286752.fenikkel.leagueoffenikkel.model;

import android.content.Context;

import com.al286752.fenikkel.leagueoffenikkel.server.ILeagueServer;
import com.al286752.fenikkel.leagueoffenikkel.server.LeagueServer;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONArray;

/**
 * Created by fenikkel on 2/06/18.
 */

public class ShowStatsModel implements IShowStatsModel {

    private ILeagueServer leagueServer;

    private static ShowStatsModel instance = null;

    public static ShowStatsModel getInstance(){
        return instance;
    }

    public static ShowStatsModel getInstance(Context context){
        if(instance ==null)
            instance = new ShowStatsModel(new LeagueServer(context));
        return instance;
    }

    private ShowStatsModel (ILeagueServer riotServer){

        this.leagueServer = riotServer;
    }


    @Override
    public void findMaestries(String idSummoner, ResponseReceiver<JSONArray> reciever) {
        //aci crec que hem de fer el process JSON DATA
        //aci obtindrem amb el receiver un JSONArray que tindrem que transformar en un array de MaestriaDeCampeo (utilitzant el metode processJSON)
        //eixa array de maestia la passarem al presenter per a que se la passe a la vista i ho mostre
    }
}
