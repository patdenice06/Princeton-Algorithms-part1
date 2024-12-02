/* *****************************************************************************
 *  Name: Permutation.java
 *  Author : Patrick GIRAUD
 *  Date: 01/11/2024
 *  Description:
 *  https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.InputMismatchException;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);

        int n = 0;
        // enqueue each item from standard input
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            rq.enqueue(item);
            n++;
        }

        // assume that 0 ≤ k ≤ n, where n is the number of string on
        //  standard input. Note that you are not given n.
        if (k < 0 || k > n)
            throw new InputMismatchException("k must be between 0 and " + n);

        // print k items uniformly at random
        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue() + " ");
        }
        StdOut.println();
    }
}
