package com.lightwind.guolu;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;

public class MainActivity extends Activity {

    {
        System.loadLibrary("guolu");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final PressureView pressureView = new PressureView(this);
        setContentView(pressureView);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(1000);
                    int pressure = Math.abs(getPressure());
                    pressureView.setPressure(pressure);

                    if (pressure > 220) {
                        break;
                    }
                }
            }
        }.start();
    }

    /**
     * native代码，调用C代码中的对应的方法
     */
    public native int getPressure();
}
