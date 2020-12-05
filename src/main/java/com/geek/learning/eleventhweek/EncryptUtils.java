package com.geek.learning.eleventhweek;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;

public class EncryptUtils {
    private static String SALT = "lixuan";

    /**
     * <p>MD5加密</p>
     * @param plainText 待加密字符串
     * @return          加密后字符串
     */
    public static String md5Eccrypt(String plainText) {
        if (StringUtils.isEmpty(plainText)) {
            return "";
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] srcBytes = (plainText + SALT).getBytes();
            md5.update(srcBytes);
            byte[] resultBytes = md5.digest();
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < resultBytes.length; i++) {
                int val = ((int) resultBytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception ex) {
            System.out.println("加密失败");
            return "";
        }
    }
}
