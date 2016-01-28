package m2dl.com.binarydiver;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import m2dl.com.binarydiver.data.Jeu;
import m2dl.com.binarydiver.fragment.ConfigDialogFragment;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "BinaryDiver";
    public static final String PREF_HIGH_SCORE = "highscore";
    public static final String PREF_LAST_SCORE = "lastscore";

    private Jeu jeu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Create empty game
        jeu = new Jeu();

        // Load highscore / lastscore
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        jeu.setHighscore(settings.getInt(PREF_HIGH_SCORE, 0));
        jeu.setLastscore(settings.getInt(PREF_LAST_SCORE, 0));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment newFragment = ConfigDialogFragment.newInstance();
        newFragment.show(ft, "dialog");
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setScore() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        if (jeu.getLastscore() > jeu.getHighscore()) {
            jeu.setHighscore(jeu.getLastscore());
        }

        editor.putInt(PREF_LAST_SCORE, jeu.getLastscore());
        editor.putInt(PREF_HIGH_SCORE, jeu.getHighscore());

        editor.commit();
    }
}
