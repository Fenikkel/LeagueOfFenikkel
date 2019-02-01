package com.al286752.fenikkel.leagueoffenikkel.myProfile;

import java.io.File;

/**
 * Created by fenikkel on 17/05/18.
 */

public interface IMyProfileView {

    void switchToShowStats(String nickName);

    void showError(String message);

    void setNicknameText(String text, long lvl, String sumId);

    void setSummonerIcon(String urlIcon);
}
