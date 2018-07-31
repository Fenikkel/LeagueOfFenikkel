package com.al286752.fenikkel.leagueoffenikkel.pickParalysis;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
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
import java.util.Stack;
import java.util.TreeMap;

public class PickParalysis extends AppCompatActivity implements IShowStatsActivity {



    ImageView marksmanImage;
    ImageView supportImage;
    ImageView topImage;
    ImageView midImage;
    ImageView jungleImage;


    ImageView apImage;
    ImageView adImage;
    ImageView mixImage;
    ImageView defenseImage;
    ImageView helmetBro;

    TextView laneText;
    TextView bestRoleText;
    TextView mostPlayedText;

    TextView apText;
    TextView adText;
    TextView tankText;
    TextView mixText;


    ImageView bestRoleImage1;
    ImageView bestRoleImage2;
    ImageView bestRoleImage3;
    ImageView mostPlayedImage1;
    ImageView mostPlayedImage2;
    ImageView mostPlayedImage3;

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
        supportImage.setVisibility(View.VISIBLE);
        topImage.setVisibility(View.VISIBLE);
        midImage.setVisibility(View.VISIBLE);
        jungleImage.setVisibility(View.VISIBLE);

        apImage.setVisibility(View.GONE);
        adImage.setVisibility(View.GONE);
        mixImage.setVisibility(View.GONE);
        defenseImage.setVisibility(View.GONE);

        helmetBro.setVisibility(View.GONE);
        adText.setVisibility(View.GONE);
        apText.setVisibility(View.GONE);
        tankText.setVisibility(View.GONE);
        mixText.setVisibility(View.GONE);


        bestRoleImage1.setVisibility(View.GONE);
        bestRoleImage2.setVisibility(View.GONE);
        bestRoleImage3.setVisibility(View.GONE);
        mostPlayedImage1.setVisibility(View.GONE);
        mostPlayedImage2.setVisibility(View.GONE);
        mostPlayedImage3.setVisibility(View.GONE);

        bestRoleText.setVisibility(View.GONE);
        laneText.setVisibility(View.GONE);
        mostPlayedText.setVisibility(View.GONE);





        StaticData.getAllIndividualMasteries().clear();


