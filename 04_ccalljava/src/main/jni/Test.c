#include <jni.h>
#include <stdlib.h>
#include <stdio.h>


/**
 * 调用Java代码中的JNI类中的public void sayHello()方法
 */
void JNICALL Java_com_lightwind_ccalljava_JNI_callBackSayHello(JNIEnv *env, jobject instance) {

    jclass jclazz = (*env)->FindClass(env, "com/lightwind/ccalljava/JNI");
    jmethodID jmethodid = (*env)->GetMethodID(env, jclazz, "sayHello", "()V");
    jobject jobj = (*env)->AllocObject(env, jclazz);
    (*env)->CallVoidMethod(env, jobj, jmethodid);
}