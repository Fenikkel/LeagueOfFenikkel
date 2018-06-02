package com.al286752.fenikkel.leagueoffenikkel.myProfile;

import java.io.File;

/**
 * Created by fenikkel on 17/05/18.
 */

public interface IMyProfileView {
    //aci posarem els metodos de la vista de MyProfile que se neccessita utilitzar en altres classes?

    void switchToShowStats(String nickName);

    void showError(String message);


    void setNicknameText(String text, long lvl, long sumId);

    //void setSummonerIcon(File icono);

    void setSummonerIcon(String urlIcon);
}
