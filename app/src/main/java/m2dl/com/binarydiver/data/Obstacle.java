package m2dl.com.binarydiver.data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class Obstacle {

    private final Bitmap bitmap;


    private Point position;
    private Rect bounds;

    private boolean isBig;
    private int vitesse;



    public Obstacle(Point position, int vitesse, Bitmap bitmap) {
        this.position = position;
        this.vitesse = vitesse;
        this.bitmap = bitmap;
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


    public boolean isBig() {
        return isBig;
    }

    private Rect initBound() {
        Rect rect = new Rect();
        rect.set(position.x, position.y,
                position.x + bitmap.getWidth(), position.y + bitmap.getHeight());

        return rect;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, (float) this.getPosition().x, (float)this.getPosition().y, null);
    }
}
