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

    //SINGLETTON

    /*        private static MyGamesModel instance = null;

        public static MyGamesModel getInstance(){
            return instance;
        }

        public static MyGamesModel getInstance(Context context){
            if(instance ==null)
                instance = new MyGamesModel(new GameDataBase(context),new IGDBServer(context));
            return instance;
        }
*/


    public MyProfileModel(Context context){ //private     el lleve perque no estic utilitzant instancies

        leagueServer=new LeagueServer(context);

    }


    public void findIcon(int profileIconId, final ResponseReceiver<File> responseReceiver) { //este sera llamado desde el presenter
        leagueServer.findIcon(profileIconId,responseReceiver);
    }

    public void findSummoner(String nickname, ResponseReceiver<JSONObject> reciever) { //este sera llamado desde el presenter
        leagueServer.findSummoner(nickname,reciever);
    }


}
