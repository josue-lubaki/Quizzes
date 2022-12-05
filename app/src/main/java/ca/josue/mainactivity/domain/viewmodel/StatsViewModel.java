package ca.josue.mainactivity.domain.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ca.josue.mainactivity.data.repository.StatsRepo;
import ca.josue.mainactivity.domain.entity.StatEntity;

public class StatsViewModel extends AndroidViewModel {
    private StatsRepo statsRepo;
    private LiveData<List<StatEntity>> allStats;

    public StatsViewModel(@NonNull Application application) {
        super(application);
        statsRepo = new StatsRepo(application);
        allStats = statsRepo.getAllStats();
    }

    public LiveData<List<StatEntity>> getAllStats() {
        return allStats;
    }

    public void insertStats(StatEntity statEntity) {
        statsRepo.insertStats(statEntity);
    }

    public void insertStats(StatEntity... statEntities) {
        statsRepo.insertStats(statEntities);
    }

    public void deleteStats() {
        statsRepo.deleteStats();
    }

    public void deleteStatsById(int id) {
        statsRepo.deleteStatsById(id);
    }

    public LiveData<List<StatEntity>> getStatsByTag(String tag) {
        return statsRepo.getStatsByTag(tag);
    }

}
