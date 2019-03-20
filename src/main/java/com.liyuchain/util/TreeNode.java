package com.liyuchain.util;

/**
 * @Author: Igarashi
 * @Date: 2019-02-27 16:57
 */
public class TreeNode {
    //左节点
    private TreeNode left;
    //右节点
    private TreeNode right;
    //节点数据
    private String data;
    //节点对应的哈希值
    private String hash;
    //节点名称
    private String name;

    //构造函数

    public TreeNode() {
    }

    public TreeNode(String data) {
        this.data = data;
        this.hash = SHAUtil.getSHA256BaseHutool(data);
        this.name = "[节点：" + data + "]";
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
