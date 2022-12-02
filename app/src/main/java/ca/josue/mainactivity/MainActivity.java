package ca.josue.mainactivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.Objects;

import ca.josue.mainactivity.databinding.ActivityMainBinding;
import ca.josue.mainactivity.ui.fragments.Home;
import ca.josue.mainactivity.ui.fragments.Quizzes;
import ca.josue.mainactivity.utils.Menu;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        configureNavigationBar();
    }

    private void configureNavigationBar() {
        MeowBottomNavigation navBar = binding.navBar;
        navBar.add(new MeowBottomNavigation.Model(Menu.HOME.getId(),R.drawable.ic_home));
        navBar.add(new MeowBottomNavigation.Model(Menu.SCORE.getId(),R.drawable.ic_score));
        navBar.add(new MeowBottomNavigation.Model(Menu.PROFILE.getId(), R.drawable.ic_profile));
        navBar.setOnShowListener(item -> {
            Class<? extends Fragment> fragment = null;
            switch(item.getId()){
                case 1:
                    fragment = Home.class;
                    Log.i(TAG,"show Home.class");
                    break;
                case 2:
                    fragment = Quizzes.class;
                    Log.i(TAG,"show Home.class");
                    break;
                case 3:
                    fragment = Home.class;
                    Log.i(TAG,"show Profile.class");
                    break;
            }
            assert fragment != null;
            showFragment(fragment);
        });

        navBar.show(1,true);
        navBar.setOnClickMenuListener(item -> {});
        navBar.setOnReselectListener(item -> {
            if(item.getId() == Menu.HOME.getId()){
                showFragment(Home.class);
            }
        });
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
            Log.d(TAG,"erreur au moment d'instancier fragment");
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmExit)
                .setCancelable(false)
                .setPositiveButton(R.string.confirmYes, (dialog, which) -> {
                    MainActivity.super.onBackPressed();
                })
                .setNegativeButton(R.string.confirmNo, (dialog, which) -> {
                    dialog.cancel();
                })
                .create()
                .show();
    }
}