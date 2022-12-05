package ca.josue.mainactivity.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ca.josue.mainactivity.BuildConfig;
import ca.josue.mainactivity.data.data_source.local.dao.AnswersDao;
import ca.josue.mainactivity.data.data_source.local.dao.QuizzesDao;
import ca.josue.mainactivity.data.data_source.local.dao.StatsDao;
import ca.josue.mainactivity.domain.entity.Answers;
import ca.josue.mainactivity.domain.entity.QuizEntity;
import ca.josue.mainactivity.domain.entity.StatEntity;

@Database(entities = { QuizEntity.class, Answers.class, StatEntity.class }, version = 2, exportSchema = false)
public abstract class QuizzesDatabase extends RoomDatabase {

    public abstract QuizzesDao quizzesDao();
    public abstract AnswersDao answersDao();
    public abstract StatsDao statsDao();

    private static QuizzesDatabase INSTANCE;
    private static final String DATABASE_NAME = BuildConfig.QUIZZES_DB_NAME;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized QuizzesDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuizzesDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
