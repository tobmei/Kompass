package com.example.kompass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MainActivityListener implements SensorEventListener {

    SensorManager sensorManager;
    MainActivity mainActivity;

    private float[] magneticValues;
    private double direction;

    public MainActivityListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        sensorManager = (SensorManager) mainActivity.getSystemService(Context.SENSOR_SERVICE);

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.values.length > 0){
            if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
                magneticValues = event.values;
                //mT to Gauss
                double xG = magneticValues[0] / 100;
                double yG = magneticValues[1] / 100;

//                if(xG == 0){
//                    if(yG < 0)
//                        direction = 90.0;
//                    else
//                        direction = 0;
//                }
//                else {
//                    direction = Math.atan(yG/xG) * (180 / Math.PI);
//                    if (direction < 0)
//                        direction += 360;
//                    if (direction > 360)
//                        direction -= 360;
//                }

                direction = 90 - Math.atan2(yG,xG) * (180 / Math.PI);

                mainActivity.getSupportActionBar().setTitle("Degree: " + String.valueOf(direction));
                mainActivity.ivNeedle.setRotation((float) direction);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
