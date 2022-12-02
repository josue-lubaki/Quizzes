package ca.josue.mainactivity.data.data_source.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ca.josue.mainactivity.domain.entity.QuizEntity;

@Dao
public interface QuizzesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuiz(QuizEntity quiz);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertQuizzes(QuizEntity... quizzes);

    @Query("SELECT * FROM quizzesTable WHERE correct_answer NOT NULL GROUP BY category, id LIMIT 5")
    LiveData<List<QuizEntity>> getAllQuizzes();

    @Query("SELECT * FROM quizzesTable WHERE correct_answer NOT NULL AND category LIKE :category LIMIT 5")
    LiveData<List<QuizEntity>> getQuizzesByCategory(String category);

    @Query("SELECT * FROM quizzesTable WHERE id = :id")
    QuizEntity getQuizById(long id);

    @Query("DELETE FROM quizzesTable")
    void deleteAllQuizzes();

    @Query("DELETE FROM quizzesTable WHERE id = :id")
    void deleteQuiz(long id);

    @Query("SELECT 1")
    int start();
}
