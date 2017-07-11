/*
 * Copyright (C) 2015 CaMnter 421482590@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mincor.apptest.application;

import android.app.Application;

import com.mincor.apptest.di.component.AppComponent;
import com.mincor.apptest.di.component.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Description：MainApplication
 * Created by：Alex
 */
public class MainApplication extends Application {

    public static RefWatcher refWatcher;
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        ///---------- DAGGER INITIALIZE
        appComponent = DaggerAppComponent.builder().build();

        ////----- LEAK CANARY INITIALIZATION
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
