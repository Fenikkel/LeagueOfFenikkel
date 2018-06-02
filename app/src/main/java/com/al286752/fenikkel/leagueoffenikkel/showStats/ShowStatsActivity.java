package com.al286752.fenikkel.leagueoffenikkel.showStats;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.R;
/*import com.al286752.fenikkel.leagueoffenikkel.server.DownloadCallback;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;
import com.al286752.fenikkel.leagueoffenikkel.server.simpleConection.TaskWithProgress;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.LinkedList;
import java.util.List;*/

public class ShowStatsActivity extends AppCompatActivity {

    public static final String NICKNAME = "nickName";//se suposa que se replena al cambiar de activitat, sera el parametre que li passem (esta en MyProfile)
    public static final String ID_SUMMONER = "idSummoner";
    TextView nickNameStats;
    ProgressBar progressBarProba;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);

        Intent intent = getIntent();
        String nick = intent.getStringExtra(NICKNAME); //no se molt be per a que serveix la final string... es tipo un mediador o uns transportador de paquetes?
        String idSummoner = intent.getStringExtra(ID_SUMMONER);

        nickNameStats = findViewById(R.id.nickNameStats);
        //nickNameStats.setText(nick); //tindra el que hem escrit en el dialog
        nickNameStats.setText(idSummoner);
        progressBarProba = findViewById(R.id.progressBarProba);

    }


}
