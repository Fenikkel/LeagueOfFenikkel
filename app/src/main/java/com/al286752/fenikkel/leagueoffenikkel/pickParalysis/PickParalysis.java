package com.al286752.fenikkel.leagueoffenikkel.pickParalysis;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.ElementComparator;
import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.StaticData;
import com.al286752.fenikkel.leagueoffenikkel.champMastery.ChampMastery;
import com.al286752.fenikkel.leagueoffenikkel.myProfile.DownloadImageTask;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;
import com.al286752.fenikkel.leagueoffenikkel.showStats.IShowStatsActivity;
import com.al286752.fenikkel.leagueoffenikkel.showStats.ShowStatsPresenter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class PickParalysis extends AppCompatActivity implements IShowStatsActivity {

    TextView freeToPlay;
    ImageView marksmanImage;
    ImageView midImage;
    ImageView apImage;
    ImageView adImage;
    ImageView mixImage;
    ImageView defenseImage;

    TextView laneText;
    TextView bestRoleText;

    ImageView bestRoleImage1;
    ImageView bestRoleImage2;
    ImageView bestRoleImage3;

    ArrayList<String> linea = new ArrayList<>();
    String tipo;

    private ShowStatsPresenter presenter;


    @Override
    public void onBackPressed() {

       // onCreate(null);

        if(marksmanImage.getVisibility() == View.VISIBLE){
            super.onBackPressed();
        }
        marksmanImage.setVisibility(View.VISIBLE);
        midImage.setVisibility(View.VISIBLE);
        apImage.setVisibility(View.GONE);
        adImage.setVisibility(View.GONE);
        mixImage.setVisibility(View.GONE);
        defenseImage.setVisibility(View.GONE);
        bestRoleImage1.setVisibility(View.GONE);
        bestRoleImage2.setVisibility(View.GONE);
        bestRoleImage3.setVisibility(View.GONE);
        bestRoleText.setVisibility(View.GONE);
        laneText.setVisibility(View.GONE);
        freeToPlay.setVisibility(View.GONE);






        StaticData.getElementFilter().clear();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_paralysis);


        bestRoleImage1 = findViewById(R.id.bestImage1);
        bestRoleImage2 = findViewById(R.id.bestImage2);
        bestRoleImage3 = findViewById(R.id.bestImage3);

        bestRoleText = findViewById(R.id.bestText);
        laneText = findViewById(R.id.laneText);
        freeToPlay = findViewById(R.id.freeToPlay);
        marksmanImage = findViewById(R.id.marksmanImage);
        midImage = findViewById(R.id.midImage);
        apImage = findViewById(R.id.apImage);
        adImage = findViewById(R.id.adImage);
        mixImage = findViewById(R.id.mixImage);
        defenseImage = findViewById(R.id.defenseImage);
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

                    //onBackPressed(); //demanaria massa vegades champ mastery i fotria la API
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

    private void processJSONChampsByID(String requested) { //AÇO PA QUE COLLONS ERAAAAAAAAAAAAAAAAAAAAAAA????

        //freeToPlay.setText(StaticData.getChampMapByID().toString());


    }

    public void onMarksmanClick(View view){
        marksmanImage.setVisibility(View.GONE);
        midImage.setVisibility(View.GONE);
        apImage.setVisibility(View.VISIBLE);
        adImage.setVisibility(View.VISIBLE);
        mixImage.setVisibility(View.VISIBLE);
        defenseImage.setVisibility(View.VISIBLE);
        linea.add("Marksman");

        //freeToPlay.setText(StaticData.getChampMapByID().get("106").toString());


    }

    public void onAPClick(View view){

        mixImage.setVisibility(View.GONE);
        apImage.setVisibility(View.GONE);
        adImage.setVisibility(View.GONE);
        defenseImage.setVisibility(View.GONE);
        tipo = "AP";

        showMasteryFilter(linea,tipo);
    }

    public void onDefenseClick(View view){

        mixImage.setVisibility(View.GONE);
        apImage.setVisibility(View.GONE);
        adImage.setVisibility(View.GONE);
        defenseImage.setVisibility(View.GONE);
        tipo = "defense";

        showMasteryFilter(linea,tipo);
    }

    public void onADClick(View view){

        mixImage.setVisibility(View.GONE);
        apImage.setVisibility(View.GONE);
        adImage.setVisibility(View.GONE);
        defenseImage.setVisibility(View.GONE);
        tipo = "AD";

        showMasteryFilter(linea,tipo);
    }

    public void onMixClick(View view){

        mixImage.setVisibility(View.GONE);
        apImage.setVisibility(View.GONE);
        adImage.setVisibility(View.GONE);
        defenseImage.setVisibility(View.GONE);
        tipo = "MIX";

        showMasteryFilter(linea,tipo);
    }

    private void showMasteryFilter(ArrayList<String> linea, String tipo) {

        ArrayList<String> masteryID = StaticData.getMasteriesIds();//de 0 a n tiene por orden los id de los campeones con mas puntuacion de maestria (contador serviria tambien para el que tiene el JSON)

        TreeMap<String, JSONObject> allChamps = StaticData.getChampMapByID(); //la key es la id del champ que queremos

        ElementComparator comparator = new ElementComparator(tipo);
        PriorityQueue<ArrayList> elementFilter = new PriorityQueue<>(3, comparator);


        for(int contador=0; contador<masteryID.size() ; contador++){

            JSONObject currentChamp = allChamps.get(masteryID.get(contador));
            JSONArray tags = currentChamp.optJSONArray("tags");
            JSONObject info = currentChamp.optJSONObject("info");// not necessary


            if(tags.optString(0,"").equals(linea.get(0)) || tags.optString(1,"").equals(linea.get(0)) || tags.optString(2,"").equals(linea.get(0)) ){
                //primer filtro passat



                if(elementFilter.size()>=3){ //major no deuria passar


                    ArrayList mutante = new ArrayList();
                    mutante.add(0,currentChamp);
                    mutante.add(1,StaticData.getMasteries().get(contador));

                    int resultado = comparator.compare(elementFilter.peek(),mutante); ////Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.

                    if(resultado<0){
                        elementFilter.poll();
                        elementFilter.add(mutante);
                    }
                }
                else {

                    ArrayList mutante = new ArrayList();
                    mutante.add(0,currentChamp);
                    mutante.add(1,StaticData.getMasteries().get(contador));

                    elementFilter.add(mutante);
                }




                //cai AP

                //aci mixte

            }

            StaticData.setElementFilter(elementFilter);



        }

        laneText.setVisibility(View.VISIBLE);
        bestRoleText.setVisibility(View.VISIBLE);
        bestRoleImage1.setVisibility(View.VISIBLE);
        bestRoleImage2.setVisibility(View.VISIBLE);
        bestRoleImage3.setVisibility(View.VISIBLE);
        freeToPlay.setVisibility(View.VISIBLE);


        if(linea.get(0).equals("Mage")){

            laneText.setText("Mid Lane");

        }else if(linea.get(0).equals("Tank")){
            laneText.setText("Top Lane");
        }
        else if(linea.get(0).equals("Fighter")){
            laneText.setText("Jungle");
        }else{
            laneText.setText(linea.get(0));
        }


        if(tipo.equals("MIX")){

            bestRoleText.setText("Best in mixed damage");

        } /*else if (tipo.equals("defense")) {

            bestRoleText.setText("Best in tankiness"); //esque no se si tankiness esta ben dit

        }*/else{
            bestRoleText.setText("Best in " + tipo);
        }

        int contador =0;
        while (!StaticData.getElementFilter().isEmpty()){ //mientras la cola no esta vacia
            ChampionMaestries masteryData = (ChampionMaestries) StaticData.getElementFilter().peek().get(1); //per al champion level etc
            JSONObject champData = (JSONObject) StaticData.getElementFilter().poll().get(0);


            JSONObject imagenes = champData.optJSONObject("image");
            String iconURL = "http://ddragon.leagueoflegends.com/cdn/"+ StaticData.getVersion()+"/img/champion/" + imagenes.optString("full");

            if(contador == 0 ){

                setChampionImage(iconURL,bestRoleImage3);

            }
            else if(contador == 1){

                setChampionImage(iconURL,bestRoleImage2);
            }
            else{

                setChampionImage(iconURL,bestRoleImage1);

            }
            contador++;
        }


        //freeToPlay.setText(StaticData.getElementFilter().toString());


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


    private void setChampionImage(String urlIcon, ImageView image) {

        new DownloadImageTask(image).execute(urlIcon);

    }

    public void onChampClick(View view){

        // aço per a quan apretem a la llista


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
