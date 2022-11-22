package ca.josue.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import ca.josue.mainactivity.dto.QuizDto;;
import ca.josue.mainactivity.service.QuizzesService;
import ca.josue.mainactivity.service.api.QuizApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveAllQuestions();
    }

    private void retrieveAllQuestions() {
        QuizzesService
            .getApi()
            .create(QuizApi.class)
            .getAllQuizzes()
            .enqueue(new Callback<List<QuizDto>>() {
                @Override
                public void onResponse(@NonNull Call<List<QuizDto>> questions, @NonNull Response<List<QuizDto>> response) {
                    if(!response.isSuccessful()) {
                        Log.e("MainActivity", "onResponse: " + response.code());
                        return;
                    }

                    List<QuizDto> quizzes = response.body();
                    if(quizzes == null || quizzes.isEmpty()) {
                        Log.e("MainActivity", "onResponse: quizzes is null");
                        return;
                    }
                    Log.d("MainActivity", quizzes.toString());
                }

                @Override
                public void onFailure(@NonNull Call<List<QuizDto>> call, @NonNull Throwable t) {
                    Log.e("MainActivity", t.getMessage());
                }
            });
    }
}