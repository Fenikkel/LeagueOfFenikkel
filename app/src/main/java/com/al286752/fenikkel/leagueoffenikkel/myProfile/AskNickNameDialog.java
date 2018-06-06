package com.al286752.fenikkel.leagueoffenikkel.myProfile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.al286752.fenikkel.leagueoffenikkel.R;

/**
 * Created by fenikkel on 17/05/18.
 */

public class AskNickNameDialog extends DialogFragment{

    public interface INickNameListener{ //aixina es com li passarem la resposta a MyProfileActivity
        void onNickNameInput(String nickname);
    }

    EditText nickNameEditor;
    INickNameListener nickNameListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Activity context = getActivity();

        View view = context.getLayoutInflater().inflate(R.layout.lookfor_nickname_layout,null);
        nickNameEditor = view.findViewById(R.id.nickNameWritter);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(view)
                .setPositiveButton("Search", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(nickNameListener != null){
                            nickNameListener.onNickNameInput(nickNameEditor.getText().toString()); //aço se suposa que va a parar al MyProfileActivity i al seu metodo .onNickNameInput
                        }
                    }
                })
                .setNegativeButton("Cancel",null);

        return builder.create();


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        nickNameListener = (INickNameListener) context;
    }

}
