package com.liyuchain.p2ppbft;

/**
 * 投票信息类
 *
 * @Author: Igarashi
 * @Date: 2019-03-01 14:10
 */
public class VoteInfo {
    //投票状态码
    private int code;
    //待写入区块的内容
    private java.util.List<String> list;
    //待写入区块的内容的Merkle树根节点哈希值
    private String hash;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public java.util.List<String> getList() {
        return list;
    }

    public void setList(java.util.List<String> list) {
        this.list = list;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
