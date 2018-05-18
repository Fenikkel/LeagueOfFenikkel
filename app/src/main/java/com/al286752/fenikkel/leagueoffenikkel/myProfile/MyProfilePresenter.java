package com.al286752.fenikkel.leagueoffenikkel.myProfile;

import com.al286752.fenikkel.leagueoffenikkel.model.IMyProfileModel;
import com.al286752.fenikkel.leagueoffenikkel.server.ILeagueServer;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by fenikkel on 17/05/18.
 */

public class MyProfilePresenter {

    private IMyProfileView myProfileView;

    private String nickNamePresenter;

    private IMyProfileModel myProfileModel;

    MyProfilePresenter(IMyProfileView view, IMyProfileModel model){

        myProfileView = view;
        myProfileModel = model;

    }

    public void onNickNameRequested(String nickname) {

        //nickNamePresenter=nickname;
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
        //aci tenim el nickname que hem de buscar
        //myProfileView.switchToShowStats(nickname);
    }

    private void processJSONData(JSONObject response) {
        //aci ficarem tot a la vista MyProfil
        nickNamePresenter = response.optString("name");

        myProfileView.setNicknameText(nickNamePresenter);

    }

    public String getNickName() {
        return nickNamePresenter;
    }
}
