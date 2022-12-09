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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ca.josue.mainactivity.MainActivity;
import ca.josue.mainactivity.R;
import ca.josue.mainactivity.databinding.FragmentHomeBinding;
import ca.josue.mainactivity.utils.Menu;

public class Home extends Fragment {
    private FragmentHomeBinding binding;

    private final MainActivity activity;

    public Home(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MainActivity)context).setTitle(R.string.home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_400);
        Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_800);
        Animation animation3 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_1200);

        animation.setInterpolator(getContext(), android.R.interpolator.accelerate_decelerate);
        animation2.setInterpolator(getContext(), android.R.interpolator.accelerate_decelerate);
        animation3.setInterpolator(getContext(), android.R.interpolator.bounce);

        RelativeLayout btnBegin = binding.btnBegin;
        btnBegin.setAnimation(animation3);

        ImageButton startGame = binding.btnBeginImage;
        startGame.setOnClickListener(this::startGame);

        TextView tvWelcome = binding.tvWelcome;
        tvWelcome.setAnimation(animation);

        TextView tvWelcomeDescription = binding.tvWelcomeDescription;
        tvWelcomeDescription.setAnimation(animation2);
    }

    private void startGame(View view) {
        activity.changeNavBar(Menu.PREGAME, true);
        activity.showFragment(activity, new Pregame(activity));
    }
}