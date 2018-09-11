package com.al286752.fenikkel.leagueoffenikkel.pickParalysis;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.ElementComparator;
import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.SkillComparator;
import com.al286752.fenikkel.leagueoffenikkel.StaticData;
import com.al286752.fenikkel.leagueoffenikkel.Utils;
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

import static com.al286752.fenikkel.leagueoffenikkel.R.color.oxfordBlueBackground;

public class PickParalysis extends AppCompatActivity implements IShowStatsActivity {


    ScrollView bgElement;

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
    TextView lessPlayedText;
    TextView easiestText;
    TextView skilledText;


    TextView apText;
    TextView adText;
    TextView tankText;
    TextView mixText;


    ImageView bestRoleImage1;
    ImageView bestRoleImage2;
    ImageView bestRoleImage3;

    ImageView bestRoleShield1;
    ImageView bestRoleShield2;
    ImageView bestRoleShield3;

    ImageView mostPlayedImage1;
    ImageView mostPlayedImage2;
    ImageView mostPlayedImage3;

    ImageView mostPlayedShield1;
    ImageView mostPlayedShield2;
    ImageView mostPlayedShield3;



    ImageView lessPlayedImage1;
    ImageView lessPlayedImage2;
    ImageView lessPlayedImage3;

    ImageView lessPlayedShield1;
    ImageView lessPlayedShield2;
    ImageView lessPlayedShield3;

    ImageView easiestImage1;
    ImageView easiestImage2;
    ImageView easiestImage3;

    ImageView easiestShield1;
    ImageView easiestShield2;
    ImageView easiestShield3;

    ImageView skilledImage1;
    ImageView skilledImage2;
    ImageView skilledImage3;

    ImageView skilledShield1;
    ImageView skilledShield2;
    ImageView skilledShield3;

    ArrayList<String> linea = new ArrayList<>();
    String tipo;

    private ShowStatsPresenter presenter;

    public void onCursorPickPressed(View view){

       onBackPressed();
    }
    @Override
    public void onBackPressed() {

       // onCreate(null);
        //setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);





        if(marksmanImage.getVisibility() == View.VISIBLE){
            super.onBackPressed();
        }
        bgElement.setBackgroundResource(R.drawable.gradient_background);
        //bgElement.setBackgroundColor(0xFF003366);
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

        bestRoleShield1.setVisibility(View.GONE);
        bestRoleShield2.setVisibility(View.GONE);
        bestRoleShield3.setVisibility(View.GONE);

        mostPlayedImage1.setVisibility(View.GONE);
        mostPlayedImage2.setVisibility(View.GONE);
        mostPlayedImage3.setVisibility(View.GONE);

        mostPlayedShield1.setVisibility(View.GONE);
        mostPlayedShield2.setVisibility(View.GONE);
        mostPlayedShield3.setVisibility(View.GONE);

        lessPlayedImage1.setVisibility(View.GONE);
        lessPlayedImage2.setVisibility(View.GONE);
        lessPlayedImage3.setVisibility(View.GONE);

        lessPlayedShield1.setVisibility(View.GONE);
        lessPlayedShield2.setVisibility(View.GONE);
        lessPlayedShield3.setVisibility(View.GONE);

        skilledImage1.setVisibility(View.GONE);
        skilledImage2.setVisibility(View.GONE);
        skilledImage3.setVisibility(View.GONE);

        skilledShield1.setVisibility(View.GONE);
        skilledShield2.setVisibility(View.GONE);
        skilledShield3.setVisibility(View.GONE);

        easiestImage1.setVisibility(View.GONE);
        easiestImage2.setVisibility(View.GONE);
        easiestImage3.setVisibility(View.GONE);

        easiestShield1.setVisibility(View.GONE);
        easiestShield2.setVisibility(View.GONE);
        easiestShield3.setVisibility(View.GONE);


        bestRoleText.setVisibility(View.GONE);
        laneText.setVisibility(View.GONE);
        mostPlayedText.setVisibility(View.GONE);
        lessPlayedText.setVisibility(View.GONE);
        skilledText.setVisibility(View.GONE);
        easiestText.setVisibility(View.GONE);







        StaticData.getAllIndividualMasteries().clear();


        if(StaticData.getElementFilter()==null){

        }else{
            StaticData.getElementFilter().clear();
        }

        if(StaticData.getMostPlayedFilter()==null){

        }else{
            StaticData.getMostPlayedFilter().clear();
        }

        if(StaticData.getLessPlayedFilter()==null){

        }else{
            StaticData.getLessPlayedFilter().clear();
        }


        if(StaticData.getSkilledFilter()==null){

        }else{
            StaticData.getSkilledFilter().clear();
        }

        if(StaticData.getEasiestFilter()==null){

        }else{
            StaticData.getEasiestFilter().clear();
        }


        linea.clear();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_paralysis);


        bgElement = (ScrollView) findViewById(R.id.container);

        easiestImage1 = findViewById(R.id.easiestImage1);
        easiestImage2 = findViewById(R.id.easiestImage2);
        easiestImage3 = findViewById(R.id.easiestImage3);

        easiestShield1 = findViewById(R.id.easyShield1);
        easiestShield2 = findViewById(R.id.easyShield2);
        easiestShield3 = findViewById(R.id.easyShield3);

        skilledImage1 = findViewById(R.id.skilledImage1);
        skilledImage2 = findViewById(R.id.skilledImage2);
        skilledImage3 = findViewById(R.id.skilledImage3);

