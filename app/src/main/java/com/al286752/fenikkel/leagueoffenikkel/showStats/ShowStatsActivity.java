package com.al286752.fenikkel.leagueoffenikkel.showStats;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.model.MyProfileModel;
import com.al286752.fenikkel.leagueoffenikkel.myProfile.MyProfileActivity;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/*import com.al286752.fenikkel.leagueoffenikkel.server.DownloadCallback;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;
import com.al286752.fenikkel.leagueoffenikkel.server.simpleConection.TaskWithProgress;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.LinkedList;
import java.util.List;*/

public class ShowStatsActivity  extends AppCompatActivity implements IShowStatsActivity{

    public static final String NICKNAME = "nickName";//se suposa que se replena al cambiar de activitat, sera el parametre que li passem (esta en MyProfile)
    public static final String ID_SUMMONER = "idSummoner";
    TextView championId;
    TextView championLevel;
    TextView championPoints;
    TextView tokensEarned;
    TextView championPointsUntilNextLevel;
    TextView chestGranted;
    TextView championPointsSinceLastLevel;
    TextView lastPlayTime;
    ProgressBar progressBarProba;
    ListView listMaestries;
    Map<String, JSONObject> allChampions; //autoupdates
    JSONObject champListByName;

    //private IMyProfileModel model;
    private ShowStatsPresenter presenter;

    private String idSum;

    private static String champName ="view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        presenter = new ShowStatsPresenter(this,getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);

        Intent intent = getIntent();
        String nick = intent.getStringExtra(NICKNAME); //no se molt be per a que serveix la final string... es tipo un mediador o uns transportador de paquetes?
        idSum = intent.getStringExtra(ID_SUMMONER);

        championId = findViewById(R.id.championId);
        championLevel= findViewById(R.id.lvlMaestrie);
        championPoints = findViewById(R.id.maestryPoints);
        tokensEarned = findViewById(R.id.tokens);
        championPointsUntilNextLevel = findViewById(R.id.pointsUntilNextLevel);
        chestGranted = findViewById(R.id.chest);
        championPointsSinceLastLevel = findViewById(R.id.pointsSinceLastLevel);
        lastPlayTime = findViewById(R.id.lastPlayTime);


        //championId.setText(nick); //tindra el que hem escrit en el dialog
        //championId.setText(idSum);
        progressBarProba = findViewById(R.id.progressBarProba);
        listMaestries = findViewById(R.id.maestriesList);

        presenter.getChampions(new ResponseReceiver<JSONObject>() {
            @Override
            public void onResponseReceived(JSONObject response) {
                //convert to map
                //championId.setText(String.valueOf(response));
                processMap(response);
            }

            @Override
            public void onErrorReceived(String message) {
                championId.setText(String.valueOf(message));

                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Server error: Too many request, rate limit exceeded", Snackbar.LENGTH_LONG).show();

                //Intent intent = new Intent(this, MyProfileActivity.class);

                //Anyadim parametres a ShowStatsActivity
                //intent.putExtra(ShowStatsActivity.NICKNAME, nickName);
                //intent.putExtra(ShowStatsActivity.ID_SUMMONER, String.valueOf(idSummoner));

                //startActivity(intent);

            }
        });

