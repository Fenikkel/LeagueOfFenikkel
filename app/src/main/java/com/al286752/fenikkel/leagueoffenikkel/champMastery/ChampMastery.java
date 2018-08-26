package com.al286752.fenikkel.leagueoffenikkel.champMastery;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.StaticData;
import com.al286752.fenikkel.leagueoffenikkel.model.ShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.myProfile.DownloadImageTask;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class ChampMastery extends AppCompatActivity {


    public static final String CHAMPION = "championId";
    public static final String SUMMONER = "summonerId";
    public static final String CHAMPION_NAME = "championName";
    public static final String CHAMP_ID_NAME = "championNameId";

    TextView championName;
    TextView championLevel;
    TextView totalMaestryPoints;
    TextView chestAvaliable;
    TextView tokensMastery;
    TextView lastTimePlayed;
    ProgressBar experienceBar;

    ImageView champImage;

    //ChampionMaestries allData;

    ShowStatsModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champ_mastery);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);


        Intent intent = getIntent();
        final String champId = intent.getStringExtra(CHAMPION);
        //String summId = intent.getStringExtra(SUMMONER);
        //String champName = intent.getStringExtra(CHAMPION_NAME);
        final String chamNamId = intent.getStringExtra(CHAMP_ID_NAME);

        model= ShowStatsModel.getInstance();

        championLevel = findViewById(R.id.levelMastery);
        lastTimePlayed = findViewById(R.id.lastTimePlayed);
        tokensMastery = findViewById(R.id.tokensMastery);
        totalMaestryPoints = findViewById(R.id.totalMaestryPoints);
        chestAvaliable = findViewById(R.id.chestAvaliable);
        experienceBar = findViewById(R.id.experienceBar);
        // Get the Drawable custom_progressbar
        Drawable draw= getResources().getDrawable(R.drawable.custom_progressbar); //getResources te da la carpeta res
        // set the drawable as progress drawable
        experienceBar.setProgressDrawable(draw);
        championName = findViewById(R.id.nameChamMastery);

        ArrayList<ChampionMaestries> copia =  StaticData.getMasteries();
        long id = Long.valueOf(champId);
        for(ChampionMaestries actual : copia){

            if(actual.getChampionId() == id){
                //championName.setText(String.valueOf(actual.getChampionId()));

                championLevel.setText(String.valueOf(actual.getChampionLevel()));


                long unixSeconds = actual.getLastPlayTime(); // lo que mos passa league of legends esta en miliseconds ja
                Date date = new Date(unixSeconds); //*1000L); // *1000 is to convert seconds to milliseconds
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");// HH:mm:ss z"); // the format of your date
                sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
                String formattedDate = sdf.format(date);
                //System.out.println(formattedDate);


                lastTimePlayed.setText("Last time played: "+formattedDate);
                tokensMastery.setText("Tokens earned: "+String.valueOf(actual.getTokensEarned()));

                totalMaestryPoints.setText("Total points: "+ String.valueOf((int) actual.getChampionPoints()));
                if(!actual.isChestGranted()){ //la pregunta es si la chest esta guanyada(concedida) ja
                    chestAvaliable.setText("Chest: Avaliable");
                }else {
                    chestAvaliable.setText("Chest: Unavaliable");
                }

                int totalPoint = (int) actual.getChampionPointsSinceLastLevel() + (int) actual.getChampionPointsUntilNextLevel();
                experienceBar.setMax(totalPoint);
                experienceBar.setProgress((int) actual.getChampionPointsSinceLastLevel());

                break;
            }

        }

        championName.setText(StaticData.getChampListDDragon().optJSONObject("data").optJSONObject(chamNamId).optString("name")); //StaticData.getTheChampMapByID(chamNamId).optString("name")
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



        //allData = new ChampionMaestries();

/*
        model.getChampionMastery(summId, champId, new ResponseReceiver<JSONObject>() {
            @Override
            public void onResponseReceived(JSONObject response) {
                processJSONChampion(response);


                championLevel = findViewById(R.id.levelMastery);
                lastTimePlayed = findViewById(R.id.lastTimePlayed);
                tokensMastery = findViewById(R.id.tokensMastery);
                totalMaestryPoints = findViewById(R.id.totalMaestryPoints);
                chestAvaliable = findViewById(R.id.chestAvaliable);

                championLevel.setText(String.valueOf(allData.getChampionLevel()));


                long unixSeconds = allData.getLastPlayTime(); // lo que mos passa league of legends esta en miliseconds ja
                Date date = new Date(unixSeconds); //*1000L); // *1000 is to convert seconds to milliseconds
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");// HH:mm:ss z"); // the format of your date
                sdf.setTimeZone(TimeZone.getTimeZone("GMT-4")); // give a timezone reference for formating (see comment at the bottom
                String formattedDate = sdf.format(date);
                System.out.println(formattedDate);


                lastTimePlayed.setText("Last time played: "+formattedDate);
                tokensMastery.setText("Tokens earned: "+String.valueOf(allData.getTokensEarned()));

                totalMaestryPoints.setText("Total points: "+ String.valueOf((int) allData.getChampionPoints()));
                if(!allData.isChestGranted()){ //la pregunta es si la chest esta guanyada(concedida) ja
                    chestAvaliable.setText("Chest: Avaliable");
                }else {
                    chestAvaliable.setText("Chest: Unavaliable");
                }

                int totalPoint = (int) allData.getChampionPointsSinceLastLevel() + (int) allData.getChampionPointsUntilNextLevel();
                experienceBar.setMax(totalPoint);
                experienceBar.setProgress((int) allData.getChampionPointsSinceLastLevel());
            }

            @Override
            public void onErrorReceived(String message) {

                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Something goes wrong getting the mastery of "+ champId, Snackbar.LENGTH_LONG).show();

            }
        });
        */

    }


    private void setChampionImage(String urlIcon) {

        new DownloadImageTask(champImage).execute(urlIcon);

    }

    /*private void processJSONChampion(JSONObject champion) {



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

    }*/
    
}
