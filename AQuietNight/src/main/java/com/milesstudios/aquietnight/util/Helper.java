package com.milesstudios.aquietnight.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.milesstudios.aquietnight.R;

public class Helper extends Activity {
    Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public Helper(Context context){
        this.context=context;
    }

    public void build(final String title, String message, final String r1, final int amount1, final String r2, final int amount2, final String output, Context context){
       AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
       final TextView log = (TextView) ((Activity)context).findViewById(R.id.log);
       final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
       final int r1_counter = sharedPref.getInt(r1, 0);
       final int r2_counter = sharedPref.getInt(r2, 0);
       alertDialog.setTitle("Build: " + title);
       alertDialog.setMessage(message);
       alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
           }
       });

       alertDialog.setPositiveButton("Build", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int which) {
               if (r1_counter >= amount1 && r2_counter >= amount2) {
                   int r1_counter_save = r1_counter -  amount1;
                   int r2_counter_save = r2_counter -  amount2;
                   SharedPreferences.Editor editor = sharedPref.edit();
                   editor.putInt(r1, r1_counter_save);
                   editor.putInt(r2, r2_counter_save);
                   editor.putBoolean(output, true);
                   editor.apply();
                   dialog.dismiss();
                   log.setText("You built a " + title + "! \n" + log.getText());
               } else {
                 dialog.dismiss();
                 log.setText(" You don't have enough resources! \n" + log.getText());
               }
           }
       });
       AlertDialog alert = alertDialog.create();
       alert.show();
    }

    public void buildBigger(final String title, String message, final String r1, final int amount1, final String r2, final int amount2,  final String r3, final int amount3, final String output, Context context){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        final TextView log = (TextView) ((Activity)context).findViewById(R.id.log);
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        final int r1_counter = sharedPref.getInt(r1, 0);
        final int r2_counter = sharedPref.getInt(r2, 0);
        final int r3_counter = sharedPref.getInt(r3, 0);

        alertDialog.setTitle("Build: " + title);
        alertDialog.setMessage(message);
        alertDialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.setPositiveButton("Build", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (r1_counter >= amount1 && r2_counter >= amount2 && r3_counter >= amount3) {
                    int r1_counter_save = r1_counter -  amount1;
                    int r2_counter_save = r2_counter -  amount2;
                    int r3_counter_save = r3_counter -  amount3;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(r1, r1_counter_save);
                    editor.putInt(r2, r2_counter_save);
                    editor.putInt(r3, r3_counter_save);
                    editor.putBoolean(output, true);
                    editor.apply();
                    dialog.dismiss();
                    log.setText("You built a "+ title + "! \n" + log.getText());
                } else {
                    dialog.dismiss();
                    log.setText(" You don't have enough resources! \n" + log.getText());
                }
            }
        });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void updateText() {
        final TextView storage = (TextView) ((Activity)context).findViewById(R.id.storage);
        final SharedPreferences sharedPref = context.getSharedPreferences("save-data", Context.MODE_PRIVATE);
        int wood_counter = sharedPref.getInt("wood", 0);
        int leaves_counter = sharedPref.getInt("leaves", 0);
        int stone_counter = sharedPref.getInt("stone", 0);
        int hard_wood_counter = sharedPref.getInt("hard_wood", 0);
        int dirty_water_counter = sharedPref.getInt("dirty_water", 0);
        int food_counter = sharedPref.getInt("food", 0);
        int cooked_food_counter = sharedPref.getInt("cooked_food", 0);
        int boiled_water_counter = sharedPref.getInt("boiled_water", 0);
        int apple_counter = sharedPref.getInt("apples", 0);
        int coin_counter = sharedPref.getInt("coins", 0);
        int copper_counter = sharedPref.getInt("copper", 0);
        int r_copper_counter = sharedPref.getInt("r_copper", 0);
        int coal_counter = sharedPref.getInt("coal", 0);

        storage.setText("\t Storage:");
        if (wood_counter >= 1) {
            storage.append("\n Wood: " + wood_counter);
        }
        if (leaves_counter >= 1) {
            storage.append("\n Leaves: " + leaves_counter);
        }
        if (stone_counter >= 1) {
            storage.append("\n Stone: " + stone_counter);
        }
        if (copper_counter >= 1) {
            storage.append("\n Raw Copper: " + copper_counter);
        }
        if (r_copper_counter >= 1) {
            storage.append("\n Refined Copper: " + r_copper_counter);
        }
        if (coal_counter >= 1) {
            storage.append("\n Coal: " + coal_counter);
        }
        if (dirty_water_counter >= 1) {
            storage.append("\n Dirty Water: " + dirty_water_counter + "/20L");
        }
        if (food_counter >= 1) {
            storage.append("\n Food: " + food_counter + "/12Lb");
        }
        if (cooked_food_counter >= 1) {
            storage.append("\n Cooked Food: " + cooked_food_counter + "/12Lb");
        }
        if (boiled_water_counter >= 1) {
            storage.append("\n Boiled Water: " + boiled_water_counter + "/20L");
        }
        if (apple_counter >= 1) {
            storage.append("\n Apples: " + apple_counter);
        }


        if (coin_counter >= 1) {
            storage.append("\n \n \n Coins: " + coin_counter);
        }


    }



    }

