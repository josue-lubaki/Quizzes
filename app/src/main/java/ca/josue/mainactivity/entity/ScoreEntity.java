package ca.josue.mainactivity.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "scoresTable")
public class ScoreEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String tag;
    private int score;
    private final long dateCreated;

    public ScoreEntity(long id, String tag, int score) {
        this.id = id;
        this.tag = tag;
        this.score = score;
        this.dateCreated = System.currentTimeMillis();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getDateCreated() {
        return dateCreated;
    }
}
