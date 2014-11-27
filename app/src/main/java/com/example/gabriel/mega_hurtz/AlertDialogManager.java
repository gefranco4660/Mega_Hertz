package com.example.gabriel.mega_hurtz;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Carl on 11/26/2014.
 */
public class AlertDialogManager {
    /*
    Function to display simple Alert Dialog
     */
    public void showAlertDialog(Context context, String title, String message, Boolean status){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        //Setting Dialog Title
        alertDialog.setTitle(title);

        //Setting Dialog Message
        alertDialog.setMessage(message);

        if(status != null){
            //Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

            //Setting OK button
            alertDialog.setButton("OK", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){

                }
            });

            //showing alert message
            alertDialog.show();
        }
    }
}