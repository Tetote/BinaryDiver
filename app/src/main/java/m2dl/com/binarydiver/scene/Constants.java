package m2dl.com.binarydiver.scene;

/**
 * Created by kelto on 28/01/16.
 */
public class Constants {
    /*
    Constants to handle BIGGY
     */

    //step before music warning
    public final static int EASY_BEFORE_WARN = 10;
    public final static int NORMAL_BEFORE_WARN = 5;
    public final static int DIFFICULT_BEFORE_WARN = 2;
    //step before the biggy pop, when the small should stop pop
    public final static int EASY_STOP_BEFORE_BIG = 15;
    public final static int NORMAL_STOP_BEFORE_BIG = 7;
    public final static int DIFFICULT_STOP_BEFORE_BIG = 3;
    //step after the biggy pop, then small pop again
    public final static int EASY_AFTER_BIG_POP = 30;
    public final static int NORMAL_AFTER_BIG_POP = 30;
    public final static int DIFFICULT_AFTER_BIG_POP = 30;

    //to handle the biggy popping rate
    public final static int BIG_POP_IN_MIN = 100;
    public final static int POP_DIFF = 50;

    public final static int EASY_VELOCITY = 30;
    public final static int NORMAL_VELOCITY = 50;
    public final static int DIFFICULT_VELOCITY = 70;
    public final static int HARDCORE_VELOCITY = 150;

    public static final int EASY_POP = 10;
    public static final int NORMAL_POP = 20;
    public static final int DIFFICULT_POP = 35;
    public static final int HARDCORE_POP = 50;
}
