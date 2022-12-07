package ca.josue.mainactivity.data.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

import ca.josue.mainactivity.domain.entity.QuizEntity;

public interface QuizzesRepo {
    LiveData<List<QuizEntity>> getAllQuizzes();
    LiveData<List<QuizEntity>> getQuizzesByCategory(String category);
    void insertQuiz(QuizEntity quiz);
    void insertQuizzes(QuizEntity... quizzes);
    void deleteQuiz(long id);
    void deleteAllQuizzes();
    QuizEntity getQuizById(long id);
    int getSizeQuizzesTags(String tags);
    int start();
}
