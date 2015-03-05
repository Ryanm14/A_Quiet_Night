package com.milesstudios.aquietnight;

import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.milesstudios.aquietnight.reference.SharedPref;
import com.milesstudios.aquietnight.util.Helper;

/**
 * Created by Ryanm14 on 9/12/2014.
 */
public class Buildings extends ActivityGroup {
    Helper helper = new Helper(this);
    private Handler counterHandler = new Handler();
    private Runnable TextViewChanger = new Runnable() {
        public void run() {
            helper.updateText();
            runTimer();
        }
    };

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                // Do whatever you want, e.g. finish()
                Intent openMain = new Intent(Buildings.this, Cave.class);
                openMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(openMain);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        //smooth trans for crafting, fix layout


        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Saving
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().show();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        final SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buildings);
        TextView log = (TextView) findViewById(R.id.log);
        TextView storage = (TextView) findViewById(R.id.storage);
        Button tannery = (Button) findViewById(R.id.tannery);
        log.setTextSize(11);

        storage.setTextSize(15);

        final String log_text = sharedPref.getString("log_text", "");
        log.setText(log_text);
        helper.updateButtons("Buildings");
        helper.updateText();
        runTimer();
        tannery.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {
        Intent openMain = new Intent(Buildings.this, Cave.class);
        openMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(openMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void onPause() {
        TextView log = (TextView) findViewById(R.id.log);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("save-data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String log_text = log.getText().toString();
        editor.putString("log_text", log_text);
        finish();
        editor.apply();
        super.onPause();
    }

    //Buttons
    public void buttonFireplace(View v) {
        helper.build("Bonfire", "Wood: 30 \nStone: 20", "wood", 30, "stone", 20, "bonfire", this, "Buildings");
    }

    public void buttonWorkshop(View v) {
        helper.build("Workshop", "Wood: 10 \nStone: 10", "wood", 10, "stone", 10, "workshop", this, "Buildings");
    }

    public void buttonVillage(View v) {
        helper.build("Village Foundation", "Stone: 100 \nLeaves: 50\nCooked Food: 10", "stone", 100, "leaves", 50, "cooked_food", 10, SharedPref.VILLAGE, this, "Buildings");
        helper.updateWorkers();
    }

    public void buttonRebuildMine(View v) {
        helper.build("Rebuild Mine", "Wood: 20 \nStone: 15", "wood", 20, "stone", 15, "rebuildmine", this, "Buildings");
    }

    public void buttonTannery(View v) {
        helper.build("Tannery", "Wood: 45 \nStone: 20\nLeaves:20", "wood", 45, "stone", 20, "leaves", 20, "tannery", this, "Buildings");
    }

    public void buttonSmithery(View v) {
        helper.build("Smithery", "Wood: 15 \nStone: 25", "wood", 15, "stone", 25, "smithery", this, "Buildings");
    }

    public void buttonStorageShed(View v) {
        helper.build("Storage Shed", "Wood: 150 \nStone: 100\nLeaves: 75", "wood", 150, "stone", 100, "leaves", 75, "storage_shed", this, "Buildings");
    }

    public void runTimer() {
        counterHandler.postDelayed(TextViewChanger, 5000);
    }

}




