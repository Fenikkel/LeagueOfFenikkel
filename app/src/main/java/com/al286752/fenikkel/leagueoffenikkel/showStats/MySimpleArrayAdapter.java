package com.al286752.fenikkel.leagueoffenikkel.showStats;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.al286752.fenikkel.leagueoffenikkel.R;
import com.al286752.fenikkel.leagueoffenikkel.StaticData;
import com.al286752.fenikkel.leagueoffenikkel.model.IShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.model.ShowStatsModel;
import com.al286752.fenikkel.leagueoffenikkel.myProfile.DownloadImageTask;
import com.al286752.fenikkel.leagueoffenikkel.server.ResponseReceiver;

import org.json.JSONObject;

/**
 * Created by fenikkel on 3/06/18.
 */

public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] champName;
    private final String[] subNames;
    //private String urlIcon;

    //private JSONObject mediador;

    IShowStatsModel model;

    public MySimpleArrayAdapter(Context context, String[] values, String[] subNames) {
        super(context, R.layout.list_maestries_layout, values);
        this.context = context;
        this.champName = values;
        this.subNames = subNames;
        this.model = ShowStatsModel.getInstance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { //aci modifica cada fila

        //JSONObject champs = getChampions(); //CHAMPIONS TORNA NULL, MIRAR QUE PASSA

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_maestries_layout, parent, false);
        final TextView textView = (TextView) rowView.findViewById(R.id.rowText);
        final TextView title = (TextView) rowView.findViewById(R.id.title);

        final ImageView imageView = (ImageView) rowView.findViewById(R.id.rowIcon);

        String s = champName[position]; //champName sera una string de id champion (ho te de quan creem el mySimplearrayadapter)
        String t = subNames[position];
        textView.setText(s);
        title.setText(t);

        /*String champNameKey = StaticData.getChampionNameKeys().get(s);

        model.getChampionIcon(champNameKey, new ResponseReceiver<String>() {
            @Override
            public void onResponseReceived(String urlIcon) {


                new DownloadImageTask(imageView).execute(urlIcon);
            }

            @Override
            public void onErrorReceived(String message) {
                title.setText(message);
            }
        });

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Log.i("tag", "This'll run 300 milliseconds later");


                    }
                },
                300);

         */

        /*if (s.startsWith("Ahri") || s.startsWith("Master")
                || s.startsWith("Rammus")) {
            imageView.setImageResource(R.drawable.teemo_profile);
        } else {
            imageView.setImageResource(R.drawable.stats_rammus);
        }*/
        imageView.setImageResource(R.drawable.list_icon);


        return rowView;

        //String champName = model.getChampionName(s);

        /*model.getChampionName(s, new ResponseReceiver<JSONObject>() { DESCOMENTAR
            @Override
            public void onResponseReceived(JSONObject response) {
                textView.setText(response.optString("name"));
            }

            @Override
            public void onErrorReceived(String message) {

                textView.setText(message);

            }
        });*/

        //textView.setText(s);

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

    }

    public JSONObject getChampions(){

        final JSONObject[] cosa = new JSONObject[1];

        model.getChampions(new ResponseReceiver<JSONObject>() {
                    @Override
                    public void onResponseReceived(JSONObject response) {

                        cosa[0] = response;

                    }

                    @Override
                    public void onErrorReceived(String message) {

                        //view.showError(message);
                    }
                }
        );

        return cosa[0];
    }

}
