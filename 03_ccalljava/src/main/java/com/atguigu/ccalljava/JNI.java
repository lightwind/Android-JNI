package com.atguigu.ccalljava;

import android.util.Log;

public class JNI {
    {
        System.loadLibrary("ccalljava");
    }

    /**
     * 当执行这个方法的时候，让C代码调用
     * public int add(int x, int y)
     */
    public native void callbackAdd();

    /**
     * 当执行这个方法的时候，让C代码调用
     * public void helloFromJava()
     */
    public native void callbackHelloFromJava();


    /**
     * 当执行这个方法的时候，让C代码调用void printString(String s)
     */
    public native void callbackPrintString();

    /**
     * 当执行这个方法的时候，让C代码静态方法 static void sayHello(String s)
     */
    public native void callbackSayHello();

    /**
     * 让C代码调用MainActivity中的showToast()方法
     */
    public native void callBackShowToast();


    public int add(int x, int y) {
        Log.e("TAG", "add() x=" + x + " y=" + y);
        return x + y;
    }

    public void helloFromJava() {
        Log.e("TAG", "helloFromJava()");
    }

    public void printString(String s) {
        Log.e("TAG", "C中输入的：" + s);
    }

    public static void sayHello(String s) {
        Log.e("TAG", "我是java代码中的JNI."
                + "java中的sayHello(String s)静态方法，我被C调用了:" + s);
    }
}
