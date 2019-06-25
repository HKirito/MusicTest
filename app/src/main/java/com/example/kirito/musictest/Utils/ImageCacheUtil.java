package com.example.kirito.musictest.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.InputStream;

public class ImageCacheUtil {
    public static Bitmap getResizedBitmap(String path, byte[] data,
                                          Context context, Uri uri, int target, boolean width) {
        BitmapFactory.Options options = null;

        if (target > 0) {

            BitmapFactory.Options info = new BitmapFactory.Options();
            // 这里设置true的时候，decode时候Bitmap返回的为空，
            // 将图片宽高读取放在Options里.
            info.inJustDecodeBounds = false;

            decode(path, data, context, uri, info);

            int dim = info.outWidth;
            if (!width)
                dim = Math.max(dim, info.outHeight);
            int ssize = sampleSize(dim, target);

            options = new BitmapFactory.Options();
            options.inSampleSize = ssize;

        }

        Bitmap bm = null;
        try {
            bm = decode(path, data, context, uri, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;

    }

    /**
     * 解析Bitmap的公用方法.
     *
     * @param path
     * @param data
     * @param context
     * @param uri
     * @param options
     * @return
     */
    public static Bitmap decode(String path, byte[] data, Context context,
                                Uri uri, BitmapFactory.Options options) {

        Bitmap result = null;

        if (path != null) {

            result = BitmapFactory.decodeFile(path, options);

        } else if (data != null) {

            result = BitmapFactory.decodeByteArray(data, 0, data.length,
                    options);

        } else if (uri != null) {
            // uri不为空的时候context也不要为空.
            ContentResolver cr = context.getContentResolver();
            InputStream inputStream = null;

            try {
                inputStream = cr.openInputStream(uri);
                result = BitmapFactory.decodeStream(inputStream, null, options);
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    /**
     * 获取合适的sampleSize.
     *
     * @param width
     * @param target
     * @return Size
     */
    public static int sampleSize(int width, int target) {
        int result = 1;
        for (int i = 0; i < 10; i++) {
            if (width < target * 2) {
                break;
            }
            width = width / 2;
            result = result * 2;
        }
        return result;
    }
}
