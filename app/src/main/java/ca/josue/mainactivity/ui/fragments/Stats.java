package ca.josue.mainactivity.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import ca.josue.mainactivity.MainActivity;
import ca.josue.mainactivity.R;
import ca.josue.mainactivity.databinding.FragmentStatsBinding;
import ca.josue.mainactivity.domain.viewmodel.StatsViewModel;
import ca.josue.mainactivity.ui.adpater.StatsAdapter;

public class Stats extends Fragment {

    private FragmentStatsBinding binding;
    private StatsAdapter adapter;

    private final MainActivity application;
    private final StatsViewModel viewModel;

    public Stats(MainActivity application, StatsViewModel viewModel) {
        this.application = application;
        this.viewModel = viewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stats, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewStats = binding.recyclerViewStats;

        adapter = new StatsAdapter(application);
        binding.setShowEmpty(adapter.getItemCount() == 0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        recyclerViewStats.setLayoutManager(linearLayoutManager);
        recyclerViewStats.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull android.graphics.Rect outRect,
                                       @NonNull View view,
                                       @NonNull RecyclerView parent,
                                       @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = 20;
                outRect.bottom = 20;
                outRect.right = 20;
                outRect.left = 20;
            }
        });

        recyclerViewStats.setAdapter(adapter);

        viewModel.getAllStats().observe(getViewLifecycleOwner(), stats -> {
            binding.setShowEmpty(stats.isEmpty());
            adapter.setStats(stats);
            adapter.notifyDataSetChanged();
        });

        ExtendedFloatingActionButton btnDeleteAll = binding.btnDeleteAll;
        btnDeleteAll.setOnClickListener(v -> viewModel.deleteStats());
    }
}