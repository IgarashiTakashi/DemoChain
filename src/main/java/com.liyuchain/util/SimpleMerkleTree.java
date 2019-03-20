package com.liyuchain.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 简化的Merkle树根节点哈希值计算
 *
 * @Author: Igarashi
 * @Date: 2019-02-27 10:54
 */
public class SimpleMerkleTree {

    //按Merkele树思想计算根节点哈希值
    public static String getTreeNodeHash(List<String> hashsList){
        if (hashsList == null || hashsList.size() == 0){
            return null;
        }

        while (hashsList.size() != 1){
            hashsList = getMerKleNodeList(hashsList);
        }

        return hashsList.get(0);
    }

    //按Merkle树思想计算根节点哈希值
    public static List<String> getMerKleNodeList(List<String> contentList){
        List<String> merKleNodeList = new ArrayList<String>();

        if (contentList == null || contentList.size() == 0){
            return null;
        }

        int index = 0, length = contentList.size();
        while (index < length){
            //获取左节点数据
            String left = contentList.get(index++);
            //获取右节点数据
            String right = "";
            if (index < length){
                right = contentList.get(index++);
            }

            //计算左右节点的父节点的哈希值
            String sha2HexValue = SHAUtil.getSHA256BaseHutool(left + right);
            merKleNodeList.add(sha2HexValue);
        }
        return merKleNodeList;
    }
}
