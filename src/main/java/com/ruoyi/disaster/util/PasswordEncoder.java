package com.ruoyi.disaster.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 简单的密码加密工具类，替代Spring Security的BCryptPasswordEncoder
 */
public class PasswordEncoder {
    
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String ALGORITHM = "SHA-256";
    
    /**
     * 生成随机盐值
     */
    private static String generateSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return bytesToHex(salt);
    }
    
    /**
     * 字节数组转十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
    
    /**
     * 密码加密
     */
    public String encode(String password) {
        String salt = generateSalt();
        String saltedPassword = password + salt;
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            byte[] hash = digest.digest(saltedPassword.getBytes());
            return salt + bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法不可用", e);
        }
    }
    
    /**
     * 密码验证
     */
    public boolean matches(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null || encodedPassword.length() < 32) {
            return false;
        }
        // 提取盐值（前32位是盐值）
        String salt = encodedPassword.substring(0, 32);
        // 计算原始密码加盐后的哈希值
        String saltedPassword = rawPassword + salt;
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            byte[] hash = digest.digest(saltedPassword.getBytes());
            String hashedPassword = salt + bytesToHex(hash);
            // 比较哈希值
            return hashedPassword.equals(encodedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("加密算法不可用", e);
        }
    }
}