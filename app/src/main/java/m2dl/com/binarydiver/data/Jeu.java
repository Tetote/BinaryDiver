package m2dl.com.binarydiver.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import m2dl.com.binarydiver.R;
import m2dl.com.binarydiver.scene.Constants;

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

    private long startTime;
    private long stopTime;

    public Jeu(Context context) {
        this.context = context;
    }

    public Perso getPerso() {
        return perso;
    }

    public void createPerso() {
        int id = (sex) ? R.drawable.guy_resized : R.drawable.girl_resized;
        Bitmap avatar = BitmapFactory.decodeResource(context.getResources(), id);
        int life = 4;
        switch(difficulty) {
            case FACILE:
                life = Constants.EASY_LIFE;
                break;
            case NORMAL:
                life = Constants.NORMAL_LIFE;
                break;
            case DIFFICILE:
                life = Constants.DIFFICULT_LIFE;
                break;
            case HARDCORE:
                life = Constants.HARDCORE_LIFE;
                break;
        }
        perso = new Perso(avatar,life);
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

    public void startGame() {
        startTime = System.currentTimeMillis();
    }

    public void stopGame() {
        stopTime = System.currentTimeMillis();
    }

    public int getScore() {
        return (int) (stopTime - startTime);
    }

    public int getCurrentScore() {
        long currentTime = System.currentTimeMillis();
        return (int) (currentTime - startTime);
    }
}
