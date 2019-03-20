package com.liyuchain.util;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Author: Igarashi
 * @Date: 2019-02-17 17:26
 */
public class DeEnCoderCipherUtilTest {
    private static String ciphertextGlobal;

    @Test
    public void testEncrypt(){

        //case1:originalContent = null;key = null;
        String originalContant = null;
        String key = null;
        Assert.assertEquals(DeEnCoderCipherUtil.encrypt(originalContant, key),null);

        //case2:originalContent != null;key = null;
        originalContant = "院子文化创意园";
        key = null;
        Assert.assertEquals(DeEnCoderCipherUtil.encrypt(originalContant,key),null);

        //case3:originalContent = null;key != null;
        originalContant = null;
        key = "倪家桥党群服务中心";
        Assert.assertEquals(DeEnCoderCipherUtil.encrypt(originalContant,key),null);

        //case4:originalContent != null;key != null
        originalContant = "院子文化创意园";
        key = "倪家桥党群服务中心";
        ciphertextGlobal = DeEnCoderCipherUtil.encrypt(originalContant, key);
        Assert.assertEquals(ciphertextGlobal,"K03MzaXBxMqFQxoJXai1/isIdm7sbE9g");
        System.out.println(ciphertextGlobal);
    }

    @Test(dependsOnMethods = {"testEncrypt"})
    public void testDecrypt(){
        //case1:ciphertext = null;key = null;
        String ciphertext = null;
        String key = null;
        Assert.assertEquals(DeEnCoderCipherUtil.decrypt(ciphertext, key),null);

        //case2:originalContent != null;key = null;
        ciphertext = ciphertextGlobal;
        key = null;
        Assert.assertEquals(DeEnCoderCipherUtil.decrypt(ciphertext,key),null);

        //case3:originalContent = null;key != null;
        ciphertext = null;
        key = "倪家桥党群服务中心";
        Assert.assertEquals(DeEnCoderCipherUtil.decrypt(ciphertext,key),null);

        //case4:originalContent != null;key != null
        ciphertext = ciphertextGlobal;
        key = "倪家桥党群服务中心";
        Assert.assertEquals(DeEnCoderCipherUtil.decrypt(ciphertext,key),"院子文化创意园");
    }
}

