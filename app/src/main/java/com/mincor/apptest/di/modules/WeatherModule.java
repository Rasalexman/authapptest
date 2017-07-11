package com.mincor.apptest.di.modules;

import com.mincor.apptest.di.interfaces.IWeatherService;
import com.mincor.apptest.di.services.WeatherService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by alexander on 11.07.17.
 */
@Module
public class WeatherModule {
    @Provides
    @Singleton
    IWeatherService provideWeatherService(Retrofit retrofit){
        return new WeatherService(retrofit);
    }
}
