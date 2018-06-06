package com.al286752.fenikkel.leagueoffenikkel.champMastery;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.model.ShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.myProfile.DownloadImageTask;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONObject;

public class ChampMastery extends AppCompatActivity {

    //public static final ChampionMaestries ALL_DATA = null;
    public static final String CHAMPION = "championId";
    public static final String SUMMONER = "summonerId";
    public static final String CHAMPION_NAME = "championName";
    public static final String CHAMP_ID_NAME = "championNameId";

    TextView championName;
    TextView championLevel;
    TextView totalMaestryPoints;
    TextView pointsSinceLastLevel;
    TextView pointsUntilNextLevel;
    TextView chestAvaliable;
    TextView tokensMastery;
    TextView lastTimePlayed;
    ProgressBar experienceBar;

    ImageView champImage;

    ChampionMaestries allData;

    ShowStatsModel model;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ_mastery);

        Intent intent = getIntent();
        final String champId = intent.getStringExtra(CHAMPION);
        String summId = intent.getStringExtra(SUMMONER);
        String champName = intent.getStringExtra(CHAMPION_NAME);
        final String chamNamId = intent.getStringExtra(CHAMP_ID_NAME);

        model= ShowStatsModel.getInstance();


        experienceBar = findViewById(R.id.experienceBar);
        championName = findViewById(R.id.nameChamMastery);
        championName.setText(champName);
        champImage = findViewById(R.id.imageMastery);

        model.getChampionIcon(chamNamId, new ResponseReceiver<String>() {
            @Override
            public void onResponseReceived(String urlIcon) {

                setChampionImage(urlIcon);

            }

            @Override
            public void onErrorReceived(String message) {
                championName.setText(message);
            }
        });



        allData = new ChampionMaestries();






        model.getChampionMastery(summId, champId, new ResponseReceiver<JSONObject>() {
            @Override
            public void onResponseReceived(JSONObject response) {
                processJSONChampion(response);


                championLevel = findViewById(R.id.levelMastery);
                lastTimePlayed = findViewById(R.id.lastTimePlayed);
                tokensMastery = findViewById(R.id.tokensMastery);
                totalMaestryPoints = findViewById(R.id.totalMaestryPoints);
                pointsSinceLastLevel = findViewById(R.id.sinceLastLevel);
                pointsUntilNextLevel = findViewById(R.id.untilNextlevel);
                chestAvaliable = findViewById(R.id.chestAvaliable);

                championLevel.setText(String.valueOf("Mastery\n\t\t"+allData.getChampionLevel()));

                lastTimePlayed.setText(String.valueOf(allData.getLastPlayTime()));
                tokensMastery.setText(String.valueOf(allData.getTokensEarned()));

                totalMaestryPoints.setText(String.valueOf((int) allData.getChampionPoints()));
                pointsSinceLastLevel.setText(String.valueOf((int) allData.getChampionPointsSinceLastLevel()));
                pointsUntilNextLevel.setText(String.valueOf((int) allData.getChampionPointsUntilNextLevel()));
                if(!allData.isChestGranted()){ //la pregunta es si la chest esta guanyada(concedida) ja
                    chestAvaliable.setText("Chest: Avaliable");
                }else {
                    chestAvaliable.setText("Chest: Unavaliable");
                }
                /*
                int totalPoint = (int) allData.getChampionPointsSinceLastLevel() + (int) allData.getChampionPointsUntilNextLevel();
                experienceBar.setMax(totalPoint);
                experienceBar.setProgress((int) allData.getChampionPointsSinceLastLevel());
                */



            }

            @Override
            public void onErrorReceived(String message) {

                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Something goes wrong getting the mastery of "+ champId, Snackbar.LENGTH_LONG).show();

            }
        });







    }

    private void setChampionImage(String urlIcon) {

        String comprobar = urlIcon;

        new DownloadImageTask(champImage).execute(urlIcon);

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
