package com.example.gvg.myweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by GVG on 29.10.2017.
 */

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    public static final String MY_PREFERENCES = "MyWeatherPreferences";
    public static final String MY_START_CITY = "City";
    private SharedPreferences myPreferences;
    private TextView returnText;

    public static final String ARRAY_INDEX = "aIndex";
    public static final int RETURN_NUMBER = 1;
    public static final String RETURN_TEXT = "return_text";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "savedInstanceState​ ​" + savedInstanceState);
        init();
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
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putInt(MY_START_CITY, spinner.getSelectedItemPosition());
        editor.apply();
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
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(RETURN_TEXT, returnText.getText().toString());
        outState.putInt(MY_START_CITY, spinner.getSelectedItemPosition());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        spinner.setSelection(savedInstanceState.getInt(MY_START_CITY));
        returnText.setText(savedInstanceState.getString(RETURN_TEXT));
    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

    protected void init() {

        spinner = (Spinner) findViewById(R.id.select_list);
        returnText = (TextView) findViewById(R.id.sendback_text);

        myPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        if (myPreferences.contains(MY_START_CITY)) {
            spinner.setSelection(myPreferences.getInt(MY_START_CITY, 0));
        }
        returnText.setText("");

        Button button = (Button) findViewById(R.id.select_button);
        button.setOnClickListener(new MyOnClickListener());
    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.select_button) {
                returnText.setText("");
                int idSelect = spinner.getSelectedItemPosition();
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra(ARRAY_INDEX, idSelect);
                startActivityForResult(intent, RETURN_NUMBER);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RETURN_NUMBER) {
            if (resultCode == RESULT_OK) {
                String sendBackText = data.getStringExtra(RETURN_TEXT);
                returnText.setText(sendBackText);
            }
        }
    }
}


