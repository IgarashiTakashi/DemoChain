package com.liyuchain.util;

import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Author: Igarashi
 * @Date: 2019-02-26 16:25
 */
public class DeEnCoderHutoolUtilTest {

    @Test
    public void testDesEncrypt(){
        //case1:originalContent = null;key = null;
        String originalContant = null;
        String key = null;
        Assert.assertEquals(DeEnCoderHutoolUtil.desEncrypt(originalContant, key),null);

        //case2:originalContent != null;key = null;
        originalContant = "院子文化创意园";
        Assert.assertEquals(DeEnCoderHutoolUtil.desEncrypt(originalContant,key),null);

        //case3:originalContent = null;key != null;
        originalContant = null;
        key = "倪家桥党群服务中心";
        Assert.assertEquals(DeEnCoderHutoolUtil.desEncrypt(originalContant,key),null);

        //case4:originalContent != null;key != null
        originalContant = "院子文化创意园";
        key = new String(SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded());
        String s = DeEnCoderHutoolUtil.desEncrypt(originalContant, key);
        Assert.assertNotNull(s);
        System.out.println(s);
    }

    @Test
    public void testDesDecrypt(){
        //case1:ciphertext = null;key = null;
        String ciphertext = null;
        String key = null;
        Assert.assertEquals(DeEnCoderHutoolUtil.desDecrypt(ciphertext, key),null);

        //case2:originalContent != null;key = null;
        String originalContant = "院子文化创意园";
        String keyTmp = new String(SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded());
        ciphertext = DeEnCoderHutoolUtil.desDecrypt(originalContant, keyTmp);
        Assert.assertEquals(DeEnCoderHutoolUtil.desDecrypt(ciphertext,key),null);

        //case3:originalContent = null;key != null;
        ciphertext = null;
        key = new String(SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded());
        Assert.assertEquals(DeEnCoderHutoolUtil.desDecrypt(ciphertext,key),null);

        //case4:originalContent != null;key != null
        ciphertext = DeEnCoderHutoolUtil.desDecrypt(ciphertext, key);
        key = "倪家桥党群服务中心";
        Assert.assertNotNull(DeEnCoderHutoolUtil.desDecrypt(ciphertext,key));
    }
}
