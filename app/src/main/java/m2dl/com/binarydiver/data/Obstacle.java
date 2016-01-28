package m2dl.com.binarydiver.data;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Obstacle {

    public static float SIZE = 60f;
    public static float BIG_SIZE = 250f;
    private Point position;
    private Rect bounds;

    private String binary;
    private boolean isBig;
    private int vitesse;

    private static Paint NORMAL_PAINT = new Paint(Color.BLACK);
    private static Paint BIG_PAINT = new Paint(Color.BLACK);
    static {
        NORMAL_PAINT.setTextSize(SIZE);
        BIG_PAINT.setTextSize(BIG_SIZE);
    }

    public static Obstacle getBig(int width, int height) {
        Point p = new Point(50, height);
        Obstacle obstacle = new Obstacle(p,"119879879411568",175);
        obstacle.isBig = true;
        return obstacle;
    }

    public Obstacle(Point position, String binary, int vitesse) {
        this.position = position;
        this.binary = binary;
        this.vitesse = vitesse;
        this.bounds = initBound();
    }

    public Point getPosition() {
        return position;
    }

    public void move() {
        this.position.y -= vitesse;
        bounds.offset(0,-vitesse);
    }

    public Rect getBounds() {
        return bounds;
    }

    public String getBinary() {
        return binary;
    }

    public boolean isBig() {
        return isBig;
    }

    private Rect initBound() {
        Rect rect = new Rect();
        NORMAL_PAINT.getTextBounds(binary, 0, binary.length(), rect);
        rect.offsetTo(position.x, position.y);
        return rect;
    }

    public void draw(Canvas canvas) {
        int top = position.y;
        int left = position.x;
        Paint p = this.isBig ? BIG_PAINT : NORMAL_PAINT;
        canvas.drawText(binary,(float)left,(float)top, p);
    }
}
