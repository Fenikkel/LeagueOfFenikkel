package com.al286752.fenikkel.leagueoffenikkel;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Pair;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by fenikkel on 6/06/18.
 */

public class StaticData {

    //PROFILE

    private static String idSummoner;
    private static String summonerName = "The evil";
    private static String sumonerLVL = "∞";
    private static String version;
    private static String currentVersion;
    private static String region = "EUW1";
    private static String regionName = "EUW1";

    private static Bitmap summonerIcon = BitmapFactory.decodeResource(Resources.getSystem(),
            R.drawable.evil_teemo);


/*

id:	22339646
accountId:	26170201
name:	"Fenikkel"
profileIconId:	3506
revisionDate:	1531736966000
summonerLevel:	71

*/



    private static Map<String, JSONObject> allChampions; //autoupdates (key, JSONObject campeon) la key es el id del champ (que es diferente al nombre)

    private static JSONObject champListByName; //la key del champ que es su nombre (menos monkey king y algunos espacios "'" y lowercase



    private static Map<String, String> championNameKeys = new HashMap<>(); //la key es el nombre del champ (con espacios y "'") y lo que te devuelve es la key del champion que es un nombre sin espacios ni mariconadas


    //STATIC CHAMPIONS

    private static JSONObject champListDDragon; //lista campeones por la ID(ordenado creo) con sus tags y info de facil, tanque, mago..

    private static TreeMap<String,JSONObject> champMapByID = new TreeMap<>(); //la key es el numero ID del campeon

/*

type:	"champion"
format:	"standAloneComplex"
version:	"8.14.1"
data:
    Aatrox:
        version:	"8.14.1"
        id:	"Aatrox"
        key:	"266"
        name:	"Aatrox"
        title:	"the Darkin Blade"
        blurb:	"Once honored defenders o…as the first to find..."
        info:
            attack:	8
            defense:	4
            magic:	3
            difficulty:	4
        image:
            full:	"Aatrox.png"
            sprite:	"champion0.png"
            group:	"champion"
            x:	0
            y:	0
            w:	48
            h:	48
        tags:
            0:	"Fighter"
            1:	"Tank"
        partype:	"Blood Well"
        stats:	{…}
    Ahri:	{…}


*/

    //MAESTERIES

    private static ArrayList<String> masteriesIds = new ArrayList<>(); //aci tenim totes les id delschamps que tenim en maestries (en el mateix ordre

    private static ArrayList<ChampionMaestries> masteries = new ArrayList<>(); //Masteries of the actual summoner (llista de 0 a n campeons. Tu busques per id(numero) el campeo)

    //private static Comparator<ArrayList> attackComparator = new ElementComparator();

    //ElementFilter funciona que el menor esta davant
    private static PriorityQueue<ArrayList> elementFilter = new PriorityQueue<>(3);//, attackComparator

    private static PriorityQueue<ArrayList> easiestFilter = new PriorityQueue<>(3);//, attackComparator

    private static PriorityQueue<ArrayList> skilledFilter = new PriorityQueue<>(3);//, attackComparator
    //private static PriorityQueue<JSONObject> bestFilter = new PriorityQueue<>(3, attackComparator);
    private static Stack<ArrayList> mostPlayedFilter;
    private static Stack<ArrayList> lessPlayedFilter;

    private static ArrayList<Pair<String, Integer>> individualMasteries = new ArrayList(); //id champs para que champMastery sepa que mostrar (3 primers bestRole)
/* MASTERIES

0:
    playerId:	22339646
    championId:	103
    championLevel:	7
    championPoints:	105111
    lastPlayTime:	1518810742000
    championPointsSinceLastLevel:	83511
    championPointsUntilNextLevel:	0
    chestGranted:	false
    tokensEarned:	0

*/

    public static String getRegionName() {
        return regionName;
    }

    public static void setRegionName(String regionName) {
        StaticData.regionName = regionName;
    }

    public static ArrayList<Pair<String, Integer>> getAllIndividualMasteries() {
        return individualMasteries;
    }

    public static PriorityQueue<ArrayList> getEasiestFilter() {
        return easiestFilter;
    }

    public static PriorityQueue<ArrayList> getSkilledFilter() {
        return skilledFilter;
    }

    public static void setSkilledFilter(PriorityQueue<ArrayList> skilledFilter) {
        StaticData.skilledFilter = skilledFilter;
    }

    public static void setEasiestFilter(PriorityQueue<ArrayList> easiestFilter) {
        StaticData.easiestFilter = easiestFilter;
    }

    public static Pair<String, Integer> getIndividualMasteries(int index) {//el dato que nos interesa
        return individualMasteries.get(index);
    }

    public static void addIndividualMasteries(String idChamp, int idNumChamp) {
        StaticData.individualMasteries.add(new Pair<String, Integer>(idChamp,idNumChamp));
    }


    public static Map<String, String> getChampionNameKeys() {
        return championNameKeys;
    }

    public static String getTheChampionNameKeys(String key) {
        return championNameKeys.get(key);
    }
    public static void addChampionNameKeys(String champ, String id) {///eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
        StaticData.championNameKeys.put(champ, id);
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

    public static ArrayList<ChampionMaestries> getMasteries() {
        return masteries;
    }

    public static void setMasteries(ArrayList<ChampionMaestries> masteries) {
        StaticData.masteries = masteries;
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

    public static JSONObject getChampListDDragon() {
        return champListDDragon;
    }

    public static void setChampListDDragon(JSONObject champListDDragon) {
        StaticData.champListDDragon = champListDDragon;
    }

    public static String getCurrentVersion() {
        return currentVersion;
    }

    public static void setCurrentVersion(String curreVersion) {
        StaticData.currentVersion = curreVersion;
    }

    public static void setMasteriesIds(ArrayList<String> masteriesIds) {
        StaticData.masteriesIds = masteriesIds;
    }

    public static ArrayList<String> getMasteriesIds() {
        return masteriesIds;
    }

    public static void emptyMasteriesIds() {
        StaticData.masteriesIds.clear();
    }
    public static void emptyMasteries() {
        StaticData.masteries.clear();
    }

    public static void setChampMapByID(TreeMap<String, JSONObject> champMapByID) {
        StaticData.champMapByID = champMapByID;
    }
    public static void addChampMapByID(String key, JSONObject champ) {
        StaticData.champMapByID.put(key, champ);
    }

    public static TreeMap<String, JSONObject> getChampMapByID() {

        return champMapByID;
    }
    public static JSONObject getTheChampMapByID(String key) {

        return (JSONObject) champMapByID.get(key);
    }

    public static PriorityQueue<ArrayList> getElementFilter() {
        return elementFilter;
    }

    public static void setElementFilter(PriorityQueue<ArrayList> elementFilter) {
        StaticData.elementFilter = elementFilter;
    }

    public static Stack<ArrayList> getMostPlayedFilter() {
        return mostPlayedFilter;
    }

    public static void setMostPlayedFilter(Stack<ArrayList> mostPlayedFilter) {
        StaticData.mostPlayedFilter = mostPlayedFilter;
    }

    public static Stack<ArrayList> getLessPlayedFilter() {
        return lessPlayedFilter;
    }

    public static void setLessPlayedFilter(Stack<ArrayList> lessPlayedFilter) {
        StaticData.lessPlayedFilter = lessPlayedFilter;
    }
}
