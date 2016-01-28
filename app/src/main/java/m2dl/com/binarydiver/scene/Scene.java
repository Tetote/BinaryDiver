package m2dl.com.binarydiver.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import m2dl.com.binarydiver.data.Obstacle;

/**
 * Created by kelto on 28/01/16.
 */
public class Scene extends View{

    private final static int MAX_OBSTACLE = 10;
    private final static int DEFAULT_VELOCITY = 50;
    private final Random random = new Random();
    private int velocity;
    private boolean launched = false;


    private Queue<Obstacle> obstacles = new LinkedList<>();

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
        //TODO: perso.draw(canvas);
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(launched) {
            if(obstacles.size() < MAX_OBSTACLE && random.nextBoolean()) {
                addObstacles(canvas);
            }
            update();
            render(canvas);
        }

    }

    private void addObstacles(Canvas canvas) {
        Random random = new Random();
        int max = canvas.getWidth();
        int y = canvas.getHeight();
        Point point = new Point(random.nextInt(max),y);
        String b = random.nextBoolean() ? "0" : "1";
        obstacles.add(new Obstacle(point,b,velocity));


    }

}
