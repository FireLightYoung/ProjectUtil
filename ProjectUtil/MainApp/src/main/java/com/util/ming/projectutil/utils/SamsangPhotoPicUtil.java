package com.util.ming.projectutil.utils;

/**
 * Created by ming on 17/10/17.
 */

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by kfzx-zhangsl01 on 2016/4/11.
 * 用于处理三星手机拍照存储的缩略图被旋转
 */
public class SamsangPhotoPicUtil {

    public static int readPictureDegree(String path) {
        ExifInterface exifInterface = null;
        if (TextUtils.isEmpty(path)) {
            return 0;
        }
        int degree = 0;
        try {
            File file = new File(path);
            if (file.exists()) {
                exifInterface = new ExifInterface(path);
            } else {
                return 0;
            }
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return degree;
    }

    public static Bitmap changeBitmapDegree(Bitmap bitmap, int degree) {
        Bitmap b = null;
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        b = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return b;
    }


}
