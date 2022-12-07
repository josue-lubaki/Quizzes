package ca.josue.mainactivity.domain.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ca.josue.mainactivity.data.repository.QuizzesRepo;
import ca.josue.mainactivity.domain.entity.QuizEntity;

public class QuizzesViewModel extends ViewModel {
    private final QuizzesRepo quizzesRepo;
    private final LiveData<List<QuizEntity>> allQuizzes;

    @Inject
    public QuizzesViewModel(QuizzesRepo quizzesRepo) {
        this.quizzesRepo = quizzesRepo;
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
