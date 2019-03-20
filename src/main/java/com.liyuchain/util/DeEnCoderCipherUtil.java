package com.liyuchain.util;


import org.testng.util.Strings;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;

/**
 * 基于Cipher实现的加密和解密工具类
 *
 * @Author: Igarashi
 * @Date: 2019-01-31 21:41
 */
public class DeEnCoderCipherUtil {
    //加密、解密模式
    private final static String CIPHER_MODE = "DES";

    //DES
    public static String DEFAULT_DES_KEY = "区块链是分布式数据存储、点对点传输、共识机制、加密算法等计算机技术的新型应用模式";

    /**
     *  function 加密通用方法
     *
     * @param originalContent :明文
     * @param key :加密密钥
     * @return 密文
     */
    public static String encrypt(String originalContent, String key){
        //明文或加密密钥为空时
        if(Strings.isNullOrEmpty(originalContent)|| Strings.isNullOrEmpty(key)){
            return null;
        }

        //明文或加密密钥不为空时
        byte[] byteContent = new byte[0];
        try {
            byteContent = encrypt(originalContent.getBytes(), key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new BASE64Encoder().encode(byteContent);
    }

    /**
     * function 解密通用方法
     *
     * @param ciphertext 密文
     * @param key DES 解密密钥
     * @return 明文
     */
    public static String decrypt(String ciphertext,String key){
        //密文或加密密钥为空时
        if (Strings.isNullOrEmpty(ciphertext)||Strings.isNullOrEmpty(key)){
            return null;
        }

        //密文和加密密钥不为空时
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bufCiphertext = decoder.decodeBuffer(ciphertext);
            byte[] contentByte = decrypt(bufCiphertext, key.getBytes());
            return new String(contentByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * function 字节加密方法
     *
     * @param originalContent 明文
     * @param key 加密密钥的byte数组
     * @return 密文的byte数组
     */
    private static byte[] encrypt(byte[] originalContent,byte[] key) throws Exception {
        //1.生成可信任的随机数源
        SecureRandom secureRandom = new SecureRandom();
        //2.基于密钥数据创建DESKeySpec对象
        DESKeySpec desKeySpec = new DESKeySpec(key);
        //3.创建密钥工厂，将DESKeySpec转换为SecretKey对象来保存对称密钥
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(CIPHER_MODE);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        //4.Cipher对象实际完成加密操作，指定其支持的加密和解密算法
        Cipher cipher = Cipher.getInstance(CIPHER_MODE);
        //5.用密钥初始化Cipher对象，ENCRYPT_MODE表示加密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);
        //返回密文
        return cipher.doFinal(originalContent);
    }

    /**
     * function 字节解密方法
     *
     * @param ciphertextByte：字节密文
     * @param key 解密密钥（同加密密钥）byte数组
     * @return 明文byte数组
     */
    private static byte[] decrypt(byte[] ciphertextByte,byte[] key) throws Exception {
        //1.生成可信任的随机数源
        SecureRandom secureRandom = new SecureRandom();
        //2.从原始密钥数据创建DESKeySpec对象
        DESKeySpec desKeySpec = new DESKeySpec(key);
        //3.创建密钥工厂，将DESKeySpec转换成SecretKey对象来保存对称密钥
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(CIPHER_MODE);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        //4.Cipher对象实际完成解密操作，指定其支持响应的加密和解密算法
        Cipher cipher = Cipher.getInstance(CIPHER_MODE);
        //5.用密钥初始化Cipher对象，DECRYPT_MODE表示解密模式
        cipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);
        //返回明文
        return cipher.doFinal(ciphertextByte);
    }

}
