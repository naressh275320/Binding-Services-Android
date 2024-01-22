package com.example.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyService extends Service {
    // Binder given to clients (notice class declaration below)
    private final IBinder mBinder = new MyBinder();
    // Random number generator
    private final Random mGenerator = new Random();
    public class MyBinder extends Binder {
        MyService getService() {
            // Return this instance of MyService so clients can call public methods
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    /** method for clients to get a random number from 0 - 100 */
    public int getSumNumber(int a, int b) {
        return (a+b);
    }

    public int getSubractNumber(int a, int b) {
        return (a-b);
    }

    public int getMultiplyNumber(int a, int b) {
        return (a*b);
    }

    public int getDivideNumber(int a, int b) {
        return (a/b);
    }
}