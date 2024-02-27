package com.blazer.javaconcurrency.threadsafe.lru;

public interface LRUCache {
    void put(String key, String value);
    String get(String key);
}
