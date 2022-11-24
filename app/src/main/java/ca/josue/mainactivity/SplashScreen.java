package ca.josue.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ca.josue.mainactivity.data.data_source.network.QuizzesApiClient;
import ca.josue.mainactivity.data.data_source.network.QuizzesApiService;
import ca.josue.mainactivity.databinding.ActivitySplashScreenBinding;
import ca.josue.mainactivity.data.repository.QuizzesRepo;
import ca.josue.mainactivity.domain.dto.QuizDto;
import ca.josue.mainactivity.domain.entity.QuizEntity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    private static final String TAG = SplashScreen.class.getSimpleName();
    private CircularProgressIndicator myProgress;
    private final Handler handler = new Handler();
    private int pStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySplashScreenBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

        // Disable the ToolBar
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        myProgress = binding.myProgressBar;
        LottieAnimationView myAnimation = binding.animationView;
        myAnimation.playAnimation();

        new Thread(() -> {
            while (pStatus < 100) {
                pStatus += 1;
                handler.post(() -> myProgress.setProgress(pStatus));

                if (pStatus == 100) {
                    new QuizzesRepo(this.getApplication()).start();
                    retrieveAllQuestions();

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                try {
                    Thread.sleep(80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void retrieveAllQuestions() {
        QuizzesApiClient
                .getApi()
                .create(QuizzesApiService.class)
                .getAllQuizzes()
                .enqueue(new Callback<List<QuizDto>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<QuizDto>> questions, @NonNull Response<List<QuizDto>> response) {
                        if(!response.isSuccessful()) {
                            return;
                        }

                        List<QuizDto> quizzes = response.body();

                        if(quizzes == null || quizzes.isEmpty()) {
                            Log.e(TAG, "onResponse: quizzes is null");
                            return;
                        }

                        QuizEntity[] quizArray = getQuizEntitiesArray(quizzes);
                        new QuizzesRepo(getApplication()).insertQuizzes(quizArray);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<QuizDto>> call, @NonNull Throwable t) {
                        Log.e(TAG, t.getMessage());
                    }
                });
    }

    @NonNull
    private QuizEntity[] getQuizEntitiesArray(List<QuizDto> quizzes) {
        // transform List<QuizDto> to List<Quiz>
        List<QuizEntity> quizEntities = quizzes
                .stream()
                .map(QuizDto::toEntity)
                .collect(Collectors.toList());

        // convert List<QuizEntity> to QuizEntity[]
        QuizEntity[] quizArray = new QuizEntity[quizzes.size()];
        quizArray = quizEntities.toArray(quizArray);
        return quizArray;
    }
}