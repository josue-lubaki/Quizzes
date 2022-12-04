package ca.josue.mainactivity.domain.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ca.josue.mainactivity.domain.entity.QuizEntity;
import ca.josue.mainactivity.data.repository.QuizzesRepo;

public class QuizzesViewModel extends AndroidViewModel {
    private final QuizzesRepo quizzesRepo;
    private final LiveData<List<QuizEntity>> allQuizzes;

    public QuizzesViewModel(Application application) {
        super(application);
        this.quizzesRepo = new QuizzesRepo(application);
        this.allQuizzes = quizzesRepo.getAllQuizzes();
    }

    public LiveData<List<QuizEntity>> getAllQuizzes() {
        return allQuizzes;
    }

    public LiveData<List<QuizEntity>> getQuizzesByCategory(String category) {
        return quizzesRepo.getQuizzesByCategory(category);
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

    public int getSizeQuizzesTags(String tags) {
        return quizzesRepo.getSizeQuizzesTags(tags);
    }

    public int start() {
        return quizzesRepo.start();
    }
}
