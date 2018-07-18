package com.al286752.fenikkel.leagueoffenikkel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

public class ElementComparator implements Comparator<ArrayList> {

    private String tipo;

    public ElementComparator(String elemento) {

        //aci faltaria un espai per a la dificultat

        if(elemento.equals("AD")){

            tipo="attack";

        }
        else if(elemento.equals("AP")){
            tipo= "magic";
        }

        else{//si tipo es "mixto" o es defense
            tipo=elemento;
        }

    }

    //Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.

    @Override
    public int compare(ArrayList principal, ArrayList secundario) {



        JSONObject prin = (JSONObject) principal.get(0);
        JSONObject sec = (JSONObject) secundario.get(0);


        JSONObject pInfo = prin.optJSONObject("info");
        JSONObject sInfo = sec.optJSONObject("info");


        if(tipo.equals("MIX")){


            int pAttack = pInfo.optInt("attack");
            int sAttack = sInfo.optInt("attack");
            int pMagic = pInfo.optInt("magic");
            int sMagic = sInfo.optInt("magic");

            //es attack per a reciclar codic
            pAttack = pMagic+pAttack;
            sAttack = sMagic +sAttack;


            //CODIC RECICLAT
            if(pAttack>sAttack){ //si el primer es mes fort que el segon
                return 1;
            }
            else if(pAttack<sAttack){ //si el segon es mes fort que el primer
                return -1;
            }
            else{ // si son igual deuria ficar algun factor de fer les coses random si empaten


                int random = (int )(Math.random() * 2 + 1);

                if(random == 2){
                    return -1;
                }
                else{
                    return 1;
                }

                /*
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
                */
            }
            //FI CODI RECICLAT



        }else{
            int pAttack = pInfo.optInt(tipo);//"attack"
            int sAttack = sInfo.optInt(tipo);//"attack"

            if(pAttack>sAttack){ //si el primer es mes fort que el segon
                return 1;
            }
            else if(pAttack<sAttack){ //si el segon es mes fort que el primer
                return -1;
            }
            else{ // si son igual deuria ficar algun factor de fer les coses random si empaten

                int random = (int )(Math.random() * 2 + 1);

                if(random == 2){
                    return -1;
                }
                else{
                    return 1;
                }

                /*
                int pDefense = pInfo.optInt("defense");
                int sDefense = sInfo.optInt("defense");

                if(pDefense>sDefense){
                    return 1;
                }
                else if(pDefense<sDefense){
                    return -1;
                }
                else{ //ACI PODRIA SEGUIR EN QUI TE MES MAGIA O MENOS DIFICULTAT PERO GL
                    int random = (int )(Math.random() * 2 + 1);

                    if(random == 2){
                        return -1;
                    }
                    else{
                        return 1;
                    }
                }*/
            }
        }


    }
}
