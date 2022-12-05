package ca.josue.mainactivity;

import androidx.multidex.MultiDexApplication;

import java.util.HashMap;
import java.util.Map;

import ca.josue.mainactivity.database.QuizzesDatabase;
import ca.josue.mainactivity.utils.ResponseAnswer;

public class BaseApplication extends MultiDexApplication {
    public static int totalScore = 0;
    public static int score = 0;
    public static Map<Long, ResponseAnswer> answersMapSession = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
    }
}