        findMaestries();






    }

    public void processMap(JSONObject jdata) {

        try {
            //@SuppressWarnings("unchecked")
            champListByName= jdata.getJSONObject("data");

            Iterator<String> nameItr = champListByName.keys();
            Map<String, JSONObject> outMap = new HashMap<String, JSONObject>();




            while (nameItr.hasNext()) {

                String name = nameItr.next();
                //String name = nameItr.toString();
                championId.setText(name);
                outMap.put(champListByName.optJSONObject(name).optString("id"), champListByName.optJSONObject(name));

            }

            allChampions = outMap;


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void findMaestries(){

        presenter.findMaestries(idSum, new ResponseReceiver<ArrayList<ChampionMaestries>>() {
            @Override
            public void onResponseReceived(ArrayList<ChampionMaestries> response) {

                if(response.size()==0){
                    listMaestries.setEmptyView(findViewById(R.id.noMaestriesImage));

                    View parentLayout = findViewById(android.R.id.content);
                    findViewById(R.id.noMaestriesImage).setVisibility(View.VISIBLE);
                    Snackbar.make(parentLayout, "This summoner don't have any mastery", Snackbar.LENGTH_LONG).show();

                    return; //si es algu que porta molt de temps sense jugar i no te maestria en res
                }

                //Champion ID
                long chamId = response.get(0).getChampionId();
                //String champ = ""+chamId; //si no faig aço peta
                //championId.setText(String.valueOf(chamId));

                //Champion LVL
                int championLev = response.get(0).getChampionLevel();
                championLevel.setText(String.valueOf(championLev));

                //Champion Points
                int champPoints = response.get(0).getChampionPoints();
                championPoints.setText(String.valueOf(champPoints));


                //Tokens
                int tokensEar = response.get(0).getTokensEarned();
                tokensEarned.setText(String.valueOf(tokensEar));

                //Points until next level
                long pointsUntilNextLevel = response.get(0).getChampionPointsUntilNextLevel();
                championPointsUntilNextLevel.setText(String.valueOf(pointsUntilNextLevel)); // molt millor que  String champ = ""+chamId;


                //Chest
                boolean chest = response.get(0).isChestGranted();
                chestGranted.setText(String.valueOf(chest));

                long lastplay = response.get(0).getLastPlayTime();
                lastPlayTime.setText(String.valueOf(lastplay));

                long pointsSinceLastlvl =response.get(0).getChampionPointsSinceLastLevel();
                championPointsSinceLastLevel.setText(String.valueOf(pointsSinceLastlvl));

                //aci fem un findChampion i creem una llista de champions per a passarlila a

                ArrayList<String> champsIds = new ArrayList<>();

                for(ChampionMaestries champ : response){

                    champsIds.add(String.valueOf(champ.getChampionId()));

                }

                fillList(champsIds);

            }

            @Override
            public void onErrorReceived(String message) {

                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Something goes wrong", Snackbar.LENGTH_LONG).show();

            }
        });

    }




    public void fillList(ArrayList<String> champions){ //desdeOn responce received de findMaestries cridema esta funcio?

        /*String[] value2s = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };*/

        String[] value2s = champions.toArray(new String[0]);
        String[] subNames = champions.toArray(new String[0]);

        for(int contador=0; contador<value2s.length; contador++){

            JSONObject champ =allChampions.get(value2s[contador]);

            value2s[contador]= champ.optString("name");
            subNames[contador]= champ.optString("title");



        }

        final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, value2s, subNames);

        listMaestries.setAdapter(adapter);

        listMaestries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //presenter.onAddGameRequested(position);
                String item = (String) adapter.getItem(position);

                //mirem quin champ es en champslistbyname y enviem a una altra activity els maestries on les mostrarem totes


                Toast.makeText(getApplicationContext(),item + " selected",Toast.LENGTH_LONG).show();//this, item + " selected", Toast.LENGTH_LONG

            }
        });

        //List<String> twentyFirst = champions.subList(0,2); DESCOMENTAR

        //final String[] value2s = twentyFirst.toArray(new String[0]); DESCOMENTAR




        //ESTE STRING[] EL TINC QUE PLENAR PERO OBTENINT TOTS ELS CAMPEONS I BUSCANTLOS PER ID, QUE SINO SUPERE EL RATE LIMIT

        /*for (int contador = 0;contador<value2s.length; contador++){

           presenter.getChampionName(value2s[contador], new ResponseReceiver<JSONObject>() {
                @Override
                public void onResponseReceived(JSONObject response) {
                    champName= response.optString("name");
                }

                @Override
                public void onErrorReceived(String message) {

                }
            });

           value2s[contador]=champName;


        }*/

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_maestries_layout, R.id.rowText, values);*/

/*

        ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,champions);

        listMaestries.setAdapter(adapter);
        listMaestries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //presenter.onAddGameRequested(position);
            }
        });

        */

    }
/*
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }
*/

}