        skilledShield1 = findViewById(R.id.skilledShield1);
        skilledShield2 = findViewById(R.id.skilledShield2);
        skilledShield3 = findViewById(R.id.skilledShield3);



        bestRoleImage1 = findViewById(R.id.bestImage1);
        bestRoleImage2 = findViewById(R.id.bestImage2);
        bestRoleImage3 = findViewById(R.id.bestImage3);

        bestRoleShield1 = findViewById(R.id.bestRoleShield1);
        bestRoleShield2 = findViewById(R.id.bestRoleShield2);
        bestRoleShield3 = findViewById(R.id.bestRoleShield3);

        mostPlayedImage1 = findViewById(R.id.mostPlayedImage1);
        mostPlayedImage2 = findViewById(R.id.mostPlayedImage2);
        mostPlayedImage3 = findViewById(R.id.mostPlayedImage3);

        mostPlayedShield1 = findViewById(R.id.mPlayedShield1);
        mostPlayedShield2 = findViewById(R.id.mPlayedShield2);
        mostPlayedShield3 = findViewById(R.id.mPlayedShield3);

        lessPlayedImage1 = findViewById(R.id.lessPlayed1);
        lessPlayedImage2 = findViewById(R.id.lessPlayed2);
        lessPlayedImage3 = findViewById(R.id.lessPlayed3);

        lessPlayedShield1 = findViewById(R.id.lPlayedShield1);
        lessPlayedShield2 = findViewById(R.id.lPlayedShield2);
        lessPlayedShield3 = findViewById(R.id.lPlayedShield3);

        bestRoleText = findViewById(R.id.bestText);
        laneText = findViewById(R.id.laneText);
        mostPlayedText = findViewById(R.id.mostPlayedText);
        lessPlayedText = findViewById(R.id.lessPlayedText);
        skilledText = findViewById(R.id.skilledText);
        easiestText = findViewById(R.id.easiestText);


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

/*
             outState.putInt("LaneText",laneText.getVisibility());

        outState.putInt("BestInHerRole",bestRoleText.getVisibility());

        outState.putInt("BestRoleImage1",bestRoleImage1.getVisibility());
        outState.putInt("BestRoleImage2",bestRoleImage2.getVisibility());
        outState.putInt("BestRoleImage3",bestRoleImage3.getVisibility());

        outState.putInt("BestRoleShield1",bestRoleShield1.getVisibility());
        outState.putInt("BestRoleShield1",bestRoleShield2.getVisibility());
        outState.putInt("BestRoleShield1",bestRoleShield3.getVisibility());

        outState.putInt("MostPlayedText",mostPlayedText.getVisibility());*/

            tipo = savedInstanceState.getString("TipoSeleccionado");
            linea = savedInstanceState.getStringArrayList("LineaSeleccionada");


            marksmanImage.setVisibility(savedInstanceState.getInt("MarksmanVisibility"));
            topImage.setVisibility(savedInstanceState.getInt("TopVisibility"));
            supportImage.setVisibility(savedInstanceState.getInt("SupportVisibility"));
            jungleImage.setVisibility(savedInstanceState.getInt("JungleVisibility"));
            midImage.setVisibility(savedInstanceState.getInt("MidVisibility"));

            helmetBro.setVisibility(savedInstanceState.getInt("HelmetBroVisibility"));

            apImage.setVisibility(savedInstanceState.getInt("APVisibility"));
            adImage.setVisibility(savedInstanceState.getInt("ADVisibility"));
            mixImage.setVisibility(savedInstanceState.getInt("MIXVisibility"));
            defenseImage.setVisibility(savedInstanceState.getInt("TankVisibility"));

            adText.setVisibility(savedInstanceState.getInt("ADTextVisibility"));
            apText.setVisibility(savedInstanceState.getInt("APTextVisibility"));
            mixText.setVisibility(savedInstanceState.getInt("MIXTextVisibility"));
            tankText.setVisibility(savedInstanceState.getInt("TankTextVisibility"));

            laneText.setVisibility(savedInstanceState.getInt("LaneText"));
            laneText.setText(savedInstanceState.getCharSequence("LaneTextWrited"));


            bestRoleText.setVisibility(savedInstanceState.getInt("BestInHerRole"));

            bestRoleImage1.setVisibility(savedInstanceState.getInt("BestRoleImage1"));
            bestRoleImage2.setVisibility(savedInstanceState.getInt("BestRoleImage2"));
            bestRoleImage3.setVisibility(savedInstanceState.getInt("BestRoleImage3"));

            bestRoleShield1.setVisibility(savedInstanceState.getInt("BestRoleShield1"));
            bestRoleShield2.setVisibility(savedInstanceState.getInt("BestRoleShield2"));
            bestRoleShield3.setVisibility(savedInstanceState.getInt("BestRoleShield3"));

