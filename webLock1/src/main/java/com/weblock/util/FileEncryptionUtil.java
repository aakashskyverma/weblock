package com.weblock.util;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class FileEncryptionUtil {
    private static final String ALGORITHM = "AES";

    public static byte[] encrypt(byte[] data, String key) throws GeneralSecurityException {
        SecretKey secretKey = generateKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, String key) throws GeneralSecurityException {
        SecretKey secretKey = generateKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    private static SecretKey generateKey(String keyString) {
        byte[] keyBytes = new byte[16]; // AES key size of 16 bytes
        byte[] originalKeyBytes = keyString.getBytes(StandardCharsets.UTF_8);
        System.arraycopy(originalKeyBytes, 0, keyBytes, 0, Math.min(originalKeyBytes.length, keyBytes.length));
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

}
