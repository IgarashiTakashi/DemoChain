package com.liyuchain.util;

import com.xiaoleilu.hutool.crypto.digest.DigestUtil;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: Igarashi
 * @Date: 2019-02-27 10:18
 */
public class SHAUtil {

    /**
     * function 利用Apache commons-codec的工具类实现SHA-256加密
     *
     * @param originalStr 明文
     * @return 密文
     */
    public static String getSHA256BasedMD(String originalStr){
        MessageDigest messageDigest;
        String encodeStr = "";

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(originalStr.getBytes("UTF-8"));
            encodeStr =  Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * function 利用Hutool的工具类实现SHA-256加密
     *
     * @param originalStr 明文
     * @return 密文
     */
    public static String getSHA256BaseHutool(String originalStr){
        return DigestUtil.sha256Hex(originalStr);
    }

}
