package com.al286752.fenikkel.leagueoffenikkel.pickParalysis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.R;

public class PickParalysis extends AppCompatActivity {

    TextView freeToPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_paralysis);

        freeToPlay = findViewById(R.id.freeToPlay);


    }
}
