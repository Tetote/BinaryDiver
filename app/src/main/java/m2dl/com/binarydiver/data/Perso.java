package m2dl.com.binarydiver.data;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import m2dl.com.binarydiver.MainActivity;

public class Perso {

    private Point position;
    private Rect bounds;

    private Bitmap bitmap;

    public Perso(Bitmap bitmap) {
        this.position = new Point(MainActivity.WIDTH/2 - bitmap.getWidth(), 100);
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

    public void setX(int x) {
        this.position.x = x;
        refreshBounds();
    }

    public void setY(int y) {
        this.position.y = y;
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
                position.x + bitmap.getWidth(), position.y + bitmap.getHeight());
    }

    public void drawPerso(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, (float)this.getPosition().x, (float)this.getPosition().y, null);
    }
}
