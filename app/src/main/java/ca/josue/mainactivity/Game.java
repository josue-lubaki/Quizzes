package ca.josue.mainactivity;

import static ca.josue.mainactivity.BaseApplication.answersMapSession;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import ca.josue.mainactivity.databinding.ActivityGameBinding;
import ca.josue.mainactivity.domain.entity.QuizEntity;
import ca.josue.mainactivity.domain.entity.StatEntity;
import ca.josue.mainactivity.domain.viewmodel.AnswersViewModel;
import ca.josue.mainactivity.domain.viewmodel.QuizzesViewModel;
import ca.josue.mainactivity.domain.viewmodel.StatsViewModel;
import ca.josue.mainactivity.ui.adpater.GameAdapter;
import ca.josue.mainactivity.utils.Menu;
import ca.josue.mainactivity.utils.ResponseAnswer;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Game extends AppCompatActivity {
    private static final String CHANNEL_ID = Game.class.getSimpleName() + ".channel";
    public static final String GAME_NOTIFICATION = Game.class.getSimpleName() + ".notification";
    public static final String TAGS = Game.class.getSimpleName() + ".tag";
    private GameAdapter adapter;

    private String tag = null;
    private ActivityGameBinding binding;

    @Inject
    public AnswersViewModel answersViewModel;

    @Inject
    public QuizzesViewModel quizzesViewModel;

    @Inject
    public StatsViewModel statViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        LottieAnimationView myAnimation = binding.animationViewGame;
        myAnimation.playAnimation();

        // create notification channel
        createNotificationChannel();

        // put the app in fullscreen
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            tag = bundle.getString(TAGS);
        }

        renderResults(tag);
    }

    private void renderResults(String tag) {
        RecyclerView recyclerView = binding.recyclerViewGame;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new GameAdapter(this.getApplicationContext(), answersViewModel);
        binding.setShowLoading(adapter.getQuizzes().size() == 0);

        if (tag != null) {
            quizzesViewModel.getQuizzesByCategory(tag).observe(this, quizzes -> {
                binding.setShowLoading(quizzes.size() == 0);
                adapter.setQuizzes(quizzes);
                recyclerView.setAdapter(adapter);
            });
        } else {
            quizzesViewModel.getAllQuizzes().observe(this, quizzes -> {
                binding.setShowLoading(quizzes.size() == 0);
                adapter.setQuizzes(quizzes);
                recyclerView.setAdapter(adapter);
            });
        }

        ExtendedFloatingActionButton sendButton = binding.btnSend;
        sendButton.setOnClickListener(this::validateAnswers);
    }

    private void validateAnswers(View view) {
        List<QuizEntity> quizzes = adapter.getQuizzes();

        // validate the answers
        int score = quizzes.stream().mapToInt(quiz -> {
            ResponseAnswer responseAnswer = answersMapSession.get(quiz.getId());
            if (responseAnswer != null && quiz.getCorrect_answer().equalsIgnoreCase(responseAnswer.assertion)) {
                return 1;
            }
            return 0;
        }).sum();

        // send Notification to the user with the score
        sendNotification(score, quizzes.size());
        saveStats(score, quizzes);

        // navigate to Home
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveStats(int score, List<QuizEntity> quizzes) {
        String tag = quizzes.get(0).getTags();
        int total = quizzes.size();

        statViewModel.insertStats(new StatEntity(tag, score, total));
    }

    private void sendNotification(int score, int size) {

        // build long text message
        StringBuilder message = new StringBuilder()
                .append("You have ")
                .append(score)
                .append(" correct answers out of ")
                .append(size)
                .append(" questions in the category: ")
                .append(tag);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(GAME_NOTIFICATION, Menu.STATS.getId());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent,PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notificationBuilder =
            new NotificationCompat
                .Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_message)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.message2))
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_message) + resolvePercentage(score, size))
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setGroup(CHANNEL_ID)
                .setGroupSummary(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager =  (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(2022, notificationBuilder.build());
    }

    private String resolvePercentage(int score, int size) {
        return String.format(Locale.CANADA, " (%d%%)", (score * 100) / size);
    }

    @SuppressLint("ObsoleteSdkInt")
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = getString(R.string.channel_name);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setVibrationPattern(new long[]{100, 200, 100});
            channel.setSound(RingtoneManager
                            .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                    Notification.AUDIO_ATTRIBUTES_DEFAULT);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}