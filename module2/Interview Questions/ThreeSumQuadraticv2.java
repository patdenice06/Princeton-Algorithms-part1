/******************************************************************************
 *  Compilation:  javac ThreeSumQuadraticv2.java
 *  Execution:    java ThreeSumQuadraticv2 input.txt
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   https://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 * Interview Questions: Analysis of Algorithms. Question1
 * 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes
 * time proportional to n^2 in the worst case. You may assume that you can sort
 * the nn integers in time proportional to n^2 or better.
 *
 * Design of algorithm : https://en.wikipedia.org/wiki/3SUM
 * section: Quadratic algorithm
 *
 *
 ******************************************************************************/

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The {@code ThreeSumQuadratic} class provides static methods for counting
 * and printing the number of triples in an array of distinct integers that
 * sum to 0 (ignoring integer overflow).
 * <p>
 * This implementation uses sorting and binary search and takes time
 * proportional to n^2, where n is the number of integers.
 * <p>
 * For additional documentation, see <a
 * href="https://en.wikipedia.org/wiki/3SUM">Section
 * Quadratic algorithm</a> of
 *
 * @author Michael Ho mann <hoffmann@inf.ethz.ch>
 */
public class ThreeSumQuadraticv2 {

    // Do not instantiate.
    private ThreeSumQuadraticv2() {
    }

    /**
     * Prints to standard output the (i, j, k) with {@code i < j < k}
     * such that {@code a[i] + a[j] + a[k] == 0}.
     *
     * @param a the array of integers
     * @throws IllegalArgumentException if the array contains duplicate integers
     */
    public static void printAll(Integer[] a) {
        int n = a.length;
        for (int i = 0; i <= n - 2; i++) {
            int j = i;
            int k = n - 1;
            while (k >= j) {
                if (a[i] + a[j] + a[k] == 0)
                    StdOut.println(a[i] + " " + a[j] + " " + a[k]);
                if (a[i] + a[j] + a[k] > 0)
                    k--;
                else
                    j++;
            }
        }
    }

    /**
     * Returns a 1D array of random integers uniformly in [a, b)
     *
     * @param n Size of the array containing n distinct numbers
     * @param a low bound
     * @param b high bound
     * @return a 1D array of random integers
     */
    public static Integer[] uniqueRandomIntegers(int n, int a, int b) {
        Set<Integer> distinctsIntegers = new LinkedHashSet<Integer>();
        while (distinctsIntegers.size() < n) {
            distinctsIntegers.add(StdRandom.uniformInt(a, b));
        }
        Integer[] di = distinctsIntegers.toArray(new Integer[0]);
        Arrays.sort(di);
        return di;
    }


    /**
     * Returns the number of triples (i, j, k) with {@code i < j < k}
     * such that {@code a[i] + a[j] + a[k] == 0}.
     *
     * @param S the array of integers
     * @return the number of triples (i, j, k) with {@code i < j < k}
     * such that {@code a[i] + a[j] + a[k] == 0}
     */
    public static int count(Integer[] S) {
        int n = S.length;
        int count = 0;
        for (int i = 0; i <= n - 2; i++) {
            int a = S[i];
            int start = i + 1;
            int end = n - 1;
            while (start < end) {
                int b = S[start];
                int c = S[end];
                if (a + b + c == 0) {
                    count++;
                    start++;
                    end--;
                }
                else if (a + b + c > 0)
                    end--;
                else
                    start++;
            }
        }
        return count;
    }

    /**
     * Reads in a sequence of distinct integers from a file, specified as a command-line argument;
     * counts the number of triples sum to exactly zero; prints out the time to perform
     * the computation.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // In in = new In(args[0]);
        // int[] a = in.readAllInts();

        int n = 100;
        int begin = -1000;
        int end = 1000;
        Integer[] a = uniqueRandomIntegers(n, begin, end);
        StdOut.println("The number of triples whose sum is 0");
        Stopwatch timer = new Stopwatch();
        int count = count(a);
        StdOut.println("elapsed time = " + timer.elapsedTime());
        StdOut.println(count);

        // StdOut.println("All the triples whose sum is 0");
        // printAll(a);
    }
}
