package com.mincor.apptest.di.component;

import com.mincor.apptest.controllers.AuthController;
import com.mincor.apptest.di.modules.NetModule;
import com.mincor.apptest.di.modules.WeatherModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alex on 19.01.2017.
 */

@Singleton
@Component(modules = {NetModule.class, WeatherModule.class})
public interface AppComponent {
    void inject(AuthController authController);
}
