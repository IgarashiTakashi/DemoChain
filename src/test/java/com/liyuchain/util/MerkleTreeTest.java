package com.liyuchain.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MerkleTree单元测试
 *
 * @Author: Igarashi
 * @Date: 2019-02-28 10:59
 */
public class MerkleTreeTest {

    @Test
    public void testMerkleTree(){
        //case1:List<String> contents = null;
        List<String> contents = null;
        Assert.assertEquals(new MerkleTree(contents).getList(),null);
        Assert.assertEquals(new MerkleTree(contents).getRoot(),null);

        //case2:List<String> contents = new ArrayList<>()；
        contents = new ArrayList<>();
      //  Assert.assertEquals(new MerkleTree(contents).getList(),null);
        //Assert.assertEquals(new MerkleTree(contents).getRoot(),null);

        //case3:List<String> contents 有内容 为偶数
        contents = Arrays.asList("天府广场", "锦江宾馆", "华西坝", "省体育馆");
        Assert.assertEquals(new MerkleTree(contents).getRoot().getHash().length(),64);
        Assert.assertEquals(new MerkleTree(contents).getRoot().getName(),"(([节点：天府广场]和[节点：锦江宾馆]的父节点)和([节点：华西坝]和[节点：省体育馆]的父节点)的父节点)");

        new MerkleTree(contents).traverseTreeNodes();

        //case4:List<String> contents 有内容 为奇数
        contents = Arrays.asList("天府广场", "锦江宾馆","华西坝");
        Assert.assertEquals(new MerkleTree(contents).getRoot().getHash().length(),64);
        Assert.assertEquals(new MerkleTree(contents).getRoot().getName(),"(([节点：天府广场]和[节点：锦江宾馆]的父节点)和(继承节点{[节点：华西坝]}成为父节点的父节点)");

        new MerkleTree(contents).traverseTreeNodes();
    }

}
