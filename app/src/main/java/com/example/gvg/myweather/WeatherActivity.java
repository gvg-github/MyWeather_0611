package com.example.gvg.myweather;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

/**
 * Created by GVG on 31.10.2017.
 */

public class WeatherActivity extends AppCompatActivity {

    private TextView text;
    private TextView textCity;
    private String[] weatherArray;
    private static final String SENDBACK_TEXT = "Get data for city: ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        init();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.RETURN_TEXT, SENDBACK_TEXT + textCity.getText().toString());
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    protected void init() {

        Button button = (Button) findViewById(R.id.button_sendMessage);
        button.setOnClickListener(new MyOnClickListener());

        textCity = (TextView) findViewById(R.id.choosenCity_text);
        text = (TextView) findViewById(R.id.weather_text);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            weatherArray = getResources().getStringArray(R.array.weather_list);
            text = (TextView) findViewById(R.id.weather_text);
            text.setText(weatherArray[extras.getInt(MainActivity.ARRAY_INDEX)]);
            String[] cityArray = getResources().getStringArray(R.array.city_list);
            textCity.setText(cityArray[extras.getInt(MainActivity.ARRAY_INDEX)]);
        }
    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.button_sendMessage) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                PackageManager pm = getPackageManager();
                List<ResolveInfo> list = pm.queryIntentActivities(intent, 0);
                if (list != null || list.size() > 0) {
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, text.getText().toString());
                    startActivity(intent);
                }
            }
        }
    }
}
