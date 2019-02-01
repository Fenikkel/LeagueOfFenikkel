package com.al286752.fenikkel.leagueoffenikkel.myProfile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.InfoActivity;
import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.StaticData;
import com.al286752.fenikkel.leagueoffenikkel.Utils;
import com.al286752.fenikkel.leagueoffenikkel.model.MyProfileModel;
import com.al286752.fenikkel.leagueoffenikkel.model.ShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.pickParalysis.PickParalysis;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;
import com.al286752.fenikkel.leagueoffenikkel.showStats.IShowStatsActivity;
import com.al286752.fenikkel.leagueoffenikkel.showStats.ShowStatsActivity;

import org.json.JSONObject;

import java.util.Iterator;

public class MyProfileActivity extends AppCompatActivity implements IShowStatsActivity, IMyProfileView, AskNickNameDialog.INickNameListener{

    //ScrollView bgElement;

    MyProfilePresenter myProfilePresenter;
    ImageView profileImage;
    TextView nicknameText;
    //ImageView noImage;
    TextView region;
    TextView lvltext;
    MyProfileModel myProfileModel;
    ShowStatsModel showStatsModel;
    //private ShowStatsPresenter presenter;


    String idSummoner = null;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finishAffinity(); //ActivityCompat.finishAffinity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        /*
        bgElement = (ScrollView) findViewById(R.id.profileScrollView);

        double random = Math.random() * 4 + 1; //This will give you value from 1 to 50 using Math.random()

        switch ((int)random){
            case 1:
                bgElement.setBackgroundResource(R.drawable.bg1);
                break;

            case 2:
                bgElement.setBackgroundResource(R.drawable.bg2);
                break;

            case 3:
                bgElement.setBackgroundResource(R.drawable.bg3);
                break;

            case 4:
                bgElement.setBackgroundResource(R.drawable.bg4);
                break;

        }*/

       /* ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();*/

        //Lo meu
        nicknameText = findViewById(R.id.nicknameText);
        myProfileModel = MyProfileModel.getInstance(getApplicationContext());
        showStatsModel = ShowStatsModel.getInstance(getApplicationContext());


        profileImage = findViewById(R.id.profileImage);  //demoment no fa falta profileImatge.setEmptyView(noImage);
        lvltext = findViewById(R.id.levelText);
        region = findViewById(R.id.region);

        myProfilePresenter = new MyProfilePresenter(this, myProfileModel);
        //presenter = new ShowStatsPresenter(this,getApplicationContext());


