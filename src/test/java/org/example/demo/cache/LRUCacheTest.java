//package org.example.demo.cache;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//class LRUCacheTest {
//
//    @Test
//    void testPutAndGet() {
//        LRUCache cache = new LRUCache(3);
//
//        cache.put(1, "A");
//        cache.put(2, "B");
//        cache.put(3, "C");
//
//        assertEquals("A", cache.get(1));
//        assertEquals("B", cache.get(2));
//        assertEquals("C", cache.get(3));
//
//        cache.get(1);
//
//        cache.put(4, "D");
//        assertNull(cache.get(2));
//        assertEquals("A", cache.get(1));
//        assertEquals("C", cache.get(3));
//        assertEquals("D", cache.get(4));
//    }
//
//    @Test
//    void testUpdateValue() {
//        LRUCache cache = new LRUCache(2);
//
//        cache.put(1, "A");
//        cache.put(2, "B");
//        cache.put(1, "C");
//
//        assertEquals("C", cache.get(1));
//        assertEquals("B", cache.get(2));
//    }
//
//    @Test
//    void testEvictionOrder() {
//        LRUCache cache = new LRUCache(2);
//
//        cache.put(1, "A");
//        cache.put(2, "B");
//
//        cache.get(1);
//
//
//        cache.put(3, "C");
//        assertNull(cache.get(2));
//        assertEquals("A", cache.get(1));
//        assertEquals("C", cache.get(3));
//    }
//
//    @Test
//    void testCacheStateAfterMultipleOperations() {
//        LRUCache cache = new LRUCache(3);
//
//
//        for (int i = 1; i <= 1000; i++) {
//            cache.put(i, "Value" + i);
//            if (i > 3) {
//                assertNull(cache.get(i - 3));
//            }
//        }
//
//        assertEquals("Value1000", cache.get(1000));
//        assertEquals("Value999", cache.get(999));
//        assertEquals("Value998", cache.get(998));
//        assertNull(cache.get(997));
//    }
//
//    @Test
//    void testConcurrency() throws InterruptedException {
//        LRUCache cache = new LRUCache(10);
//
//
//        for (int i = 0; i < 10; i++) {
//            cache.put(i, "Value" + i);
//        }
//
//        Thread thread1 = new Thread(() -> cache.put(11, "Value11"));
//        Thread thread2 = new Thread(() -> cache.get(0));
//        thread1.start();
//        thread2.start();
//
//        thread1.join();
//        thread2.join();
//
//        assertEquals("Value11", cache.get(11));
//        assertNull(cache.get(0));
//    }
//}