package ca.josue.mainactivity.domain.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

import ca.josue.mainactivity.domain.dto.QuizDto;

@Entity(tableName = "quizzesTable")
public class QuizEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String question;
    private String description;

    // exclude AnswersDao answers from database Entity
    @Ignore
    private Answers answers;
    private String multiple_correct_answers;

    @Ignore
    private Correct_answers correct_answers;
    private String correct_answer;
    private String explanation;
    private String tip;

    @Ignore
    private List<Tag> tags = new ArrayList<>();
    private String category;
    private String difficulty;

    public QuizEntity() {
    }

    // Getter Methods

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getDescription() {
        return description;
    }

    public Answers getAnswers() {
        return answers;
    }

    public String getMultiple_correct_answers() {
        return multiple_correct_answers;
    }

    public Correct_answers getCorrect_answers() {
        return correct_answers;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getTip() {
        return tip;
    }

    public String getCategory() {
        return category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getDifficulty() {
        return difficulty;
    }

    // Setter Methods

    public void setId(long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnswers(Answers answersObject) {
        this.answers = answersObject;
    }

    public void setMultiple_correct_answers(String multiple_correct_answers) {
        this.multiple_correct_answers = multiple_correct_answers;
    }

    public void setCorrect_answers(Correct_answers correct_answersObject) {
        this.correct_answers = correct_answersObject;
    }

    public void setCorrect_answer(String correct_answer) {
        this.correct_answer = correct_answer;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @NonNull
    public String toString() {
        return "Question: " + question + "/n"
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

    public static QuizDto toDTO(QuizEntity quizEntity) {
        QuizDto dto = new QuizDto();
        dto.id = quizEntity.getId();
        dto.question = quizEntity.getQuestion();
        dto.description = quizEntity.getDescription();
        dto.answers = quizEntity.getAnswers();
        dto.multiple_correct_answers = quizEntity.getMultiple_correct_answers();
        dto.correct_answers = quizEntity.getCorrect_answers();
        dto.correct_answer = quizEntity.getCorrect_answer();
        dto.explanation = quizEntity.getExplanation();
        dto.tip = quizEntity.getTip();
        dto.tags = quizEntity.getTags();
        dto.category = quizEntity.getCategory();
        dto.difficulty = quizEntity.getDifficulty();
        return dto;
    }
}
