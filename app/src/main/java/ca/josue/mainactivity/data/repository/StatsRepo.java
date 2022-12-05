package ca.josue.mainactivity.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ca.josue.mainactivity.data.data_source.local.dao.StatsDao;
import ca.josue.mainactivity.database.QuizzesDatabase;
import ca.josue.mainactivity.domain.entity.StatEntity;

public class StatsRepo {
    private StatsDao statsDao;

    private LiveData<List<StatEntity>> allStats;

    public StatsRepo(Application application) {
        QuizzesDatabase db = QuizzesDatabase.getInstance(application);
        statsDao = db.statsDao();
        allStats = statsDao.getAllStats();
    }

    public LiveData<List<StatEntity>> getAllStats() {
        return allStats;
    }

    public void insertStats(StatEntity statEntity) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> {
            statsDao.insert(statEntity);
        });
    }

    public void insertStats(StatEntity... statEntities) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> {
            statsDao.insertAll(statEntities);
        });
    }

    public void deleteStats() {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> {
            statsDao.deleteAllStats();
        });
    }

    public void deleteStatsById(int id) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> {
            statsDao.deleteStat(id);
        });
    }

    public LiveData<List<StatEntity>> getStatsByTag(String tag) {
        return statsDao.getStatsByTag(tag);
    }
}
