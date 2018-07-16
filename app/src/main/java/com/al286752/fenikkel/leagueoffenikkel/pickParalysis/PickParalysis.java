package com.al286752.fenikkel.leagueoffenikkel.pickParalysis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.StaticData;

import org.json.JSONException;
import org.json.JSONObject;

public class PickParalysis extends AppCompatActivity {

    TextView freeToPlay;
    ImageView marksmanImage;
    ImageView midImage;
    ImageView apImage;
    ImageView adImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_paralysis);

        freeToPlay = findViewById(R.id.freeToPlay);
        marksmanImage = findViewById(R.id.marksmanImage);
        midImage = findViewById(R.id.midImage);
        apImage = findViewById(R.id.apImage);
        adImage = findViewById(R.id.adImage);

        processJSONChampsByID("EASYEST");


        if(savedInstanceState!= null){ // important, que sino ho fa a la primera

            marksmanImage.setVisibility(savedInstanceState.getInt("MarksmanVisibility"));
            midImage.setVisibility(savedInstanceState.getInt("MidVisibility"));
            apImage.setVisibility(savedInstanceState.getInt("APVisibility"));
            adImage.setVisibility(savedInstanceState.getInt("ADVisibility"));



        }


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
