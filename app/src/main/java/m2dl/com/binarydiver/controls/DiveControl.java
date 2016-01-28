package m2dl.com.binarydiver.controls;

import android.content.Context;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import m2dl.com.binarydiver.MainActivity;
import m2dl.com.binarydiver.data.Perso;
import m2dl.com.binarydiver.exceptions.UnsupportedMaterialException;

/**
 * Created by kelto on 28/01/16.
 */
public class DiveControl implements SensorEventListener {

    private static final int FACTEUR_HORIZONTAL = 5;
    private static final int FACTEUR_VERTICAL = 30;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Perso monPerso;

    public DiveControl(Context context, Perso monPerso) throws UnsupportedMaterialException {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.monPerso = monPerso;

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer == null) {
            throw new UnsupportedMaterialException("The system does not provide the necessary material");
        }
        activateAccelerometer();
    }

    public void activateAccelerometer() {
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    public void desactivateAccelerometer() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[0];
            float z = event.values[0];

            handleSensorValues(x,y,z);
        }
    }

    private void handleSensorValues(float x, float y, float z) {
        monPerso.setPosition(new Point(handleX(x), monPerso.getPosition().y));
    }

    private int handleX(float x) {
        float posXFloat = monPerso.getPosition().x -x * FACTEUR_HORIZONTAL;
        if (posXFloat > MainActivity.WIDTH - monPerso.getBitmap().getWidth()) {
            posXFloat = MainActivity.WIDTH - monPerso.getBitmap().getWidth();
        }
        if (posXFloat < 0) {
            posXFloat = 0;
        }
        return (int)posXFloat;
    }

    private int handleY(float y) {
        float posYFloat = monPerso.getPosition().y - y * FACTEUR_VERTICAL;
        if (posYFloat > MainActivity.HEIGHT - monPerso.getBitmap().getHeight()) {
            posYFloat = MainActivity.HEIGHT - monPerso.getBitmap().getHeight();
        }
        if (posYFloat < 0) {
            posYFloat = 0;
        }
        return (int)posYFloat;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
