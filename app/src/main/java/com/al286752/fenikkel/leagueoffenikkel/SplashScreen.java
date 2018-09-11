package com.al286752.fenikkel.leagueoffenikkel;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.model.IMyProfileModel;
import com.al286752.fenikkel.leagueoffenikkel.model.MyProfileModel;
import com.al286752.fenikkel.leagueoffenikkel.model.ShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.myProfile.MyProfileActivity;
import com.al286752.fenikkel.leagueoffenikkel.server.DownloadCallback;
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
    MediaPlayer mp;

    DownloadCallback<String> downloadCallback;

    private static final String NETWORK_NOT_CONNECTED = "Network not connected";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        mp = MediaPlayer.create(this, R.raw.load);
        //downloadCallback = new DownloadCallback<String>();

        showStatsModel = ShowStatsModel.getInstance(getApplicationContext());

        myProfileModel = MyProfileModel.getInstance(getApplicationContext());


        final boolean networkInfo = Utils.isConnected(getApplicationContext());//DownloadCallback.getActiveNetworkInfo();//.getActiveNetworkInfo();

        if (!networkInfo) {

            AlertDialog.Builder builderInner = new AlertDialog.Builder(this);
            builderInner.setMessage(NETWORK_NOT_CONNECTED);
            builderInner.setTitle("This app needs Internet for work sorry");
            builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog,int which) {
                    dialog.dismiss();
                    System.exit(0);
                }
            });
            //eliminar la database i que no sen vaja del splash screen
            myProfileModel.deleteCurrentSummoner();
            builderInner.show();

        }
        else{
            /*showStatsModel.getVersion(new ResponseReceiver<String>() {
                @Override
                public void onResponseReceived(String response) {
                    StaticData.setCurrentVersion(response);
                }

                @Override
                public void onErrorReceived(String message) {

                }
            });//ho guarda en static data (current version)*/
        }









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
            StaticData.setRegionName(lista.get(3));

            /*if(StaticData.getVersion()== version actual){
                no descargamos los campeones
            }
            else{
                actualizamos los campeones
            }*/

        }


        runAnimation();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Log.i("tag", "This'll run 3000 milliseconds later");
                        if(networkInfo){
                            startApp();
                        }

                    }
                },
                2800);
    }

    private void runAnimation()
    {


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Log.i("tag", "This'll run 3000 milliseconds later");

                        mp.start();
                    }
                },
                900);


        @SuppressLint("ResourceType") Animation a = AnimationUtils.loadAnimation(this, R.animator.splash_animation_text);
        a.reset();
        TextView tv = (TextView) findViewById(R.id.fenikkel);
        tv.clearAnimation();

        tv.startAnimation(a);

        @SuppressLint("ResourceType") Animation b = AnimationUtils.loadAnimation(this, R.animator.splash_animation_principal_circle);
        b.reset();
        ImageView tv2 = (ImageView) findViewById(R.id.splashCircle);
        tv2.clearAnimation();
        tv2.startAnimation(b);

        @SuppressLint("ResourceType") Animation traslate = AnimationUtils.loadAnimation(this, R.animator.particle1);
        traslate.reset();
        ImageView particle1 = (ImageView) findViewById(R.id.particle1);
        particle1.clearAnimation();
        particle1.startAnimation(traslate);

        @SuppressLint("ResourceType") Animation traslate2 = AnimationUtils.loadAnimation(this, R.animator.particle2);
        traslate2.reset();
        ImageView particle2 = (ImageView) findViewById(R.id.particle2);
        particle2.clearAnimation();
        particle2.startAnimation(traslate2);

        @SuppressLint("ResourceType") Animation traslate3 = AnimationUtils.loadAnimation(this, R.animator.particle3);
        traslate3.reset();
        ImageView particle3 = (ImageView) findViewById(R.id.particle3);
        particle3.clearAnimation();
        particle3.startAnimation(traslate3);

        @SuppressLint("ResourceType") Animation traslate4 = AnimationUtils.loadAnimation(this, R.animator.particle4);
        traslate4.reset();
        ImageView particle4 = (ImageView) findViewById(R.id.particle4);
        particle4.clearAnimation();
        particle4.startAnimation(traslate4);

        @SuppressLint("ResourceType") Animation traslate5 = AnimationUtils.loadAnimation(this, R.animator.particle);
        traslate5.reset();
        ImageView particle = (ImageView) findViewById(R.id.particle);
        particle.clearAnimation();
        particle.startAnimation(traslate5);
    }

    void startApp(){
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }

/*
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
    */

}
