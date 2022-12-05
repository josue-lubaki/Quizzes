package ca.josue.mainactivity;

import static ca.josue.mainactivity.Game.GAME_NOTIFICATION;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.Objects;

import ca.josue.mainactivity.databinding.ActivityMainBinding;
import ca.josue.mainactivity.ui.fragments.Home;
import ca.josue.mainactivity.ui.fragments.Pregame;
import ca.josue.mainactivity.ui.fragments.Stats;
import ca.josue.mainactivity.utils.Menu;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private MeowBottomNavigation navBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initScreenConfig();
        configureNavigationBar();
        checkBundleData();
    }

    private void initScreenConfig() {
        // Hide the status bar. and make the app fullscreen
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void configureNavigationBar() {
        navBar = binding.navBar;
        navBar.add(new MeowBottomNavigation.Model(Menu.HOME.getId(),R.drawable.ic_home));
        navBar.add(new MeowBottomNavigation.Model(Menu.PREGAME.getId(),R.drawable.ic_game));
        navBar.add(new MeowBottomNavigation.Model(Menu.PROFILE.getId(), R.drawable.ic_score));
        navBar.setOnShowListener(item -> {
            Class<? extends Fragment> fragment = null;
            switch(item.getId()){
                case 1:
                    fragment = Home.class;
                    break;
                case 2:
                    fragment = Pregame.class;
                    break;
                case 3:
                    fragment = Stats.class;
                    break;
            }
            assert fragment != null;
            showFragment(fragment);
        });

        changeNavBar(Menu.HOME,true);
        navBar.setOnClickMenuListener(item -> {});
        navBar.setOnReselectListener(item -> {
            if(item.getId() == Menu.HOME.getId()){
                showFragment(Home.class);
            }
        });
    }

    private void checkBundleData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) return;

        int menu = bundle.getInt(GAME_NOTIFICATION);
        if (menu != 0 && menu == Menu.PREGAME.getId()) {
            changeNavBar(Menu.PREGAME, false);
            showFragment(Pregame.class);
        }
    }

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
            Log.d(TAG,"Error while creating fragment");
        }
    }

    public void changeNavBar(Menu menu, boolean animation) {
        navBar.show(menu.getId(), animation);
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