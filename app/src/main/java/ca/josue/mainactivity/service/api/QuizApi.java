package ca.josue.mainactivity.service.api;

import java.util.List;

import ca.josue.mainactivity.BuildConfig;
import ca.josue.mainactivity.dto.QuizDto;
import ca.josue.mainactivity.enums.CategoryEnum;
import ca.josue.mainactivity.enums.DifficultyEnum;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuizApi {
    String API_KEY = BuildConfig.QUIZZES_KEY;
    String URL = "questions/?apiKey=" + API_KEY;

    @GET(URL + "&limit=10")
    Call<List<QuizDto>> getAllQuizzes();

    // with tags
    @GET(URL + "&limit=10&tags={tagType}")
    Call<List<QuizDto>> getQuizzesByTags(@Path("tagType") String tagType);

    // with category
    @GET(URL + "&limit=10&category={categoryType}")
    Call<List<QuizDto>> getQuizzesByCategory(@Path("categoryType") CategoryEnum category);

    // with difficulty
    @GET(URL + "&limit=10&difficulty={difficultyId}")
    Call<List<QuizDto>> getQuizzesByDifficulty(@Path("difficultyId") DifficultyEnum difficultyId);

}
