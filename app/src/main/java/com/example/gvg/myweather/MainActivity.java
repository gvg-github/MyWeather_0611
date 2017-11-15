package com.example.gvg.myweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GVG on 29.10.2017.
 */

public class MainActivity extends AppCompatActivity implements MainFragment.WeatherListListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = new MainFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, mainFragment);
        transaction.commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.myweather_menu, menu);
//        return true;
//    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
//        SharedPreferences.Editor editor = myPreferences.edit();
////        editor.putInt(MY_START_CITY, spinner.getSelectedItemPosition());
//        editor.putBoolean(CHECKBOX_PRESSURE, boxPressure.isChecked());
//        editor.putBoolean(CHECKBOX_MOONPHASE, boxMoon.isChecked());
//
//        editor.putBoolean(CHECKBOX_TOMORROW, boxTomorrow.isChecked());
//        editor.putBoolean(CHECKBOX_WEEK, boxWeek.isChecked());
//
//        editor.apply();
        Log.i(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onListItemClick(int id) {

        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            DetailFragment detailFragment = new DetailFragment();
            detailFragment.setCity(id);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, detailFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            Intent intent = new Intent(this, WeatherActivity.class);
            intent.putExtra(WeatherActivity.CITY_TEXT, id);
            startActivity(intent);
        }
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        outState.putBoolean(CHECKBOX_PRESSURE, boxPressure.isChecked());
//        outState.putBoolean(CHECKBOX_MOONPHASE, boxMoon.isChecked());
//        outState.putBoolean(CHECKBOX_TOMORROW, boxTomorrow.isChecked());
//        outState.putBoolean(CHECKBOX_WEEK, boxWeek.isChecked());
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        boxPressure.setChecked(savedInstanceState.getBoolean(CHECKBOX_PRESSURE));
//        boxMoon.setChecked(savedInstanceState.getBoolean(CHECKBOX_MOONPHASE));
//        boxTomorrow.setChecked(savedInstanceState.getBoolean(CHECKBOX_TOMORROW));
//        boxWeek.setChecked(savedInstanceState.getBoolean(CHECKBOX_WEEK));
//    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

}