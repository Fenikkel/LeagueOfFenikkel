package com.al286752.fenikkel.leagueoffenikkel.server;

/**
 * Created by jvilar on 4/11/16.
 */

public interface ResponseReceiver<T> {
    void onResponseReceived(T response);
    void onErrorReceived(String message);
}
