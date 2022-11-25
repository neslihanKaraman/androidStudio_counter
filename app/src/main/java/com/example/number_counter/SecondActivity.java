package com.example.number_counter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.math.MathUtils;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class SecondActivity extends AppCompatActivity {
    ImageButton settings,minus,plus;
    public static TextView value;
    MediaPlayer mp;
    Vibrator vibrator;

    SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        SharedPreferencesClass.getInstance(getApplicationContext());

        settings=(ImageButton)findViewById(R.id.ibttn_settings);
        minus=(ImageButton)findViewById(R.id.ibttn_minus);
        plus=(ImageButton)findViewById(R.id.ibttn_plus);
        value=(TextView) findViewById(R.id.txt_counter);
        mp=MediaPlayer.create(getApplicationContext(),R.raw.beep2);
        vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor shake=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent != null) {
                    float x = sensorEvent.values[0];
                    float y = sensorEvent.values[1];
                    float z = sensorEvent.values[2];

                    float sum = Math.abs(x) + Math.abs(y) + Math.abs(z);
                    if (sum > 17) {
                        SharedPreferencesClass.setCounterValue(0);
                        value.setText(String.valueOf(SharedPreferencesClass.getCounterValue()));
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) { }
        };
        sensorManager.registerListener(sensorEventListener,shake,SensorManager.SENSOR_DELAY_NORMAL);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCounter(1);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCounter(-1);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== android.view.KeyEvent.KEYCODE_VOLUME_DOWN){
            updateCounter(-5);
            return true;
        }
        return false;
    }
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == android.view.KeyEvent.KEYCODE_VOLUME_UP) {
            updateCounter(5);
            return true;
        }
        return false;
    }
    private void updateCounter(int number){
        if(number+SharedPreferencesClass.getCounterValue()<SharedPreferencesClass.getDownLimitValue() && SharedPreferencesClass.getDownSound()) {
            mp.start();
        }
        if(number+SharedPreferencesClass.getCounterValue()>SharedPreferencesClass.getUpLimitValue() && SharedPreferencesClass.getUpSound()){
            mp.start();
        }
        if(number+SharedPreferencesClass.getCounterValue()<SharedPreferencesClass.getDownLimitValue() && SharedPreferencesClass.getDownVib()){
            if(vibrator.hasVibrator()) {
                Toast.makeText(getApplicationContext(), "Alt limit titreşim uyarısı", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(2000);
            }
        }
        if(number+SharedPreferencesClass.getCounterValue()>SharedPreferencesClass.getUpLimitValue()  && SharedPreferencesClass.getUpVib()){
            if(vibrator.hasVibrator()) {
                Toast.makeText(getApplicationContext(), "Üst limit titreşim uyarısı", Toast.LENGTH_SHORT).show();
                vibrator.vibrate(2000);
            }
        }
        SharedPreferencesClass.setCounterValue(MathUtils.clamp(number+SharedPreferencesClass.getCounterValue(),SharedPreferencesClass.getDownLimitValue(),SharedPreferencesClass.getUpLimitValue() ));
        value.setText(String.valueOf(SharedPreferencesClass.getCounterValue()));

    }
}