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
    private FragmentPregameBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pregame, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView cardHtml = binding.cardHtml;
        CardView cardDocker = binding.cardDocker;
        CardView cardLinux = binding.cardLinux;
        CardView cardBash = binding.cardBash;
        CardView cardDevops = binding.cardDevops;
        CardView cardPHP = binding.cardPhp;
        CardView cardJavascript = binding.cardJavascript;
        CardView cardLaravel = binding.cardLaravel;
        CardView cardWordPress = binding.cardWordpress;
        CardView cardKubernetes = binding.cardKubernetes;
        CardView cardMySQL = binding.cardMysql;

        cardHtml.setOnClickListener((v) -> startGame("html"));
        cardDocker.setOnClickListener((v) -> startGame("docker"));
        cardLinux.setOnClickListener((v) -> startGame("linux"));
        cardBash.setOnClickListener((v) -> startGame("bash"));
        cardDevops.setOnClickListener((v) -> startGame("devops"));
        cardPHP.setOnClickListener((v) -> startGame("php"));
        cardJavascript.setOnClickListener((v) -> startGame("javascript"));
        cardLaravel.setOnClickListener((v) -> startGame("laravel"));
        cardWordPress.setOnClickListener((v) -> startGame("wordpress"));
        cardKubernetes.setOnClickListener((v) -> startGame("kubernetes"));
        cardMySQL.setOnClickListener((v) -> startGame("mysql"));
    }

    private void startGame(String tag) {
        Intent intent = new Intent(getActivity(), Loading.class);
        switch(tag) {
            case "html":
                intent.putExtra("tag", "html");
                break;
            case "docker":
                intent.putExtra("tag", "docker");
                break;
            case "linux":
                intent.putExtra("tag", "linux");
                break;
            case "bash":
                intent.putExtra("tag", "bash");
                break;
            case "devops":
                intent.putExtra("tag", "devops");
                break;
            case "php":
                intent.putExtra("tag", "php");
                break;
            case "javascript":
                intent.putExtra("tag", "javascript");
                break;
            case "laravel":
                intent.putExtra("tag", "laravel");
                break;
            case "wordpress":
                intent.putExtra("tag", "wordpress");
                break;
            case "kubernetes":
                intent.putExtra("tag", "kubernetes");
                break;
            case "mysql":
                intent.putExtra("tag", "mysql");
                break;
        }
        startActivity(intent);
    }
}