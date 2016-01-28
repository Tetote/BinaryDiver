package m2dl.com.binarydiver.data;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Obstacle {

    private Point position;
    private Rect bounds;

    private String binary;
    private boolean isBig;
    private int vitesse;
    private static Paint paint = new Paint(Color.BLACK);

    public Obstacle(Point position, String binary, int vitesse) {
        this.position = position;
        this.binary = binary;
        this.vitesse = vitesse;
        this.bounds = new Rect();
    }

    public Point getPosition() {
        return position;
    }

    public void move() {
        this.position.y -= vitesse;
        refreshBounds();
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

    // TODO: calc hitbox
    private void refreshBounds() {
        int top = position.y;
        int left = position.x;
        int bottom = top + 40;
        int right = left - 40;
        //bounds.set(top,left,bottom,right);
        bounds = new Rect(top,left,bottom,right);
    }

    public void draw(Canvas canvas) {
        int top = position.y;
        int left = position.x;
        int bottom = top + 40;
        int right = left + 40;
        canvas.drawRect(left,top,right,bottom,paint);
    }
}
