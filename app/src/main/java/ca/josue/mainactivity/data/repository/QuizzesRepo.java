package ca.josue.mainactivity.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import ca.josue.mainactivity.data.data_source.local.dao.QuizzesDao;
import ca.josue.mainactivity.database.QuizzesDatabase;
import ca.josue.mainactivity.domain.entity.QuizEntity;

public class QuizzesRepo {
   private final QuizzesDao quizzesDao;
   private final LiveData<List<QuizEntity>> allQuizzes;

    public QuizzesRepo(Application application) {
         QuizzesDatabase database = QuizzesDatabase.getInstance(application);
         this.quizzesDao = database.quizzesDao();
         this.allQuizzes = quizzesDao.getAllQuizzes();
    }

    public LiveData<List<QuizEntity>> getAllQuizzes() {
        return allQuizzes;
    }

    public LiveData<List<QuizEntity>> getQuizzesByCategory(String category) {
        return quizzesDao.getQuizzesByCategory(category);
    }

    public void insertQuiz(QuizEntity quiz) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> quizzesDao.insertQuiz(quiz));
    }

    public void insertQuizzes(QuizEntity... quizzes) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> quizzesDao.insertQuizzes(quizzes));
    }

    public void deleteQuiz(long id) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> quizzesDao.deleteQuiz(id));
    }

    public void deleteAllQuizzes() {
        QuizzesDatabase.databaseWriteExecutor.execute(quizzesDao::deleteAllQuizzes);
    }

    public QuizEntity getQuizById(long id) {
        return quizzesDao.getQuizById(id);
    }

    public int getSizeQuizzesTags(String tags) {
        return quizzesDao.getSizeQuizzesTags(tags);
    }

    public int start() {
        return quizzesDao.start();
    }
}
