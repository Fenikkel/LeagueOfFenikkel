package com.al286752.fenikkel.leagueoffenikkel.champMastery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.model.ShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONObject;

public class ChampMastery extends AppCompatActivity {

    //public static final ChampionMaestries ALL_DATA = null;
    public static final String CHAMPION = "championId";
    public static final String SUMMONER = "summonerId";

    TextView championName;
    TextView championLevel;

    ChampionMaestries allData;

    ShowStatsModel model;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ_mastery);

        Intent intent = getIntent();
        String champId = intent.getStringExtra(CHAMPION);
        String summId = intent.getStringExtra(SUMMONER);

        allData = new ChampionMaestries();

        model= ShowStatsModel.getInstance();


        model.getChampionMastery(summId, champId, new ResponseReceiver<JSONObject>() {
            @Override
            public void onResponseReceived(JSONObject response) {
                processJSONChampion(response);

                championName = findViewById(R.id.nameChamMastery);
                championLevel = findViewById(R.id.levelMastery);

                championName.setText(String.valueOf(allData.getLastPlayTime()));
                championLevel.setText(String.valueOf(allData.getTokensEarned()));

            }

            @Override
            public void onErrorReceived(String message) {

            }
        });







    }

    public void setAllData(ChampionMaestries allData) {
        this.allData = allData;
    }


    private void processJSONChampion(JSONObject champion) {



        JSONObject cosa;



        cosa = champion;



        if(cosa.optLong("championId")==0L){
            allData.setChampionId(-1L);
        }else{

            long championId = cosa.optLong("championId");
            allData.setChampionId(championId);

        }


        if(cosa.optInt("championLevel")==0){
            allData.setChampionLevel(-1);
        }else {
            int champLvl = cosa.optInt("championLevel");
            allData.setChampionLevel(champLvl);
        }





        boolean chst = cosa.optBoolean("chestGranted");
        allData.setChestGranted(chst);

        int champPont = cosa.optInt("championPoints");
        allData.setChampionPoints(champPont);

        if(cosa.optLong("championPointsUntilNextLevel")==0L){
            allData.setChampionPointsUntilNextLevel(-1L);
        }else {
            long championPointsUntilNextLevel = cosa.optLong("championPointsUntilNextLevel");
            allData.setChampionPointsUntilNextLevel(championPointsUntilNextLevel);
        }



        int tokens = cosa.optInt("tokensEarned");
        allData.setTokensEarned(tokens);

        if(cosa.optLong("championPointsSinceLastLevel")==0L){
            allData.setChampionPointsSinceLastLevel(-1L);
        }
        else {
            long sinceLast = cosa.optLong("championPointsSinceLastLevel");

            allData.setChampionPointsSinceLastLevel(sinceLast);
        }


        if(cosa.optLong("lastPlayTime")==0){
            allData.setLastPlayTime(-1L);
        }else {
            long lastPlay = cosa.optLong("lastPlayTime"); //never played que valor sera??

            allData.setLastPlayTime(lastPlay);
        }





    }
    
}
