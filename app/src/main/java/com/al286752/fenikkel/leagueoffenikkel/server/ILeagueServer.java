package com.al286752.fenikkel.leagueoffenikkel.server;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by fenikkel on 17/05/18.
 */

public interface ILeagueServer {

    void findSummoner(String nickName, ResponseReceiver<JSONObject> responseReceiver);

}
