package com.al286752.fenikkel.leagueoffenikkel.showStats;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.al286752.fenikkel.leagueoffenikkel.StaticData;
import com.al286752.fenikkel.leagueoffenikkel.champMastery.ChampMastery;

import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

    ImageView errorImg;

    ListView listMaestries;
    private ShowStatsPresenter presenter;

    private String idSum;

    //private static String champName ="view";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        presenter = new ShowStatsPresenter(this,getApplicationContext());


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);

        Intent intent = getIntent();
        String nick = intent.getStringExtra(NICKNAME); //no se molt be per a que serveix la final string... es tipo un mediador o uns transportador de paquetes?
        idSum = intent.getStringExtra(ID_SUMMONER);

        errorImg=findViewById(R.id.imageErrorShow);

        listMaestries = findViewById(R.id.maestriesList);

        findMaestries();

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

                StaticData.setChampMaestries(response);


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

        String[] value2s = champions.toArray(new String[0]);
        String[] subNames = champions.toArray(new String[0]);

        for(int contador=0; contador<value2s.length; contador++){

            JSONObject champ = StaticData.getAllChampions().get(value2s[contador]);

            value2s[contador]= champ.optString("name");
            subNames[contador]= champ.optString("title");



        }

        final MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this, value2s, subNames);

        listMaestries.setAdapter(adapter);

        listMaestries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //presenter.onAddGameRequested(position);
                String champSelected = (String) adapter.getItem(position);

                //mirem quin champ es en champslistbyname y enviem a una altra activity els maestries on les mostrarem tote

                Toast.makeText(getApplicationContext(),champSelected + " selected",Toast.LENGTH_LONG).show();//this, item + " selected", Toast.LENGTH_LONG

                //CHANGE TO MAESTRY
                switchToChampMastery(champSelected);
            }
        });


    }

    private void switchToChampMastery(String champSelected) {

        //QUITAR ESPACIOS NOMBRES

        String st = champSelected;

        st = st.replaceAll("\\s",""); // aixina llevem els espais i merdes que no existeixen en les ID
        st= st.replaceAll("'","");
        //st= st.replaceAll(".","");
        if(st.equals("Wukong")){
            st = "MonkeyKing";
        }else if(st.equals("LeBlanc")){
            st = "Leblanc";
        }else if(st.equals("KaiSa")){
            st = "Kaisa";
        }
        else if(st.equals("KhaZix")){
            st = "Khazix";
        }
        else if(st.equals("VelKoz")){
            st = "Velkoz";
        }else if(st.equals("Dr. Mundo")){ //Dr mundo no va, mira de arreglar-ho be passant les keys lerdo
            st = "DrMundo";
        }
        else if(st.equals("Twisted Fate")){
            st = "TwistedFate";
        }
        else if(st.equals("Cho'Gath")){
            st = "Chogath";
        }

        JSONObject allChampData = StaticData.getChampListByName().optJSONObject(st);

        String laId = allChampData.optString("id");

        String bitxoName = allChampData.optString("name");


        Intent intento = new Intent(this, ChampMastery.class);


        intento.putExtra(ChampMastery.CHAMP_ID_NAME, st);
        intento.putExtra(ChampMastery.CHAMPION_NAME, bitxoName);
        intento.putExtra(ChampMastery.CHAMPION, laId);
        intento.putExtra(ChampMastery.SUMMONER, idSum);

        startActivity(intento);
    }


}
