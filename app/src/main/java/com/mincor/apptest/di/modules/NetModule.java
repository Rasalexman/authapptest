package com.mincor.apptest.di.modules;

import com.mincor.apptest.consts.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexander on 11.07.17.
 */

@Module
public class NetModule {

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("units","metric")
                    .addQueryParameter("lang", "ru")
                    .addQueryParameter("appid","45a6d116244cff6716da9d20e025e430")
                    .build();

            Request request = original.newBuilder().url(url).build();
            return chain.proceed(request);
        });
        return httpClient.build();
    }
    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.WEATHER_API_URL)
                .client(okHttpClient)
                .build();
    }
}
