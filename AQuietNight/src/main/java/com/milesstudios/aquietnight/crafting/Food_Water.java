package com.milesstudios.aquietnight.crafting;

import android.app.ActionBar;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.milesstudios.aquietnight.Cave;
import com.milesstudios.aquietnight.R;
import com.milesstudios.aquietnight.util.Helper;

/**
 * Created by Ryan on 9/27/2014.
 */
public class Food_Water extends ActivityGroup {
    Helper helper = new Helper(this);
    TextView log;
    private android.os.Handler counterHandler = new android.os.Handler();
    private Runnable TextViewChanger = new Runnable() {
        public void run() {
            helper.updateText();
            runTimer();
        }
    };

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent openMain = new Intent(Food_Water.this, Cave.class);
                openMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(openMain);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //Saving
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.food_water, R.layout.spinner_item);
        ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {
            String[] items = getResources().getStringArray(R.array.food_water);

            @Override
            public boolean onNavigationItemSelected(int position, long id) {
                if (items[position].equals("Food and Water")) {
                }
                if (items[position].equals("Weapons and Armor")) {
                    Intent openWepaons_Armor = new Intent(Food_Water.this, Weapons_Armor.class);
                    openWepaons_Armor.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(openWepaons_Armor);
                    overridePendingTransition(0, 0);
                }
                if (items[position].equals("Tools")) {
                    Intent openTools = new Intent(Food_Water.this, Tools.class);
                    openTools.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(openTools);
                    overridePendingTransition(0, 0);
                }
                Log.d("NavigationItemSelected", items[position]);
                return true;

            }

        };

// Action Bar
        ActionBar actions = getActionBar();
        actions.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actions.setDisplayShowTitleEnabled(false);
        actions.setListNavigationCallbacks(adapter, callback);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.crafting_food_water);
        log = (TextView) findViewById(R.id.log);
        TextView storage = (TextView) findViewById(R.id.storage);
        log.setTextSize(11);
        storage.setTextSize(15);
        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);
        saveChoice();
        helper.updateButtons("Food_Water");
        helper.updateText();
    }

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Food_Water.this, Cave.class);
        openMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public void saveChoice() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        int food_water = 1;
        int tools = 0;
        int weapons_armor = 0;
        editor.putInt("food_water", food_water);
        editor.putInt("tools", tools);
        editor.putInt("weapons_armor", weapons_armor);
        editor.apply();
    }

    @Override
    public void onPause() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String log_text = log.getText().toString();
        editor.putString("log_text", log_text);
        editor.apply();
        finish();
        super.onPause();
    }

    public void buttonBoilWater(View v) {
        helper.fandW("dirty_water", 1, "boiled_water", 1);
    }

    public void buttonCookFood(View v) {
        helper.fandW2("raw_food", 1, "cooked_food", 1);
    }

    public void buttonLeafCanteen(View v) {
        helper.build("Leaf Canteen", "Leaves: 10", "leaves", 10, "leaf_canteen", this, "Food_Water");
    }

    public void runTimer() {
        counterHandler.postDelayed(TextViewChanger, 5000);
    }


}







