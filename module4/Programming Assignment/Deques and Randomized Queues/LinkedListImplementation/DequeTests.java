import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class DequeTests {

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
    }

    /********************************************************************************
     *  TESTING CORRECTNESS. Testing correctness of Deque
     ********************************************************************************/
    /********************************************************************************
     *  Test 3: check random calls to addFirst(), removeLast(),
     *  and isEmpty()
     ********************************************************************************/
    @Test
    void test3_1_Correctness_Deque() {
        Deque<Integer> deque = new Deque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(2);
        assertFalse(deque.isEmpty());
        assertEquals(2, deque.removeLast());
        deque.addFirst(5);
        assertEquals(5, deque.removeLast());

    }

    @Test
    void test3_2_Correctness_Deque() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        assertEquals(1, deque.removeLast());
        assertTrue(deque.isEmpty());
        assertTrue(deque.isEmpty());
        deque.addFirst(5);
        assertEquals(5, deque.removeLast());
    }

    @Test
    void test3_3_Correctness_Deque() {
        Deque<Integer> deque = new Deque<>();
        assertTrue(deque.isEmpty());
        assertTrue(deque.isEmpty());
        assertTrue(deque.isEmpty());
        deque.addFirst(4);
        deque.addFirst(5);
        assertEquals(4, deque.removeLast());
        assertEquals(5, deque.removeLast());
        deque.addFirst(8);
        assertEquals(8, deque.removeLast());
    }

    /********************************************************************************
     *  Test 5: check random calls to addLast(), removeFirst(), and isEmpty()
     ********************************************************************************/
    @Test
    void test5_Correctness_Deque() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        assertEquals(1, deque.removeFirst());
        deque.addLast(3);
        deque.addLast(4);
        // StdOut.println(deque.removeFirst());
        assertEquals(3, deque.removeFirst());
    }

    /********************************************************************************
     *  Test 7: check random calls to all methods except iterator()
     ********************************************************************************/
    @Test
    void test7_Correctness_Deque() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(2);
        assertEquals(2, deque.removeFirst());
        assertTrue(deque.isEmpty());
        deque.addLast(5);
        assertEquals(5, deque.removeFirst());
    }

    /********************************************************************************
     *  Test 8: check random calls to all methods, including iterator()
     ********************************************************************************/
    @Test
    void test8_Correctness_Deque() {
        Deque<Integer> deque = new Deque<>();
        assertEquals(0, deque.size());
        deque.addFirst(2);
        assertEquals(1, deque.size());
        assertEquals(2, deque.removeLast());
        deque.addLast(5);
        assertEquals(5, deque.removeFirst());
        assertEquals(0, deque.size());
        deque.addLast(8);
        assertEquals(8, deque.iterator().next());
    }

    /********************************************************************************
     *  Test 12: check iterator() after random calls to addFirst(), addLast(),
     *          removeFirst(), and removeLast() with probabilities (p1, p2, p3, p4)
     ********************************************************************************/
    @Test
    void test12_Correctness_Deque() {
        Deque<Integer> deque = new Deque<>();
        deque.addLast(1);
        assertEquals(1, deque.iterator().next());
        assertEquals(1, deque.removeFirst());
        assertFalse(deque.iterator().hasNext());
        deque.addLast(3);
        assertEquals(3, deque.iterator().next());
    }

    /********************************************************************************
     *  TESTING CORRECTNESS. Testing correctness of RandomizedQueue
     ********************************************************************************/

    
    // @Test
    // void failingTest() {
    //     fail("a failing test");
    // }
    // @Test
    // @Disabled("for demonstration purposes")
    // void skippedTest() {
    //     // not executed
    // }

    // @Test
    // void abortedTest() {
    //     assumeTrue("abc".contains("Z"));
    //     fail("test should have been aborted");
    // }
    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

}

