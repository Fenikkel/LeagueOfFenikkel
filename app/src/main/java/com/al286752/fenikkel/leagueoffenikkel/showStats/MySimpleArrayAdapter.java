package com.al286752.fenikkel.leagueoffenikkel.showStats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.ChampionMaestries;
import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.model.IShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.model.ShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by fenikkel on 3/06/18.
 */

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    private JSONArray mediador;

    IShowStatsModel model;

    public MySimpleArrayAdapter(Context context, String[] values) {
        super(context, R.layout.list_maestries_layout, values);
        this.context = context;
        this.values = values;
        this.model = ShowStatsModel.getInstance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //aci modifica cada fila

        JSONArray champs = getChampions(); //CHAMPIONS TORNA NULL, MIRAR QUE PASSA

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_maestries_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.rowText);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.rowIcon);


        textView.setText(String.valueOf(champs));

        // Change the icon for Windows and iPhone


        String s = values[position]; //values sera una string de id champion (ho te de quan creem el mySimplearrayadapter)

        //Map mapa = (Map<String,String>) champs;


        //textView.setText(mapa.get("103")); //ACI FICAREM EL NOM I NO LA ID DEL CHAMP


/*
        try {
            JSONObject jsonObject = (JSONObject) champs.get(0);
            textView.setText(String.valueOf(jsonObject));
        } catch (JSONException e) {
            e.printStackTrace();
        }
*/
        //
        if (s.startsWith("103") || s.startsWith("iPhone")
                || s.startsWith("Solaris")) {
            imageView.setImageResource(R.drawable.teemo_profile);
        } else {
            imageView.setImageResource(R.drawable.stats_rammus);
        }

        return rowView;
    }

    public JSONArray getChampions(){



        model.getChampions(new ResponseReceiver<JSONArray>() {
                    @Override
                    public void onResponseReceived(JSONArray response) {

                        mediador = response;

                    }

                    @Override
                    public void onErrorReceived(String message) {

                        //view.showError(message);
                    }
                }
        );

        return mediador;
    }

}
