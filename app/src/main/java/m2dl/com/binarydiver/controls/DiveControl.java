package m2dl.com.binarydiver.controls;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import m2dl.com.binarydiver.exceptions.UnsupportedMaterialException;

/**
 * Created by kelto on 28/01/16.
 */
public class DiveControl {

    private static final float DEFAULT_THRESHOLD = 0.5f;

    private SensorManager sensorManager;
    private DiveControl.Callback callback;
    private float threshold;

    public DiveControl(Context context, DiveControl.Callback cb) throws UnsupportedMaterialException {
        callback = cb;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer == null) {
            throw new UnsupportedMaterialException("The system does not provide the necessary material");
        }
        sensorManager.registerListener(listener,accelerometer,SensorManager.SENSOR_DELAY_GAME);

        threshold = DEFAULT_THRESHOLD;
    }

    public DiveControl(Context context, DiveControl.Callback cb, float threshold) throws UnsupportedMaterialException {
        this(context,cb);
        this.threshold = threshold;
    }

    private final SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float x = event.values[0];
                float y = event.values[0];
                float z = event.values[0];

                handleSensorValues(x,y,z);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    private void handleSensorValues(float x, float y, float z) {
        // TODO: check this algorithm taken on android documentation
        /*
        final float alpha = 0.8f;
        float[] gravity = new float[3];
        float[] linear_acceleration = new float[3];
        // Isolate the force of gravity with the low-pass filter.
        gravity[0] = alpha * gravity[0] + (1 - alpha) * x;
        gravity[1] = alpha * gravity[1] + (1 - alpha) * y;
        gravity[2] = alpha * gravity[2] + (1 - alpha) * z;

        // Remove the gravity contribution with the high-pass filter.
        linear_acceleration[0] = x - gravity[0];
        linear_acceleration[1] = y - gravity[1];
        linear_acceleration[2] = z - gravity[2];

        */
        if(Math.abs(x) > threshold) {
            if(x > 0) {
                callback.onRight(x);
            } else {
                callback.onLeft(x);
            }
        }
    }



    public interface Callback {
        void onRight(float value);
        void onLeft(float value);
    }
}