        if(savedInstanceState!= null){ // important, que sino ho fa a la primera
            nicknameText.setText(savedInstanceState.getString("SummonerName"));
            lvltext.setText("Lvl "+savedInstanceState.getString("SummonerLVL"));
            region.setText(savedInstanceState.getString("Region"));
            if(StaticData.getSummonerIcon()!=null){
                profileImage.setImageBitmap(StaticData.getSummonerIcon());
                //region.setText(StaticData.getRegion());
            }
            else{
                profileImage.setImageResource(R.drawable.evil_teemo);
                @SuppressLint("ResourceType") Animation a = AnimationUtils.loadAnimation(this, R.animator.click_teemo);
                a.reset();
                //a.setRepeatCount(Animation.INFINITE);
                //a.setRepeatMode(Animation.INFINITE);
                final ImageView tv = (ImageView) findViewById(R.id.helpfulCursor);
                tv.setVisibility(View.VISIBLE);
                tv.clearAnimation();

                tv.startAnimation(a);

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                Log.i("tag", "je je");

                                tv.setVisibility(View.GONE);
                            }
                        },
                        7500);
            }

            if(StaticData.getIdSummoner()!=null){
                idSummoner = StaticData.getIdSummoner();
            }else {
                idSummoner = null;
            }


        }
        else if(! (StaticData.getIdSummoner()==null)){ //.equals() no val perque si es null no pot fer eixe metode
            //osea que si que hay info en la database y no estamos girando
            //region.setText("HA APLEGAT");


            //SI STATIC DATA == NULL PUES POSEM -1 A IDSUMMONER SINO POSEM STATIC DATA




            boolean networkInfo = Utils.isConnected(getApplicationContext());//DownloadCallback.getActiveNetworkInfo();//.getActiveNetworkInfo();

            if (!networkInfo) {

                AlertDialog.Builder builderInner = new AlertDialog.Builder(this);
                builderInner.setMessage("Network not connected");
                builderInner.setTitle("This app needs Internet for work sorry");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                        finishAffinity();
                        //System.exit(0);
                    }
                });
                //eliminar la database i que no sen vaja del splash screen
                myProfileModel.deleteCurrentSummoner();
                builderInner.show();

            }else{

                myProfileModel.findSummonerByID(StaticData.getIdSummoner(), new ResponseReceiver<JSONObject>() {
                    @Override
                    public void onResponseReceived(JSONObject response) {
                        //nicknameText.setText(response.optString("name"));
                        //StaticData.setSummonerName(response.optString("name"));
                        //lvltext.setText(String.valueOf(response.optLong("summonerLevel")));
                        //StaticData.setSumonerLVL(String.valueOf(response.optLong("summonerLevel")));
                        //setSummonerIcon(String.valueOf(response.optInt("profileIconId")));
                        setNicknameText(response.optString("name"),response.optLong("summonerLevel"), StaticData.getIdSummoner());


                        String urlIcon = myProfileModel.getUrlIcon(response.optInt("profileIconId"));

                        setSummonerIcon(urlIcon);

                        myProfileModel.insertCurrentSummoner(StaticData.getIdSummoner(),StaticData.getVersion(), StaticData.getRegion(), StaticData.getRegionName());//
                    }

                    @Override
                    public void onErrorReceived(String message) {
                        showError(message);
                    }
                });


                region.setText(StaticData.getRegionName());

                //si aço es tret per db tenim que traure campeons tambe CUIDAOOOOOOOOOOOOOOOO
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
                        View parentLayout = findViewById(android.R.id.content);
                        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show();
                    }
                });
            }




        }
        else{
            //(StaticData.getIdSummoner()==null)
            //fiquem teemo per primera vegada
            boolean networkInfo = Utils.isConnected(getApplicationContext());
            if (!networkInfo) {

                AlertDialog.Builder builderInner = new AlertDialog.Builder(this);
                builderInner.setMessage("Network not connected");
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
                        View parentLayout = findViewById(android.R.id.content);
                        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG).show();
                    }
                });
            }





            @SuppressLint("ResourceType") Animation a = AnimationUtils.loadAnimation(this, R.animator.click_teemo);
            a.reset();
            //a.setRepeatCount(Animation.INFINITE);
            //a.setRepeatMode(Animation.INFINITE);
            final ImageView tv = (ImageView) findViewById(R.id.helpfulCursor);
            tv.setVisibility(View.VISIBLE);
            tv.clearAnimation();

            tv.startAnimation(a);

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            Log.i("tag", "je je");

                            tv.setVisibility(View.GONE);
                        }
                    },
                    7500);


        }

        Button angryButton = (Button) findViewById(R.id.info_button);
        angryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switchToInfoActivity();
            }
        });

        //dialog.hide();









    }

    private void switchToInfoActivity() {
        Intent intent = new Intent(this, InfoActivity.class);

        startActivity(intent);
    }


    private void processChampions(JSONObject jChampions) {

        StaticData.setChampListDDragon(jChampions);

        JSONObject champs = jChampions.optJSONObject("data");
        Iterator<?> keys = champs.keys();

        while( keys.hasNext() ) {
            String key = (String)keys.next();
            String treeKey = champs.optJSONObject(key).optString("key");
            String name =champs.optJSONObject(key).optString("name");
            JSONObject treeChamp = champs.optJSONObject(key);
            StaticData.addChampMapByID(treeKey,treeChamp);
            StaticData.addChampionNameKeys(name, treeKey);
        }

    }



    public void onProfileImageClick(View view){

        AskNickNameDialog askNickNameDialog = new AskNickNameDialog();
        askNickNameDialog.show(getFragmentManager(), "AskNickName");
    }

    public void onStatsImageClick(View view){

        this.switchToShowPickParalysis();

    }

    public void onRegionClick(View view){


        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MyProfileActivity.this);
        builderSingle.setIcon(R.drawable.ward);
        builderSingle.setTitle("Regions:");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MyProfileActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("Europe West");
        arrayAdapter.add("Europe Nordic & East");
        arrayAdapter.add("North America");
        arrayAdapter.add("Latin America North");
        arrayAdapter.add("Latin America South ");
        arrayAdapter.add("Brazil");
        arrayAdapter.add("Turkey");
        arrayAdapter.add("Russia");
        arrayAdapter.add("Korea");
        arrayAdapter.add("Japan");
        arrayAdapter.add("Oceania");



        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String[] key = {"euw1","eun1","na1","la1","la2","br1","tr1","ru","kr","jp1","oc1"}; //newest use NA1 and oldest use NA?
                String[] acronym = {"EUW","EUNE","NA","LAN","LAS","BR","TR","RU","KR","JP","OCE"};

                region.setText(acronym[which]);
                StaticData.setRegionName(acronym[which]);
                StaticData.setRegion(key[which]);

                if(!(StaticData.getSummonerName().equals("The evil")) && StaticData.getSummonerName()!=null){
                    myProfilePresenter.onNickNameRequested(StaticData.getSummonerName());
                }
