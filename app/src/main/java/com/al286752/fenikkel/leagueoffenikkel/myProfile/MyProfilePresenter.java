package com.al286752.fenikkel.leagueoffenikkel.myProfile;

/**
 * Created by fenikkel on 17/05/18.
 */

public class MyProfilePresenter {

    private IMyProfileView myProfileView;

    private String nickNamePresenter;

    MyProfilePresenter(IMyProfileView view){

        myProfileView = view;

    }

    public void onNickNameRequested(String nickname) {

        nickNamePresenter=nickname;
        //aci tenim el nickname que hem de buscar
        //myProfileView.switchToShowStats(nickname);
    }

    public String getNickName() {
        return nickNamePresenter;
    }
}
