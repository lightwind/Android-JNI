package com.lightwind.mtxx;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.mt.mtxx.image.JNI;

public class MainActivity extends AppCompatActivity {

    private JNI jni;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.iv_icon);
        jni = new JNI();
    }

    public void lomoHDR(View view) {
        // 把图片转化成数组
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tiananmen);
        // 装图片的像素
        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        /**
         * 参数
         * pixels       接收位图颜色值的数组
         * offset      写入到pixels[]中的第一个像素索引值
         * stride       pixels[]中的行间距个数值(必须大于等于位图宽度)。可以为负数
         * x             从位图中读取的第一个像素的x坐标值。
         * y             从位图中读取的第一个像素的y坐标值
         * width       从每一行中读取的像素宽度
         * height 　　读取的行数
         * 异常
         */
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        // 将数组传给C代码处理
        jni.StyleLomoHDR(pixels, bitmap.getWidth(), bitmap.getHeight());
        // 将处理好的数组重新生成图片
        bitmap = Bitmap.createBitmap(pixels, bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        // 显示图片
        imageView.setImageBitmap(bitmap);
    }

    public void lomoC(View view) {
    }

    public void lomoB(View view) {
    }


    public void reset(View view) {
        imageView.setImageResource(R.drawable.tiananmen);
    }
}
