import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomizedQueueTests {

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
    }

    /********************************************************************************
     *  TESTING CORRECTNESS. Testing correctness of RandomizedQueue
     ********************************************************************************/
    @Test
    void test1_Correctness_RandomizedQueue() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        assertTrue(rq.isEmpty());
        rq.enqueue(1);
        assertFalse(rq.isEmpty());
        assertEquals(1, rq.dequeue());
        assertTrue(rq.isEmpty());
        rq.enqueue(2);
        rq.enqueue(3);
        rq.dequeue();
        assertEquals(1, rq.size());
        rq.dequeue();
        assertEquals(0, rq.size());
        assertTrue(rq.isEmpty());
    }


    /********************************************************************************
     *  Test 11: create two nested iterators over the same randomized queue of size n
     ********************************************************************************/
    @Test
    void test11_Correctness_RandomizedQueue() {
        // From https://coursera.cs.princeton.edu/algs4/assignments/queues/faq.php
        // Test that multiple iterators can be used simultaneously and operate independently of one another
        StdOut.println("Test with two nested iterators");
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }
    }


    /********************************************************************************
     *  Test 12: create two parallel iterators over the same randomized queue of size n
     ********************************************************************************/
    @Test
    void test12_Correctness_RandomizedQueue() {
        StdOut.println("Test with two parallel iterators");
        int n = 10;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue)
            StdOut.print(a + " ");
        StdOut.println();
        for (int b : queue)
            StdOut.print(b + " ");
        StdOut.println();
    }

    /********************************************************************************
     *  Test 16: check randomness of sample() by enqueueing n items, repeatedly calling
     *          sample(), and counting the frequency of each item
     ********************************************************************************/
    /*
      * n = 3, trials = 12000

            value  observed  expected   2*O*ln(O/E)
        -------------------------------------------
                A      3889    4000.0       -218.89
                B      8111    4000.0      11467.77
                C         0    4000.0          0.00
        -------------------------------------------
                      12000   12000.0      11248.88

    G-statistic = 11248.88 (p-value = 0.000000, reject if p-value <= 0.0001)
    Note: a correct solution will fail this test by bad luck 1 time in 10,000.
     */
    @Test
    void test16_Correctness_RandomizedQueue() {
        StdOut.println("Test 16: check randomness of sample()");
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        int cA = 0, cB = 0, cC = 0;
        String s;
        for (int i = 0; i < 12000; i++) {
            s = queue.sample();
            if (s.equals("A")) cA++;
            if (s.equals("B")) cB++;
            if (s.equals("C")) cC++;
            // StdOut.print(queue.sample() + " ");
        }
        StdOut.println("A = " + cA);
        StdOut.println("B = " + cB);
        StdOut.println("C = " + cC);
    }

    /********************************************************************************
     Timing RandomizedQueue
     *  Test 4a-k: make n calls to enqueue() followed by n calls to dequeue()
     ********************************************************************************/
    /*
                        n  seconds
----------------------------------
=> passed        1024     0.00
=> passed        2048     0.00
=> passed        4096     0.01
=> passed        8192     0.05
=> passed       16384     0.21
=> passed       32768     0.91
=> FAILED       65536     4.54
   [ Most likely one of your operations is not constant time. ]
     */
    @Test
    void test_4a_k() {
        StdOut.println("Test test_4a_k: n calls to enqueue() followed by n calls to dequeue()");
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        Stopwatch stopwatch = new Stopwatch();
        int n = 1024;
        for (int k = 1; k < 8; k++) {
            for (int i = 0; i < n * k; i++) {
                queue.enqueue(i);
            }
            for (int i = 0; i < n * k; i++) {
                queue.dequeue();
            }

            double time = stopwatch.elapsedTime();
            StdOut.println(time);
        }
    }

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

