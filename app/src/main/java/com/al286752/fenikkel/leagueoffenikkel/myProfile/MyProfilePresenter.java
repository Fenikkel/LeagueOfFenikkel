package com.al286752.fenikkel.leagueoffenikkel.myProfile;

/**
 * Created by fenikkel on 17/05/18.
 */

public class MyProfilePresenter {

    IMyProfileView myProfileView;

    MyProfilePresenter(IMyProfileView view){

        myProfileView = view;

    }

    public void onNickNameRequested(String nickname) {
        //aci tenim el nickname que hem de buscar
    }
}
