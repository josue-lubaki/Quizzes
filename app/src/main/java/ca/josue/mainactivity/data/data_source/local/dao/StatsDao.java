package ca.josue.mainactivity.data.data_source.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ca.josue.mainactivity.domain.entity.StatEntity;

@Dao
public interface StatsDao {
    @Insert
    void insert(StatEntity statEntity);

    @Insert
    void insertAll(StatEntity... stats);

    @Query("SELECT * FROM stats")
    LiveData<List<StatEntity>> getAllStats();

    @Query("SELECT * FROM stats WHERE tag LIKE '%' || :tag ||'%'")
    LiveData<List<StatEntity>> getStatsByTag(String tag);

    @Query("DELETE FROM stats")
    void deleteAllStats();

    @Query("DELETE FROM stats WHERE id = :id")
    void deleteStat(int id);

}