        StaticData.getElementFilter().clear();
        StaticData.getMostPlayedFilter().clear();
        linea.clear();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_paralysis);


        bestRoleImage1 = findViewById(R.id.bestImage1);
        bestRoleImage2 = findViewById(R.id.bestImage2);
        bestRoleImage3 = findViewById(R.id.bestImage3);

        mostPlayedImage1 = findViewById(R.id.mostPlayedImage1);
        mostPlayedImage2 = findViewById(R.id.mostPlayedImage2);
        mostPlayedImage3 = findViewById(R.id.mostPlayedImage3);

        bestRoleText = findViewById(R.id.bestText);
        laneText = findViewById(R.id.laneText);
        mostPlayedText = findViewById(R.id.mostPlayedText);

        adText = findViewById(R.id.onAttackIcon); //text que esta per damunt del icono
        apText = findViewById(R.id.onMagicIcon);
        mixText = findViewById(R.id.onMixIcon);
        tankText = findViewById(R.id.onDefenseIcon);




        marksmanImage = findViewById(R.id.marksmanImage);
        supportImage = findViewById(R.id.supportImage);
        topImage = findViewById(R.id.topImage);
        midImage = findViewById(R.id.midImage);
        jungleImage = findViewById(R.id.jungleImage);

        apImage = findViewById(R.id.apImage);
        adImage = findViewById(R.id.adImage);
        mixImage = findViewById(R.id.mixImage);
        defenseImage = findViewById(R.id.defenseImage);
        helmetBro = findViewById(R.id.helmetBro);


        presenter = new ShowStatsPresenter(this,getApplicationContext());

        processJSONChampsByID("EASYEST");


        if(savedInstanceState!= null){ // important, que sino ho fa a la primera

            marksmanImage.setVisibility(savedInstanceState.getInt("MarksmanVisibility"));
            supportImage.setVisibility(savedInstanceState.getInt("MidVisibility"));
            apImage.setVisibility(savedInstanceState.getInt("APVisibility"));
            adImage.setVisibility(savedInstanceState.getInt("ADVisibility"));



        }


        if(StaticData.getMasteriesIds().isEmpty()){
            findMaestries();
        }

        bestRoleImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(StaticData.getIndividualMasteries(2).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(2));
                }


            }
        });
        bestRoleImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(1).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(1));
                }
            }
        });
        bestRoleImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(0).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(0));
                }
            }
        });

        mostPlayedImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(5).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(5));
                }
            }
        });
        mostPlayedImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(4).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(4));
                }
            }
        });
        mostPlayedImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(3).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(3));
                }
            }
        });


    }

    private void switchToChampMastery(Pair<String,Integer> pareja) {

        String champID = pareja.first;
        int chmpNumID= pareja.second;
        String quePesaos = String.valueOf(chmpNumID);




        Intent intento = new Intent(this, ChampMastery.class);


        intento.putExtra(ChampMastery.CHAMP_ID_NAME, champID);
        //intento.putExtra(ChampMastery.CHAMPION_NAME, bitxoName);
        intento.putExtra(ChampMastery.CHAMPION, quePesaos);
        //intento.putExtra(ChampMastery.SUMMONER, idSum);

        startActivity(intento);
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

        //mostPlayedText.setText(StaticData.getChampMapByID().toString());


    }

    public void onTopClick(View view){
        marksmanImage.setVisibility(View.GONE);
        supportImage.setVisibility(View.GONE);
        topImage.setVisibility(View.GONE);
        jungleImage.setVisibility(View.GONE);
        midImage.setVisibility(View.GONE);

        apImage.setVisibility(View.VISIBLE);
        adImage.setVisibility(View.VISIBLE);
        mixImage.setVisibility(View.VISIBLE);
        defenseImage.setVisibility(View.VISIBLE);

        helmetBro.setVisibility(View.VISIBLE);
        adText.setVisibility(View.VISIBLE);
        apText.setVisibility(View.VISIBLE);
        tankText.setVisibility(View.VISIBLE);
        mixText.setVisibility(View.VISIBLE);

        linea.add("Tank");
        linea.add("Fighter");
    }

    public void onMidClick(View view){
        marksmanImage.setVisibility(View.GONE);
        supportImage.setVisibility(View.GONE);
        topImage.setVisibility(View.GONE);
        jungleImage.setVisibility(View.GONE);
        midImage.setVisibility(View.GONE);

        apImage.setVisibility(View.VISIBLE);
        adImage.setVisibility(View.VISIBLE);
        mixImage.setVisibility(View.VISIBLE);
        defenseImage.setVisibility(View.VISIBLE);

        helmetBro.setVisibility(View.VISIBLE);
        adText.setVisibility(View.VISIBLE);
        apText.setVisibility(View.VISIBLE);
        tankText.setVisibility(View.VISIBLE);
        mixText.setVisibility(View.VISIBLE);
        linea.add("Mage");
        linea.add("Assasin");
    }
    public void onJungleClick(View view){
        marksmanImage.setVisibility(View.GONE);
        supportImage.setVisibility(View.GONE);
        topImage.setVisibility(View.GONE);
        jungleImage.setVisibility(View.GONE);
        midImage.setVisibility(View.GONE);

        apImage.setVisibility(View.VISIBLE);
        adImage.setVisibility(View.VISIBLE);
        mixImage.setVisibility(View.VISIBLE);
        defenseImage.setVisibility(View.VISIBLE);

        helmetBro.setVisibility(View.VISIBLE);
        adText.setVisibility(View.VISIBLE);
        apText.setVisibility(View.VISIBLE);
        tankText.setVisibility(View.VISIBLE);
        mixText.setVisibility(View.VISIBLE);
        linea.add("Fighter");
        //linea.add("Assasin");
    }

    public void onMarksmanClick(View view){
        marksmanImage.setVisibility(View.GONE);
        supportImage.setVisibility(View.GONE);
        topImage.setVisibility(View.GONE);
        jungleImage.setVisibility(View.GONE);
        midImage.setVisibility(View.GONE);

        apImage.setVisibility(View.VISIBLE);
        adImage.setVisibility(View.VISIBLE);
        mixImage.setVisibility(View.VISIBLE);
        defenseImage.setVisibility(View.VISIBLE);

        helmetBro.setVisibility(View.VISIBLE);
        adText.setVisibility(View.VISIBLE);
        apText.setVisibility(View.VISIBLE);
        tankText.setVisibility(View.VISIBLE);
        mixText.setVisibility(View.VISIBLE);


        linea.add("Marksman");

        //mostPlayedText.setText(StaticData.getChampMapByID().get("106").toString());


    }
    public void onSupportClick(View view){
        marksmanImage.setVisibility(View.GONE);
        supportImage.setVisibility(View.GONE);
        topImage.setVisibility(View.GONE);
        jungleImage.setVisibility(View.GONE);
        midImage.setVisibility(View.GONE);

        apImage.setVisibility(View.VISIBLE);
        adImage.setVisibility(View.VISIBLE);
        mixImage.setVisibility(View.VISIBLE);
        defenseImage.setVisibility(View.VISIBLE);

        helmetBro.setVisibility(View.VISIBLE);
        adText.setVisibility(View.VISIBLE);
        apText.setVisibility(View.VISIBLE);
        tankText.setVisibility(View.VISIBLE);
        mixText.setVisibility(View.VISIBLE);
        linea.add("Support");

        //mostPlayedText.setText(StaticData.getChampMapByID().get("106").toString());


    }

    public void onAPClick(View view){

        mixImage.setVisibility(View.GONE);
        apImage.setVisibility(View.GONE);
        adImage.setVisibility(View.GONE);
        defenseImage.setVisibility(View.GONE);

        helmetBro.setVisibility(View.GONE);
        adText.setVisibility(View.GONE);
        apText.setVisibility(View.GONE);
        tankText.setVisibility(View.GONE);
        mixText.setVisibility(View.GONE);
        tipo = "AP";

        showMasteryFilter(linea,tipo);
    }

    public void onDefenseClick(View view){

        mixImage.setVisibility(View.GONE);
        apImage.setVisibility(View.GONE);
        adImage.setVisibility(View.GONE);
        defenseImage.setVisibility(View.GONE);

        helmetBro.setVisibility(View.GONE);
        adText.setVisibility(View.GONE);
        apText.setVisibility(View.GONE);
        tankText.setVisibility(View.GONE);
        mixText.setVisibility(View.GONE);
        tipo = "defense";

        showMasteryFilter(linea,tipo);
    }

    public void onADClick(View view){

        mixImage.setVisibility(View.GONE);
        apImage.setVisibility(View.GONE);
        adImage.setVisibility(View.GONE);
        defenseImage.setVisibility(View.GONE);

        helmetBro.setVisibility(View.GONE);
        adText.setVisibility(View.GONE);
        apText.setVisibility(View.GONE);
        tankText.setVisibility(View.GONE);
        mixText.setVisibility(View.GONE);
        tipo = "AD";

        showMasteryFilter(linea,tipo);
    }

    public void onMixClick(View view){

        mixImage.setVisibility(View.GONE);
        apImage.setVisibility(View.GONE);
        adImage.setVisibility(View.GONE);
        defenseImage.setVisibility(View.GONE);

        helmetBro.setVisibility(View.GONE);
        adText.setVisibility(View.GONE);
        apText.setVisibility(View.GONE);
        tankText.setVisibility(View.GONE);
        mixText.setVisibility(View.GONE);
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


            if(linea.contains(tags.optString(0,"")) || linea.contains(tags.optString(1,"")) || linea.contains(tags.optString(2,"")) ){
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


        //filtro masteries


        Stack<JSONObject> mostPlayedFilter = new Stack<>();
        ArrayList<ChampionMaestries> allMasteries = StaticData.getMasteries();
        int contadore =0;

        while (mostPlayedFilter.size()<3 && contadore<allMasteries.size() ){//|| mostPlayedFilter==null
            ChampionMaestries currentMasteryChamp = allMasteries.get(contadore);
            String idCurrent = String.valueOf(currentMasteryChamp.getChampionId());
            JSONObject currentChamp = allChamps.get(idCurrent);

            JSONArray tags = currentChamp.optJSONArray("tags");

            //linea.contains(tags.optString(0,"")) || linea.contains(tags.optString(1,"")) || linea.contains(tags.optString(2,""))

            if(linea.contains(tags.optString(0,"")) || linea.contains(tags.optString(1,"")) || linea.contains(tags.optString(2,""))){ //(tags.optString(0,"").equals(linea.get(0)) || tags.optString(1,"").equals(linea.get(0)) || tags.optString(2,"").equals(linea.get(0)) )

                int attack = currentChamp.optJSONObject("info").optInt("attack");
                int magic = currentChamp.optJSONObject("info").optInt("magic");
                int defense = currentChamp.optJSONObject("info").optInt("defense");


                if(tipo.equals("MIX")){
                    if(attack< 10 && magic< 10 && (magic+attack)>12){ //VARIAR els menors
                        mostPlayedFilter.push(currentChamp);
                    }
                }
                else if(tipo.equals("AD")){
                    if(attack>magic && attack>defense){
                        mostPlayedFilter.push(currentChamp);
                    }

                }else if (tipo.equals("AP")){
                    if(attack<magic && magic>defense){
                        mostPlayedFilter.push(currentChamp);
                    }

                }else{//defense

                    if(linea.get(0).equals("Marksman")){// si son marksman ||linea.get(1).equals("Marksman")
                        if(defense>=5){
                            mostPlayedFilter.push(currentChamp);
                        }
                    }
                    else{
                        if(defense>magic && attack<defense){
                            mostPlayedFilter.push(currentChamp);
                        }
                    }



                }

            }

            contadore++;
        }
        StaticData.setMostPlayedFilter(mostPlayedFilter);




        laneText.setVisibility(View.VISIBLE);
        bestRoleText.setVisibility(View.VISIBLE);
        bestRoleImage1.setVisibility(View.VISIBLE);
        bestRoleImage2.setVisibility(View.VISIBLE);
        bestRoleImage3.setVisibility(View.VISIBLE);

        mostPlayedImage1.setVisibility(View.VISIBLE);
        mostPlayedImage2.setVisibility(View.VISIBLE);
        mostPlayedImage3.setVisibility(View.VISIBLE);
        mostPlayedText.setVisibility(View.VISIBLE);


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


        //Per a elms millors en el seu element
        int contador =0;
        while (!StaticData.getElementFilter().isEmpty()){ //mientras la cola no esta vacia
            ChampionMaestries masteryData = (ChampionMaestries) StaticData.getElementFilter().peek().get(1); //per al champion level etc
            JSONObject champData = (JSONObject) StaticData.getElementFilter().poll().get(0);

            StaticData.addIndividualMasteries(champData.optString("id"), champData.optInt("key"));

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

        while (contador<=2){

            if(contador == 0 ){

                bestRoleImage3.setImageResource(R.drawable.captain_teemo);
                StaticData.addIndividualMasteries(null, -1);

            }
            else if(contador == 1){

                bestRoleImage2.setImageResource(R.drawable.captain_teemo);
                StaticData.addIndividualMasteries(null, -1);
            }
            else{

                bestRoleImage1.setImageResource(R.drawable.captain_teemo);
                StaticData.addIndividualMasteries(null, -1);

            }
            contador++;

        }

        while(!mostPlayedFilter.empty()){
            JSONObject current = mostPlayedFilter.pop();//.optJSONObject("data");
            StaticData.addIndividualMasteries(current.optString("id"), current.optInt("key")); //5 = el millor de mes jugat , index 4 el segon millor, index 3 el 3r millor

            JSONObject imagenes = current.optJSONObject("image");
            String iconURL = "http://ddragon.leagueoflegends.com/cdn/"+ StaticData.getVersion()+"/img/champion/" + imagenes.optString("full");

            if(contador == 3 ){

                setChampionImage(iconURL,mostPlayedImage3);

            }
            else if(contador == 4){

                setChampionImage(iconURL,mostPlayedImage2);
            }
            else{

                setChampionImage(iconURL,mostPlayedImage1);

            }
            contador++;
        }

        while(contador<=5){

            if(contador == 3 ){

                mostPlayedImage3.setImageResource(R.drawable.captain_teemo);
                StaticData.addIndividualMasteries(null, -1);

            }
            else if(contador == 4){

                mostPlayedImage2.setImageResource(R.drawable.captain_teemo);
                StaticData.addIndividualMasteries(null, -1);
            }
            else{

                mostPlayedImage1.setImageResource(R.drawable.captain_teemo);
                StaticData.addIndividualMasteries(null, -1);

            }
            contador++;

        }

        /*for(int cont=0; cont< StaticData ; cont++){

        }*/




        //mostPlayedText.setText(StaticData.getElementFilter().toString());


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

    /*public void onChampClick(View view){

        // aço per a quan apretem a la llista

        switch (view) {
            case bestRoleImage1:
                //monthString = "January";
                break;
            case 2:  monthString = "February";
                break;
            case 3:  monthString = "March";
                break;
            case 4:  monthString = "April";
                break;
            case 5:  monthString = "May";
                break;
            case 6:  monthString = "June";
                break;
            case 7:  monthString = "July";
                break;
            case 8:  monthString = "August";
                break;
            case 9:  monthString = "September";
                break;
            case 10: monthString = "October";
                break;
            case 11: monthString = "November";
                break;
            case 12: monthString = "December";
                break;
            default: monthString = "Invalid month";
                break;
        }


    }
*/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("MidVisibility", supportImage.getVisibility());
        outState.putInt("MarksmanVisibility",marksmanImage.getVisibility());
        outState.putInt("APVisibility",apImage.getVisibility());
        outState.putInt("ADVisibility",adImage.getVisibility());

    }
}
