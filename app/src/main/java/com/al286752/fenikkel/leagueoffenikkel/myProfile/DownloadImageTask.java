package com.al286752.fenikkel.leagueoffenikkel.myProfile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.al286752.fenikkel.leagueoffenikkel.StaticData;

import java.io.InputStream;

/**
 * Created by fenikkel on 30/05/18.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {

        StaticData.setSummonerIcon(result); //aço ho crida ChampMastery com MyProfileActivity SOLUCIO: descarregar la image cada vega enves de guardarla

        bmImage.setImageBitmap(result);
    }
}