package ca.josue.mainactivity;

import static ca.josue.mainactivity.Game.TAGS;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import ca.josue.mainactivity.data.data_source.network.QuizzesApiClient;
import ca.josue.mainactivity.data.data_source.network.QuizzesApiService;
import ca.josue.mainactivity.data.repository.AnswersRepo;
import ca.josue.mainactivity.data.repository.QuizzesRepo;
import ca.josue.mainactivity.domain.dto.QuizDto;
import ca.josue.mainactivity.domain.entity.Answers;
import ca.josue.mainactivity.domain.entity.QuizEntity;
import ca.josue.mainactivity.domain.enums.TagsEnum;
import ca.josue.mainactivity.utils.Converter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Loading extends AppCompatActivity {

    private static final String TAG = Loading.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        String tag = bundle.getString("tag");
        if(tag != null) {
            fetchQuizzes(tag);
        }
        prepareQuiz(tag);
    }

    private void fetchQuizzes(String tag) {
        boolean isTagsValid = Arrays.stream(TagsEnum.values())
                .anyMatch(categoryEnum -> categoryEnum.name().equalsIgnoreCase(tag));

        if(isTagsValid) {
            QuizzesApiClient
                    .getApi()
                    .create(QuizzesApiService.class)
                    .getQuizzesByTags(tag).enqueue(new Callback<List<QuizDto>>() {
                @Override
                public void onResponse(@NonNull Call<List<QuizDto>> call, @NonNull Response<List<QuizDto>> response) {
                    if(!response.isSuccessful()) {
                        return;
                    }

                    List<QuizDto> quizzes = response.body();

                    if(quizzes == null || quizzes.isEmpty()) {
                        Log.e(TAG, "onResponse: quizzes is null");
                        return;
                    }

                    QuizEntity[] quizArray = Converter.getQuizEntitiesArray(quizzes);
                    Answers[] answersArray = Converter.getAnswersArray(quizzes);
                    new QuizzesRepo(getApplication()).insertQuizzes(quizArray);
                    new AnswersRepo(getApplication()).insertAnswers(answersArray);
                }

                @Override
                public void onFailure(@NonNull Call<List<QuizDto>> call, @NonNull Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }

    private void prepareQuiz(String tag) {
       Intent intent = new Intent(this, Game.class);
       intent.putExtra(TAGS, tag);
       startActivity(intent);
       finish();
    }
}