package ca.josue.mainactivity.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.josue.mainactivity.Loading;
import ca.josue.mainactivity.R;
import ca.josue.mainactivity.databinding.FragmentPregameBinding;

public class Pregame extends Fragment {
    private static final String TAG = Pregame.class.getSimpleName();
    private FragmentPregameBinding binding;
    private CardView cardHtml;
    private CardView cardDocker;
    private CardView cardSQL;
    private CardView cardCMS;
    private CardView cardLinux;
    private CardView cardCode;
    private CardView cardUncategorized;
    private CardView cardBash;
    private CardView cardDevops;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pregame, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardHtml = binding.cardHtml;
        cardDocker = binding.cardDocker;
        cardSQL = binding.cardSql;
        cardCMS = binding.cardCms;
        cardLinux = binding.cardLinux;
        cardCode = binding.cardCode;
        cardUncategorized = binding.cardUncategorized;
        cardBash = binding.cardBash;
        cardDevops = binding.cardDevops;

        cardHtml.setOnClickListener(this::startGameHtml);
        cardDocker.setOnClickListener(this::startGameDocker);
        cardSQL.setOnClickListener(this::startGameSQL);
        cardCMS.setOnClickListener(this::startGameCMS);
        cardLinux.setOnClickListener(this::startGameLinux);
        cardCode.setOnClickListener(this::startGameCode);
        cardUncategorized.setOnClickListener(this::startGameUncategorized);
        cardBash.setOnClickListener(this::startGameBash);
        cardDevops.setOnClickListener(this::startGameDevops);
    }

    private void startGameDevops(View view) {
        Intent intent = new Intent(getActivity(), Loading.class);
        intent.putExtra("category", "devops");
        startActivity(intent);
    }

    private void startGameBash(View view) {
        Intent intent = new Intent(getActivity(), Loading.class);
        intent.putExtra("category", "bash");
        startActivity(intent);
    }

    private void startGameUncategorized(View view) {
        Intent intent = new Intent(getActivity(), Loading.class);
        intent.putExtra("category", "uncategorized");
        startActivity(intent);
    }

    private void startGameCode(View view) {
        Intent intent = new Intent(getActivity(), Loading.class);
        intent.putExtra("category", "code");
        startActivity(intent);
    }

    private void startGameLinux(View view) {
        Intent intent = new Intent(getActivity(), Loading.class);
        intent.putExtra("category", "Linux");
        startActivity(intent);
    }

    private void startGameCMS(View view) {
        Intent intent = new Intent(getActivity(), Loading.class);
        intent.putExtra("category", "CMS");
        startActivity(intent);
    }

    private void startGameSQL(View view) {
        Intent intent = new Intent(getActivity(), Loading.class);
        intent.putExtra("category", "SQL");
        startActivity(intent);
    }

    private void startGameDocker(View view) {
        Intent intent = new Intent(getActivity(), Loading.class);
        intent.putExtra("category", "docker");
        startActivity(intent);
    }

    private void startGameHtml(View view) {
        Intent intent = new Intent(getActivity(), Loading.class);
        intent.putExtra("category", "html");
        startActivity(intent);
    }
}