package ca.josue.mainactivity.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import ca.josue.mainactivity.entity.QuizEntity;
import ca.josue.mainactivity.repository.QuizzesRepo;

public class QuizzesViewModel extends AndroidViewModel {
    private final QuizzesRepo quizzesRepo;
    private final LiveData<QuizEntity> allQuizzes;

    public QuizzesViewModel(Application application) {
        super(application);
        this.quizzesRepo = new QuizzesRepo(application);
        this.allQuizzes = quizzesRepo.getAllQuizzes();
    }

    public LiveData<QuizEntity> getAllQuizzes() {
        return allQuizzes;
    }

    public void insertQuiz(QuizEntity quiz) {
        quizzesRepo.insertQuiz(quiz);
    }

    public void insertQuizzes(QuizEntity... quizzes) {
        quizzesRepo.insertQuizzes(quizzes);
    }

    public void deleteQuiz(long id) {
        quizzesRepo.deleteQuiz(id);
    }

    public void deleteAllQuizzes() {
        quizzesRepo.deleteAllQuizzes();
    }

    public QuizEntity getQuizById(long id) {
        return quizzesRepo.getQuizById(id);
    }

    public int start() {
        return quizzesRepo.start();
    }
}
