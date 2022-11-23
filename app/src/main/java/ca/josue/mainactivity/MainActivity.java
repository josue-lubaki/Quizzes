package ca.josue.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

import java.util.List;
import java.util.stream.Collectors;

import ca.josue.mainactivity.dto.QuizDto;;
import ca.josue.mainactivity.entity.QuizEntity;
import ca.josue.mainactivity.repository.QuizzesRepo;
import ca.josue.mainactivity.service.QuizzesService;
import ca.josue.mainactivity.service.api.QuizApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{
    private final String LOG_TAG = MainActivity.class.getSimpleName();

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
                        return;
                    }

                    List<QuizDto> quizzes = response.body();

                    if(quizzes == null || quizzes.isEmpty()) {
                        Log.e(LOG_TAG, "onResponse: quizzes is null");
                        return;
                    }

                    QuizEntity[] quizArray = getQuizEntitiesArray(quizzes);
                    new QuizzesRepo(getApplication()).insertQuizzes(quizArray);
                }

                @Override
                public void onFailure(@NonNull Call<List<QuizDto>> call, @NonNull Throwable t) {
                    Log.e(LOG_TAG, t.getMessage());
                }
            });
    }

    @NonNull
    private QuizEntity[] getQuizEntitiesArray(List<QuizDto> quizzes) {
        // transform List<QuizDto> to List<Quiz>
        List<QuizEntity> quizEntities = quizzes
                .stream()
                .map(QuizDto::toEntity)
                .collect(Collectors.toList());

        // convert List<QuizEntity> to QuizEntity[]
        QuizEntity[] quizArray = new QuizEntity[quizzes.size()];
        quizArray = quizEntities.toArray(quizArray);
        return quizArray;
    }

    /*****************  Affichage des Fragments  *****************/
    public void showFragment(Class<? extends Fragment> fragment) {
        try {
            Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(fragment.getName());

            if(currentFragment == null)
                currentFragment = fragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, currentFragment, fragment.getName()).commit();

        } catch(InstantiationException | IllegalAccessException e){
            e.printStackTrace();
            Log.d(LOG_TAG,"erreur au moment d'instancier fragment");
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        switch(item.getItemId()){
            case R.id.optionHome:
                return true;
            case R.id.optionScore:
                return true;
            case R.id.optionProfile:
                return true;
        }
        return false;
    }
}