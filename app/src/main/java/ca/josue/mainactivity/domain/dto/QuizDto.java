package ca.josue.mainactivity.domain.dto;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ca.josue.mainactivity.domain.entity.Answers;
import ca.josue.mainactivity.domain.entity.Correct_answers;
import ca.josue.mainactivity.domain.entity.QuizEntity;
import ca.josue.mainactivity.domain.entity.Tag;

public class QuizDto {
    public long id;
    public String question;
    public String description = null;
    public Answers answers;
    public String multiple_correct_answers;
    public Correct_answers correct_answers;
    public String correct_answer;
    public String explanation = null;
    public String tip = null;
    public List<Tag> tags = new ArrayList<>();
    public String category;
    public String difficulty;

    @NonNull
    public String toString() {
        return "Question " + id + " : " + question + "/n"
                + "description: " + description + "/n"
                + "AnswersDao: " + answers + "/n"
                + "multiple_correct_answers: " + multiple_correct_answers + "/n"
                + "Correct_answers: " + correct_answers + "/n"
                + "correct_answer: " + correct_answer + "/n"
                + "explanation: " + explanation + "/n"
                + "tip: " + tip + "/n"
                + "tags: " + tags + "/n"
                + "category: " + category + "/n"
                + "difficulty: " + difficulty + "/n";
    }

    public static QuizEntity toEntity(QuizDto quizDto) {
        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setId(quizDto.id);
        quizEntity.setQuestion(quizDto.question);
        quizEntity.setDescription(quizDto.description);
        quizEntity.setAnswers(quizDto.answers);
        quizEntity.setMultiple_correct_answers(quizDto.multiple_correct_answers);
        quizEntity.setCorrect_answers(quizDto.correct_answers);
        quizEntity.setCorrect_answer(quizDto.correct_answer);
        quizEntity.setExplanation(quizDto.explanation);
        quizEntity.setTip(quizDto.tip);
        quizEntity.setTags(quizDto.tags.stream().map(Tag::getName).collect(Collectors.joining(",")));
        quizEntity.setCategory(quizDto.category);
        quizEntity.setDifficulty(quizDto.difficulty);
        return quizEntity;
    }
}
