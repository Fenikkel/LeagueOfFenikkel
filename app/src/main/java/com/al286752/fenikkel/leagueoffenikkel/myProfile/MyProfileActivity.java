package com.al286752.fenikkel.leagueoffenikkel.myProfile;

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

public class MyProfileActivity extends AppCompatActivity implements IMyProfileView, AskNickNameDialog.INickNameListener{

    MyProfilePresenter myProfilePresenter;
    ImageView profileImage;
    TextView nicknameText;
    ImageView noImage; //si no hay nickname o el nickname es invalido
    TextView noNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        //Lo meu


        nicknameText = findViewById(R.id.nicknameText);
        profileImage = findViewById(R.id.profileImage);  //demoment no fa falta profileImatge.setEmptyView(noImage);

        myProfilePresenter = new MyProfilePresenter(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onProfileImageClick(View view){

        AskNickNameDialog askNickNameDialog = new AskNickNameDialog();
        askNickNameDialog.show(getFragmentManager(), "AskNickName");
    }

    @Override
    public void onNickNameInput(String nickname) { //aci li passem del dialogo que pregunta el nickname al presenter
        myProfilePresenter.onNickNameRequested(nickname);
    }
}