            mostPlayedText.setVisibility(savedInstanceState.getInt("MostPlayedText"));













        }


        if(StaticData.getMasteriesIds().isEmpty()){

            boolean networkInfo = Utils.isConnected(getApplicationContext());//DownloadCallback.getActiveNetworkInfo();//.getActiveNetworkInfo();

            if (!networkInfo) {

                android.app.AlertDialog.Builder builderInner = new android.app.AlertDialog.Builder(this);
                builderInner.setMessage("This app needs Internet for work sorry");
                builderInner.setTitle("Network not connected");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                        //System.exit(0);
                        finishAffinity();
                    }
                });
                //eliminar la database i que no sen vaja del splash screen
                //myProfileModel.deleteCurrentSummoner();
                builderInner.show();

            }
            else{
                findMaestries();
            }

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

        lessPlayedImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(8).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(8));
                }
            }
        });
        lessPlayedImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(7).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(7));
                }
            }
        });
        lessPlayedImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(6).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(6));
                }
            }
        });


        easiestImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(11).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(11));
                }
            }
        });
        easiestImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(10).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(10));
                }
            }
        });
        easiestImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(9).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(9));
                }
            }
        });

        skilledImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(14).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(14));
                }
            }
        });
        skilledImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(13).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(13));
                }
            }
        });
        skilledImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(StaticData.getIndividualMasteries(12).first==null){
                    View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "No matches or not enought data", Snackbar.LENGTH_SHORT).show();
                }else{
                    switchToChampMastery(StaticData.getIndividualMasteries(12));
                }
            }
        });

        //supressLint serveix per eliminar l'ultim dibuixat que deixes en l'animacio?? NOPE
        /*@SuppressLint("ResourceType") Animation a = AnimationUtils.loadAnimation(this, R.animator.light_show);
        a.reset();
        ImageView tv = (ImageView) findViewById(R.id.midImage);
        tv.clearAnimation();

        tv.startAnimation(a);

        @SuppressLint("ResourceType") Animation b = AnimationUtils.loadAnimation(this, R.animator.light_show);
        b.reset();
        ImageView tv2 = (ImageView) findViewById(R.id.topImage);
        tv2.clearAnimation();

        tv2.startAnimation(b);

        @SuppressLint("ResourceType") Animation c = AnimationUtils.loadAnimation(this, R.animator.light_show);
        c.reset();
        ImageView tv3 = (ImageView) findViewById(R.id.marksmanImage);
        tv3.clearAnimation();

        tv3.startAnimation(c);

        @SuppressLint("ResourceType") Animation d = AnimationUtils.loadAnimation(this, R.animator.light_show);
        d.reset();
        ImageView tv4 = (ImageView) findViewById(R.id.jungleImage);
        tv4.clearAnimation();

        tv4.startAnimation(d);


        @SuppressLint("ResourceType") Animation e = AnimationUtils.loadAnimation(this, R.animator.light_show);
        e.reset();

        supportImage.clearAnimation();
        supportImage.startAnimation(e);
        //ImageView tv5 = (ImageView) findViewById(R.id.supportImage);
        //tv5.clearAnimation();

        //tv5.startAnimation(e);
        */

        setAlphaAnimation(supportImage);
        setAlphaAnimation(marksmanImage);
        setAlphaAnimation(topImage);
        setAlphaAnimation(midImage);
        setAlphaAnimation(jungleImage);


    }

    public static void setAlphaAnimation(View v) {
        /*ObjectAnimator fadeOut = ObjectAnimator.ofFloat(v, "alpha",  1f, .3f);
        fadeOut.setDuration(2000);*/

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(v, "alpha", 0.0f, 1f);
        fadeIn.setDuration(1000);

        ObjectAnimator moveIn = ObjectAnimator.ofFloat(v, "alpha", 0.0f, 1f);
        moveIn.setDuration(1500);

        float targetY = 200f;

        ObjectAnimator y = ObjectAnimator.ofFloat(v,
                "translationY", v.getY()-targetY, v.getY());

        /*ObjectAnimator x = ObjectAnimator.ofFloat(v,
                "translationX", v.getX(), targetX);

        animSetXY.playTogether(x, y);
        animSetXY.setInterpolator(new LinearInterpolator(1f));*/

        final AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.playTogether(fadeIn,y);//.after(fadeOut);
        mAnimationSet.setInterpolator(new LinearInterpolator());

        /*mAnimationSet.addListener(new AnimatorListenerAdapter() { //QUE FER QUAN L'ANIMACIÓ ACABA
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationSet.start();
            }
        });*/
        mAnimationSet.start();
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


                    /*View parentLayout = findViewById(android.R.id.content);
                    Snackbar.make(parentLayout, "This summoner don't have any mastery", Snackbar.LENGTH_LONG).show();*/

                    AlertDialog.Builder builderInner = new AlertDialog.Builder(PickParalysis.this);
                    builderInner.setMessage("This summoner don't have any mastery, he hasn't played for a long time ^^'");
                    builderInner.setTitle("Sorry");
                    builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,int which) {
                            dialog.dismiss();
                            onBackPressed();
                        }
                    });
                    builderInner.show();

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
        linea.add("Assassin");
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

    public void onHelmetClick(View view){
        onBackPressed();
    }


    private void showMasteryFilter(ArrayList<String> linea, String tipo) {


        /*ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();*/

        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        bgElement.setBackgroundResource(R.drawable.black_gradient_background);
        //bgElement.setBackgroundColor(0xFF000000);

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

            }

            StaticData.setElementFilter(elementFilter);



        }


        //filtro easiest


        //creem comparador de facilAAAAAAAAAAAAAAAAAAAAAAAAAAA
        SkillComparator comparatorSkill = new SkillComparator(tipo, "EASIEST");
        PriorityQueue<ArrayList> easiestFilter = new PriorityQueue<>(3, comparatorSkill);



        for(int contador=0; contador<masteryID.size() ; contador++){

            JSONObject currentChamp = allChamps.get(masteryID.get(contador));
            JSONArray tags = currentChamp.optJSONArray("tags");
            JSONObject info = currentChamp.optJSONObject("info");// not necessary


            if(linea.contains(tags.optString(0,"")) || linea.contains(tags.optString(1,"")) || linea.contains(tags.optString(2,"")) ){
                //primer filtro passat



                if(easiestFilter.size()>=3){ //major no deuria passar


                    ArrayList mutante = new ArrayList();
                    mutante.add(0,currentChamp);
                    mutante.add(1,StaticData.getMasteries().get(contador));

                    int resultado = comparatorSkill.compare(easiestFilter.peek(),mutante); ////Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.

                    if(resultado<0){
                        easiestFilter.poll();
                        easiestFilter.add(mutante);
                    }
                }


                else {//EL PROBLEMA D'ACI ES QUE NO ORDENE SI NO SUPERE LES 3 INSERCIONS

                    int attack =info.optInt("attack");
                    int magic = info.optInt("magic");
                    int defense = info.optInt("defense");
                    String tipoArreglado;
                    if(tipo.equals("AD")){
                        tipoArreglado="attack";
                    }else if(tipo.equals("AP")){
                        tipoArreglado="magic";
                    }
                    else{
                        tipoArreglado="defense";
                    }

                    if(tipo.equals("MIX")){

                        if(attack< 10 && magic< 10 && (magic+attack)>12){
                            ArrayList mutante = new ArrayList();
                            mutante.add(0,currentChamp);
                            mutante.add(1,StaticData.getMasteries().get(contador));

                            easiestFilter.add(mutante);
                        }
                    }



                    else if((info.optInt(tipoArreglado)>=attack) && (info.optInt(tipoArreglado)>=magic) && (info.optInt(tipoArreglado)>=defense)){
                        ArrayList mutante = new ArrayList();
                        mutante.add(0,currentChamp);
                        mutante.add(1,StaticData.getMasteries().get(contador));

                        easiestFilter.add(mutante);


                    }

                    else if(tipoArreglado.equals("defense")&& linea.get(0).equals("Marksman")){
                        if(defense>=5){
                            ArrayList mutante = new ArrayList();
                            mutante.add(0,currentChamp);
                            mutante.add(1,StaticData.getMasteries().get(contador));

                            easiestFilter.add(mutante);

                        }
                    }


                }

            }

            StaticData.setEasiestFilter(easiestFilter);



        }

        //filtro SKILLED

        SkillComparator skilledComparator = new SkillComparator(tipo, "SKILLED");
        PriorityQueue<ArrayList> skilledFilter = new PriorityQueue<>(3, skilledComparator);



        for(int contador=0; contador<masteryID.size() ; contador++){

            JSONObject currentChamp = allChamps.get(masteryID.get(contador));
            JSONArray tags = currentChamp.optJSONArray("tags");
            JSONObject info = currentChamp.optJSONObject("info");// not necessary


            if(linea.contains(tags.optString(0,"")) || linea.contains(tags.optString(1,"")) || linea.contains(tags.optString(2,"")) ){
                //primer filtro passat



                if(skilledFilter.size()>=3){ //major no deuria passar


                    ArrayList mutante = new ArrayList();
                    mutante.add(0,currentChamp);
                    mutante.add(1,StaticData.getMasteries().get(contador));

                    int resultado = skilledComparator.compare(skilledFilter.peek(),mutante); ////Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.

                    if(resultado<0){
                        skilledFilter.poll();
                        skilledFilter.add(mutante);
                    }
                }


                else {//EL PROBLEMA D'ACI ES QUE NO ORDENE SI NO SUPERE LES 3 INSERCIONS

                    int attack =info.optInt("attack");
                    int magic = info.optInt("magic");
                    int defense = info.optInt("defense");
                    String tipoArreglado;
                    if(tipo.equals("AD")){
                        tipoArreglado="attack";
                    }else if(tipo.equals("AP")){
                        tipoArreglado="magic";
                    }
                    else{
                        tipoArreglado="defense";
                    }

                    if(tipo.equals("MIX")){

                        if(attack< 10 && magic< 10 && (magic+attack)>12){
                            ArrayList mutante = new ArrayList();
                            mutante.add(0,currentChamp);
                            mutante.add(1,StaticData.getMasteries().get(contador));

                            skilledFilter.add(mutante);
                        }
                    }



                    else if((info.optInt(tipoArreglado)>=attack) && (info.optInt(tipoArreglado)>=magic) && (info.optInt(tipoArreglado)>=defense)){
                        ArrayList mutante = new ArrayList();
                        mutante.add(0,currentChamp);
                        mutante.add(1,StaticData.getMasteries().get(contador));

                        skilledFilter.add(mutante);


                    }

                    else if(tipoArreglado.equals("defense")&& linea.get(0).equals("Marksman")){
                        if(defense>=5){
                            ArrayList mutante = new ArrayList();
                            mutante.add(0,currentChamp);
                            mutante.add(1,StaticData.getMasteries().get(contador));

                            skilledFilter.add(mutante);

                        }
                    }


                }

            }

            StaticData.setSkilledFilter(skilledFilter);



        }

        //filtro masteries


        Stack<ArrayList> mostPlayedFilter = new Stack<>();
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

                ArrayList mutante = new ArrayList();
                mutante.add(0,currentChamp);
                mutante.add(1,currentMasteryChamp);

                if(tipo.equals("MIX")){
                    if(attack< 10 && magic< 10 && (magic+attack)>12){ //VARIAR els menors
                        mostPlayedFilter.push(mutante);
                    }
                }
                else if(tipo.equals("AD")){
                    if(attack>magic && attack>defense){
                        mostPlayedFilter.push(mutante);
                    }

                }else if (tipo.equals("AP")){
                    if(attack<magic && magic>defense){
                        mostPlayedFilter.push(mutante);
                    }

                }else{//defense

                    if(linea.get(0).equals("Marksman")){// si son marksman ||linea.get(1).equals("Marksman")
                        if(defense>=5){
                            mostPlayedFilter.push(mutante);
                        }
                    }
                    else{
                        if(defense>magic && attack<defense){
                            mostPlayedFilter.push(mutante);
                        }
                    }



                }

            }

            contadore++;
        }
        StaticData.setMostPlayedFilter(mostPlayedFilter);


        Stack<ArrayList> lessPlayedFilter = new Stack<>();
        //ArrayList<ChampionMaestries> allMasteries = StaticData.getMasteries();
        int cunt =allMasteries.size()-1;


        //LESS PLAYED
        while (lessPlayedFilter.size()<3 && cunt>=0 ){//|| mostPlayedFilter==null
            ChampionMaestries currentMasteryChamp = allMasteries.get(cunt);
            String idCurrent = String.valueOf(currentMasteryChamp.getChampionId());
            JSONObject currentChamp = allChamps.get(idCurrent);

            JSONArray tags = currentChamp.optJSONArray("tags");

            //linea.contains(tags.optString(0,"")) || linea.contains(tags.optString(1,"")) || linea.contains(tags.optString(2,""))

            if(linea.contains(tags.optString(0,"")) || linea.contains(tags.optString(1,"")) || linea.contains(tags.optString(2,""))){ //(tags.optString(0,"").equals(linea.get(0)) || tags.optString(1,"").equals(linea.get(0)) || tags.optString(2,"").equals(linea.get(0)) )

                int attack = currentChamp.optJSONObject("info").optInt("attack");
                int magic = currentChamp.optJSONObject("info").optInt("magic");
                int defense = currentChamp.optJSONObject("info").optInt("defense");

                ArrayList mutante = new ArrayList();
                mutante.add(0,currentChamp);
                mutante.add(1,currentMasteryChamp);


                if(tipo.equals("MIX")){
                    if(attack< 10 && magic< 10 && (magic+attack)>12){ //VARIAR els menors
                        lessPlayedFilter.push(mutante);
                    }
                }
                else if(tipo.equals("AD")){
                    if(attack>magic && attack>defense){
                        lessPlayedFilter.push(mutante);
                    }

                }else if (tipo.equals("AP")){
                    if(attack<magic && magic>defense){
                        lessPlayedFilter.push(mutante);
                    }

                }else{//defense

                    if(linea.get(0).equals("Marksman")){// si son marksman ||linea.get(1).equals("Marksman")
                        if(defense>=5){
                            lessPlayedFilter.push(mutante);
                        }
                    }
                    else{
                        if(defense>magic && attack<defense){
                            lessPlayedFilter.push(mutante);
                        }
                    }



                }

            }

            cunt--;
        }
        StaticData.setLessPlayedFilter(lessPlayedFilter);





        laneText.setVisibility(View.VISIBLE);
        bestRoleText.setVisibility(View.VISIBLE);
        lessPlayedText.setVisibility(View.VISIBLE);
        mostPlayedText.setVisibility(View.VISIBLE);
        easiestText.setVisibility(View.VISIBLE);
        skilledText.setVisibility(View.VISIBLE);

        bestRoleImage1.setVisibility(View.VISIBLE);
        bestRoleImage2.setVisibility(View.VISIBLE);
        bestRoleImage3.setVisibility(View.VISIBLE);

        mostPlayedImage1.setVisibility(View.VISIBLE);
        mostPlayedImage2.setVisibility(View.VISIBLE);
        mostPlayedImage3.setVisibility(View.VISIBLE);


        lessPlayedImage1.setVisibility(View.VISIBLE);
        lessPlayedImage2.setVisibility(View.VISIBLE);
        lessPlayedImage3.setVisibility(View.VISIBLE);

        easiestImage1.setVisibility(View.VISIBLE);
        easiestImage2.setVisibility(View.VISIBLE);
        easiestImage3.setVisibility(View.VISIBLE);

        skilledImage1.setVisibility(View.VISIBLE);
        skilledImage2.setVisibility(View.VISIBLE);
        skilledImage3.setVisibility(View.VISIBLE);



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

            int masteryLVL = masteryData.getChampionLevel();

            if(contador == 0 ){

                setChampionImage(iconURL,bestRoleImage3);
                bestRoleShield3.setVisibility(View.VISIBLE);
                switch (masteryLVL){
                    case 2:
                        bestRoleShield3.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        bestRoleShield3.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        bestRoleShield3.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        bestRoleShield3.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        bestRoleShield3.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        bestRoleShield3.setImageResource(R.drawable.m7);
                        break;
                    default:
                        bestRoleShield3.setVisibility(View.GONE);
                        break;

                }



            }
            else if(contador == 1){

                setChampionImage(iconURL,bestRoleImage2);
                bestRoleShield2.setVisibility(View.VISIBLE);

                switch (masteryLVL){
                    case 2:
                        bestRoleShield2.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        bestRoleShield2.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        bestRoleShield2.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        bestRoleShield2.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        bestRoleShield2.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        bestRoleShield2.setImageResource(R.drawable.m7);
                        break;
                    default:
                        bestRoleShield2.setVisibility(View.GONE);
                        break;

                }
            }
            else{

                setChampionImage(iconURL,bestRoleImage1);
                bestRoleShield1.setVisibility(View.VISIBLE);
                switch (masteryLVL){
                    case 2:
                        bestRoleShield1.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        bestRoleShield1.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        bestRoleShield1.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        bestRoleShield1.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        bestRoleShield1.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        bestRoleShield1.setImageResource(R.drawable.m7);
                        break;
                    default:
                        bestRoleShield1.setVisibility(View.GONE);
                        break;

                }

            }
            contador++;
        }

        while (contador<=2){

            if(contador == 0 ){

                bestRoleShield3.setVisibility(View.GONE);
                bestRoleImage3.setImageResource(R.drawable.fill_icon);
                StaticData.addIndividualMasteries(null, -1);

            }
            else if(contador == 1){

                bestRoleShield2.setVisibility(View.GONE);
                bestRoleImage2.setImageResource(R.drawable.fill_icon);
                StaticData.addIndividualMasteries(null, -1);
            }
            else{
                bestRoleShield1.setVisibility(View.GONE);

                bestRoleImage1.setImageResource(R.drawable.fill_icon);
                StaticData.addIndividualMasteries(null, -1);

            }
            contador++;

        }

        while(!mostPlayedFilter.empty()){

            ArrayList current = mostPlayedFilter.pop();//.optJSONObject("data");
            JSONObject currentCham = (JSONObject) current.get(0);
            ChampionMaestries masteryData = (ChampionMaestries) current.get(1);
            StaticData.addIndividualMasteries(currentCham.optString("id"), currentCham.optInt("key")); //5 = el millor de mes jugat , index 4 el segon millor, index 3 el 3r millor

            JSONObject imagenes = currentCham.optJSONObject("image");
            String iconURL = "http://ddragon.leagueoflegends.com/cdn/"+ StaticData.getVersion()+"/img/champion/" + imagenes.optString("full");

            int masteryLVL = masteryData.getChampionLevel();

            if(contador == 3 ){

                setChampionImage(iconURL,mostPlayedImage3);

                mostPlayedShield3.setVisibility(View.VISIBLE);
                switch (masteryLVL){
                    case 2:
                        mostPlayedShield3.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        mostPlayedShield3.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        mostPlayedShield3.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        mostPlayedShield3.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        mostPlayedShield3.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        mostPlayedShield3.setImageResource(R.drawable.m7);
                        break;
                    default:
                        mostPlayedShield3.setVisibility(View.GONE);
                        break;

                }

            }
            else if(contador == 4){

                setChampionImage(iconURL,mostPlayedImage2);

                mostPlayedShield2.setVisibility(View.VISIBLE);
                switch (masteryLVL){
                    case 2:
                        mostPlayedShield2.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        mostPlayedShield2.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        mostPlayedShield2.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        mostPlayedShield2.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        mostPlayedShield2.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        mostPlayedShield2.setImageResource(R.drawable.m7);
                        break;
                    default:
                        mostPlayedShield2.setVisibility(View.GONE);
                        break;

                }
            }
            else{

                setChampionImage(iconURL,mostPlayedImage1);
                mostPlayedShield1.setVisibility(View.VISIBLE);
                switch (masteryLVL){
                    case 2:
                        mostPlayedShield1.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        mostPlayedShield1.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        mostPlayedShield1.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        mostPlayedShield1.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        mostPlayedShield1.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        mostPlayedShield1.setImageResource(R.drawable.m7);
                        break;
                    default:
                        mostPlayedShield1.setVisibility(View.GONE);
                        break;

                }

            }
            contador++;
        }

        while(contador<=5){

            if(contador == 3 ){

                mostPlayedShield3.setVisibility(View.GONE);
                mostPlayedImage3.setImageResource(R.drawable.fill_icon);
                StaticData.addIndividualMasteries(null, -1);

            }
            else if(contador == 4){
                mostPlayedShield2.setVisibility(View.GONE);

                mostPlayedImage2.setImageResource(R.drawable.fill_icon);
                StaticData.addIndividualMasteries(null, -1);
            }
            else{
                mostPlayedShield1.setVisibility(View.GONE);

                mostPlayedImage1.setImageResource(R.drawable.fill_icon);
                StaticData.addIndividualMasteries(null, -1);

            }
            contador++;

        }



        while(!lessPlayedFilter.empty()){







            ArrayList current = lessPlayedFilter.pop();//.optJSONObject("data");
            JSONObject currentCham = (JSONObject) current.get(0);
            ChampionMaestries masteryData = (ChampionMaestries) current.get(1);

            StaticData.addIndividualMasteries(currentCham.optString("id"), currentCham.optInt("key")); //5 = el millor de mes jugat , index 4 el segon millor, index 3 el 3r millor

            JSONObject imagenes = currentCham.optJSONObject("image");
            String iconURL = "http://ddragon.leagueoflegends.com/cdn/"+ StaticData.getVersion()+"/img/champion/" + imagenes.optString("full");

            int masteryLVL = masteryData.getChampionLevel();

            if(contador == 6 ){

                setChampionImage(iconURL,lessPlayedImage3);

                lessPlayedShield3.setVisibility(View.VISIBLE);
                switch (masteryLVL){
                    case 2:
                        lessPlayedShield3.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        lessPlayedShield3.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        lessPlayedShield3.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        lessPlayedShield3.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        lessPlayedShield3.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        lessPlayedShield3.setImageResource(R.drawable.m7);
                        break;
                    default:
                        lessPlayedShield3.setVisibility(View.GONE);
                        break;

                }

            }
            else if(contador == 7){

                setChampionImage(iconURL,lessPlayedImage2);

                lessPlayedShield2.setVisibility(View.VISIBLE);
                switch (masteryLVL){
                    case 2:
                        lessPlayedShield2.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        lessPlayedShield2.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        lessPlayedShield2.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        lessPlayedShield2.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        lessPlayedShield2.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        lessPlayedShield2.setImageResource(R.drawable.m7);
                        break;
                    default:
                        lessPlayedShield2.setVisibility(View.GONE);
                        break;

                }
            }
            else{

                setChampionImage(iconURL,lessPlayedImage1);

                lessPlayedShield1.setVisibility(View.VISIBLE);
                switch (masteryLVL){
                    case 2:
                        lessPlayedShield1.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        lessPlayedShield1.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        lessPlayedShield1.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        lessPlayedShield1.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        lessPlayedShield1.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        lessPlayedShield1.setImageResource(R.drawable.m7);
                        break;
                    default:
                        lessPlayedShield1.setVisibility(View.GONE);
                        break;

                }

            }
            contador++;
        }

        while(contador<=8){

            if(contador == 6 ){

                lessPlayedShield3.setVisibility(View.GONE);

                lessPlayedImage3.setImageResource(R.drawable.fill_icon);
                StaticData.addIndividualMasteries(null, -1);

            }
            else if(contador == 7){

                lessPlayedShield2.setVisibility(View.GONE);

                lessPlayedImage2.setImageResource(R.drawable.fill_icon);
                StaticData.addIndividualMasteries(null, -1);
            }
            else{

                lessPlayedShield1.setVisibility(View.GONE);

                lessPlayedImage1.setImageResource(R.drawable.fill_icon);
                StaticData.addIndividualMasteries(null, -1);

            }
            contador++;

        }

        //EASIEST

        int cantadore =0; //LOL deuria ser contador
        while (!StaticData.getEasiestFilter().isEmpty()){ //mientras la cola no esta vacia
            ChampionMaestries masteryData = (ChampionMaestries) StaticData.getEasiestFilter().peek().get(1); //per al champion level etc
            JSONObject champData = (JSONObject) StaticData.getEasiestFilter().poll().get(0);

            StaticData.addIndividualMasteries(champData.optString("id"), champData.optInt("key"));

            JSONObject imagenes = champData.optJSONObject("image");
            String iconURL = "http://ddragon.leagueoflegends.com/cdn/"+ StaticData.getVersion()+"/img/champion/" + imagenes.optString("full");

            int masteryLVL = masteryData.getChampionLevel();

            if(cantadore == 0 ){

                setChampionImage(iconURL,easiestImage3);

                easiestShield3.setVisibility(View.VISIBLE);
                switch (masteryLVL) {
                    case 2:
                        easiestShield3.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        easiestShield3.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        easiestShield3.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        easiestShield3.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        easiestShield3.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        easiestShield3.setImageResource(R.drawable.m7);
                        break;
                    default:
                        easiestShield3.setVisibility(View.GONE);
                        break;

                }

            }
            else if(cantadore == 1){

                setChampionImage(iconURL,easiestImage2);

                easiestShield2.setVisibility(View.VISIBLE);
                switch (masteryLVL) {
                    case 2:
                        easiestShield2.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        easiestShield2.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        easiestShield2.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        easiestShield2.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        easiestShield2.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        easiestShield2.setImageResource(R.drawable.m7);
                        break;
                    default:
                        easiestShield2.setVisibility(View.GONE);
                        break;

                }
            }
            else{

                setChampionImage(iconURL,easiestImage1);

                easiestShield1.setVisibility(View.VISIBLE);
                switch (masteryLVL) {
                    case 2:
                        easiestShield1.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        easiestShield1.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        easiestShield1.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        easiestShield1.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        easiestShield1.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        easiestShield1.setImageResource(R.drawable.m7);
                        break;
                    default:
                        easiestShield1.setVisibility(View.GONE);
                        break;

                }

            }
            cantadore++;
        }

        while (cantadore<=2){

            if(cantadore == 0 ){
                easiestShield3.setVisibility(View.GONE);

                easiestImage3.setImageResource(R.drawable.fill_icon);
                StaticData.addIndividualMasteries(null, -1);

            }
            else if(cantadore == 1){
                easiestShield2.setVisibility(View.GONE);

                easiestImage2.setImageResource(R.drawable.fill_icon);
                StaticData.addIndividualMasteries(null, -1);
            }
            else{
                easiestShield1.setVisibility(View.GONE);

                easiestImage1.setImageResource(R.drawable.fill_icon);
                StaticData.addIndividualMasteries(null, -1);

            }
            cantadore++;

        }


        //SKilled

        cantadore =0; //LOL deuria ser contador
        while (!StaticData.getSkilledFilter().isEmpty()){ //mientras la cola no esta vacia
            ChampionMaestries masteryData = (ChampionMaestries) StaticData.getSkilledFilter().peek().get(1); //per al champion level etc
            JSONObject champData = (JSONObject) StaticData.getSkilledFilter().poll().get(0);

            StaticData.addIndividualMasteries(champData.optString("id"), champData.optInt("key"));

            JSONObject imagenes = champData.optJSONObject("image");
            String iconURL = "http://ddragon.leagueoflegends.com/cdn/"+ StaticData.getVersion()+"/img/champion/" + imagenes.optString("full");

            int masteryLVL = masteryData.getChampionLevel();


            if(cantadore == 0 ){

                setChampionImage(iconURL,skilledImage3);

                skilledShield3.setVisibility(View.VISIBLE);
                switch (masteryLVL) {
                    case 2:
                        skilledShield3.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        skilledShield3.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        skilledShield3.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        skilledShield3.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        skilledShield3.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        skilledShield3.setImageResource(R.drawable.m7);
                        break;
                    default:
                        skilledShield3.setVisibility(View.GONE);
                        break;

                }

            }
            else if(cantadore == 1){

                setChampionImage(iconURL,skilledImage2);

                skilledShield2.setVisibility(View.VISIBLE);
                switch (masteryLVL) {
                    case 2:
                        skilledShield2.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        skilledShield2.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        skilledShield2.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        skilledShield2.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        skilledShield2.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        skilledShield2.setImageResource(R.drawable.m7);
                        break;
                    default:
                        skilledShield2.setVisibility(View.GONE);
                        break;

                }
            }
            else{

                setChampionImage(iconURL,skilledImage1);

                skilledShield1.setVisibility(View.VISIBLE);
                switch (masteryLVL) {
                    case 2:
                        skilledShield1.setImageResource(R.drawable.m2);
                        break;
                    case 3:
                        skilledShield1.setImageResource(R.drawable.m3);
                        break;
                    case 4:
                        skilledShield1.setImageResource(R.drawable.m4);
                        break;
                    case 5:
                        skilledShield1.setImageResource(R.drawable.m5);
                        break;
                    case 6:
                        skilledShield1.setImageResource(R.drawable.m6);
                        break;
                    case 7:
                        skilledShield1.setImageResource(R.drawable.m7);
                        break;
                    default:
                        skilledShield1.setVisibility(View.GONE);
                        break;

                }

            }
            cantadore++;
        }

        while (cantadore<=2){

            if(cantadore == 0 ){

                skilledImage3.setImageResource(R.drawable.fill_icon);
                skilledShield3.setVisibility(View.GONE);

                StaticData.addIndividualMasteries(null, -1);

            }
            else if(cantadore == 1){

                skilledImage2.setImageResource(R.drawable.fill_icon);
                skilledShield2.setVisibility(View.GONE);

                StaticData.addIndividualMasteries(null, -1);
            }
            else{

                skilledImage1.setImageResource(R.drawable.fill_icon);
                skilledShield1.setVisibility(View.GONE);

                StaticData.addIndividualMasteries(null, -1);

            }
            cantadore++;

        }

        //dialog.hide();
    }

    //FICAR UN METODO DE RESET( per a tornar-ho a fer )


    private void setChampionImage(String urlIcon, ImageView image) {

        new DownloadImageTask(image).execute(urlIcon);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList("LineaSeleccionada",linea);
        outState.putString("TipoSeleccionado",tipo);

        outState.putInt("MidVisibility", midImage.getVisibility());
        outState.putInt("MarksmanVisibility",marksmanImage.getVisibility());
        outState.putInt("TopVisibility",topImage.getVisibility());
        outState.putInt("JungleVisibility",jungleImage.getVisibility());
        outState.putInt("SupportVisibility",supportImage.getVisibility());

        outState.putInt("HelmetBroVisibility",helmetBro.getVisibility());

        outState.putInt("APVisibility",apImage.getVisibility());
        outState.putInt("ADVisibility",adImage.getVisibility());
        outState.putInt("MIXVisibility",mixImage.getVisibility());
        outState.putInt("TankVisibility",defenseImage.getVisibility());

        outState.putInt("APTextVisibility",apText.getVisibility());
        outState.putInt("ADTextVisibility",adText.getVisibility());
        outState.putInt("MIXTextVisibility",mixText.getVisibility());
        outState.putInt("TankTextVisibility",tankText.getVisibility());

        outState.putInt("LaneText",laneText.getVisibility());
        outState.putCharSequence("LaneTextWrited",laneText.getText());



        outState.putInt("BestInHerRole",bestRoleText.getVisibility());

        outState.putInt("BestRoleImage1",bestRoleImage1.getVisibility());
        outState.putInt("BestRoleImage2",bestRoleImage2.getVisibility());
        outState.putInt("BestRoleImage3",bestRoleImage3.getVisibility());

        outState.putInt("BestRoleShield1",bestRoleShield1.getVisibility());
        outState.putInt("BestRoleShield2",bestRoleShield2.getVisibility());
        outState.putInt("BestRoleShield3",bestRoleShield3.getVisibility());

        outState.putInt("MostPlayedText",mostPlayedText.getVisibility());













    }
}
