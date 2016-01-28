package m2dl.com.binarydiver.data;

import android.graphics.Point;
import android.graphics.Rect;

public class Obstacle {

    private Point position;
    private Rect bounds;

    private String binary;
    private boolean isBig;
    private int vitesse;
    private boolean hasMoved;

    public Obstacle(Point position, String binary, int vitesse) {
        this.position = position;
        this.binary = binary;
        this.vitesse = vitesse;
        this.bounds = new Rect();
        hasMoved = true;
    }

    public Point getPosition() {
        return position;
    }

    public void move() {
        this.position.y += vitesse;
        hasMoved = true;
    }

    public Rect getBounds() {
        if(hasMoved) {
            refreshBounds();
        }
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
        hasMoved = false;
        //bounds.set(position.x, position.y,
        //        right, bottom);
    }
}
