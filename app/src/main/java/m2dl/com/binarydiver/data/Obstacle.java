package m2dl.com.binarydiver.data;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Obstacle {

    public static float SIZE = 60f;
    private Point position;
    private Rect bounds;

    private String binary;
    private boolean isBig;
    private int vitesse;
    private static Paint paint = new Paint(Color.BLACK);
    static {
        paint.setTextSize(SIZE);
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
        paint.getTextBounds(binary,0,binary.length(),rect);
        rect.offsetTo(position.x, position.y);
        return rect;
    }

    public void draw(Canvas canvas) {
        int top = position.y;
        int left = position.x;

        canvas.drawText(binary,(float)left,(float)top, paint);
    }
}
