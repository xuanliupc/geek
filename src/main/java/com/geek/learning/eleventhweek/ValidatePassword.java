package com.geek.learning.eleventhweek;

import java.util.HashMap;
import java.util.Map;

/**
 * 第十一周作业
 * 请用你熟悉的编程语言写一个用户密码验证函数，
 * Boolean checkPW（String 用户 ID，String 密码明文，String 密码密文），返回密码是否正确 boolean 值，
 * 密码加密算法使用你认为合适的加密算法。
 */
public class ValidatePassword {

    /**
     * 验证密码是否正确
     * @param userId      登录名
     * @param inputPwd    输入密码
     * @param encryptPwd  保存的密码密文
     */
    public static boolean checkPW(String userId, String inputPwd, String encryptPwd) {
        System.out.println("===输入用户名： " +userId);
        System.out.println("===输入密 码： " + inputPwd);
        String savedPwd = userStore.get(userId).getPassword();
        boolean flag = savedPwd.equals(EncryptUtils.md5Eccrypt(inputPwd));
        System.out.println("===密码验证： " + flag);
        return flag;
    }

    protected static Map<String, User> userStore;
    static {
        userStore = new HashMap<>();
        userStore.put("geek", new User("geek", EncryptUtils.md5Eccrypt("hello")));
        System.out.println("===系统中保存的用户信息===");
        for (Map.Entry<String, User> entry : userStore.entrySet()) {
            System.out.println("===用户名： " + entry.getValue().getUsetId());
            System.out.println("===密 码： " + entry.getValue().getPassword());
        }
    }

    public static void main(String[] args) {
        checkPW("geek", "hello", "");
        checkPW("geek", "test", "");
    }

}