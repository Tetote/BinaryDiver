package m2dl.com.binarydiver.data;

import android.graphics.Point;
import android.graphics.Rect;

public class Obstacle {

    private Point position;
    private Rect bounds;

    private String binary;
    private boolean isBig;
    private int vitesse;

    public Obstacle(Point position, String binary, int vitesse) {
        this.position = position;
        this.binary = binary;
        this.vitesse = vitesse;
        this.bounds = new Rect();
        refreshBounds();
    }

    public Point getPosition() {
        return position;
    }

    public void move() {
        this.position.y += vitesse;
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
        //bounds.set(position.x, position.y,
        //        right, bottom);
    }
}
