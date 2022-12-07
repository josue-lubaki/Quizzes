package ca.josue.mainactivity.data.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import ca.josue.mainactivity.domain.entity.StatEntity;

public interface StatsRepo {
    LiveData<List<StatEntity>> getAllStats();
    LiveData<List<StatEntity>> getStatsByTag(String tag);
    void insertStats(StatEntity stat);
    void insertStats(StatEntity... stats);
    void deleteStats();
    void deleteStatsById(int id);
}
