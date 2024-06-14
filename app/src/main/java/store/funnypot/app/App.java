package store.funnypot.app;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import dagger.hilt.android.HiltAndroidApp;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

@HiltAndroidApp
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Consumer<? super Throwable> ff = (e)->{

        };
        RxJavaPlugins.setErrorHandler(ff);
    }

    @Override
    public void attachBaseContext(Context base){
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
