package ca.josue.mainactivity.utils;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import ca.josue.mainactivity.domain.dto.QuizDto;
import ca.josue.mainactivity.domain.entity.Answers;
import ca.josue.mainactivity.domain.entity.QuizEntity;

public class Converter {
    public static Answers[] getAnswersArray(List<QuizDto> quizzes) {
        List<Answers> answers = quizzes
                .stream()
                .map(Answers::toEntity)
                .collect(Collectors.toList());

        Answers[] answersArray = new Answers[answers.size()];
        answersArray = answers.toArray(answersArray);
        return answersArray;
    }

    @NonNull
    public static QuizEntity[] getQuizEntitiesArray(List<QuizDto> quizzes) {
        // transform List<QuizDto> to List<Quiz>
        List<QuizEntity> quizEntities = quizzes
                .stream()
                .map(QuizDto::toEntity)
                .collect(Collectors.toList());

        // convert List<QuizEntity> to QuizEntity[]
        QuizEntity[] quizArray = new QuizEntity[quizzes.size()];
        quizArray = quizEntities.toArray(quizArray);
        return quizArray;
    }

    public static String toTimeStr(Date time) {
        String date = android.text.format.DateFormat.format("dd/MM/yyyy", time).toString();
        String timeStr = android.text.format.DateFormat.format("HH:mm", time).toString();
        return String.format(Locale.getDefault(), "%s - %s", date, timeStr);
    }
}
