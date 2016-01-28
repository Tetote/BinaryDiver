package m2dl.com.binarydiver.scene;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by kelto on 28/01/16.
 */
class SceneThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private Scene scene;
    private boolean running;

    public SceneThread(SurfaceHolder surfaceHolder, Scene scene) {
        this.surfaceHolder = surfaceHolder;
        this.scene = scene;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        Canvas canvas;
        while (running) {
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    //TODO: check if onDraw should be replaced with update then invalidate()
                    scene.onDraw(canvas);
                    //scene.update(canvas);
                    //scene.invalidate();
                }
            } finally {
                if(canvas != null) {
                    scene.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
