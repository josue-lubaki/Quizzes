package ca.josue.mainactivity.domain.entity;

import androidx.annotation.NonNull;

public class Answers {
    private String answer_a;
    private String answer_b;
    private String answer_c;
    private String answer_d;
    private String answer_e = null;
    private String answer_f = null;

    public Answers(){}

    public Answers(String answer_a, String answer_b, String answer_c, String answer_d, String answer_e, String answer_f) {
        this.answer_a = answer_a;
        this.answer_b = answer_b;
        this.answer_c = answer_c;
        this.answer_d = answer_d;
        this.answer_e = answer_e;
        this.answer_f = answer_f;
    }

    public String getAnswer_a() {
        return answer_a;
    }

    public String getAnswer_b() {
        return answer_b;
    }

    public String getAnswer_c() {
        return answer_c;
    }

    public String getAnswer_d() {
        return answer_d;
    }

    public String getAnswer_e() {
        return answer_e;
    }

    public String getAnswer_f() {
        return answer_f;
    }

    // Setter Methods

    public void setAnswer_a(String answer_a) {
        this.answer_a = answer_a;
    }

    public void setAnswer_b(String answer_b) {
        this.answer_b = answer_b;
    }

    public void setAnswer_c(String answer_c) {
        this.answer_c = answer_c;
    }

    public void setAnswer_d(String answer_d) {
        this.answer_d = answer_d;
    }

    public void setAnswer_e(String answer_e) {
        this.answer_e = answer_e;
    }

    public void setAnswer_f(String answer_f) {
        this.answer_f = answer_f;
    }

    // toString() method
    @Override
    public String toString() {
        return "Answers{" +
                "answer_a='" + answer_a + '\'' +
                ", answer_b='" + answer_b + '\'' +
                ", answer_c='" + answer_c + '\'' +
                ", answer_d='" + answer_d + '\'' +
                ", answer_e='" + answer_e + '\'' +
                ", answer_f='" + answer_f + '\'' +
                '}';
    }
}
