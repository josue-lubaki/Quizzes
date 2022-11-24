package ca.josue.mainactivity.domain.entity;

import androidx.annotation.NonNull;

public class Correct_answers {
    private String answer_a_correct;
    private String answer_b_correct;
    private String answer_c_correct;
    private String answer_d_correct;
    private String answer_e_correct;
    private String answer_f_correct;

    public Correct_answers(){}

    public Correct_answers(String answer_a_correct, String answer_b_correct, String answer_c_correct, String answer_d_correct, String answer_e_correct, String answer_f_correct) {
        this.answer_a_correct = answer_a_correct;
        this.answer_b_correct = answer_b_correct;
        this.answer_c_correct = answer_c_correct;
        this.answer_d_correct = answer_d_correct;
        this.answer_e_correct = answer_e_correct;
        this.answer_f_correct = answer_f_correct;
    }

    // Getter Methods

    public String getAnswer_a_correct() {
        return answer_a_correct;
    }

    public String getAnswer_b_correct() {
        return answer_b_correct;
    }

    public String getAnswer_c_correct() {
        return answer_c_correct;
    }

    public String getAnswer_d_correct() {
        return answer_d_correct;
    }

    public String getAnswer_e_correct() {
        return answer_e_correct;
    }

    public String getAnswer_f_correct() {
        return answer_f_correct;
    }

    // Setter Methods

    public void setAnswer_a_correct(String answer_a_correct) {
        this.answer_a_correct = answer_a_correct;
    }

    public void setAnswer_b_correct(String answer_b_correct) {
        this.answer_b_correct = answer_b_correct;
    }

    public void setAnswer_c_correct(String answer_c_correct) {
        this.answer_c_correct = answer_c_correct;
    }

    public void setAnswer_d_correct(String answer_d_correct) {
        this.answer_d_correct = answer_d_correct;
    }

    public void setAnswer_e_correct(String answer_e_correct) {
        this.answer_e_correct = answer_e_correct;
    }

    public void setAnswer_f_correct(String answer_f_correct) {
        this.answer_f_correct = answer_f_correct;
    }

    public String toString() {
        return "class Correct_answers { " +
                "answer_a_correct: " + answer_a_correct +
                ", answer_b_correct: " + answer_b_correct +
                ", answer_c_correct: " + answer_c_correct +
                ", answer_d_correct: " + answer_d_correct +
                ", answer_e_correct: " + answer_e_correct +
                ", answer_f_correct: " + answer_f_correct +
                " }";
    }
}
