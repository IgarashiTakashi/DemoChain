package com.liyuchain.util;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Author: Igarashi
 * @Date: 2019-02-27 10:34
 */
public class SHAUtilTest {

    @Test
    public void testGetSHA256(){
        String originalStr = "会龙社区图书室";
        Assert.assertEquals(SHAUtil.getSHA256BasedMD(originalStr),SHAUtil.getSHA256BaseHutool(originalStr));
    }
}
