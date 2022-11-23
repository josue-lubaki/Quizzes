package ca.josue.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.Objects;

import ca.josue.mainactivity.databinding.ActivitySplashScreenBinding;
import ca.josue.mainactivity.repository.QuizzesRepo;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

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

        myProgress = binding.myProgressBar;
        LottieAnimationView myAnimation = binding.animationView;
        myAnimation.playAnimation();

        new Thread(() -> {
            while (pStatus < 100) {
                pStatus += 1;
                handler.post(() -> myProgress.setProgress(pStatus));

                if (pStatus == 100) {
                    new QuizzesRepo(this.getApplication()).start();

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}