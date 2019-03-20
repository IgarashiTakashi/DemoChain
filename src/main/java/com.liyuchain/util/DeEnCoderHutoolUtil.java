package com.liyuchain.util;

import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.crypto.asymmetric.KeyType;
import com.xiaoleilu.hutool.crypto.asymmetric.RSA;
import com.xiaoleilu.hutool.crypto.symmetric.DES;
import org.testng.util.Strings;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 基于Hutool工具类的加密解密类
 *
 * @Author: Igarashi
 * @Date: 2019-02-19 17:33
 */
public class DeEnCoderHutoolUtil {

    //构建RSA对象
    private static RSA rsa = new RSA();
    //获得私钥
    private static PrivateKey privateKey = rsa.getPrivateKey();
    //获得公钥
    private static PublicKey publicKey = rsa.getPublicKey();

    /**
     * function RSA加密通用方法：对称加密解密
     * @param originalContent 明文
     * @return 密文
     */
    public static String rsaEncrypt(String originalContent){

        //明文为空时
        if (Strings.isNullOrEmpty(originalContent)){
            return null;
        }

        //公钥加密，之后私钥解密
        return rsa.encryptStr(originalContent, KeyType.PublicKey);
    }

    /**
     * function RSA解密通用方法，对称加密解密
     * @param ciphertext 密文
     * @return 明文
     */
    public static String rsaDecrypt(String ciphertext){
        //密文为空时
        if (Strings.isNullOrEmpty(ciphertext)){
            return null;
        }

        return rsa.decryptStr(ciphertext, KeyType.PrivateKey);
    }

    /**
     * function DES加密通用方法：对称加密解密
     *
     * @param originalContent 明文
     * @param key 密钥
     * @return 密文
     */
    public static String desEncrypt(String originalContent,String key){
        if (Strings.isNullOrEmpty(originalContent)||Strings.isNullOrEmpty(key)) {
            return null;
        }
        //构建
        DES des = SecureUtil.des(key.getBytes());
        //加密
        return des.encryptHex(originalContent);
    }

    /**
     * function DES解密通用方法
     *
     * @param ciphertext 密文
     * @param key 密钥
     * @return 明文
     */
    public static String desDecrypt(String ciphertext,String key){
        if (Strings.isNullOrEmpty(ciphertext)||Strings.isNullOrEmpty(key)) {
            return null;
        }

        //构建
        DES des = SecureUtil.des(key.getBytes());
        //解密
        return des.decryptStr(ciphertext);
    }

}
