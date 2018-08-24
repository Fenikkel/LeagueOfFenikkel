package com.al286752.fenikkel.leagueoffenikkel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

public class SkillComparator implements Comparator<ArrayList> {

    private String tipo;
    private String dificult;


    public SkillComparator(String elemento, String dificultad) { //aci mos pasen el tipo

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

        if(dificultad.equals("SKILLED")){
            dificult="skilled";
        }
        else {
            dificult="easiest";
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

            int pDifficulty =pInfo.optInt("difficulty");
            int sDifficulty =sInfo.optInt("difficulty");

            //es attack per a reciclar codic
            pAttack = pMagic+pAttack;
            sAttack = sMagic +sAttack;



            if(sAttack>12) {


                if(pDifficulty>sDifficulty){ //si el primer es mes fort que el segon

                    if(dificult.equals("skilled")){
                        return 1;
                    }else{
                        return -1;
                    }

                }
                else if(pDifficulty<sDifficulty){ //si el segon es mes fort que el primer
                    if(dificult.equals("skilled")){
                        return -1;
                    }else{
                        return 1;
                    }
                }
                else{ // si son igual deuria ficar algun factor de fer les coses random si empaten


                    int random = (int )(Math.random() * 2 + 1);

                    if(random == 2){
                        return -1;
                    }
                    else{
                        return 1;
                    }

                }

            }


        }else{
            int pAttack = pInfo.optInt(tipo);//"attack"
            int sAttack = sInfo.optInt(tipo);//"attack"

            int aattack = sInfo.optInt("attack");

            int aaMagic = sInfo.optInt("magic");

            int aaDefense = sInfo.optInt("defense");



            int pDifficulty =pInfo.optInt("difficulty");
            int sDifficulty =sInfo.optInt("difficulty");



            if(sAttack>=aattack && sAttack>=aaMagic && sAttack>=aaDefense) {//
                if(pDifficulty>sDifficulty){ //si el primer es mes fort que el segon
                    if(dificult.equals("skilled")){
                        return 1;
                    }else{
                        return -1;
                    }
                }
                else if(pDifficulty<sDifficulty){ //si el segon es mes fort que el primer
                    if(dificult.equals("skilled")){
                        return -1;
                    }else{
                        return 1;
                    }
                }
                else{ // si son igual deuria ficar algun factor de fer les coses random si empaten


                    int random = (int )(Math.random() * 2 + 1);

                    if(random == 2){
                        return -1;
                    }
                    else{
                        return 1;
                    }

                }
            }



        }


        return 0;
    }


}
