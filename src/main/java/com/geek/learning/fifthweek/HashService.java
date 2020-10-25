package com.geek.learning.fifthweek;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 第五周作业
 * 用你熟悉的编程语言实现一致性 hash 算法。
 * 编写测试用例测试这个算法，测试 100 万 KV 数据，10 个服务器节点的情况下，计算这些 KV 数据在服务器上分布数量的标准差，以评估算法的存储负载不均衡性。
 */
public class HashService {
    private TreeMap<Long, Object> nodes = null;
    //真实服务器节点信息
    private List<Object> shards = new ArrayList();
    //设置虚拟节点数目
    private int VIRTUAL_NUM = 150;

    /**
     * 初始化一致环
     */
    public void init() {
        shards.add("192.168.0.1-服务器1");
        shards.add("192.168.0.2-服务器2");
        shards.add("192.168.0.3-服务器3");
        shards.add("192.168.0.4-服务器4");
        shards.add("192.168.0.5-服务器5");
        shards.add("192.168.0.6-服务器6");
        shards.add("192.168.0.7-服务器7");
        shards.add("192.168.0.8-服务器8");
        shards.add("192.168.0.9-服务器9");
        shards.add("192.168.0.10-服务器10");

        nodes = new TreeMap<Long, Object>();
        for (int i = 0; i < shards.size(); i++) {
            Object shardInfo = shards.get(i);
            for (int j = 0; j < VIRTUAL_NUM; j++) {
                nodes.put(hash(computeMd5("SHARD-" + i + "-NODE-" + j)), shardInfo);
            }
        }
    }

    /**
     * 根据key的hash值取得服务器节点信息
     *
     * @param hash
     * @return
     */
    public Object getShardInfo(long hash) {
        Long key = hash;
        SortedMap<Long, Object> tailMap = nodes.tailMap(key);
        if (tailMap.isEmpty()) {
            key = nodes.firstKey();
        } else {
            key = tailMap.firstKey();
        }
        return nodes.get(key);
    }

    /**
     * 打印圆环节点数据
     */
    public void printMap() {
        System.out.println(nodes);
    }

    /**
     * 根据2^32把节点分布到圆环上面。
     *
     * @param digest
     * @param nTime
     * @return
     */
    public long hash(byte[] digest, int nTime) {
        long rv = ((long) (digest[3 + nTime * 4] & 0xFF) << 24)
                | ((long) (digest[2 + nTime * 4] & 0xFF) << 16)
                | ((long) (digest[1 + nTime * 4] & 0xFF) << 8)
                | (digest[0 + nTime * 4] & 0xFF);

        return rv & 0xffffffffL; /* Truncate to 32-bits */
    }

    /**
     * 另一种hash方法
     * @param digest
     * @return
     */
    public long hash(byte[] digest){
        long res = ((long) (digest[3] & 0xFF) << 24) | ((long) (digest[2] & 0xFF) << 16) | ((long) (digest[1] & 0xFF) << 8)| (long) (digest[0] & 0xFF);
        return res;
    }

    /**
     * Get the md5 of the given key.
     * 计算MD5值
     */
    public byte[] computeMd5(String k) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes = null;
        try {
            keyBytes = k.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown string :" + k, e);
        }

        md5.update(keyBytes);
        return md5.digest();
    }

    public static void main(String[] args) {
        // 定义总数量
        int count = 1000000;

        // init
        HashService hash = new HashService();
        hash.init();

        // hash
        Map<String, Integer> computeMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String node = (String) hash.getShardInfo(hash.hash(hash.computeMd5(String.valueOf(i))));
            computeMap.merge(node, 1, Integer::sum);
        }

        // 计算当有10个服务器节点，每个节点虚拟150个节点计算标准差
        int average = count / computeMap.size();
        double s = 0D;
        for (Map.Entry<String, Integer> entry : computeMap.entrySet()) {
            s += Math.pow(entry.getValue() - average, 2);
        }
        s = Math.sqrt(s / computeMap.size());
        System.out.println("总体标准差为" + s);
    }
}
