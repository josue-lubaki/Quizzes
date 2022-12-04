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

import java.util.Arrays;

import ca.josue.mainactivity.Loading;
import ca.josue.mainactivity.R;
import ca.josue.mainactivity.databinding.FragmentPregameBinding;
import ca.josue.mainactivity.domain.enums.TagsEnum;

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
                intent.putExtra("tag", TagsEnum.HTML.getName());
                break;
            case "docker":
                intent.putExtra("tag", TagsEnum.DOCKER.getName());
                break;
            case "linux":
                intent.putExtra("tag", TagsEnum.LINUX.getName());
                break;
            case "bash":
                intent.putExtra("tag", TagsEnum.BASH.getName());
                break;
            case "devops":
                intent.putExtra("tag", TagsEnum.DEVOPS.getName());
                break;
            case "php":
                intent.putExtra("tag", TagsEnum.PHP.getName());
                break;
            case "javascript":
                intent.putExtra("tag", TagsEnum.JAVASCRIPT.getName());
                break;
            case "laravel":
                intent.putExtra("tag", TagsEnum.LARAVEL.getName());
                break;
            case "wordpress":
                intent.putExtra("tag", TagsEnum.WORDPRESS.getName());
                break;
            case "kubernetes":
                intent.putExtra("tag", TagsEnum.KUBERNETES.getName());
                break;
            case "mysql":
                intent.putExtra("tag", TagsEnum.MYSQL.getName());
                break;
        }
        startActivity(intent);
    }
}