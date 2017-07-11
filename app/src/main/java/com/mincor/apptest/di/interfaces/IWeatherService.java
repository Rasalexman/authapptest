package com.mincor.apptest.di.interfaces;

import com.mincor.apptest.models.openweathermap.WeatherData;

import io.reactivex.Observable;

/**
 * Created by alexander on 11.07.17.
 */

public interface IWeatherService {
    Observable<WeatherData> findWeatherByCity();
    Observable<WeatherData> findWeatherByCoord();
}
