package ca.josue.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Objects;

public class Loading extends AppCompatActivity {

    private static final String TAG = Loading.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // get "category" from bundle
        Bundle bundle = getIntent().getExtras();

        if (bundle!= null) {
            String category = bundle.getString("category");
            Log.i(TAG, "category: " + category);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}