package com.blazer.javaconcurrency.threadsafe.lru;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LRUCacheImpl implements LRUCache{

    private final ConcurrentHashMap<String, CacheNode> cache;
    private final Lock lock;

    private CacheNode head;
    private CacheNode tail;

    public LRUCacheImpl() {
        this.cache = new ConcurrentHashMap<String, CacheNode>();
        this.lock = new ReentrantLock();
        this.head = new CacheNode("", "");
        this.tail = new CacheNode("", "");
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    @Override
    public void put(String key, String value) {
        lock.lock();
        try {
            if (cache.containsKey(key)) {
                CacheNode node = cache.get(key);
                node.value = value;
                moveNodeToFront(node);
            } else {
                CacheNode node = new CacheNode(key, value);
                addNode(node);
                cache.put(key, node);
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String get(String key) {
        lock.lock();
        try {
            if (cache.containsKey(key)) {
                CacheNode node = cache.get(key);
                moveNodeToFront(node);
                return node.value;
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    private void addNode(CacheNode node) {
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    private void removeNode(CacheNode node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    private void moveNodeToFront(CacheNode node) {
        removeNode(node);
        addNode(node);
    }
}
