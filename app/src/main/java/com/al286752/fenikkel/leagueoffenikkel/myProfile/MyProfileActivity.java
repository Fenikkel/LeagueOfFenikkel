package com.al286752.fenikkel.leagueoffenikkel.myProfile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Region;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.StaticData;
import com.al286752.fenikkel.leagueoffenikkel.model.IMyProfileModel;
import com.al286752.fenikkel.leagueoffenikkel.model.MyProfileModel;
import com.al286752.fenikkel.leagueoffenikkel.model.ShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.showStats.ShowStatsActivity;

import org.json.JSONObject;

import java.io.File;

public class MyProfileActivity extends AppCompatActivity implements IMyProfileView, AskNickNameDialog.INickNameListener{

    MyProfilePresenter myProfilePresenter;
    ImageView profileImage;
    TextView nicknameText;
    //ImageView noImage;
    TextView region;
    TextView lvltext;
    MyProfileModel myProfileModel;

    long idSummoner = -1;


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finishAffinity(); //ActivityCompat.finishAffinity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        //Lo meu
        nicknameText = findViewById(R.id.nicknameText);
        myProfileModel = MyProfileModel.getInstance(getApplicationContext());


        profileImage = findViewById(R.id.profileImage);  //demoment no fa falta profileImatge.setEmptyView(noImage);
        lvltext = findViewById(R.id.levelText);
        region = findViewById(R.id.region);

        myProfilePresenter = new MyProfilePresenter(this, myProfileModel);


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
            }

        }
        else if(! (StaticData.getIdSummoner()==null)){ //.equals() no val perque si es null no pot fer eixe metode

            //region.setText("HA APLEGAT");

            //buscar el summoner per StaticData.sumoneride

            nicknameText.setText(StaticData.getIdSummoner());
            lvltext.setText(StaticData.getVersion());
            region.setText(StaticData.getRegion());
        }




    }

    public void onProfileImageClick(View view){

        AskNickNameDialog askNickNameDialog = new AskNickNameDialog();
        askNickNameDialog.show(getFragmentManager(), "AskNickName");
    }

    public void onStatsImageClick(View view){
        this.switchToShowStats(myProfilePresenter.getNickName());
    }

    @Override
    public void onNickNameInput(String nickname) { //aci li passem del dialogo que pregunta el nickname al presenter
        myProfilePresenter.onNickNameRequested(nickname);
    }

    @Override
    public void switchToShowStats(String nickName) {

        if(idSummoner == -1){
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, "Please insert your summoner name to start", Snackbar.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, ShowStatsActivity.class);

        //Anyadim parametres a ShowStatsActivity
        intent.putExtra(ShowStatsActivity.NICKNAME, nickName);
        intent.putExtra(ShowStatsActivity.ID_SUMMONER, String.valueOf(idSummoner));

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
            snackString = "This summoner doesn't exist(or is an inactive player)";
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

    public void setNicknameText(String nicknam, long lvl, long sumId) {
        this.nicknameText.setText(nicknam);

        StaticData.setIdSummoner(String.valueOf(sumId));
        StaticData.setSumonerLVL(String.valueOf(lvl));
        StaticData.setSummonerName(nicknam);

        String ltext = "Lvl "+lvl; //para passar a String
        lvltext.setText(ltext);

        idSummoner=sumId;
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
        outState.putString("Region",StaticData.getRegion());


    }
}
