package com.al286752.fenikkel.leagueoffenikkel.champMastery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.model.ShowStatsModel;

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

        model= ShowStatsModel.getInstance();

        allData = model.getChampionMastery(summId,champId);



        championName = findViewById(R.id.nameChamMastery);
        championLevel = findViewById(R.id.levelMastery);

        championName.setText(summId);
        championLevel.setText(champId);



    }



}
