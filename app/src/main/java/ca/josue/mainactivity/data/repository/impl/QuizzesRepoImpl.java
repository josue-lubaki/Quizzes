package ca.josue.mainactivity.data.repository.impl;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import ca.josue.mainactivity.data.data_source.local.dao.QuizzesDao;
import ca.josue.mainactivity.data.repository.QuizzesRepo;
import ca.josue.mainactivity.database.QuizzesDatabase;
import ca.josue.mainactivity.domain.entity.QuizEntity;

public class QuizzesRepoImpl implements QuizzesRepo {

   public QuizzesDao quizzesDao;
   private final LiveData<List<QuizEntity>> allQuizzes;

    @Inject
    public QuizzesRepoImpl(QuizzesDatabase database) {
         this.quizzesDao = database.quizzesDao();
         this.allQuizzes = quizzesDao.getAllQuizzes();
    }

    @Override
    public LiveData<List<QuizEntity>> getAllQuizzes() {
        return allQuizzes;
    }

    @Override
    public LiveData<List<QuizEntity>> getQuizzesByCategory(String category) {
        return quizzesDao.getQuizzesByCategory(category);
    }

    @Override
    public void insertQuiz(QuizEntity quiz) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> quizzesDao.insertQuiz(quiz));
    }

    @Override
    public void insertQuizzes(QuizEntity... quizzes) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> quizzesDao.insertQuizzes(quizzes));
    }

    @Override
    public void deleteQuiz(long id) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> quizzesDao.deleteQuiz(id));
    }

    @Override
    public void deleteAllQuizzes() {
        QuizzesDatabase.databaseWriteExecutor.execute(quizzesDao::deleteAllQuizzes);
    }

    @Override
    public QuizEntity getQuizById(long id) {
        return quizzesDao.getQuizById(id);
    }

    @Override
    public int getSizeQuizzesTags(String tags) {
        return quizzesDao.getSizeQuizzesTags(tags);
    }

    @Override
    public int start() {
        return quizzesDao.start();
    }
}
