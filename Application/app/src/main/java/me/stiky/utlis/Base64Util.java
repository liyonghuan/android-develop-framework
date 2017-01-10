package me.stiky.utlis;

import java.io.UnsupportedEncodingException;

/**
 * Created by LiHuan on 08/19/2016.
 */
public class Base64Util {
    /**
     * 声明BASE64的可打印64位字符
     */
    private final static char[] BASE64 = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'
    };

    /**
     * 根据指定位置获取BASE64常量表中的字符
     *
     * @param position 指定获取字符的位置
     * @return 返回一个BASE64中的字符
     */
    private static char getChar(int position) {
        char c = 0;
        c = BASE64[position];
        return c;
    }

    /**
     * 根据三个字符进行BASE64加密得到4个字符的字符串
     *
     * @param a 第一个输入的字符
     * @param b 第二个输入的字符
     * @param c 第三个输入的字符
     * @return 返回经过BASE64加密后长度为4个字符的字符串
     */
    private static String getString4Byte(int a, int b, int c) {
        StringBuffer sb = new StringBuffer();

        int n1 = a >>> 2 & 0b111111;
        sb.append(getChar(n1)); //获取第一个值

        int n2 = ((a & 0b11) << 4) | ((b >>> 4) & 0b1111);
        sb.append(getChar(n2));  //获取第二个值

        int n3 = ((b & 0b1111) << 2) | ((c >>> 6) & 0b11);
        sb.append(getChar(n3)); //获取第三个值

        int n4 = c & 0b111111;
        sb.append(getChar(n4));

        return sb.toString();
    }

    /**
     * 根据输入的内容经过处理返回BASE64加密后的密文
     *
     * @param content 需要进行处理的BASE64明文
     * @return 返回加密处理后的密文
     */
    public static String toBase64(String content) {
        StringBuffer sb = new StringBuffer();

        byte[] bytes;   //用于存储输入的内容的byte值
        try {
            bytes = content.getBytes("UTF-8");  //默认使用UTF-8来解析明文
        } catch (UnsupportedEncodingException e) {
            bytes = content.getBytes(); //如果UTF-8不存在,则使用默认的编码,会根据系统属性来定
        }

        int    len     = bytes.length; //优化:在循环的时候重复获取长度
        int    process = 0; //用于记录当前处理的进度
        String string4Byte;
        while (process < len) {
            if (process + 1 == len) {
                string4Byte = getString4Byte(bytes[process], 0, 0);
                string4Byte = string4Byte.substring(0, string4Byte.length() - 2);
                string4Byte += "==";
            } else if (process + 2 == len) {
                string4Byte = getString4Byte(bytes[process], bytes[process + 1], 0);
                string4Byte = string4Byte.substring(0, string4Byte.length() - 1);
                string4Byte += "=";
            } else {
                string4Byte = getString4Byte(bytes[process], bytes[process + 1], bytes[process + 2]);
            }
            sb.append(string4Byte);
            process += 3;
        }

        return sb.toString();
    }

    /**
     * 根据Base64密文解密获得明文
     *
     * @param content Base64密文内容
     * @return 返回解密后的明文
     */
    public static String fromBase64(String content) throws Base64FormatException {
        byte[] bytes;   //用于存储输入的内容的byte值
        try {
            bytes = content.getBytes("UTF-8");  //默认使用UTF-8来解析明文
        } catch (UnsupportedEncodingException e) {
            bytes = content.getBytes(); //如果UTF-8不存在,则使用默认的编码,会根据系统属性来定
        }

        int len = bytes.length; //优化:在循环的时候重复获取长度
        if (len % 4 != 0) throw new Base64FormatException("this's String length illegal");

        StringBuffer sb = new StringBuffer();

        int    process = 0;
        String string4Base64;
        while (process < len) {
            string4Base64 = getString4Base64(bytes[process], bytes[process + 1], bytes[process + 2], bytes[process + 3]);
            sb.append(string4Base64);
            process += 4;
        }

        return sb.toString();
    }

    /**
     * 根据Base64的算法,密文每4个字符代表明文3个字符
     *
     * @param a 第一个密文字符
     * @param b 第二个密文字符
     * @param c 第三个密文字符
     * @param d 第四个密文字符
     * @return 返回4个Base64密文字符解密后的3个明文字符
     */
    private static String getString4Base64(int a, int b, int c, int d) {
        String base64 = new String(BASE64);
        int    c1     = base64.indexOf(a);
        int    c2     = base64.indexOf(b);
        int    c3     = base64.indexOf(c);
        int    c4     = base64.indexOf(d);

        byte b1 = (byte) (((c1 << 2) & 0b11111100) | ((c2 >>> 4) & 0b11));
        byte b2 = (byte) (((c2 << 4) & 0b11110000) | ((c3 >>> 2) & 0b1111));
        byte b3 = (byte) (((c3 << 6) & 0b11000000) | (c4 & 0b111111));

        byte[] bytes;
        if (c3 == -1) {
            bytes = new byte[1];
            bytes[0] = b1;
        } else if (c4 == -1) {
            bytes = new byte[2];
            bytes[0] = b1;
            bytes[1] = b2;
        } else {
            bytes = new byte[3];
            bytes[0] = b1;
            bytes[1] = b2;
            bytes[2] = b3;
        }

        String result = new String(bytes);

        return result;
    }

    /**
     * 当Base64的密文不是4的整数倍时,表明密文错误
     */
    public static class Base64FormatException extends Exception {   //如果是继承Exception则必须强制处理异常
        private static final long serialVersionUID = 1L;

        public Base64FormatException() {
        }

        public Base64FormatException(String detailMessage) {
            super(detailMessage);
        }
    }
}
