package ca.josue.mainactivity;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

import ca.josue.mainactivity.utils.ResponseAnswer;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class BaseApplication extends Application {
    public static Map<Long, ResponseAnswer> answersMapSession;

    @Override
    public void onCreate() {
        super.onCreate();

        answersMapSession = new HashMap<>();
    }
}