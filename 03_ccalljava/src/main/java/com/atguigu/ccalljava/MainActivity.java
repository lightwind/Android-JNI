package com.atguigu.ccalljava;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
    private JNI jni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jni = new JNI();
    }

    /**
     * 让C代码调用MainActiity里面的showToast
     */
    public native void callBackShowToast();


    public void onClick(View view){
//        jni.callbackAdd();
//        jni.callbackHelloFromJava();
//        jni.callbackPrintString();
//        jni.callbackSayHello();
//        jni.callBackShowToast();
        MainActivity.this.callBackShowToast();
    }

    public void showToast(){
        System.out.println("showToast----------------");
        Toast.makeText(MainActivity.this,"showToast----------------",Toast.LENGTH_SHORT).show();
    }
}
