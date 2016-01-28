package m2dl.com.binarydiver;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import m2dl.com.binarydiver.data.Jeu;
import m2dl.com.binarydiver.fragment.ConfigDialogFragment;
import m2dl.com.binarydiver.scene.Scene;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "BinaryDiver";
    public static final String PREF_HIGH_SCORE = "highscore";
    public static final String PREF_LAST_SCORE = "lastscore";

    public static int HEIGHT;
    public static int WIDTH;

    private Jeu jeu;
    private static final int TIME = 50;
    private Handler frame;
    private Scene scene;
    private Runnable frameUpdate = new Runnable() {
        @Override
        public void run() {
            frame.removeCallbacks(frameUpdate);

            if (scene.isCollision()) {
                gameover();
            } else {
                scene.invalidate();
                frame.postDelayed(frameUpdate,TIME);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame.removeCallbacks(frameUpdate);
                showDialog();
            }
        });

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        WIDTH = metrics.widthPixels;
        HEIGHT = metrics.heightPixels;

        Log.i("TAILLE", "Display width in px is " + metrics.widthPixels);
        Log.i("TAILLE", "Display height in px is " + metrics.heightPixels);

        // Create empty game
        jeu = new Jeu(getApplicationContext());

        // Load highscore / lastscore
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        jeu.setHighscore(settings.getInt(PREF_HIGH_SCORE, 0));
        jeu.setLastscore(settings.getInt(PREF_LAST_SCORE, 0));

        showDialog();

        scene = (Scene) findViewById(R.id.scene);
        scene.setActivity(this);
        frame = new Handler();
    }

    private void gameover() {
        jeu.stopGame();
        jeu.setLastscore(jeu.getScore());
        storeScore();
        showDialog();
    }

    private void showDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment newFragment = ConfigDialogFragment.newInstance();
        newFragment.show(ft, "dialog");
    }

    private void initCanvas() {
        scene.init();
        frame.removeCallbacks(frameUpdate);
        frame.postDelayed(frameUpdate,TIME);
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void storeScore() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        if (jeu.getLastscore() > jeu.getHighscore()) {
            jeu.setHighscore(jeu.getLastscore());
        }

        editor.putInt(PREF_LAST_SCORE, jeu.getLastscore());
        editor.putInt(PREF_HIGH_SCORE, jeu.getHighscore());

        editor.commit();
    }

    public void launch() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initCanvas();
            }
        }, 1000);
    }
}
