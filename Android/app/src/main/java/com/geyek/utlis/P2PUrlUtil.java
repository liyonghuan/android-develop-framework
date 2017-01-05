package com.geyek.utlis;

/**
 * Created by LiHuan on 08/19/2016.
 */
public class P2PUrlUtil {
    public static final String THUNDER  = "thunder://";
    public static final String FLASHGET = "flashget://";
    public static final String QQDL     = "qqdl://";

    /**
     * 将输入的迅雷专用下载地址转换成普通地址
     *
     * @param thunder 迅雷专用下载地址
     * @return 返回普通下载地址
     */
    public static String fromThunder(String thunder) {
        String  lowerCase = thunder.toLowerCase();
        boolean isThunder = lowerCase.startsWith(THUNDER);
        if (!isThunder) throw new P2pUrlFormatException("It's not a thunder url!");
        thunder = thunder.substring(THUNDER.length());
        thunder = Base64Util.fromBase64(thunder);
        thunder = thunder.substring(2, thunder.length() - 2);
        return thunder;
    }

    /**
     * 将输入的快车专用下载地址转换成普通地址
     *
     * @param flashget 快车专用下载地址
     * @return 返回普通下载地址
     */
    public static String fromFlashget(String flashget) {
        String  lowerCase  = flashget.toLowerCase();
        boolean isFlashget = lowerCase.startsWith(FLASHGET);
        if (!isFlashget) throw new P2pUrlFormatException("It's not a flashget url!");
        flashget = flashget.substring(FLASHGET.length());
        int signIndex = flashget.lastIndexOf('&');
        flashget = flashget.substring(0, signIndex);
        flashget = Base64Util.fromBase64(flashget);
        int keyLen = "[FLASHGET]".length();
        flashget = flashget.substring(keyLen, flashget.length() - keyLen);
        return flashget;
    }

    /**
     * 将输入的QQ旋风专用下载地址转换成普通地址
     *
     * @param qqdl QQ旋风专用下载地址
     * @return 返回普通下载地址
     */
    public static String fromQqdl(String qqdl) {
        String  lowerCase = qqdl.toLowerCase();
        boolean isQqdl    = lowerCase.startsWith(QQDL);
        if (!isQqdl) throw new P2pUrlFormatException("It's not a qqdl url!");
        qqdl = qqdl.substring(QQDL.length());
        qqdl = Base64Util.fromBase64(qqdl);
        return qqdl;
    }

    /**
     * 智能转换专用下载地址
     *
     * @param specialUrl 专用下载地址
     * @return 返回普通下载地址
     */
    public static String smartFromBase64(String specialUrl) {
        boolean isSpecial;
        String  lowerCase = specialUrl.toLowerCase();

        //判断是否是迅雷专用下载地址
        isSpecial = lowerCase.startsWith(THUNDER);
        if (isSpecial) return fromThunder(specialUrl);

        //判断是否是快车专用下载地址
        isSpecial = lowerCase.startsWith(FLASHGET);
        if (isSpecial) return fromFlashget(specialUrl);

        //判断是否是QQ旋风专用下载地址
        isSpecial = lowerCase.startsWith(QQDL);
        if (isSpecial) return fromQqdl(specialUrl);

        //如果都不是则抛出异常
        throw new P2pUrlFormatException("It's not a special url!");
    }

    /**
     * 将普通下载地址转换成迅雷专用下载地址
     * @param content 普通下载地址
     * @return 返回迅雷专用下载地址
     */
    public static String toThunder(String content) {
        content = "AA" + content + "ZZ";
        content = Base64Util.toBase64(content);
        content = THUNDER + content;
        return content;
    }

    /**
     * 将普通下载地址转换成快车专用下载地址
     * @param content 普通下载地址
     * @return 返回快车专用下载地址
     */
    public static String toFlashget(String content) {
        String key = "[FLASHGET]";
        String sign = "&geyek";
        content = key + content + key;
        content = Base64Util.toBase64(content);
        content = FLASHGET + content + sign;
        return content;
    }

    /**
     * 将普通下载地址转换成QQ旋风专用下载地址
     * @param content 普通下载地址
     * @return 返回QQ旋风专用下载地址
     */
    public static String toQqdl(String content) {
        content = Base64Util.toBase64(content);
        content = QQDL + content;
        return content;
    }

    /**
     * 根据指定的专用下载地址类型进行转换
     * @param content 普通下载地址连接
     * @param type 专用下载地址类型
     * @return 返回指定专用下载地址
     */
    public static String smartToBase64(String content, String type) {
        switch (type) {
            case THUNDER:   //如果是迅雷专用下载地址
                content = toThunder(content);
                break;
            case FLASHGET:  //如果是快车专用下载地址
                content = toFlashget(content);
                break;
            case QQDL:  //如果是QQ旋风专用下载地址
                content = toQqdl(content);
                break;
        }
        return content;
    }

    /**
     * P2pUrlFormatException 异常类
     * 如果输入的专用下载地址不符合要求,则抛出此异常
     */
    private static class P2pUrlFormatException extends RuntimeException {
        public P2pUrlFormatException() {

        }

        public P2pUrlFormatException(String detailMessage) {
            super(detailMessage);
        }
    }
}
