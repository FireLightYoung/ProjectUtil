package com.util.ming.projectutil.utils;

/**
 * 位运算工具类
 * <p>
 * https://blog.csdn.net/black_bird_cn/article/details/80171652
 */
public class BitwiseUtil {

    /**
     * 指定位置清零
     * 指index位置清零 ；index低位向高位数为
     *
     * @param num
     * @param index
     * @return
     */
    public static int setIndexZero(int num, int index) {
        int i = 1 << index;
        return num & ~i;
    }

    /**
     * 指定位置清零
     * 将数据值清为零
     *
     * @param num
     * @return
     */
    public static int setZero(int num) {
        return num & 0x0;
    }

    /**
     * 获取指定位数据(此方法使用时请按实际编辑)
     * 获取整型数的低八位的数字
     *
     * @param unm
     * @return
     */
    //
    public static int getHighRsualt(int unm) {
        return unm & 0xFF;
    }

    /**
     * 获取指定位数据(此方法使用时请按实际编辑)
     * 获取整型数的高八位的数字
     *
     * @param unm
     * @return
     */
    public static int getLowRsualt(int unm) {
        return unm & 0xFF00;
    }


    /**
     * 指定位段为1的正数
     * 二级制表达式中start到offset为1的正数
     *
     * @param start
     * @param offset
     * @return
     */
    public static int getBitValue(int start, int offset) {
        int e = -1 >>> (32 - offset);
        int d = -1 << start;
        return e & d;
    }

    /**
     * 位用算方法是否为偶数
     * a % 2 等价于 a & 1
     *
     * @param i
     * @return
     */

    public static boolean isOdd(int i) {
        return (i & 1) != 0;
    }


//    //设置整型数低四位全部为1，即返回最小为15的数字；
//    public int setResult(int num) {
//        return num | OXF;
//    }
//
//    //设置整型数最低位为1，即返回奇数
//    public int setOdd() {
//        return num | 1;
//    }

    /**
     * 定位翻转(此方法使用时请按实际编辑)
     * 整型数低八位数据进行翻位的操作
     *
     * @param num
     * @return
     */
    public static int turnHigh(int num) {
        return num ^ 0xFF;
    }

    /**
     * 定位翻转(此方法使用时请按实际编辑)
     * 整型数高八位数据进行翻位的操作
     *
     * @param num
     * @return
     */
    public static int turnLow(int num) {
        return num ^ 0xFF00;
    }

    /**
     * 交换两整形数[不需要引入第三个变量],
     * 风险：a和b为两个很大数，a+b结果超过Integer.MAX_VALUE的话,结果就是错误的
     *
     * @param a
     * @param b
     */
    public static void swap(int a, int b) {
        a ^= b;
        b ^= a;
        a ^= b;
    }

    /**
     * 求平均值
     * 比如有两个int类型变量x、y,首先要求x+y的和，再除
     * 以2，但是有可能x+y的结果会超过int的最大表示范围，
     * 所以位运算就派上用场啦。
     *
     * @param x
     * @param y
     * @return
     */
    public static int average(int x, int y) {
        return (x & y) + ((x ^ y) >> 1);
    }


    /**
     * 求绝对值
     *
     * @param x
     * @return
     */
    public static int abs(int x) {
        int y;
        y = x >> 31;
        return (x ^ y) - y;        //or: (x+y)^y
    }
}