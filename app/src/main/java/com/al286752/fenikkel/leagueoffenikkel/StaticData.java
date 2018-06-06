package com.al286752.fenikkel.leagueoffenikkel;

import org.json.JSONObject;
import org.w3c.dom.ProcessingInstruction;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by fenikkel on 6/06/18.
 */

public class StaticData {

    private static String idSummoner;
    private static String summonerName;
    private static String sumonerLVL;

    public static void setIdSummoner(String idSummoner) {
        StaticData.idSummoner = idSummoner;
    }

    public static void setSummonerName(String summonerName) {
        StaticData.summonerName = summonerName;
    }

    public static void setSumonerLVL(String sumonerLVL) {
        StaticData.sumonerLVL = sumonerLVL;
    }

    public static String getIdSummoner() {
        return idSummoner;
    }

    public static String getSummonerName() {
        return summonerName;
    }

    public static String getSumonerLVL() {
        return sumonerLVL;
    }

    private static Map<String, JSONObject> allChampions; //autoupdates (key, JSONObject campeon) la key es el id del champ

    private static JSONObject champListByName; //la key del champ que es su nombre (menos monkey king y algunos espacios "'" y lowercase

    private static ArrayList<ChampionMaestries> champMaestries; //Masteries of the actual summoner

    public static ArrayList<ChampionMaestries> getChampMaestries() {
        return champMaestries;
    }

    public static void setChampMaestries(ArrayList<ChampionMaestries> champMaestries) {
        StaticData.champMaestries = champMaestries;
    }

    public static Map<String, JSONObject> getAllChampions() {
        return allChampions;
    }

    public static void setChampListByName(JSONObject champListByName) {
        StaticData.champListByName = champListByName;
    }

    public static JSONObject getChampListByName() {

        return champListByName;
    }

    public static void setAllChampions(Map<String, JSONObject> allChamp) {

        allChampions = allChamp;
    }
}
