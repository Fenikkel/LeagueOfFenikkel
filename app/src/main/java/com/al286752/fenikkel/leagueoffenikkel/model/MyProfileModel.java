package com.al286752.fenikkel.leagueoffenikkel.model;

import android.content.Context;

import com.al286752.fenikkel.leagueoffenikkel.server.ILeagueServer;
import com.al286752.fenikkel.leagueoffenikkel.server.LeagueServer;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by fenikkel on 17/05/18.
 */

public class MyProfileModel implements IMyProfileModel {

    private ILeagueServer leagueServer ;

    public MyProfileModel(Context context){

        leagueServer=new LeagueServer(context);

    }
    public void findSummoner(String nickname, ResponseReceiver<JSONObject> reciever) {
        leagueServer.findSummoner(nickname,reciever);
    }


    @Override
    public String getUrlIcon(int idIcon) {
        return leagueServer.getUrlIcon(idIcon);
    }


}
