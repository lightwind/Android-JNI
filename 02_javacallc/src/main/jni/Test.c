#include "com_lightwind_javacallc_JNI.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

/**
 * 将Java中的String转化成C中的char
 */
char *_JString2CStr(JNIEnv *env, jstring jstr) {
    char *rtn = NULL;
    jclass clsstring = (*env)->FindClass(env, "java/lang/String");
    jstring strencode = (*env)->NewStringUTF(env, "GB2312");
    jmethodID mid = (*env)->GetMethodID(env, clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr = (jbyteArray) (*env)->CallObjectMethod(env, jstr, mid,
                                                            strencode); // String .getByte("GB2312");
    jsize alen = (*env)->GetArrayLength(env, barr);
    jbyte *ba = (*env)->GetByteArrayElements(env, barr, JNI_FALSE);
    if (alen > 0) {
        rtn = (char *) malloc(alen + 1); //"\0"
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    (*env)->ReleaseByteArrayElements(env, barr, ba, 0);
    return rtn;
}

/**
jint：返回值
*/
jint Java_com_lightwind_javacallc_JNI_add(JNIEnv *env, jobject jobj, jint ji, jint jj) {
    int result = ji + jj;
    return result;
};

JNIEXPORT jstring JNICALL Java_com_lightwind_javacallc_JNI_sayHello
        (JNIEnv *env, jobject jobj, jstring js) {
    char *fromJava = _JString2CStr(env, js);
    // C：
    char *fromC = " add I am from C";
    // 拼接函数，会将拼接的结果放在第一个参数中
    strcat(fromJava, fromC);

    return (*env)->NewStringUTF(env, fromJava);
};

/**
 * 给每个元素加上10
 */
JNIEXPORT jintArray JNICALL Java_com_lightwind_javacallc_JNI_increaseArrayEles
        (JNIEnv *env, jobject jobj, jintArray jarray) {
    // 1、得到数组的长度
    jsize size = (*env)->GetArrayLength(env, jarray);
    // 2、得到数组元素
    // JNI_FALSE表示数组的修改就在当前空间操作
    jint *intArray = (*env)->GetIntArrayElements(env, jarray, JNI_FALSE);
    // 3、遍历数组，给每个元素加上10
    int i;
    for (i = 0; i < size; i++) {
//        *(intArray + i) = *(intArray + i) + 10;
        *(intArray + i) += 10;
    }
    // 4、返回结果
    return jarray;
};

JNIEXPORT jint JNICALL Java_com_lightwind_javacallc_JNI_checkPwd
        (JNIEnv *env, jobject jobj, jstring js) {
    // 服务器的密码是123456
    char *origin = "123456";
    char *fromUser = _JString2CStr(env, js);
    // strcmp函数，比较字符串是否相同
    int code = strcmp(origin, fromUser);
    if (code == 0) {
        return 200;
    } else {
        return 400;
    }
};