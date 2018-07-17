package com.al286752.fenikkel.leagueoffenikkel.pickParalysis;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.AttackComparator;
import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.StaticData;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;
import com.al286752.fenikkel.leagueoffenikkel.showStats.IShowStatsActivity;
import com.al286752.fenikkel.leagueoffenikkel.showStats.ShowStatsPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class PickParalysis extends AppCompatActivity implements IShowStatsActivity {

    TextView freeToPlay;
    ImageView marksmanImage;
    ImageView midImage;
    ImageView apImage;
    ImageView adImage;

    ArrayList<String> linea = new ArrayList<>();
    String tipo;

    private ShowStatsPresenter presenter;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        StaticData.getAttackFilter().clear();
    }

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


        if(StaticData.getMasteriesIds().isEmpty()){
            findMaestries();
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

                StaticData.setMasteries(response);


                ArrayList<String> champsIds = new ArrayList<>(); //aci tenim totes les id delschamps que teni, maestries

                for(ChampionMaestries champ : response){

                    champsIds.add(String.valueOf(champ.getChampionId()));

                }
                StaticData.setMasteriesIds(champsIds);


            }

            @Override
            public void onErrorReceived(String message) {

                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(parentLayout, "Something goes wrong", Snackbar.LENGTH_LONG).show();

            }
        });

    }

    private void processJSONChampsByID(String requested) {

        freeToPlay.setText(StaticData.getChampMapByID().toString());


    }

    public void onMarksmanClick(View view){
        marksmanImage.setVisibility(View.GONE);
        midImage.setVisibility(View.GONE);
        apImage.setVisibility(View.VISIBLE);
        adImage.setVisibility(View.VISIBLE);
        linea.add("Marksman");

        freeToPlay.setText(StaticData.getChampMapByID().get("106").toString());


    }

    public void onAPClick(View view){

        apImage.setVisibility(View.GONE);
        adImage.setVisibility(View.GONE);
        tipo = "AP";

        showMasteryFilter(linea,tipo);
    }

    public void onADClick(View view){

        apImage.setVisibility(View.GONE);
        adImage.setVisibility(View.GONE);
        tipo = "AD";

        showMasteryFilter(linea,tipo);
    }

    private void showMasteryFilter(ArrayList<String> linea, String tipo) {

        ArrayList<String> masteryID = StaticData.getMasteriesIds();//de 0 a n tiene por orden los id de los campeones con mas puntuacion de maestria (contador serviria tambien para el que tiene el JSON)

        TreeMap<String, JSONObject> allChamps = StaticData.getChampMapByID(); //la key es la id del champ que queremos
        for(int contador=0; contador<masteryID.size() ; contador++){

            JSONObject currentChamp = allChamps.get(masteryID.get(contador));
            JSONArray tags = currentChamp.optJSONArray("tags");
            JSONObject info = currentChamp.optJSONObject("info");// not necessary


            if(tags.optString(0,"").equals(linea.get(0)) || tags.optString(1,"").equals(linea.get(0)) || tags.optString(2,"").equals(linea.get(0)) ){
                //primer filtro passat

                if(tipo.equals("AD")){
                    if(StaticData.getAttackFilter().size()>=3){ //major no deuria passar
                        AttackComparator comparator = new AttackComparator();

                        int resultado = comparator.compare(StaticData.getAttackFilter().peek(),currentChamp); ////Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.

                        if(resultado<0){
                            StaticData.getAttackFilter().poll();
                            StaticData.getAttackFilter().add(currentChamp);
                        }
                    }
                    else {
                        StaticData.getAttackFilter().add(currentChamp);
                    }
                }

            }



        }

        freeToPlay.setText(StaticData.getAttackFilter().toString());


        /*
        JSONObject allChamp = StaticData.getChampListDDragon().optJSONObject("data");

        //jObject = new JSONObject(contents.trim());
        Iterator<?> keys = allChamp.keys();

        while( keys.hasNext() ) { //AIXINA PERO EN UN ARBRE
            String key = (String)keys.next();
            if ( allChamp.opt(key) instanceof JSONObject ) {
                JSONObject tags = ((JSONObject) allChamp.opt(key)).optJSONObject("tags");

                Iterator<?> tagKeys = tags.keys();

                while( tagKeys.hasNext() ) {
                    String keytag = (String) tagKeys.next();
                    if ( allChamp.opt(key) instanceof JSONObject ) {
                        ((JSONObject) allChamp.opt(key)).optJSONObject("tags");

                        }
                }
            }
        }
        */


    }

    //FICAR UN METODO DE RESET( per a tornar-ho a fer )

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("MidVisibility",midImage.getVisibility());
        outState.putInt("MarksmanVisibility",marksmanImage.getVisibility());
        outState.putInt("APVisibility",apImage.getVisibility());
        outState.putInt("ADVisibility",adImage.getVisibility());

    }
}
