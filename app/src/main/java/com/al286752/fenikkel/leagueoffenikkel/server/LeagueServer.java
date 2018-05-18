package com.al286752.fenikkel.leagueoffenikkel.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by fenikkel on 17/05/18.
 */

public class LeagueServer implements ILeagueServer {

    //private static final int MAX_RESULTS = 20;
    private static final String BAD_JSON_IN_SERVER_RESPONSE = "Bad JSON in server response";

    private static final String BASE_URL = "https://euw1.api.riotgames.com";
    private static final String API_KEY = "?api_key=RGAPI-45b77d34-0635-47d4-90ef-3e8336fc89df";
    private static final String SEARCH_SUMMONER = "/lol/summoner/v3/summoners/by-name/"; //aci falta sumarli el nickname + la API_KEY

    private Context context;


    public LeagueServer(Context context){ //de mo ment no recorde pa que es EL FAIG PUBLIC
        this.context = context;

    }

    @Override
    public void findSummoner(String nickName, final ResponseReceiver<JSONArray> responseReceiver) { //este es llamado desde el modelo que sera llamado desde el presenter

        String nickEncoded;
        try {
            nickEncoded=  URLEncoder.encode(nickName,"UTF-8");

        }catch (Exception e){
            nickEncoded = nickName;
        }

        String urlPetition =BASE_URL+SEARCH_SUMMONER+nickEncoded+API_KEY;


        DownloadTask downloadTask =  new DownloadTask(urlPetition, new DownloadCallback<String>() {
            @Override
            public void updateFromDownload(String result) {
                try {
                    JSONArray json = new JSONArray(result);

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

        downloadTask.execute();

    }


    private NetworkInfo getNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;


    }
}
