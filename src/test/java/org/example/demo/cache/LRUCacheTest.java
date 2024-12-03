package org.example.demo.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class LRUCacheTest {

    @InjectMocks
    private LRUCache lruCache;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(lruCache, "capacity", 3);
    }

    @Test
    void testPutAndGet() {
        lruCache.put(1, "value1");
        lruCache.put(2, "value2");
        lruCache.put(3, "value3");
        assertEquals("value1", lruCache.get(1));
        assertEquals("value2", lruCache.get(2));
        assertEquals("value3", lruCache.get(3));
    }

    @Test
    void testEvictionWhenCapacityExceeded() {
        lruCache.put(1, "value1");
        lruCache.put(2, "value2");
        lruCache.put(3, "value3");
        lruCache.put(4, "value4");

        assertNull(lruCache.get(1));
        assertEquals("value2", lruCache.get(2));
        assertEquals("value3", lruCache.get(3));
        assertEquals("value4", lruCache.get(4));
    }

    @Test
    void testGetUpdatesOrder() {
        lruCache.put(1, "value1");
        lruCache.put(2, "value2");
        lruCache.put(3, "value3");

        assertEquals("value1", lruCache.get(1));
        lruCache.put(4, "value4");
        assertNull(lruCache.get(2));
        assertEquals("value1", lruCache.get(1));
        assertEquals("value3", lruCache.get(3));
        assertEquals("value4", lruCache.get(4));
    }

    @Test
    void testPutUpdatesExistingKey() {
        lruCache.put(1, "value1");
        lruCache.put(2, "value2");
        lruCache.put(3, "value3");
        lruCache.put(1, "newValue1");

        assertEquals("newValue1", lruCache.get(1));
        assertEquals("value2", lruCache.get(2));
        assertEquals("value3", lruCache.get(3));
    }

    @Test
    void testGetNonExistentKey() {
        assertNull(lruCache.get(100));
    }
}