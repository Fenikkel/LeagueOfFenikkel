package com.al286752.fenikkel.leagueoffenikkel.pickParalysis;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.StaticData;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;
import com.al286752.fenikkel.leagueoffenikkel.showStats.IShowStatsActivity;
import com.al286752.fenikkel.leagueoffenikkel.showStats.ShowStatsPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PickParalysis extends AppCompatActivity implements IShowStatsActivity {

    TextView freeToPlay;
    ImageView marksmanImage;
    ImageView midImage;
    ImageView apImage;
    ImageView adImage;

    private ShowStatsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_paralysis);

        freeToPlay = findViewById(R.id.freeToPlay);
        marksmanImage = findViewById(R.id.marksmanImage);
        midImage = findViewById(R.id.midImage);
        apImage = findViewById(R.id.apImage);
        adImage = findViewById(R.id.adImage);
        presenter = new ShowStatsPresenter(this,getApplicationContext());

        processJSONChampsByID("EASYEST");


        if(savedInstanceState!= null){ // important, que sino ho fa a la primera

            marksmanImage.setVisibility(savedInstanceState.getInt("MarksmanVisibility"));
            midImage.setVisibility(savedInstanceState.getInt("MidVisibility"));
            apImage.setVisibility(savedInstanceState.getInt("APVisibility"));
            adImage.setVisibility(savedInstanceState.getInt("ADVisibility"));



        }


    }


    public void findMaestries(){

        presenter.findMaestries(StaticData.getIdSummoner(), new ResponseReceiver<ArrayList<ChampionMaestries>>() {
            @Override
            public void onResponseReceived(ArrayList<ChampionMaestries> response) {

                if(response.size()==0){


                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "This summoner don't have any mastery", Snackbar.LENGTH_LONG).show();

                    return; //si es algu que porta molt de temps sense jugar i no te maestria en res
                }

                StaticData.setChampMaestries(response);


                ArrayList<String> champsIds = new ArrayList<>(); //aci tenim totes les id delschamps que teni, maestries

                for(ChampionMaestries champ : response){

                    champsIds.add(String.valueOf(champ.getChampionId()));

                }


            }

            @Override
            public void onErrorReceived(String message) {

                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Something goes wrong", Snackbar.LENGTH_LONG).show();

            }
        });

    }

    private void processJSONChampsByID(String requested) {
        JSONObject champsByID = StaticData.getChampListByID();

        try {
            freeToPlay.setText(champsByID.getString("type"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onMarksmanClick(View view){
        marksmanImage.setVisibility(View.GONE);
        midImage.setVisibility(View.GONE);
        apImage.setVisibility(View.VISIBLE);
        adImage.setVisibility(View.VISIBLE);


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("MidVisibility",midImage.getVisibility());
        outState.putInt("MarksmanVisibility",marksmanImage.getVisibility());
        outState.putInt("APVisibility",apImage.getVisibility());
        outState.putInt("ADVisibility",adImage.getVisibility());




    }
}
