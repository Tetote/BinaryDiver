package m2dl.com.binarydiver.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;
import java.util.Queue;

import m2dl.com.binarydiver.data.Obstacle;

/**
 * Created by kelto on 28/01/16.
 */
public class Scene extends View{

    private static final String TAG = Scene.class.getSimpleName();
    private final Paint paint = new Paint(Color.BLACK);


    private Queue<Obstacle> obstacles = new LinkedList<>();

    public Scene(Context context) {
        super(context);
    }

    public Scene(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub

    }

    public void init() {
        obstacles.add(new Obstacle(new Point(10,100),"aA",10));
        obstacles.add(new Obstacle(new Point(30,200),"aA",10));
        obstacles.add(new Obstacle(new Point(40,300),"aA",10));
        obstacles.add(new Obstacle(new Point(50, 400), "aA", 10));
        obstacles.add(new Obstacle(new Point(60, 500), "aA", 10));

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

        for (Obstacle obstacle : obstacles) {
            obstacle.draw(canvas);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        update();
        render(canvas);
    }

}