/*
                String strName = arrayAdapter.getItem(which);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(MyProfileActivity.this);
                builderInner.setMessage(acronym[which]);
                builderInner.setTitle("Your Selected Item is");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();*/


            }
        });
        builderSingle.show();


    }
    public void onMasteryListClick(View view){
        this.switchToShowStats(StaticData.getSummonerName());//myProfilePresenter.getNickName()
    }

    private void switchToShowPickParalysis() {
        if(idSummoner == null){
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Please insert your summoner name to start", Snackbar.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, PickParalysis.class);

        //Anyadim parametres a ShowStatsActivity
        //intent.putExtra(ShowStatsActivity.NICKNAME, nickName);
        //intent.putExtra(ShowStatsActivity.ID_SUMMONER, String.valueOf(idSummoner));

        startActivity(intent);
    }

    @Override
    public void onNickNameInput(String nickname) { //aci li passem del dialogo que pregunta el nickname al presenter

        //levar espais

        String y;
        y = nickname.replaceAll(" ", "").trim(); //trim lleva els espais de davant i darrere i replaceAll els del mig

        StaticData.setSummonerName(y);

        myProfilePresenter.onNickNameRequested(y);
    }

    @Override
    public void switchToShowStats(String nickName) {

        if(idSummoner == null){
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Please insert your summoner name to start", Snackbar.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, ShowStatsActivity.class);

        //Anyadim parametres a ShowStatsActivity
        intent.putExtra(ShowStatsActivity.NICKNAME, nickName);
        intent.putExtra(ShowStatsActivity.ID_SUMMONER, StaticData.getIdSummoner());//String.valueOf(idSummoner)

        startActivity(intent);
    }

    @Override
    public void showError(String message) {

        int stringLenght = message.length();
        String error ="" + message.charAt(stringLenght-3)+ message.charAt(stringLenght-2) + message.charAt(stringLenght-1);
        View parentLayout = findViewById(android.R.id.content);

        String snackString = "YOLO";
        if(error.equals("403")){
            snackString = "Invalid API key";
        }
        else if(error.equals("401")){
            snackString = "Unauthoritzed, your API key can't access to this data";
        }
        else if(error.equals("503")){
            snackString = "Server currently unavaliable";
        }
        else if(error.equals("500")){
            snackString = "Internal server error";
        }
        else if(error.equals("404")){
            snackString = "This summoner doesn't exist(or is an inactive player). Try other region maybe?";
        }
        else if(error.equals("429")){
            snackString = "We reached the limit of requests! We are sorry :(";
        }
        else {
            snackString = message;
        }
       Snackbar.make(parentLayout, snackString, Snackbar.LENGTH_LONG).show();

        //https://developer.riotgames.com/response-codes.html
    }

    public void setNicknameText(String nicknam, long lvl, String sumId) {
        this.nicknameText.setText(nicknam);

        StaticData.setIdSummoner(String.valueOf(sumId));
        StaticData.setSumonerLVL(String.valueOf(lvl));
        StaticData.setSummonerName(nicknam);

        String ltext = "Lvl "+lvl; //para passar a String
        lvltext.setText(ltext);

        idSummoner=sumId;

        //AÇO ES PER A NO REPETIR PETICIONS AL SERVER DE MASTERIES

        StaticData.emptyMasteries();
        StaticData.emptyMasteriesIds();

    }

    @Override
    public void setSummonerIcon(String urlIcon) {
        //https://discussion.developer.riotgames.com/questions/3337/how-can-i-load-summoner-icon-images-using-profilei.html
        //https://ddragon.leagueoflegends.com/api/versions.json
         new DownloadImageTask(profileImage).execute(urlIcon);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("SummonerName",StaticData.getSummonerName());
        outState.putString("SummonerLVL",StaticData.getSumonerLVL());
        outState.putString("Region",StaticData.getRegionName());


    }


}
