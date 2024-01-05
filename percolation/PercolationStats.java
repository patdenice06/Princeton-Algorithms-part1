/* *****************************************************************************
 *  Compilation:  javac-algs4 PercolationStats.java
 *  Execution:    java-algs4 PercolationStats n t
 *
 * Patrick GIRAUD
 * patrickgiraud@hotmail.com
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private int size;
    private int trials;
    private double[] thresholds;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException(
                    "n and trials must be > 0");

        size = n;
        this.trials = trials;
        thresholds = new double[trials];

        doStats();
    }

    private void doStats() {
        Percolation percolation;
        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(size);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniformInt(1, size + 1),
                                 StdRandom.uniformInt(1, size + 1));
            }

            thresholds[i] = (double) percolation.numberOfOpenSites() / (size * size);
        }
    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev() / Math.sqrt(size));
    }


    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev() / Math.sqrt(size));
    }

    // test client (see below)
    public static void main(String[] args) {
        // Stopwatch stopwatch = new Stopwatch();
        if (args.length != 2)
            throw new IllegalArgumentException(
                    "The program requires 2 arguments : n and trials");

        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),
                                                   Integer.parseInt(args[1]));

        StdOut.println("mean\t\t\t = " + ps.mean());
        StdOut.println("stddev\t\t\t = " + ps.stddev());
        StdOut.println(
                "95% confidence interval\t = " + "[" + ps.confidenceLo() + ", "
                        + ps.confidenceHi() + "]");
        // double time = stopwatch.elapsedTime();
        // StdOut.println(time);
    }

}
