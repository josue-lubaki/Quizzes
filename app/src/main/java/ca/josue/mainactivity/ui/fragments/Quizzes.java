package ca.josue.mainactivity.ui.fragments;

import static ca.josue.mainactivity.BaseApplication.answersMapSession;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import ca.josue.mainactivity.BaseApplication;
import ca.josue.mainactivity.MainActivity;
import ca.josue.mainactivity.R;
import ca.josue.mainactivity.data.repository.QuizzesRepo;
import ca.josue.mainactivity.database.QuizzesDatabase;
import ca.josue.mainactivity.databinding.FragmentQuizzesBinding;
import ca.josue.mainactivity.domain.entity.QuizEntity;
import ca.josue.mainactivity.domain.viewmodel.QuizzesViewModel;
import ca.josue.mainactivity.ui.adpater.QuizzesAdapter;
import ca.josue.mainactivity.utils.ResponseAnswer;

public class Quizzes extends Fragment {

    private static final String TAG = Quizzes.class.getSimpleName();
    private QuizzesAdapter adapter;
    private MainActivity activity;
    private FragmentQuizzesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quizzes, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity) context).setTitle("Quizzes");
        this.activity = (MainActivity) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        RecyclerView recyclerView = binding.recyclerViewQuizzes;

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new QuizzesAdapter(activity);
        QuizzesViewModel quizzesViewModel = new ViewModelProvider(this).get(QuizzesViewModel.class);

        quizzesViewModel.getAllQuizzes().observe(getViewLifecycleOwner(), quizzes -> {
            adapter.setQuizzes(quizzes);
            recyclerView.setAdapter(adapter);
        });

        ImageButton sendButton = binding.btnSend;
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
        Toast.makeText(getContext(), "Score: " + score + " / " + quizzes.size(), Toast.LENGTH_SHORT).show();
    }
}