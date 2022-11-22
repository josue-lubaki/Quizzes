package ca.josue.mainactivity.entity;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ca.josue.mainactivity.dto.QuizDto;

public class QuizEntity {
    private float id;
    private String question;
    private String description;
    private Answers answers;
    private String multiple_correct_answers;
    private Correct_answers correct_answers;
    private String correct_answer;
    private String explanation;
    private String tip;
    private List<Tag> tags = new ArrayList<>();
    private String category;
    private String difficulty;

    public QuizEntity() {
    }

    // Getter Methods

    public float getId() {
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

    public void setId(float id) {
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
                + "Answers: " + answers + "/n"
                + "multiple_correct_answers: " + multiple_correct_answers + "/n"
                + "Correct_answers: " + correct_answers + "/n"
                + "correct_answer: " + correct_answer + "/n"
                + "explanation: " + explanation + "/n"
                + "tip: " + tip + "/n"
                + "tags: " + tags + "/n"
                + "category: " + category + "/n"
                + "difficulty: " + difficulty + "/n";
    }

    public QuizDto toDTO(){
        QuizDto dto = new QuizDto();
        dto.question = this.question;
        dto.description = this.description;
        dto.answers = this.answers;
        dto.multiple_correct_answers = this.multiple_correct_answers;
        dto.correct_answers = this.correct_answers;
        dto.correct_answer = this.correct_answer;
        dto.explanation = this.explanation;
        dto.tip = this.tip;
        dto.tags = this.tags;
        dto.category = this.category;
        dto.difficulty = this.difficulty;
        return dto;
    }
}
