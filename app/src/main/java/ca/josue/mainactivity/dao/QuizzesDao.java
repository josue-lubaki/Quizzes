package ca.josue.mainactivity.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import ca.josue.mainactivity.entity.QuizEntity;

@Dao
public interface QuizzesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuiz(QuizEntity quiz);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuizzes(QuizEntity... quizzes);

    @Query("SELECT * FROM quizzesTable")
    LiveData<QuizEntity> getAllQuizzes();

    @Query("SELECT * FROM quizzesTable WHERE id = :id")
    QuizEntity getQuizById(long id);

    @Query("DELETE FROM quizzesTable")
    void deleteAllQuizzes();

    @Query("DELETE FROM quizzesTable WHERE id = :id")
    void deleteQuiz(long id);

    @Query("SELECT 1")
    int start();
}
