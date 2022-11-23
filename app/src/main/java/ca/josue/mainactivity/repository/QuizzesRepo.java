package ca.josue.mainactivity.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import ca.josue.mainactivity.dao.QuizzesDao;
import ca.josue.mainactivity.database.QuizzesDatabase;
import ca.josue.mainactivity.entity.QuizEntity;

public class QuizzesRepo {
   private final QuizzesDao quizzesDao;
   private final LiveData<QuizEntity> allQuizzes;

    public QuizzesRepo(Application application) {
         QuizzesDatabase database = QuizzesDatabase.getInstance(application);
         this.quizzesDao = database.quizzesDao();
         this.allQuizzes = quizzesDao.getAllQuizzes();
    }

    public LiveData<QuizEntity> getAllQuizzes() {
        return allQuizzes;
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

    public int start() {
        return quizzesDao.start();
    }
}
