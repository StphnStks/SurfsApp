package com.example.stephen.surfsapp;

/**
 * Created by Stephen on 17/03/2015.
 */
public class Forecast {

    private String stationId;
    private String dateTime;

    private float waveHeightFt;
    private float wavePeriod;
    private float waveDir;

    private float gustMph;

    private float windDir;
    private float windSpeedMph;

    public Forecast() {

    }

/*
    public Forecast(String stationId, String dateTime, float waveHeightFt, float wavePeriod, float waveDir, float gustMph, float windDir, float windSpeedMph) {

        this.stationId = stationId;
        this.dateTime = dateTime;
        this.waveHeightFt = waveHeightFt;
        this.wavePeriod = wavePeriod;
        this.waveDir = waveDir;
        this.gustMph = gustMph;
        this.windDir = windDir;
        this.windSpeedMph = windSpeedMph;
    }
*/

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setWaveHeightFt(float waveHeightFt) {
        this.waveHeightFt = waveHeightFt;
    }

    public String getWaveHeightFt() {

//        Math.round(waveHeightFt);

        return String.format("%1.0f", waveHeightFt);
    }

    public void setWavePeriod(float wavePeriod) {
        this.wavePeriod = wavePeriod;
    }

    public String getWavePeriod() {

//        Math.round(wavePeriod);

        return String.format("%1.0f", wavePeriod);
    }

    public void setWaveDir(float waveDir) {
        this.waveDir = waveDir;
    }

    public float getWaveDir() {
        return waveDir;
    }

    public void setGustMph(float gustMph) {
        this.gustMph = gustMph;
    }

    public String getGustMph() {

//        Math.round(gustMph);

        return String.format("%1.0f", gustMph);
    }

    public void setWindDir(float windDir) {
        this.windDir = windDir;
    }

    public float getWindDir() {
        return windDir;
    }

    public void setWindSpeedMph(float windSpeedMph) {
        this.windSpeedMph = windSpeedMph;
    }

    public String getWindSpeedMph() {

//        Math.round(windSpeedMph);

        return String.format("%1.0f", windSpeedMph);
    }



}
