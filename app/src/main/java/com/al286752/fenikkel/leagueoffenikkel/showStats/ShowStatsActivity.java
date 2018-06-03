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
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import java.util.ArrayList;
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

    //private IMyProfileModel model;
    private ShowStatsPresenter presenter;

    private String idSum;

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



        findMaestries();


    }

    public void findMaestries(){

        presenter.findMaestries(idSum, new ResponseReceiver<ArrayList<ChampionMaestries>>() {
            @Override
            public void onResponseReceived(ArrayList<ChampionMaestries> response) {
                //Champion ID
                long chamId = response.get(0).getChampionId();
                //String champ = ""+chamId; //si no faig aço peta
                championId.setText(String.valueOf(chamId));

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

                fillList(response);

            }

            @Override
            public void onErrorReceived(String message) {

                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Something goes wrong", Snackbar.LENGTH_LONG).show();

            }
        });

    }




    public void fillList(ArrayList<ChampionMaestries> champions){ //desdeOn responce received de findMaestries cridema esta funcio?

        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_maestries_layout, R.id.rowText, values);*/

        final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, values);

        listMaestries.setAdapter(adapter);

        listMaestries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //presenter.onAddGameRequested(position);
                String item = (String) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),item + " selected",Toast.LENGTH_LONG).show();//this, item + " selected", Toast.LENGTH_LONG

            }
        });



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
