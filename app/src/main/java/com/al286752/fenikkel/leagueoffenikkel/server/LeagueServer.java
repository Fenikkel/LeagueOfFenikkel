package com.al286752.fenikkel.leagueoffenikkel.server;

import android.content.Context;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URLEncoder;

/**
 * Created by fenikkel on 17/05/18.
 */

public class LeagueServer implements ILeagueServer {

    //private static final int MAX_RESULTS = 20;

    private static final String BASE_URL = "https://euw1.api.riotgames.com";
    private static final String API_KEY = "?api_key=RGAPI-ab8dd7be-f9ec-4bd1-b15a-b70ccf0fea76";
    private static final String SEARCH_SUMMONER = "/lol/summoner/v3/summoners/by-name/"; //aci falta sumarli el nickname + la API_KEY

    private Context context;


    LeagueServer(Context context){ //de mo ment no recorde pa que es
        this.context = context;

    }

    @Override
    public void findSummoner(String nickName) {

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

                    //List<AllGameData> allGameData = processJSON(json);
                    //receiver.onResponseReceived(allGameData);



                }catch (JSONException e){
                    //receiver.onErrorReceived(BAD_JSON_IN_SERVER_RESPONSE);
                    e.printStackTrace();
                }

            }

            @Override
            public NetworkInfo getActiveNetworkInfo() {
                return null;//getNetworkInfo();
            }

            @Override
            public void onError(String msg) {

                //receiver.onErrorReceived(msg);
            }


        });//aqui a continuacion faltarian las cabeceras
        downloadTask.execute();




    }
}
