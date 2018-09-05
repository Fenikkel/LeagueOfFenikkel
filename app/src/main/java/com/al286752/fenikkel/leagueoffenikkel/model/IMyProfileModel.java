package com.al286752.fenikkel.leagueoffenikkel.model;

import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by fenikkel on 17/05/18.
 */

public interface IMyProfileModel {

    void findSummoner(String nickname, ResponseReceiver<JSONObject> reciever);
    String getUrlIcon(int idIcon);
    void insertCurrentSummoner(int summonerID, String lastVersion, String region, String regionName);
    ArrayList<String> getCurrentSummoner();
    boolean deleteCurrentSummoner();
    void findSummonerByID(String summonerID, ResponseReceiver<JSONObject> responseReceiver);
}
