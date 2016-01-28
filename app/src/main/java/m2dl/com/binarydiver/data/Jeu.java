package m2dl.com.binarydiver.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import m2dl.com.binarydiver.R;

public class Jeu {

    private Context context;

    private Perso perso;

    private Difficulty difficulty;

    private int highscore;
    private int lastscore;

    /**
     * 0 => Girl
     * 1 => Guy
     */
    private boolean sex;

    public Jeu(Context context) {
        this.context = context;
    }

    public Perso getPerso() {
        return perso;
    }

    public void createPerso() {
        int id = (sex) ? R.drawable.guy : R.drawable.girl;
        Bitmap avatar = BitmapFactory.decodeResource(context.getResources(), id);

        perso = new Perso(avatar);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getLastscore() {
        return lastscore;
    }

    public void setLastscore(int lastscore) {
        this.lastscore = lastscore;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean isMan() {
        return sex;
    }
}
