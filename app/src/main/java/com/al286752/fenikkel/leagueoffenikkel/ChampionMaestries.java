package com.al286752.fenikkel.leagueoffenikkel;

/**
 * Created by fenikkel on 2/06/18.
 */

/*
        chestGranted 	boolean 	Is chest granted for this champion or not in current season.
        championLevel 	int 	Champion level for specified player and champion combination.
        championPoints 	int 	Total number of champion points for this player and champion combination - they are used to determine championLevel.
        championId 	long 	Champion ID for this entry.
        playerId 	long 	Player ID for this entry.
        championPointsUntilNextLevel 	long 	Number of points needed to achieve next level. Zero if player reached maximum champion level for this champion.
        tokensEarned 	int 	The token earned for this champion to levelup.
        championPointsSinceLastLevel 	long 	Number of points earned since current level has been achieved.
        lastPlayTime 	long 	Last time this champion was played by this player - in Unix milliseconds time format.

*/
public class ChampionMaestries {

    private long championId;
    private int championLevel;
    private int championPoints;
    private int tokensEarned;
    private long championPointsUntilNextLevel;
    private boolean chestGranted;
    private long championPointsSinceLastLevel;
    private long lastPlayTime;

    public void setChampionPointsSinceLastLevel(long championPointsSinceLastLevel) {
        this.championPointsSinceLastLevel = championPointsSinceLastLevel;
    }

    public void setLastPlayTime(long lastPlayTime) {
        this.lastPlayTime = lastPlayTime;
    }

    public long getChampionPointsSinceLastLevel() {
        return championPointsSinceLastLevel;
    }

    public long getLastPlayTime() {
        return lastPlayTime;
    }

    public void setChampionId(long championId) {
        this.championId = championId;
    }

    public void setChampionLevel(int championLevel) {
        this.championLevel = championLevel;
    }

    public void setChampionPoints(int championPoints) {
        this.championPoints = championPoints;
    }

    public void setTokensEarned(int tokensEarned) {
        this.tokensEarned = tokensEarned;
    }

    public void setChampionPointsUntilNextLevel(long championPointsUntilNextLevel) {
        this.championPointsUntilNextLevel = championPointsUntilNextLevel;
    }

    public void setChestGranted(boolean chestGranted) {
        this.chestGranted = chestGranted;
    }

    public long getChampionId() {
        return championId;
    }

    public int getChampionLevel() {
        return championLevel;
    }

    public int getChampionPoints() {
        return championPoints;
    }

    public int getTokensEarned() {
        return tokensEarned;
    }

    public long getChampionPointsUntilNextLevel() {
        return championPointsUntilNextLevel;
    }

    public boolean isChestGranted() {
        return chestGranted;
    }
}
