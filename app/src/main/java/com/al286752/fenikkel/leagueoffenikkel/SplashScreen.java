package com.al286752.fenikkel.leagueoffenikkel;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.al286752.fenikkel.leagueoffenikkel.model.ShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.myProfile.MyProfileActivity;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;
import com.al286752.fenikkel.leagueoffenikkel.showStats.ShowStatsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SplashScreen extends AppCompatActivity {

    ShowStatsModel showStatsModel;

    JSONObject champListByName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        showStatsModel = ShowStatsModel.getInstance(getApplicationContext());


        showStatsModel.getChampions(new ResponseReceiver<JSONObject>() {
            @Override
            public void onResponseReceived(JSONObject response) {

                if(response!=null){
                    processMap(response);
                }else {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Server error: Server busy", Snackbar.LENGTH_LONG).show();
                }

            }

            @Override
            public void onErrorReceived(String message) {

            }
        });


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Log.i("tag", "This'll run 3000 milliseconds later");

                        startApp();
                    }
                },
                3000);
    }

    void startApp(){
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }


    public void processMap(JSONObject jdata) {

        try {
            //@SuppressWarnings("unchecked")

            StaticData.setChampListByName(jdata.getJSONObject("data"));

            JSONObject temporal = StaticData.getChampListByName();

            Iterator<String> nameItr = temporal.keys();
            Map<String, JSONObject> outMap = new HashMap<String, JSONObject>();
            Map<String, String > keysMap = new HashMap<String, String>();




            while (nameItr.hasNext()) {

                String name = nameItr.next();

                outMap.put(temporal.optJSONObject(name).optString("id"), temporal.optJSONObject(name));
                keysMap.put(temporal.optJSONObject(name).optString("name"), temporal.optJSONObject(name).optString("id"));

            }

            StaticData.setAllChampions(outMap);
            StaticData.setChampionNameKeys(keysMap);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
