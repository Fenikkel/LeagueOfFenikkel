package com.al286752.fenikkel.leagueoffenikkel.showStats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.R;

public class ShowStatsActivity extends AppCompatActivity {

    public static final String NICKNAME = "nickName";//se suposa que se replena al cambiar de activitat, sera el parametre que li passem (esta en MyProfile)
    TextView nickNameStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);

        Intent intent = getIntent();
        String nick = intent.getStringExtra(NICKNAME); //no se molt be per a que serveix la final string... es tipo un mediador o uns transportador de paquetes?



        nickNameStats = findViewById(R.id.nickNameStats);
        nickNameStats.setText(nick); //tindra el que hem escrit en el dialog

    }
}
