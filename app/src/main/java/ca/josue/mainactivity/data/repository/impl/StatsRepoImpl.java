package ca.josue.mainactivity.data.repository.impl;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ca.josue.mainactivity.data.data_source.local.dao.StatsDao;
import ca.josue.mainactivity.data.repository.StatsRepo;
import ca.josue.mainactivity.database.QuizzesDatabase;
import ca.josue.mainactivity.domain.entity.StatEntity;

public class StatsRepoImpl implements StatsRepo {
    private final StatsDao statsDao;

    private final LiveData<List<StatEntity>> allStats;

    @Inject
    public StatsRepoImpl(QuizzesDatabase database) {
        statsDao = database.statsDao();
        allStats = statsDao.getAllStats();
    }

    @Override
    public LiveData<List<StatEntity>> getAllStats() {
        return allStats;
    }

    @Override
    public void insertStats(StatEntity statEntity) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> {
            statsDao.insert(statEntity);
        });
    }

    @Override
    public void insertStats(StatEntity... statEntities) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> {
            statsDao.insertAll(statEntities);
        });
    }

    @Override
    public void deleteStats() {
        QuizzesDatabase.databaseWriteExecutor.execute(statsDao::deleteAllStats);
    }

    @Override
    public void deleteStatsById(int id) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> {
            statsDao.deleteStat(id);
        });
    }

    @Override
    public LiveData<List<StatEntity>> getStatsByTag(String tag) {
        return statsDao.getStatsByTag(tag);
    }
}
