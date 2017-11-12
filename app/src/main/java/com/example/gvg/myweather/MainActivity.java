package com.example.gvg.myweather;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    public static final String MY_PREFERENCES = "MyWeatherPreferences";
    //    public static final String MY_START_CITY = "City";
    private SharedPreferences myPreferences;

    public static final String ARRAY_INDEX = "aIndex";
    public static final int RETURN_NUMBER = 1;
    public static final String RETURN_TEXT = "return_text";
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String CHECKBOX_PRESSURE = "checkbox_pressure";
    public static final String CHECKBOX_MOONPHASE = "checkbox_moonphase";
    public static final String CHECKBOX_TOMORROW = "checkbox_tomorrow";
    public static final String CHECKBOX_WEEK = "checkbox_week";
    private CheckBox boxMoon;
    private CheckBox boxPressure;
    private CheckBox boxTomorrow;
    private CheckBox boxWeek;

    private RecyclerView recyclerView;
    List<CityList> cities = new ArrayList<>();
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "savedInstanceState​ ​" + savedInstanceState);
        init();
        setCityData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new DataAdapter(this, cities);
        recyclerView.setAdapter(adapter);
    }

    private void setCityData() {
        String[] list = getResources().getStringArray(R.array.city_list);
        for (int i = 0; i < list.length; i++) {
            cities.add(new CityList(list[i]));
        }
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
//        editor.putInt(MY_START_CITY, spinner.getSelectedItemPosition());
        editor.putBoolean(CHECKBOX_PRESSURE, boxPressure.isChecked());
        editor.putBoolean(CHECKBOX_MOONPHASE, boxMoon.isChecked());

        editor.putBoolean(CHECKBOX_TOMORROW, boxTomorrow.isChecked());
        editor.putBoolean(CHECKBOX_WEEK, boxWeek.isChecked());

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
        outState.putBoolean(CHECKBOX_PRESSURE, boxPressure.isChecked());
        outState.putBoolean(CHECKBOX_MOONPHASE, boxMoon.isChecked());
        outState.putBoolean(CHECKBOX_TOMORROW, boxTomorrow.isChecked());
        outState.putBoolean(CHECKBOX_WEEK, boxWeek.isChecked());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boxPressure.setChecked(savedInstanceState.getBoolean(CHECKBOX_PRESSURE));
        boxMoon.setChecked(savedInstanceState.getBoolean(CHECKBOX_MOONPHASE));
        boxTomorrow.setChecked(savedInstanceState.getBoolean(CHECKBOX_TOMORROW));
        boxWeek.setChecked(savedInstanceState.getBoolean(CHECKBOX_WEEK));
    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }

    protected void init() {
        boxMoon = (CheckBox) findViewById(R.id.check_moonphase);
        boxPressure = (CheckBox) findViewById(R.id.check_pressure);
        boxTomorrow = (CheckBox) findViewById(R.id.check_tomorrow);
        boxWeek = (CheckBox) findViewById(R.id.check_week);

        myPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
//        if (myPreferences.contains(MY_START_CITY)) {
//            spinner.setSelection(myPreferences.getInt(MY_START_CITY, 0));
//        }
        if (myPreferences.contains(CHECKBOX_PRESSURE)) {
            boxPressure.setChecked(myPreferences.getBoolean(CHECKBOX_PRESSURE, false));
        }
        if (myPreferences.contains(CHECKBOX_MOONPHASE)) {
            boxMoon.setChecked(myPreferences.getBoolean(CHECKBOX_MOONPHASE, false));
        }
        if (myPreferences.contains(CHECKBOX_TOMORROW)) {
            boxTomorrow.setChecked(myPreferences.getBoolean(CHECKBOX_TOMORROW, false));
        }
        if (myPreferences.contains(CHECKBOX_WEEK)) {
            boxWeek.setChecked(myPreferences.getBoolean(CHECKBOX_WEEK, false));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RETURN_NUMBER) {
            if (resultCode == RESULT_OK) {
                String sendBackText = data.getStringExtra(RETURN_TEXT);
                Toast toast = Toast.makeText(getApplicationContext(), sendBackText, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
        }
    }

    private class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

        private LayoutInflater inflater;
        private List<CityList> cities;

        DataAdapter(Context context, List<CityList> cities) {
            this.cities = cities;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.list_city, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
            CityList city = cities.get(position);
            holder.nameView.setText(city.getName());
        }

        @Override
        public int getItemCount() {
            return cities.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            final TextView nameView;

            public ViewHolder(View view) {
                super(view);
                this.nameView = itemView.findViewById(R.id.city_element);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra(ARRAY_INDEX, getLayoutPosition());
                intent.putExtra(CHECKBOX_PRESSURE, boxPressure.isChecked());
                intent.putExtra(CHECKBOX_MOONPHASE, boxMoon.isChecked());
                intent.putExtra(CHECKBOX_TOMORROW, boxTomorrow.isChecked());
                intent.putExtra(CHECKBOX_WEEK, boxWeek.isChecked());
                startActivityForResult(intent, RETURN_NUMBER);
            }
        }
    }
}