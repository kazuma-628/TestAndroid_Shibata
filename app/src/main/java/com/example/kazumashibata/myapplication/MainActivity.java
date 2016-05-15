package com.example.kazumashibata.myapplication;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    public static final String TAG = "TestApp";

    private int val = 0;
    private int val2 = 0;
/*
    static {
        System.loadLibrary("app");
    }

    public native String  testJNI();

*/
    private SensorManager mSensorManager;
    private boolean mIsSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




//        Log.d("", testJNI());

        //buttonを取得
        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(clicked);

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        Log.i(TAG,"onResume");

                /* センサーマネージャを取得する */
        mSensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

        // 照度センサー
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_LIGHT);

        // センサマネージャへリスナーを登録(implements SensorEventListenerにより、thisで登録する)
        if (sensors.size() > 0) {
            Sensor sensor = sensors.get(0);
            mIsSensor = mSensorManager.registerListener(this, sensor, 1000000);

        /*
         * int  SENSOR_DELAY_FASTEST    get sensor data as fast as possible
         * int  SENSOR_DELAY_GAME       rate suitable for games
         * int  SENSOR_DELAY_NORMAL     rate (default) suitable for screen orientation changes
         * int  SENSOR_DELAY_UI         rate suitable for the user interface
         */
        }
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // センサーの精度が変更されると呼ばれる
        Log.i(TAG,"onAccuracyChanged");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // センサーの値が変化すると呼ばれる
        if (event.sensor.getType() == Sensor.TYPE_LIGHT)
        {

            TextView textView3 = (TextView)findViewById(R.id.textView3);
            textView3.setText("" + event.values[0]);
        }
    }

    private View.OnClickListener clicked = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Log.i(TAG,"OnClickListener");
            TextView textView = (TextView)findViewById(R.id.textView);
            textView.setText("吉村 は 天才 × " + val);

            if(val%10 == 0 && val != 0)
            {
                // インテントの生成
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                // SubActivity の起動
                startActivity(intent);
            }
            val++;

        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        Log.i(TAG,"onTouchEvent");


        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                Log.d("TouchEvent", "getAction()" + "ACTION_DOWN");
                TextView textView = (TextView)findViewById(R.id.textView2);
                textView.setText("吉村 は ずるい × " + val2);

                if(val2%10 == 0 && val2 != 0)
                {
                    // インテントの生成
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                    // SubActivity の起動
                    startActivity(intent);
                }
                val2++;
                break;
            case MotionEvent.ACTION_UP:
                Log.d("TouchEvent", "getAction()" + "ACTION_UP");
                break;
        }



        return true;
    }
}
