#include <stdio.h>
#include <stdlib.h>
#include <jni.h>

/**
 * jstirng：返回值，表示返回Stirng
 * Java_全类名（里面的.换成_）_方法名
 * JNIEnv就是JNINativeInterface的别名
 * jobject jobj：谁调用设个方法就是谁的实例
 * 当前就是JNI.this
 */

jstring Java_com_lightwind_ndkdemo_JNI_sayHello(JNIEnv *env, jobject jobj) {
// jstring     (*NewStringUTF)(JNIEnv*, const char*);
    char *test = "I am from C";
    return (*env)->NewStringUTF(env, test);
}