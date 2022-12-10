package ca.josue.mainactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import ca.josue.mainactivity.data.data_source.network.QuizzesApiService;
import ca.josue.mainactivity.databinding.ActivitySplashScreenBinding;
import ca.josue.mainactivity.domain.dto.QuizDto;
import ca.josue.mainactivity.domain.entity.Answers;
import ca.josue.mainactivity.domain.entity.QuizEntity;
import ca.josue.mainactivity.domain.viewmodel.AnswersViewModel;
import ca.josue.mainactivity.domain.viewmodel.QuizzesViewModel;
import ca.josue.mainactivity.utils.Converter;
import dagger.hilt.android.AndroidEntryPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
public class SplashScreen extends AppCompatActivity {

    private static final String TAG = SplashScreen.class.getSimpleName();
    private CircularProgressIndicator myProgress;
    private final Handler handler = new Handler();
    private int pStatus = 0;

    @Inject
    public Retrofit retrofit;

    @Inject
    public QuizzesViewModel quizzesViewModel;

    @Inject
    public AnswersViewModel answersViewModel;

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
                    // fetch all quizzes from API
                    retrieveAllQuestions();

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                try {
                    Thread.sleep(75);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void retrieveAllQuestions() {
        retrofit.create(QuizzesApiService.class)
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

                        QuizEntity[] quizArray = Converter.getQuizEntitiesArray(quizzes);
                        Answers[] answersArray = Converter.getAnswersArray(quizzes);
                        quizzesViewModel.insertQuizzes(quizArray);
                        answersViewModel.insertAnswers(answersArray);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<QuizDto>> call, @NonNull Throwable t) {
                        Log.e(TAG, t.getMessage());
                    }
                });
    }
}