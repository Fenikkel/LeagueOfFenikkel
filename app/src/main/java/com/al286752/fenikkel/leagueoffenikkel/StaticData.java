package com.al286752.fenikkel.leagueoffenikkel;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONObject;
import org.w3c.dom.ProcessingInstruction;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by fenikkel on 6/06/18.
 */

public class StaticData {

    private static String idSummoner;
    private static String summonerName = "The evil";
    private static String sumonerLVL = "∞";
    private static String version;
    private static String currentVersion;
    private static String region = "EUW";


    private static Bitmap summonerIcon = BitmapFactory.decodeResource(Resources.getSystem(),
            R.drawable.evil_teemo);



    private static Map<String, JSONObject> allChampions; //autoupdates (key, JSONObject campeon) la key es el id del champ (que es diferente al nombre)

    private static JSONObject champListByName; //la key del champ que es su nombre (menos monkey king y algunos espacios "'" y lowercase

    private static ArrayList<ChampionMaestries> champMaestries; //Masteries of the actual summoner (llista de 0 a n campeons. Tu busques per id el campeo)

    private static Map<String, String> championNameKeys; //la key es el nombre del champ (con espacios y "'") y lo que te devuelve es la key del champion que es un nombre sin espacios ni mariconadas

    private static JSONObject champListByID; //lista campeones por la ID(ordenado creo) con sus tags y info de facil, tanque, mago..

    //falta lista de maestrias del summoner actual


    public static Map<String, String> getChampionNameKeys() {
        return championNameKeys;
    }

    public static void setChampionNameKeys(Map<String, String> championNameKeys) {
        StaticData.championNameKeys = championNameKeys;
    }

    public static void setRegion(String region) {
        StaticData.region = region;
    }

    public static String getRegion() {
        return region;
    }

    public static void setIdSummoner(String idSummoner) {
        StaticData.idSummoner = idSummoner;
    }

    public static void setSummonerName(String summonerName) {
        StaticData.summonerName = summonerName;
    }

    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        StaticData.version = version;
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

    public static Bitmap getSummonerIcon() {
        return summonerIcon;
    }

    public static void setSummonerIcon(Bitmap summonerIcon) {
        StaticData.summonerIcon = summonerIcon;
    }

    public static JSONObject getChampListByID() {
        return champListByID;
    }

    public static void setChampListByID(JSONObject champListByID) {
        StaticData.champListByID = champListByID;
    }

    public static String getCurrentVersion() {
        return currentVersion;
    }

    public static void setCurrentVersion(String curreVersion) {
        StaticData.currentVersion = curreVersion;
    }
}
