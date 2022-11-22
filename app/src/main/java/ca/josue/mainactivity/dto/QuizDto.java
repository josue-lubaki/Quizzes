package ca.josue.mainactivity.dto;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ca.josue.mainactivity.entity.Answers;
import ca.josue.mainactivity.entity.Correct_answers;
import ca.josue.mainactivity.entity.Tag;

public class QuizDto {
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
}
