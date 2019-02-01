package com.al286752.fenikkel.leagueoffenikkel.localDataBase;

import java.util.ArrayList;

/**
 * Created by fenikkel on 23/06/18.
 */

public interface IDataBase {
    void insertCurrentSummoner(String summonerID, String lastVersion, String region, String regionName);
    ArrayList<String> getCurrentSummoner();
    boolean deleteCurrentSummoner();
}
