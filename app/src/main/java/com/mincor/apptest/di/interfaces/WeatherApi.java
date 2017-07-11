package com.mincor.apptest.di.interfaces;

import com.mincor.apptest.models.openweathermap.WeatherData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alexander on 11.07.17.
 */

public interface WeatherApi {

    @GET("weather?")
    Observable<WeatherData> getWeatherByCity(@Query("q") String cityName);

    @GET("weather?")
    Observable<WeatherData> getWeatherByCoords(@Query("lat") Number latitude, @Query("lon") Number longtitude);
}
