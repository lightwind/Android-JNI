#include <stdio.h>
#include <stdlib.h>
#include <jni.h>
#include <unistd.h>
#include <android/log.h>

#define LOG_TAG "System.out"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

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

void JNICALL
Java_com_lightwind_uninstall_MainActivity_uninstallListener(JNIEnv *env, jobject instance, jstring packName_,
                                                            jint sdkVersion) {
    // 返回3个值，大于0：父进程id，等于0，子进程id，负数出错
    int code = fork();
    if (code == 0) {
        // 判断软件是否被卸载
        LOGD("child == %d\n", code);
        int flag = 1;
        while (flag) {
            sleep(1);// 1秒
            char *packName = _JString2CStr(env, packName_);
            FILE *file = fopen(packName, "r");// /data/data/com.lightwind.uninstall
            if (file == NULL) {
                // 应用对应的包名不存在，说明软件被卸载了

//                if (sdkVersion < 17) {
//                    // 在android 4.2之前
//                    execlp("am", "am", "start", "-a", "android.intent.action.VIEW", "-d",
//                           "http://localhost:8080/", NULL);
//                } else {
//                    // 在android 4.2之后
//                    execlp("am", "am", "start", "--user", "0", "-a", "android.intent.action.VIEW", "-d",
//                           "http://localhost:8080/", (char *) NULL);
//                }

                execlp("am", "am", "start", "--user", "0", "-a", "android.intent.action.VIEW", "-d",
                       "http://localhost:8080/", (char *) NULL);

                flag = 0;// 停止循环
            }
        }
    } else if (code > 0) {
        // 父进程
        LOGD("father == %d\n", code);
    } else {
        // 出错了
        LOGD("error == %d\n", code);
    }
}