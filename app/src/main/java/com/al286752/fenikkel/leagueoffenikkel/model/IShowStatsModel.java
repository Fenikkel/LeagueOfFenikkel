package com.al286752.fenikkel.leagueoffenikkel.model;

import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by fenikkel on 2/06/18.
 */

public interface IShowStatsModel {
    void findMaestries(String idSummoner, ResponseReceiver<ArrayList<ChampionMaestries>> reciever);
}
