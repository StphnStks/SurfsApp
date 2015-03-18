package com.example.stephen.surfsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Stephen on 17/03/2015.
 */
public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.RecyclerViewHolderForecast> {

    private LayoutInflater layoutInflater;

    private ArrayList<Forecast> listForecast = new ArrayList<>();

    public ForecastAdapter(Context context) {

        layoutInflater = LayoutInflater.from(context);
    }

    public void setListForecast(ArrayList<Forecast> listForecast) {
        this.listForecast = listForecast;

        notifyItemRangeChanged(0, listForecast.size());
    }

    @Override
    public RecyclerViewHolderForecast onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.recycler_view_row_forecast, parent, false);

        RecyclerViewHolderForecast viewHolderForecast = new RecyclerViewHolderForecast(view);

        return viewHolderForecast;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderForecast holder, int position) {

        Forecast currentForecast = listForecast.get(position);

        holder.dayDate.setText(currentForecast.getDateTime());


//        holder.time.setText(currentForecast.getDateTime);


        holder.waveHeight.setText(currentForecast.getWaveHeightFt() + "ft @");

        holder.wavePeriod.setText(currentForecast.getWavePeriod() + "s");

        String waveDir = toString(currentForecast.getWaveDir());
        holder.waveDir.setText(waveDir);

        holder.windSpeed.setText(currentForecast.getWindSpeedMph());

        String windDir = toString(currentForecast.getWindDir());
        holder.windDir.setText(windDir);

        holder.gust.setText("(" + currentForecast.getGustMph() + ")mph");
    }

    // used to convert float values to String, to set text of text views
    private String toString(float floatToString) {
        return " " + floatToString;
    }

    @Override
    public int getItemCount() {
        return listForecast.size();
    }

    static class RecyclerViewHolderForecast extends  RecyclerView.ViewHolder {

        private TextView dayDate;

        private TextView time;

        private TextView waveHeight;
        private TextView wavePeriod;
        private TextView waveDir;
        private TextView windSpeed;
        private TextView windDir;
        private TextView gust;

        // construct the widgets from xml file, assigning to widget instances
        public RecyclerViewHolderForecast(View itemView) {
            super(itemView);

            dayDate = (TextView) itemView.findViewById(R.id.textViewDayDate);


            time = (TextView) itemView.findViewById(R.id.textViewTime);


            waveHeight = (TextView) itemView.findViewById(R.id.textViewWaveHeight);
            wavePeriod = (TextView) itemView.findViewById(R.id.textViewWavePeriod);
            waveDir = (TextView) itemView.findViewById(R.id.textViewWaveDir);
            windSpeed = (TextView) itemView.findViewById(R.id.textViewWindSpeed);
            windDir = (TextView) itemView.findViewById(R.id.textViewWindDir);
            gust = (TextView) itemView.findViewById(R.id.textViewGust);
        }
    }
}


