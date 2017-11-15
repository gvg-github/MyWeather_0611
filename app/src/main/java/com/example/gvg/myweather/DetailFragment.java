package com.example.gvg.myweather;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by GVG on 15.11.2017.
 */

public class DetailFragment extends Fragment {

    private TextView text;
    private TextView textCity;
    private String[] weatherArray;
    private String[] cityArray;
    private static final String SENDBACK_TEXT = "Get data for city: ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        textCity = (TextView) view.findViewById(R.id.choosenCity_text);
        text = (TextView) view.findViewById(R.id.weather_text);

//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {
//            cityArray = getResources().getStringArray(R.array.city_list);
//            textCity.setText(cityArray[extras.getInt(MainFragment.ARRAY_INDEX)]);
//
//            weatherArray = getResources().getStringArray(R.array.weather_list);
//            text = (TextView) findViewById(R.id.weather_text);
//            text.setText(weatherArray[extras.getInt(MainFragment.ARRAY_INDEX)]);
//            if (extras.getBoolean(MainFragment.CHECKBOX_MOONPHASE, false)) {
//                text.append(", text about moon phase");
//            }
//            if (extras.getBoolean(MainFragment.CHECKBOX_PRESSURE, false)) {
//                text.append(", text about pressure");
//            }
//            if (extras.getBoolean(MainFragment.CHECKBOX_TOMORROW, false)) {
//                text.append(", text about tomorrow");
//            }
//            if (extras.getBoolean(MainFragment.CHECKBOX_WEEK, false)) {
//                text.append(", text about week");
//            }
//        }

        Button button = (Button) view.findViewById(R.id.button_sendMessage);
//        button.setOnClickListener(new WeatherActivity.MyOnClickListener());
        return view;
    }

    public void setCity(int id){
        cityArray = getResources().getStringArray(R.array.city_list);
        textCity.setText(cityArray[id]);
    }
}
