package com.lightwind.ndkdemo;

/**
 * 功能：Java调用对应C代码
 * 作者：刘洋
 * 时间：2017/9/30
 */

public class JNI {

    {
        System.loadLibrary("Hello");
    }

    /**
     * 定义native方法
     * 调用C代码对应的方法
     */
    public native String sayHello();

}
