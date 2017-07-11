package com.mincor.apptest.di.services;

import com.mincor.apptest.di.interfaces.IWeatherService;
import com.mincor.apptest.di.interfaces.WeatherApi;
import com.mincor.apptest.models.openweathermap.WeatherData;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by alexander on 11.07.17.
 */

public class WeatherService implements IWeatherService {

    WeatherApi weatherApi;

    @Inject
    public WeatherService(Retrofit retrofit){
        weatherApi = retrofit.create(WeatherApi.class);
    }

    @Override
    public Observable<WeatherData> findWeatherByCity() {
        return weatherApi.getWeatherByCity("Novaya Gollandiya").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<WeatherData> findWeatherByCoord() {
        return weatherApi.getWeatherByCoords(59.93,30.29).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
