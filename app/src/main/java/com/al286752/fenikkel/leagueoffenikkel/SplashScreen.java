package com.al286752.fenikkel.leagueoffenikkel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.al286752.fenikkel.leagueoffenikkel.model.IMyProfileModel;
import com.al286752.fenikkel.leagueoffenikkel.model.MyProfileModel;
import com.al286752.fenikkel.leagueoffenikkel.model.ShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.myProfile.MyProfileActivity;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SplashScreen extends AppCompatActivity {

    ShowStatsModel showStatsModel;

    IMyProfileModel myProfileModel;

    JSONObject champListByName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        showStatsModel = ShowStatsModel.getInstance(getApplicationContext());

        myProfileModel = MyProfileModel.getInstance(getApplicationContext());


        showStatsModel.getVersion(new ResponseReceiver<String>() {
            @Override
            public void onResponseReceived(String response) {
                StaticData.setCurrentVersion(response);
            }

            @Override
            public void onErrorReceived(String message) {

            }
        });//ho guarda en static data (current version)



        //while(StaticData.getCurrentVersion()==null){Log.i("tag", "vaya mierda");}
/*
        showStatsModel.getChampionsByID(new ResponseReceiver<JSONObject>() {
            @Override
            public void onResponseReceived(JSONObject response) {
                if(response!=null){
                    processChampions(response);
                }else {
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "Server error: Server busy", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onErrorReceived(String message) {

            }
        });

*/


//ESTO ESTA DEPRECATED!!!
/*
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
*/
        //myProfileModel.deleteCurrentSummoner(); // PER A FER COM SI SIGUERA PER PRIMERA VEGADA

        ArrayList<String> lista = myProfileModel.getCurrentSummoner();

        String text = lista.get(0);

        if(text.equals("N/A")){
            //do nothing
            //DESCARGAMOS LOS CAMPEONES PORQUE ES LA PRIMERA VEZ QUE ENRTAMOS


        }
        else{
            StaticData.setIdSummoner(lista.get(0));
            StaticData.setVersion(lista.get(1));
            StaticData.setRegion(lista.get(2));

            /*if(StaticData.getVersion()== version actual){
                no descargamos los campeones
            }
            else{
                actualizamos los campeones
            }*/

        }




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


    private void processChampions(JSONObject jChampions) {

        StaticData.setChampListDDragon(jChampions);

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
