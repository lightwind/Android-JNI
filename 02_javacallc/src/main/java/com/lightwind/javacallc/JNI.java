package com.lightwind.javacallc;

/**
 * 功能：Java调用C代码
 * 作者：刘洋
 * 时间：2017/9/30
 */

public class JNI {

    /**
     * 动态添加链接库
     * 在build.gradle中配置：
     * ndk{
     * moduleName "javacallc"
     * }
     * 然后在java中动态添加
     */
    static {
        System.loadLibrary("javacallc");
    }

    /**
     * 让C代码做加法运算，并将结果返回
     */
    public native int add(int x, int y);

    /**
     * 从Java传入字符串，C代码进行拼接，
     *
     * @param s I am from Java
     * @return I am from Java add I am from C
     */
    public native String sayHello(String s);

    /**
     * 让C代码给每个元素都加上10
     */
    public native int[] increaseArrayEles(int[] intArray);

    /**
     * 应用：检查密码是否正确，如果正确返回200，如果错误返回400
     */
    public native int checkPwd(String pwd);

}
