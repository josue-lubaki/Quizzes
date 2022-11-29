package ca.josue.mainactivity.data.data_source.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ca.josue.mainactivity.domain.entity.Answers;

@Dao
public interface AnswersDao {
    @Query("select * from answers")
    LiveData<List<Answers>> getAnswers();

    @Query("select * from answers where id = :id")
    Answers getAnswerById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnswer(Answers answer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAnswers(Answers... answers);

    @Query("DELETE FROM answers")
    void deleteAllAnswers();

    @Query("DELETE FROM answers WHERE id = :id")
    void deleteAnswer(long id);

    @Query("SELECT 1")
    int start();
}
