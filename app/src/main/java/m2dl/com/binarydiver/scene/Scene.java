package m2dl.com.binarydiver.scene;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import m2dl.com.binarydiver.MainActivity;
import m2dl.com.binarydiver.R;
import m2dl.com.binarydiver.data.Difficulty;
import m2dl.com.binarydiver.data.Obstacle;

/**
 * Created by kelto on 28/01/16.
 */
public class Scene extends View{

    private final Random random = new Random();
    private int velocity;
    private boolean launched = false;
    private int nb_since_big = 0;
    private int next_big;


    private Queue<Obstacle> obstacles = new LinkedList<>();
    private MainActivity activity;
    private int maxObstacles;
    private int afterBigPop;
    private int stopBeforeBig;
    private int beforeWarn;

    public Scene(Context context) {
        super(context);
    }

    public Scene(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

    }

    public void init(Difficulty difficulty) {
        switch (difficulty) {
            case FACILE:
                this.maxObstacles = Constants.EASY_POP;
                this.velocity = Constants.EASY_VELOCITY;
                this.afterBigPop = Constants.EASY_AFTER_BIG_POP;
                this.stopBeforeBig = Constants.EASY_STOP_BEFORE_BIG;
                this.beforeWarn = Constants.EASY_BEFORE_WARN;
                break;
            case NORMAL:
                this.velocity = Constants.NORMAL_VELOCITY;
                this.maxObstacles = Constants.NORMAL_POP;
                this.afterBigPop = Constants.NORMAL_AFTER_BIG_POP;
                this.stopBeforeBig = Constants.NORMAL_STOP_BEFORE_BIG;
                this.beforeWarn = Constants.NORMAL_BEFORE_WARN;
                break;
            case DIFFICILE:
                this.velocity = Constants.DIFFICULT_VELOCITY;
                this.maxObstacles = Constants.DIFFICULT_POP;
                this.afterBigPop = Constants.DIFFICULT_AFTER_BIG_POP;
                this.stopBeforeBig = Constants.DIFFICULT_STOP_BEFORE_BIG;
                this.beforeWarn = Constants.DIFFICULT_BEFORE_WARN;
                break;
            default:
                this.velocity = Constants.HARDCORE_VELOCITY;
                this.velocity = Constants.HARDCORE_POP;
                this.afterBigPop = Constants.DIFFICULT_AFTER_BIG_POP;
                this.stopBeforeBig = Constants.DIFFICULT_STOP_BEFORE_BIG;
                this.beforeWarn = Constants.DIFFICULT_BEFORE_WARN;
        }
        obstacles.clear();
        nb_since_big = 0;
        launched = true;
        generateNextBig();
    }


    private void generateNextBig() {
        next_big = random.nextInt(Constants.BIG_POP_IN_MIN) + Constants.POP_DIFF;
    }

    public void update() {
        //move every obstacles
        for (Obstacle obstacle : obstacles) {
            obstacle.move();
        }
        //remove obstacles that are outside of the view
        while (isOutside(obstacles.peek())) {
            obstacles.poll();
        }


    }

    private boolean isOutside(Obstacle obstacle) {
        return obstacle != null && obstacle.getBounds().bottom <= 0;
    }

    public void render(Canvas canvas) {

        activity.getJeu().getPerso().drawPerso(canvas);

        for (Obstacle obstacle : obstacles) {
            obstacle.draw(canvas);
        }
    }

    private void drawHitbox(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        for (Obstacle obstacle : obstacles) {
            canvas.drawRect(obstacle.getBounds(), paint);
        }

        paint.setColor(Color.BLUE);

        canvas.drawRect(activity.getJeu().getPerso().getBounds(), paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(launched) {
            if(obstacles.size() < maxObstacles && random.nextBoolean()) {
                addObstacles(canvas);
            }
            update();
            //drawHitbox(canvas);
            render(canvas);
        }

    }

    private boolean smallPop() {
        return nb_since_big >= 0 &&  stepToBiggy() > stopBeforeBig;
    }

    private boolean warnForBiggy() {
        return stepToBiggy() == beforeWarn;
    }

    private int stepToBiggy() {
        return next_big - nb_since_big;
    }

    private boolean biggyPop() {
        return next_big == nb_since_big;
    }

    private void addObstacles(Canvas canvas) {
        int id;
        Point p;

        if(smallPop() && randomPop()) {

            int max = canvas.getWidth();
            int y = canvas.getHeight();
            p = new Point(random.nextInt(max),y);
            id = random.nextBoolean() ? R.drawable.zero_drawable : R.drawable.one_drawable;

            Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), id);
            obstacles.add(new Obstacle(p,velocity,bitmap));
        } else if(warnForBiggy()) {
            MediaPlayer mpComming = MediaPlayer.create(getContext(), R.raw.incomming);
            mpComming.start();
        } else if(biggyPop()) {
            id = R.drawable.blue_screen;
            nb_since_big = - afterBigPop;
            p = new Point(100, canvas.getHeight());
            generateNextBig();
            Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), id);
            obstacles.add(new Obstacle(p, velocity, bitmap));
        }
        nb_since_big++;

    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    public boolean isCollision() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getBounds().intersect(activity.getJeu().getPerso().getBounds())) {
                return true;
            }
        }
        return false;
    }

    public boolean randomPop() {
        return true;
    }
}
