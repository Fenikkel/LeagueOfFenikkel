package com.al286752.fenikkel.leagueoffenikkel.model;

import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by fenikkel on 17/05/18.
 */

public interface IMyProfileModel {

    void findSummoner(String nickname, ResponseReceiver<JSONObject> reciever);

    void findIcon(int profileIconId, ResponseReceiver<File> responseReceiver);
}
