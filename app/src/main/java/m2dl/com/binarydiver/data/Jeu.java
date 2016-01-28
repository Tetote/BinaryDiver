package m2dl.com.binarydiver.data;

public class Jeu {

    private Difficulty difficulty;

    private int highscore;
    private int lastscore;

    /**
     * 0 => Girl
     * 1 => Guy
     */
    private boolean sex;

    public Jeu() {
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
