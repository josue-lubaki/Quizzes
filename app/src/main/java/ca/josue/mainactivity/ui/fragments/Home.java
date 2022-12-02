package ca.josue.mainactivity.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import ca.josue.mainactivity.MainActivity;
import ca.josue.mainactivity.R;
import ca.josue.mainactivity.databinding.FragmentHomeBinding;

public class Home extends Fragment {
    private static final String TAG = Home.class.getSimpleName();
    private FragmentHomeBinding binding;

    private MainActivity activity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity)context).setTitle(R.string.categories);
        this.activity = (MainActivity) getActivity();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton startGame = binding.btnBeginImage;
        startGame.setOnClickListener(this::startGame);
    }

    private void startGame(View view) {
        activity.showFragment(Pregame.class);
    }
}