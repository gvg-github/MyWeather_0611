package com.example.gvg.myweather;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

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
//    private DataAdapter adapter;

    private WeatherListListener mainActivity;

    public MainFragment() {
        // Required empty public constructor
    }

    interface WeatherListListener {
        void onListItemClick(int id);
    }

    @Override
    public void onAttach(Context context) {
        mainActivity = (WeatherListListener) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        init(view);
        setCityData();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyDataAdapter());

        return view;
    }

    private void setCityData() {
        String[] list = getResources().getStringArray(R.array.city_list);
        for (int i = 0; i < list.length; i++) {
            cities.add(new CityList(list[i]));
        }
    }

    protected void init(View view) {
        boxMoon = (CheckBox) view.findViewById(R.id.check_moonphase);
        boxPressure = (CheckBox) view.findViewById(R.id.check_pressure);
        boxTomorrow = (CheckBox) view.findViewById(R.id.check_tomorrow);
        boxWeek = (CheckBox) view.findViewById(R.id.check_week);

//        myPreferences = view.getContext().getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
////        if (myPreferences.contains(MY_START_CITY)) {
////            spinner.setSelection(myPreferences.getInt(MY_START_CITY, 0));
////        }
//        if (myPreferences.contains(CHECKBOX_PRESSURE)) {
//            boxPressure.setChecked(myPreferences.getBoolean(CHECKBOX_PRESSURE, false));
//        }
//        if (myPreferences.contains(CHECKBOX_MOONPHASE)) {
//            boxMoon.setChecked(myPreferences.getBoolean(CHECKBOX_MOONPHASE, false));
//        }
//        if (myPreferences.contains(CHECKBOX_TOMORROW)) {
//            boxTomorrow.setChecked(myPreferences.getBoolean(CHECKBOX_TOMORROW, false));
//        }
//        if (myPreferences.contains(CHECKBOX_WEEK)) {
//            boxWeek.setChecked(myPreferences.getBoolean(CHECKBOX_WEEK, false));
//        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
////        if (requestCode == RETURN_NUMBER) {
////            if (resultCode == RESULT_OK) {
////                String sendBackText = data.getStringExtra(RETURN_TEXT);
////                Toast toast = Toast.makeText(getApplicationContext(), sendBackText, Toast.LENGTH_LONG);
////                toast.setGravity(Gravity.BOTTOM, 0, 0);
////                toast.show();
////            }
////        }
//    }

    private void showWeatherScreen(int layoutPosition) {
        mainActivity.onListItemClick(layoutPosition);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView nameView;

//            public ViewHolder(View view) {
//                super(view);
//                this.nameView = itemView.findViewById(R.id.city_element);
//                itemView.setOnClickListener(this);
//            }

        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_city, parent, false));
            itemView.setOnClickListener(this);
            nameView = itemView.findViewById(R.id.city_element);
        }

        void bind(int position) {
            String category = cities.get(position).getName();
            nameView.setText(category);
        }

        @Override
        public void onClick(View v) {
            showWeatherScreen(this.getLayoutPosition());
//                Intent intent = new Intent(v.getContext(), WeatherActivity.class);
//                intent.putExtra(ARRAY_INDEX, getLayoutPosition());
//                intent.putExtra(CHECKBOX_PRESSURE, boxPressure.isChecked());
//                intent.putExtra(CHECKBOX_MOONPHASE, boxMoon.isChecked());
//                intent.putExtra(CHECKBOX_TOMORROW, boxTomorrow.isChecked());
//                intent.putExtra(CHECKBOX_WEEK, boxWeek.isChecked());
//                startActivityForResult(intent, RETURN_NUMBER);
        }
    }

    private class MyDataAdapter extends RecyclerView.Adapter<MyViewHolder> {

//        private LayoutInflater inflater;
//        private List<CityList> cities;
//
//        MyDataAdapter(Context context, List<CityList> cities) {
//            this.cities = cities;
//            this.inflater = LayoutInflater.from(context);
//        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = inflater.inflate(R.layout.list_city, parent, false);
//            return new MyViewHolder(view);
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new MyViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return cities.size();
        }
    }

}
