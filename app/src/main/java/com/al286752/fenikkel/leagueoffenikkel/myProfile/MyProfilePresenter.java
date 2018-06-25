package com.al286752.fenikkel.leagueoffenikkel.myProfile;

import com.al286752.fenikkel.leagueoffenikkel.StaticData;
import com.al286752.fenikkel.leagueoffenikkel.model.IMyProfileModel;
import com.al286752.fenikkel.leagueoffenikkel.server.ILeagueServer;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

/**
 * Created by fenikkel on 17/05/18.
 */

public class MyProfilePresenter  {

    private IMyProfileView myProfileView;
    private IMyProfileModel myProfileModel;

    private long idSummoner;
    private long summonerLevel;

    private String nickNamePresenter;
    private int iconIDPresenter;

    MyProfilePresenter(IMyProfileView view, IMyProfileModel model){

        myProfileView = view;
        myProfileModel = model;

    }

    public void onNickNameRequested(String nickname) {

        myProfileModel.findSummoner(nickname,new ResponseReceiver<JSONObject>() {
                    @Override
                    public void onResponseReceived(JSONObject response) {


                        processJSONData(response);

                    }

                    @Override
                    public void onErrorReceived(String message) {

                        myProfileView.showError(message);
                    }
                }
        );

    }

    private void processJSONData(JSONObject response) {

        nickNamePresenter = response.optString("name");
        iconIDPresenter =response.optInt("profileIconId");
        idSummoner = response.optLong("id");
        summonerLevel = response.optLong("summonerLevel");



        myProfileView.setNicknameText(nickNamePresenter,summonerLevel,idSummoner);

        String urlIcon = myProfileModel.getUrlIcon(iconIDPresenter);

        myProfileView.setSummonerIcon(urlIcon);

        myProfileModel.insertCurrentSummoner((int)idSummoner,StaticData.getVersion(), "califragilistico");//


    }

    public String getNickName() {
        return nickNamePresenter;
    }
}
