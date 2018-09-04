package com.al286752.fenikkel.leagueoffenikkel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class InfoActivity extends AppCompatActivity {

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        back = findViewById(R.id.backInfo);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onCursorPressed(View view){

        onBackPressed();
    }


}
