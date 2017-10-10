package com.lightwind.uninstall;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    {
        System.loadLibrary("uninstall");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uninstallListener("/data/data/com.lightwind.uninstall", Build.VERSION.SDK_INT);
    }

    /**
     * native代码
     */
    public native void uninstallListener(String packName, int sdkVersion);
}
