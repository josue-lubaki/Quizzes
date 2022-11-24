package ca.josue.mainactivity.data.data_source.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ca.josue.mainactivity.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizzesApiClient {

    private static Retrofit api = null;
    private static final String URL = BuildConfig.QUIZZES_URL;

    public static Retrofit getApi(){
        if(api == null){
            Gson gson = new GsonBuilder().create();
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            api =  new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }

        return api;
    }
}
