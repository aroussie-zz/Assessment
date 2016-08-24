package com.allstate.alexandreroussiere.allstate.ui;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import com.allstate.alexandreroussiere.allstate.network.OnCoordinatesChangedListener;

/**
 * Created by Alexandre Roussière on 24/08/2016.
 */
public class AccelerometerPresenter implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private OnCoordinatesChangedListener listener;
    private Context mContext;
    private float x, y, z;

    public AccelerometerPresenter(OnCoordinatesChangedListener listener, Context context){
        mContext = context;
        this.listener = listener;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        checkAccelerometerExists();
    }

    private void checkAccelerometerExists(){
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        } else {
            Toast.makeText(mContext, "There is no accelerometer", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event){
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        this.listener.updateUI(x, y, z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void stopAccelerometer(){
        sensorManager.unregisterListener(this);
    }

    public void startAccelerometer(){
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
