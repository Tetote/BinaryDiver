package m2dl.com.binarydiver.scene;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by kelto on 28/01/16.
 */
public class Scene extends SurfaceView implements SurfaceHolder.Callback{

    private static final String TAG = Scene.class.getSimpleName();


    private SceneThread thread;

    public Scene(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new SceneThread(getHolder(),this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean stopped = false;
        while(!stopped) {
            try {
                thread.join();
                stopped = true;
            } catch (InterruptedException e) {
                Log.d(TAG, "Tried to join thread failed: " + Log.getStackTraceString(e));

            }

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

    }
}
