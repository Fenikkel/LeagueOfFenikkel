package com.al286752.fenikkel.leagueoffenikkel.model;

import android.content.Context;

import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.server.ILeagueServer;
import com.al286752.fenikkel.leagueoffenikkel.server.LeagueServer;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    public void findMaestries(String idSummoner, final ResponseReceiver<ArrayList<ChampionMaestries>> reciever) { //crec que este receiver deuria ser ChampionMaestriesd
        //aci crec que hem de fer el process JSON DATA
        //aci obtindrem amb el receiver un JSONArray que tindrem que transformar en un array de MaestriaDeCampeo (utilitzant el metode processJSON)
        //eixa array de maestia la passarem al presenter per a que se la passe a la vista i ho mostre

        leagueServer.findMaestries(idSummoner, new ResponseReceiver<JSONArray>() {
            @Override
            public void onResponseReceived(JSONArray response) {
                ArrayList<ChampionMaestries> chamMaest = processJSONMaestries(response);

                reciever.onResponseReceived(chamMaest);
            }

            @Override
            public void onErrorReceived(String message) {

            }
        });



    }

    @Override
    public void getChampions(final ResponseReceiver<JSONArray> responseReceiver) {
        leagueServer.getChampions(new ResponseReceiver<JSONArray>(){

            @Override
            public void onResponseReceived(JSONArray response) {
                responseReceiver.onResponseReceived(response);
            }

            @Override
            public void onErrorReceived(String message) {

            }
        });
    }

    public ArrayList<ChampionMaestries> processJSONMaestries(JSONArray jsonArray){

        ArrayList<ChampionMaestries> championMaestriesArrayList = new ArrayList<>();
        JSONObject cosa;
        ChampionMaestries champion;

        for (int contador = 0 ; contador<jsonArray.length() ; contador++) { //contador<jsonArray.length()

            try {

                cosa = (JSONObject) jsonArray.get(contador);

                champion = new ChampionMaestries();

                if(cosa.optLong("championId")==0L){
                    champion.setChampionId(-1L);
                }else{

                    long championId = cosa.optLong("championId");
                    champion.setChampionId(championId);

                }


                if(cosa.optInt("championLevel")==0){
                    champion.setChampionLevel(-1);
                }else {
                    int champLvl = cosa.optInt("championLevel");
                    champion.setChampionLevel(champLvl);
                }





                boolean chst = cosa.optBoolean("chestGranted");
                champion.setChestGranted(chst);

                int champPont = cosa.optInt("championPoints");
                champion.setChampionPoints(champPont);

                if(cosa.optLong("championPointsUntilNextLevel")==0L){
                    champion.setChampionPointsUntilNextLevel(-1L);
                }else {
                    long championPointsUntilNextLevel = cosa.optLong("championPointsUntilNextLevel");
                    champion.setChampionPointsUntilNextLevel(championPointsUntilNextLevel);
                }



                int tokens = cosa.optInt("tokensEarned");
                champion.setTokensEarned(tokens);

                if(cosa.optLong("championPointsSinceLastLevel")==0L){
                    champion.setChampionPointsSinceLastLevel(-1L);
                }
                else {
                    long sinceLast = cosa.optLong("championPointsSinceLastLevel");

                    champion.setChampionPointsSinceLastLevel(sinceLast);
                }


                if(cosa.optLong("lastPlayTime")==0){
                    champion.setLastPlayTime(-1L);
                }else {
                    long lastPlay = cosa.optLong("lastPlayTime"); //never played que valor sera??

                    champion.setLastPlayTime(lastPlay);
                }


                championMaestriesArrayList.add(champion);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        return championMaestriesArrayList;
    }
}
