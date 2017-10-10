package com.lightwind.javacallc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    private JNI jni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jni = new JNI();
    }

    public void add(View view) {
        int result = jni.add(100, 30);
        Log.d("TAG", "result===" + result);
    }

    public void sayHello(View view) {
        String result = jni.sayHello("I am from Java");
        Log.d("TAG", "result===" + result);
    }

    public void increaseArrayEles(View view) {
        int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        jni.increaseArrayEles(array);
        for (int i = 0; i < array.length; i++) {
            Log.d("TAG", "array[" + i + "]==" + array[i]);
        }
    }

    public void checkPwd(View view) {
        int  result = jni.checkPwd("123456");
        Log.d("TAG", "result===" + result);
    }

}
