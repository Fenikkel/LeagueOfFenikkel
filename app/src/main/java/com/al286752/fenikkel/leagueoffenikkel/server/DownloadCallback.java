package com.al286752.fenikkel.leagueoffenikkel.server;

import android.net.NetworkInfo;



public interface DownloadCallback<T> {
    void updateFromDownload(T result);
    NetworkInfo getActiveNetworkInfo();
    void onError(String msg);
}
