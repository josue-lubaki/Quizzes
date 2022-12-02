package ca.josue.mainactivity;

import static ca.josue.mainactivity.BaseApplication.answersMapSession;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;
import java.util.Objects;

import ca.josue.mainactivity.databinding.ActivityGameBinding;
import ca.josue.mainactivity.domain.entity.QuizEntity;
import ca.josue.mainactivity.domain.viewmodel.QuizzesViewModel;
import ca.josue.mainactivity.ui.adpater.GameAdapter;
import ca.josue.mainactivity.utils.ResponseAnswer;

public class Game extends AppCompatActivity {

    private static final String TAG = Game.class.getSimpleName();
    private GameAdapter adapter;

    private String tag = null;
    private ActivityGameBinding binding;
    private LottieAnimationView myAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        myAnimation = binding.animationViewGame;
        myAnimation.playAnimation();

        Objects.requireNonNull(getSupportActionBar()).hide();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            tag = bundle.getString("tag");
        }

        renderResults(tag);
    }

    private void renderResults(String tag) {
        RecyclerView recyclerView = binding.recyclerViewGame;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new GameAdapter(this.getApplicationContext());
        binding.setShowLoading(adapter.getQuizzes().size() == 0);
        QuizzesViewModel quizzesViewModel = new ViewModelProvider(this).get(QuizzesViewModel.class);

        quizzesViewModel.getAllQuizzes().observe(this, quizzes -> {
            binding.setShowLoading(quizzes.size() == 0);
            adapter.setQuizzes(quizzes);
            recyclerView.setAdapter(adapter);
        });
//        if (tag != null) {
//            quizzesViewModel.getQuizzesByCategory(tag).observe(this, quizzes -> {
//                binding.setShowLoading(quizzes.size() == 0);
//                adapter.setQuizzes(quizzes);
//                recyclerView.setAdapter(adapter);
//            });
//        } else {
//            quizzesViewModel.getAllQuizzes().observe(this, quizzes -> {
//                binding.setShowLoading(quizzes.size() == 0);
//                adapter.setQuizzes(quizzes);
//                recyclerView.setAdapter(adapter);
//            });
//        }

        ExtendedFloatingActionButton sendButton = binding.btnSend;
        sendButton.setOnClickListener(this::validateAnswers);
    }

    private void validateAnswers(View view) {
        List<QuizEntity> quizzes = adapter.getQuizzes();

        // validate the answers
        int score = quizzes.stream().mapToInt(quiz -> {
            ResponseAnswer responseAnswer = answersMapSession.get(quiz.getId());
            if (responseAnswer != null && quiz.getCorrect_answer().equals(responseAnswer.assertion)) {
                return 1;
            }
            return 0;
        }).sum();

        BaseApplication.score = score;
        BaseApplication.totalScore = quizzes.size();

        Log.d(TAG, "score: " + score);
        Toast.makeText(this, "Score: " + score + " / " + quizzes.size(), Toast.LENGTH_SHORT).show();
    }
}