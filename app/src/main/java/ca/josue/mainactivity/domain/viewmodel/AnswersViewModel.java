package ca.josue.mainactivity.domain.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import ca.josue.mainactivity.data.repository.AnswersRepo;
import ca.josue.mainactivity.domain.entity.Answers;

public class AnswersViewModel extends ViewModel {
    private final AnswersRepo answersRepo;
    private final LiveData<List<Answers>> allAnswers;

    @Inject
    public AnswersViewModel(AnswersRepo answersRepo) {
        this.answersRepo = answersRepo;
        this.allAnswers = answersRepo.getAllAnswers();
    }

    public LiveData<List<Answers>> getAllAnswers() {
        return allAnswers;
    }

    public void insertAnswer(Answers answer) {
        answersRepo.insertAnswer(answer);
    }

    public void updateAnswer(Answers answer) {
        answersRepo.updateAnswer(answer);
    }

    public void insertAnswers(Answers... answers) {
        answersRepo.insertAnswers(answers);
    }

    public void deleteAnswer(long id) {
        answersRepo.deleteAnswer(id);
    }

    public void deleteAnswers() {
        answersRepo.deleteAllAnswers();
    }

    public int start() {
        return answersRepo.start();
    }
}
