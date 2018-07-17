package com.al286752.fenikkel.leagueoffenikkel;

import org.json.JSONObject;

import java.util.Comparator;

public class AttackComparator implements Comparator<JSONObject> {

    //Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.

    @Override
    public int compare(JSONObject principal, JSONObject secundario) {

        JSONObject pInfo = principal.optJSONObject("info");
        JSONObject sInfo = secundario.optJSONObject("info");

        int pAttack = pInfo.optInt("attack");
        int sAttack = sInfo.optInt("attack");

        if(pAttack>sAttack){ //si el primer es mes fort que el segon
            return 1;
        }
        else if(pAttack<sAttack){ //si el segon es mes fort que el primer
            return -1;
        }
        else{
            int pDefense = pInfo.optInt("defense");
            int sDefense = sInfo.optInt("defense");

            if(pDefense>sDefense){
                return 1;
            }
            else if(pDefense<sDefense){
                return -1;
            }
            else{ //ACI PODRIA SEGUIR EN QUI TE MES MAGIA O MENOS DIFICULTAT PERO GL
                return 0;
            }
        }

    }
}
