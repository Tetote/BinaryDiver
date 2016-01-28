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
import m2dl.com.binarydiver.data.Obstacle;

/**
 * Created by kelto on 28/01/16.
 */
public class Scene extends View{

    private final static int MAX_OBSTACLE = 10;
    private final static int DEFAULT_VELOCITY = 30;
    private final Random random = new Random();
    private int velocity;
    private boolean launched = false;
    private final static int BIG_MIN = 75;
    private final static int BIG_DIFF = 50;
    private int nb_since_big = 0;
    private int next_big;


    private Queue<Obstacle> obstacles = new LinkedList<>();
    private MainActivity activity;

    public Scene(Context context) {
        super(context);
    }

    public Scene(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

    }

    public void init() {
        init(DEFAULT_VELOCITY);
    }
    public void init(int velocity) {
        this.velocity = velocity;
        obstacles.clear();
        launched = true;
        generateNextBig();

    }

    private void generateNextBig() {
        next_big = random.nextInt(BIG_MIN) + BIG_DIFF;
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
            if(obstacles.size() < MAX_OBSTACLE && random.nextBoolean()) {
                addObstacles(canvas);
            }
            update();
            drawHitbox(canvas);
            render(canvas);
        }

    }

    private void addObstacles(Canvas canvas) {
        Obstacle o;
        int id;
        Point p;
        if(nb_since_big >= next_big) {
            MediaPlayer mp = MediaPlayer.create(getContext(),R.raw.biggy);
            mp.start();
            id = R.drawable.blue_screen;
            nb_since_big = 0;
            p = new Point(100, canvas.getHeight());
            generateNextBig();
        } else {
            int max = canvas.getWidth();
            int y = canvas.getHeight();
            p = new Point(random.nextInt(max),y);
            id = random.nextBoolean() ? R.drawable.zero_drawable : R.drawable.one_drawable;

            nb_since_big++;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), id);
        obstacles.add(new Obstacle(p,velocity,bitmap));
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
}
