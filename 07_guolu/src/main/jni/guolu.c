//
// Created by lightwind on 2017/10/10.
//
#include <stdio.h>
#include <stdlib.h>
#include <jni.h>


/**
 * 得到锅炉
 */
int pressure = 20;

int getPressure() {
    int increase = rand() % 20;
    pressure += increase;
    return pressure;
}

/**
 * 从锅炉的感应器中的到锅炉的压力值
 */
jint JNICALL Java_com_lightwind_guolu_MainActivity_getPressure(JNIEnv *env, jobject instance) {
    int pressure = getPressure();
    return pressure;
}