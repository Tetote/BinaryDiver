package m2dl.com.binarydiver.data;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

public class Perso {

    private Point position;
    private Rect bounds;

    private Bitmap bitmap;

    public Perso(Point position, Bitmap bitmap) {
        this.position = position;
        this.bitmap = bitmap;
        this.bounds = new Rect();
        refreshBounds();
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position.set(position.x, position.y);
        refreshBounds();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Rect getBounds() {
        return bounds;
    }

    // TODO: check hitbox
    private void refreshBounds() {
        bounds.set(position.x, position.y,
                bitmap.getWidth(), bitmap.getHeight());
    }
}
