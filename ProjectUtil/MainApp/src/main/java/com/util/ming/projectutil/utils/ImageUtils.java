package com.util.ming.projectutil.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by ming on 16/12/15.
 */
public class ImageUtils {

    private ImageUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 根据一个路径，生成一个固定长宽的Bitmap来节省内存
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     * 根据一个路径，生成一个固定长宽的Bitmap来节省内存
     *
     * @param mPath
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap decodeSampledBitmapFromPath(String mPath, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mPath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(mPath, options);
    }

    /**
     * 判断该文件是否是图片
     *
     * @param imagePath 文件路径
     * @return true 是图片 false 图片文件损坏
     */
    public static boolean isImage(String imagePath) {
        if (TextUtils.isEmpty(imagePath)) {
            return false;
        }
        File file = new File(imagePath);
        if (!file.exists()) {
            return false;
        }
        //只读取图片的宽高，不将该文件读取到内存中，目的是下方设置图片的宽高时会使用，加载图片更改成了fresco
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        if (options.mCancel || options.outWidth == -1
                || options.outHeight == -1 || options.outWidth == 0 || options.outHeight == 0) {
            //表示图片已损毁
            return false;
        }
        return true;
    }
}
