package ca.josue.mainactivity.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

import ca.josue.mainactivity.data.data_source.local.dao.AnswersDao;
import ca.josue.mainactivity.database.QuizzesDatabase;
import ca.josue.mainactivity.domain.entity.Answers;

public class AnswersRepo {
   private final AnswersDao answersDao;
   private final LiveData<List<Answers>> allAnswers;

    public AnswersRepo(Application application) {
            QuizzesDatabase database = QuizzesDatabase.getInstance(application);
            this.answersDao = database.answersDao();
            this.allAnswers = answersDao.getAnswers();
    }

    public LiveData<List<Answers>> getAllAnswers() {
        return allAnswers;
    }

    public void insertAnswer(Answers answer) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> answersDao.insertAnswer(answer));
    }

    public Answers getAnswerById(long id) {
        try {
            return QuizzesDatabase.databaseWriteExecutor.submit(() -> answersDao.getAnswerById(id)).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAnswer(Answers answer) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> answersDao.insertAnswer(answer));
    }

    public void insertAnswers(Answers... answers) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> answersDao.insertAnswers(answers));
    }

    public void deleteAnswer(long id) {
        QuizzesDatabase.databaseWriteExecutor.execute(() -> answersDao.deleteAnswer(id));
    }

    public void deleteAllAnswers() {
        QuizzesDatabase.databaseWriteExecutor.execute(answersDao::deleteAllAnswers);
    }

    public int start() {
        return answersDao.start();
    }
}
