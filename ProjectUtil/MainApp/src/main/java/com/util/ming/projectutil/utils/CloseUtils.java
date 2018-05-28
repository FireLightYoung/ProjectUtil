package com.util.ming.projectutil.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Closeable;

/**
 * Created by ming on 17/8/18.
 */

public class CloseUtils {

    private CloseUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void closeQuietly(Closeable cloneable) {
        if (cloneable != null) {
            try {
                cloneable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
