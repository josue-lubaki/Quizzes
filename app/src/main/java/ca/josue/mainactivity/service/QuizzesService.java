package ca.josue.mainactivity.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ca.josue.mainactivity.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizzesService {

    private static Retrofit api = null;
    private static final String API_KEY = BuildConfig.QUIZZES_KEY;

    public static Retrofit getApi(){
        if(api == null){
            Gson gson = new GsonBuilder().create();
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            api =  new Retrofit.Builder()
                    .baseUrl("https://quizapi.io/api/v1/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }

        return api;
    }
}
