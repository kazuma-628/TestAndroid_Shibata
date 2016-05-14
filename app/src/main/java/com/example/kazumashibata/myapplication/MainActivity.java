package com.example.kazumashibata.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public static final String TAG = "TestApp";

    private int val = 0;
/*
    static {
        System.loadLibrary("app");
    }

    public native String  testJNI();

*/

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
    private View.OnClickListener clicked = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            Log.i(TAG,"onClick");
            TextView textView = (TextView)findViewById(R.id.textView);
            textView.setText("吉村 = 天才 × " + val);

            if(10 == val)
            {
                // インテントの生成
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                // SubActivity の起動
                startActivity(intent);
            }
            val++;

        }
    };
}
