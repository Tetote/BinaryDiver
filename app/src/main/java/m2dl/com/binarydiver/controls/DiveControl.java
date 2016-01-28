package m2dl.com.binarydiver.controls;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import m2dl.com.binarydiver.exceptions.UnsupportedMaterialException;

/**
 * Created by kelto on 28/01/16.
 */
public class DiveControl implements SensorEventListener {

    private static final int HEIGHT = 2560;
    private static final int WIDTH = 1440;
    private static final int FACTEUR_HORIZONTAL = 30;
    private static final int FACTEUR_VERTICAL = 30;

    private SensorManager sensorManager;
    private Sensor accelerometer;

    public DiveControl(Context context) throws UnsupportedMaterialException {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer == null) {
            throw new UnsupportedMaterialException("The system does not provide the necessary material");
        }
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_GAME);
    }

    private void handleSensorValues(float x, float y, float z) {
        handleX(x);
        handleY(y);
    }

    private void handleX(float x) {
        /*posXFloat += -x * FACTEUR_HORIZONTAL;
        if (posXFloat > WIDTH) { //TODO: rajouter width - width du perso
            posXFloat = WIDTH;
        }
        if (posXFloat < 0) {
            posXFloat = 0;
        }
        int posX = (int)posXFloat;*/
    }

    private void handleY(float y) {
       /* posYFloat += -y * FACTEUR_VERTICAL;
        if (posYFloat > HEIGHT) { //TODO: rajouter height - height du perso
            posYFloat = HEIGHT;
        }
        if (posYFloat < 0) {
            posYFloat = 0;
        }
        int posY = (int)posYFloat;*/
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

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
