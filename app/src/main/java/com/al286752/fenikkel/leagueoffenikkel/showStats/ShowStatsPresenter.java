package com.al286752.fenikkel.leagueoffenikkel.showStats;

import android.content.Context;

import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.model.IShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.model.ShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by fenikkel on 2/06/18.
 */

public class ShowStatsPresenter {

    IShowStatsModel model;
    IShowStatsActivity view;
    Context context;

    public ShowStatsPresenter(IShowStatsActivity vista, Context context){

        view = vista;
        model = ShowStatsModel.getInstance(context); //si ja hi ha un creat te tornara la instancia sino creara un model nou
        this.context = context;

    }

    public void findMaestries (String idSummoner, final ResponseReceiver<ArrayList<ChampionMaestries>> receiver){
        model.findMaestries(idSummoner,new ResponseReceiver<ArrayList<ChampionMaestries>>() {
                    @Override
                    public void onResponseReceived(ArrayList<ChampionMaestries> response) {

                        //aci ho passem a la vista per a que ho mostre
                        receiver.onResponseReceived(response);

                    }

                    @Override
                    public void onErrorReceived(String message) {

                        //view.showError(message);
                    }
                }
        );
    }

    /*public void getChampionName(String champId, ResponseReceiver<JSONObject> receiver) {

        model.getChampionName(champId, receiver);


    }*/

    public void getChampions(ResponseReceiver<JSONObject> receiver) {
        model.getChampions(receiver);
    }
}
