package com.blazer.javaconcurrency.threadsafe.lru;

public class CacheNode {
    String key;
    String value;

    CacheNode next;
    CacheNode prev;

    public CacheNode(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
