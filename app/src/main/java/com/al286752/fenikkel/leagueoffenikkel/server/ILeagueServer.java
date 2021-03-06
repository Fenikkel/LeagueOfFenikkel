package com.al286752.fenikkel.leagueoffenikkel.server;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by fenikkel on 17/05/18.
 */

public interface ILeagueServer {

    void findSummoner(String nickName, ResponseReceiver<JSONObject> responseReceiver);
    void findSummonerByID(String summonerID, ResponseReceiver<JSONObject> responseReceiver);

    String getUrlIcon(int idIcon);

    void  findMaestries(String sumID, ResponseReceiver<JSONArray> reciever);

    void getChampions(ResponseReceiver<JSONObject> responseReceiver);

    void getChampionsByID(ResponseReceiver<JSONObject> responseReceiver);

    void getChampionMastery(String summId, String champId, ResponseReceiver<JSONObject> receiver);

    void getChampionIcon(String champNameKey, ResponseReceiver<String> responseReceiver);

    void getVersion(ResponseReceiver<String> responseReceiver);
}
