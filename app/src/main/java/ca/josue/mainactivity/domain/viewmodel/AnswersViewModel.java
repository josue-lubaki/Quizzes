package ca.josue.mainactivity.domain.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ca.josue.mainactivity.data.repository.AnswersRepo;
import ca.josue.mainactivity.domain.entity.Answers;

public class AnswersViewModel extends AndroidViewModel {
    private final AnswersRepo answersRepo;
    private final LiveData<List<Answers>> allAnswers;

    public AnswersViewModel(Application application) {
        super(application);
        this.answersRepo = new AnswersRepo(application);
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
