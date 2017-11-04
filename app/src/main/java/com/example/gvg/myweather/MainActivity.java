package com.example.gvg.myweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.myweather_menu, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putInt(MY_START_CITY, spinner.getSelectedItemPosition());
        editor.apply();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    protected void init() {

        spinner = (Spinner) findViewById(R.id.select_list);
        myPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        if (myPreferences.contains(MY_START_CITY)) {
            spinner.setSelection(myPreferences.getInt(MY_START_CITY, 0));
        }
        Button button = (Button) findViewById(R.id.select_button);
        button.setOnClickListener(new MyOnClickListener());

        returnText = (TextView) findViewById(R.id.sendback_text);
        returnText.setText("");

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


