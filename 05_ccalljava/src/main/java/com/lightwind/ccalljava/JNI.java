package com.lightwind.ccalljava;

import android.util.Log;

/**
 * 功能：
 * 作者：刘洋
 * 时间：2017/10/9
 */

public class JNI {

    static {
        System.loadLibrary("ccallJava");
    }

    /**
     * 触发C代码，让其调用sayHello()方法
     */
    public native void callBackSayHello();

    public void sayHello() {
        Log.d("TAG", "sayHello: 11111");
    }
}
