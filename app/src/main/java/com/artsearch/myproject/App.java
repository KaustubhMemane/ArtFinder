package com.artsearch.myproject;

import android.app.Application;

import com.artsearch.myproject.injection.AppComponent;
import com.artsearch.myproject.injection.AppModule;
import com.artsearch.myproject.injection.DaggerAppComponent;

public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    protected AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }
}
