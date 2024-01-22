package com.example.servicetest;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MyService mService;
    Boolean mIsBound;
    EditText e1, e2;
    TextView resultView;
    Button start, stop, mul, div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = findViewById(R.id.display1);
        e1 = findViewById(R.id.text0);
        e2 = findViewById(R.id.text1);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        mul = findViewById(R.id.multiply);
        div = findViewById(R.id.div);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mIsBound){
            unbindService(serviceConnection);
            Log.d("MyServiceExample", "ServiceConnection:Disconnected.");
            mIsBound = false;
        }
    }

    private void startService(){
        bindService();
    }

    private void bindService(){
        Intent serviceBindIntent =  new Intent(this, MyService.class);
        bindService(serviceBindIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder iBinder) {
            Log.d("MyServiceExample", "ServiceConnection: connected to service.");
            MyService.MyBinder binder = (MyService.MyBinder) iBinder;
            mService = binder.getService();
            mIsBound = true;
            getRandomNumberFromService();
        }
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d("MyServiceExample", "ServiceConnection: disconnected from service.");
            mIsBound = false;
        }
    };

    private void getRandomNumberFromService() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = Integer.parseInt(e1.getText().toString());
                int num2 = Integer.parseInt(e2.getText().toString());
                Log.d("MyServiceExample", "Sum of two numbers: " + mService.getSumNumber(num1, num2));
                int sum = mService.getSumNumber(num1, num2);
                String q = "Sum of two numbers: ";
                String s = Integer.toString(sum);
                resultView.setText(q + s);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = Integer.parseInt(e1.getText().toString());
                int num2 = Integer.parseInt(e2.getText().toString());
                Log.d("MyServiceExample", "Subtraction of two numbers: " + mService.getSubractNumber(num1, num2));
                int sum = mService.getSubractNumber(num1, num2);
                String q = "Subtraction of two numbers: ";
                String s = Integer.toString(sum);
                resultView.setText(q + s);
            }
        });

        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = Integer.parseInt(e1.getText().toString());
                int num2 = Integer.parseInt(e2.getText().toString());
                Log.d("MyServiceExample", "Multiplication of two numbers: " + mService.getMultiplyNumber(num1, num2));
                int sum = mService.getMultiplyNumber(num1, num2);
                String q = "Multiplication of two numbers: ";
                String s = Integer.toString(sum);
                resultView.setText(q + s);
            }
        });

        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num1 = Integer.parseInt(e1.getText().toString());
                int num2 = Integer.parseInt(e2.getText().toString());
                Log.d("MyServiceExample", "Division of two numbers: " + mService.getDivideNumber(num1, num2));
                int sum = mService.getDivideNumber(num1, num2);
                String q = "Division of two numbers: ";
                String s = Integer.toString(sum);
                resultView.setText(q + s);
            }
        });
    }
}