package ca.josue.mainactivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.multidex.MultiDexApplication;

import java.util.HashMap;
import java.util.Map;

import ca.josue.mainactivity.utils.ResponseAnswer;

public class BaseApplication extends MultiDexApplication {
    private static final String CHANNEL_ID = BaseApplication.class.getSimpleName() + ".channel";
    public static int totalScore = 0;
    public static int score = 0;
    public static Map<Long, ResponseAnswer> answersMapSession = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            CharSequence name = getString(R.string.channel_name);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setVibrationPattern(new long[]{100, 200, 100});
            channel.setSound(RingtoneManager
                            .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                    Notification.AUDIO_ATTRIBUTES_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
    }
}