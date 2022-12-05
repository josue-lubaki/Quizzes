package ca.josue.mainactivity.domain.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import ca.josue.mainactivity.utils.Converter;

@Entity(tableName = "stats")
public class StatEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private String tag;
    private int score;
    private int total;

    public StatEntity(String tag, int score, int total) {
        this.date = Converter.toTimeStr(new Date());;
        this.tag = tag;
        this.score = score;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @NonNull
    @Override
    public String toString() {
        return "StatEntity{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", tag='" + tag + '\'' +
                ", score=" + score +
                ", total=" + total +
                '}';
    }
}
