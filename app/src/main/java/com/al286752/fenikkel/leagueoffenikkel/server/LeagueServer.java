package com.al286752.fenikkel.leagueoffenikkel.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by fenikkel on 17/05/18.
 */

public class LeagueServer implements ILeagueServer {

    //https://euw1.api.riotgames.com/lol/summoner/v3/summoners/by-name/Fenikkel?api_key=RGAPI-1b4973bd-cc49-4833-b9cf-6711ea3412ae

    //private static final int MAX_RESULTS = 20;
    private static final String BAD_JSON_IN_SERVER_RESPONSE = "Bad JSON in server response";

    private static final String BASE_URL = "https://euw1.api.riotgames.com";
    private static final String API_KEY = "?api_key=RGAPI-1b4973bd-cc49-4833-b9cf-6711ea3412ae";
    private static final String SEARCH_SUMMONER = "/lol/summoner/v3/summoners/by-name/"; //aci falta sumarli el nickname + la API_KEY


    //icono

    private static final String ICON_VERSIONS = "https://ddragon.leagueoflegends.com/api/versions.json";

    //http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/588.png

    private static final String BASE_URL_ICON = "http://ddragon.leagueoflegends.com/cdn/";
    private static final String VERSION = "8.11.1";
    private static final String BASE_URL_ICON2 = "/img/profileicon/";
    //faltaria la id del icon
    private static final String PNG = ".png";

    private String iconVersion="caca";


    private Context context;


    public LeagueServer(Context context){ //de mo ment no recorde pa que es EL FAIG PUBLIC
        this.context = context;
        getIconVersion();

    }


    /*@Override
    public void findIcon(int profileIconId, final ResponseReceiver<File> responseReceiver){

        String urlIcon = BASE_URL_ICON + VERSION + BASE_URL_ICON2 + profileIconId+PNG;

        //"com.al286752.fenikkel.leagueoffenikkel.server.ILeagueServer"
        String filename= context.getCacheDir() +"icon.png" ; //tinc que tindre un file i sobrescriurel?

        final File fileToStoreCover = context.getApplicationContext().getFileStreamPath(filename);

        if (fileToStoreCover.exists()){
            //si existe la borro? Por si da problemas
        }


        DownloadTask downloadTask = new DownloadTask(urlIcon, fileToStoreCover, new DownloadCallback<String>() {
            @Override
            public void updateFromDownload(String result) {

                    responseReceiver.onResponseReceived(fileToStoreCover);
                    //passamos de la String result

            }

            @Override
            public NetworkInfo getActiveNetworkInfo() {
                return getNetworkInfo();
            }

            @Override
            public void onError(String msg) {
                responseReceiver.onErrorReceived(msg);
            }
        });

        downloadTask.execute();


    }*/


    @Override
    public void findSummoner(String nickName, final ResponseReceiver<JSONObject> responseReceiver) { //este es llamado desde el modelo que sera llamado desde el presenter

        //getIconVersion();

        String nickEncoded;
        try {
            nickEncoded=  URLEncoder.encode(nickName,"UTF-8");

        }catch (Exception e){
            nickEncoded = nickName;
        }

        String urlPetition =BASE_URL+SEARCH_SUMMONER+nickEncoded+API_KEY;


        DownloadTask downloadTask =  new DownloadTask(urlPetition, new DownloadCallback<String>() {
            @Override
            public void updateFromDownload(String result) { // una vegada s'ha conectat i fet la descarga
                try {

                    JSONObject json = new JSONObject(result);

                    responseReceiver.onResponseReceived(json); //mos passem un JSON ARRAY pero encara hi ha que processarlo

                    //List<AllGameData> allGameData = processJSON(json);
                    //receiver.onResponseReceived(allGameData);



                }catch (JSONException e){
                    responseReceiver.onErrorReceived(BAD_JSON_IN_SERVER_RESPONSE);
                    e.printStackTrace();
                }

            }

            @Override
            public NetworkInfo getActiveNetworkInfo() {
                return getNetworkInfo();
            }

            @Override
            public void onError(String msg) {

                responseReceiver.onErrorReceived(msg);
            }


        });//aqui a continuacion faltarian las cabeceras (que yo no tengo)

        downloadTask.execute(); //aci se fica a fer lo de onpreexecute, doitbackground(on utilitza networkhelper) i onpostexecute

    }

    @Override
    public String getUrlIcon(int idIcon) {

        //getIconVersion();

        return BASE_URL_ICON + iconVersion + BASE_URL_ICON2 + idIcon +PNG;
    }

    public void getIconVersion(){


        DownloadTask downloadTask =  new DownloadTask(ICON_VERSIONS, new DownloadCallback<String>() {
            @Override
            public void updateFromDownload(String result) { // una vegada s'ha conectat i fet la descarga
                try {

                    JSONArray jsonArray = new JSONArray(result);

                    String extracto = jsonArray.optString(0);
                    iconVersion = extracto;



                    //iconVersion = json.optString("0");

                    //responseReceiver.onResponseReceived(json); //mos passem un JSON ARRAY pero encara hi ha que processarlo

                    //List<AllGameData> allGameData = processJSON(json);
                    //receiver.onResponseReceived(allGameData);



                }catch (JSONException e){
                    //responseReceiver.onErrorReceived(BAD_JSON_IN_SERVER_RESPONSE);
                    e.printStackTrace();
                }

            }

            @Override
            public NetworkInfo getActiveNetworkInfo() {
                return getNetworkInfo();
            }

            @Override
            public void onError(String msg) {

                //responseReceiver.onErrorReceived(msg);
            }


        });//aqui a continuacion faltarian las cabeceras (que yo no tengo)

        downloadTask.execute(); //aci se fica a fer lo de onpreexecute, doitbackground(on utilitza networkhelper) i onpostexecute

    }


    private NetworkInfo getNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;


    }
}
