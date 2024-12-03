package org.example.demo.cache;

import org.example.demo.service.facade.Cache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class LRUCache implements Cache {
    private static class Node {
        int key;
        String value;
        Node prev;
        Node next;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    @Value("${user.cache.capacity:10}")
    private int capacity;
    private final HashMap<Integer, Node> map;
    private final Node head;
    private final Node tail;

    public LRUCache() {
        this.map = new HashMap<>();
        this.head = new Node(0, null);
        this.tail = new Node(0, null);
        head.next = tail;
        tail.prev = head;
    }

    private synchronized void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private synchronized void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public synchronized String get(int key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node node = map.get(key);
        remove(node);
        addToHead(node);
        return node.value;
    }

    public synchronized void put(int key, String value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            node.value = value;
            addToHead(node);
        } else {
            if (map.size() >= capacity) {
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            addToHead(newNode);
            map.put(key, newNode);
        }
    }
}