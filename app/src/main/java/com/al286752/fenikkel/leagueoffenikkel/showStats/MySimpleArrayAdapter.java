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

        imageView.setImageResource(R.drawable.list_icon);


        return rowView;
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

                    }
                }
        );

        return cosa[0];
    }

}
