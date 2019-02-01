package com.al286752.fenikkel.leagueoffenikkel.server;


public interface ResponseReceiver<T> {
    void onResponseReceived(T response);
    void onErrorReceived(String message);
}
