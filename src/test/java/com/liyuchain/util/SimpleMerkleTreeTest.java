package com.liyuchain.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Igarashi
 * @Date: 2019-02-27 11:16
 */
public class SimpleMerkleTreeTest {

    @Test
    public void testGetMerkleNodeList(){

        //case: List<String> contentList = null;
        List<String> contentList = null;
        Assert.assertEquals(SimpleMerkleTree.getMerKleNodeList(contentList),null);

        //case2: List<String> contentList = new ArrayList<>():但无内容
        contentList = new ArrayList<>();
        Assert.assertEquals(SimpleMerkleTree.getMerKleNodeList(contentList),null);

        //case3: contentList 有内容填充
        contentList = Arrays.asList("华府大道", "天府五街", "天府三街", "世纪城");
        Assert.assertEquals(SimpleMerkleTree.getMerKleNodeList(contentList).size(),2);
    }

    @Test
    public void testGetTreeNodeHash(){

        //case1: List<String> contentList = null;
        List<String> contentList = null;
        Assert.assertEquals(SimpleMerkleTree.getTreeNodeHash(contentList),null);

        //case2: List<String> contentList = new ArrayList<>();但无内容
        contentList = new ArrayList<>();
        Assert.assertEquals(SimpleMerkleTree.getTreeNodeHash(contentList),null);

        //case3: contentList 有内容填充
        contentList = Arrays.asList("华府大道", "天府五街", "天府三街", "世纪城", "锦城广场");
        Assert.assertEquals(SimpleMerkleTree.getTreeNodeHash(contentList).length(),64);
    }
}
