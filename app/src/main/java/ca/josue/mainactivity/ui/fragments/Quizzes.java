package ca.josue.mainactivity.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.josue.mainactivity.MainActivity;
import ca.josue.mainactivity.R;
import ca.josue.mainactivity.data.repository.QuizzesRepo;
import ca.josue.mainactivity.database.QuizzesDatabase;
import ca.josue.mainactivity.databinding.FragmentQuizzesBinding;
import ca.josue.mainactivity.domain.entity.QuizEntity;
import ca.josue.mainactivity.domain.viewmodel.QuizzesViewModel;
import ca.josue.mainactivity.ui.adpater.QuizzesAdapter;

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
            adapter.notifyDataSetChanged();
        });
    }
}