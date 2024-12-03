package org.example.demo.service.facade;

public interface Cache {
    String get(int key);

    void put(int key, String value);
}