import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Creative Problem #19
 * <p>
 * Local minimum in a matrix. Given an n-by-n array a[] of n2 distinct integers,
 * design an algorithm that runs in time proportional to n log n to find a local
 * minimum: an pair of indices i and j such that a[i][j] < a[i+1][j], a[i][j] < a[i][j+1],
 * a[i][j] < a[i-1][j], and a[i][j] < a[i][j-1] (assuming the neighboring entry is in bounds).
 * <p>
 * Hint: Find the minimum entry in row n/2, say a[n/2][j]. If it's a local minimum,
 * then return it. Otherwise, check it's two vertical neighbors a[n/2-1][j] and a[n/2+1][j].
 * Recur in the half with the smaller neighbor.
 * <p>
 * Extra credit: Design an algorithm that takes times proportional to n.
 *
 * @author Patrick GIRAUD
 */

public class LocalMinimumInAMatrix {

    /**
     * Returns an n-by-n matrix of random integers uniformly in [0, 100)
     *
     * @param n Size for an n-by-n matrix
     * @return a 2D array of random integers
     */
    public static Integer[][] uniqueRandomIntegers(int n) {
        Set<Integer> distinctsIntegers = new LinkedHashSet<Integer>();
        while (distinctsIntegers.size() < n * n) {
            int r = StdRandom.uniformInt(0, 100);
            distinctsIntegers.add(r);
        }

        Integer[] di = distinctsIntegers.toArray(new Integer[0]);
        Integer[][] m = new Integer[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                m[i][j] = di[(i * n) + j];

        return m;
    }


    /**
     * Print the  n-by-n matrix
     *
     * @param a Matrix
     */
    private static void printMatrix(Integer[][] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                StdOut.printf("%-3d", a[i][j]);
            }
            StdOut.println();
        }
    }

    /**
     * Copy n-by-n matrix to file
     *
     * @param a The matrix
     * @param f The file
     */
    private static void copyToFile(Integer[][] a, String f) {
        Out out;
        // write to a file
        out = new Out(f);
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a.length; j++)
                out.println(a[i][j]);

        out.close();
    }

    private static int[] smallerNeighbor(int i, int j, Integer[][] a) {
        int[] res = new int[2];
        int l = a[i][j - 1];    // left
        int r = a[i][j + 1];    // right
        int t = a[i - 1][j];    // top
        int b = a[i + 1][j];    // bottom

        if (l < r && l < t && l < b) {
            res[0] = i;
            res[1] = j - 1;
        }

        if (r < l && r < t && r < b) {
            res[0] = i;
            res[1] = j + 1;
        }

        if (t < l && t < r && t < b) {
            res[0] = i - 1;
            res[1] = j;
        }

        if (b < l && b < r && b < t) {
            res[0] = i + 1;
            res[1] = j;
        }

        return res;
    }

    private static int[] localMinimumIn(Integer[][] a) {
        boolean find = false;
        int[] index = new int[2];
        int loI = 0, loJ = 0;
        int hiI = a.length - 1, hiJ = a.length - 1;
        int i = 0, j = 0;

        while (!find) {
            i = (loI + hiI) / 2;
            j = (loJ + hiJ) / 2;

            if (a[i][j] < a[i][j - 1] &&
                    a[i][j] < a[i][j + 1] &&
                    a[i][j] < a[i - 1][j] &&
                    a[i][j] < a[i + 1][j]) {
                index[0] = i;
                index[1] = j;
                find = true;
            }
            else {
                // the neighboring
                int l = a[i][j - 1];    // left
                int r = a[i][j + 1];    // right
                int t = a[i - 1][j];    // top
                int b = a[i + 1][j];    // bottom

                if (l < r && l < t && l < b)
                    hiJ = j - 1;

                else if (r < l && r < t && r < b)
                    loJ = j + 1;

                else if (t < l && t < r && t < b)
                    hiI = i - 1;

                else if (b < l && b < r && b < t)
                    loI = i + 1;
            }
        }

        return index;
    }


    public static void main(String[] args) {
        // set n for n-by-n array a[][] of n2 distinct integers of n2 distinct integers
        int n = 10;
        Integer[][] m = uniqueRandomIntegers(n);
        printMatrix(m);
        // String filename = n + "by" + n + "Matrix.txt";
        // copyToFile(a, filename);

        // Read file 6b6Matrix.txt and create the matrix
/*        In in = new In(args[0]);
        int[] a = in.readAllInts();
        Integer[][] m = new Integer[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                m[i][j] = a[(i * n) + j];

        printMatrix(m);*/

        // Search for a local minimum in the matrix
        int[] res = localMinimumIn(m);
        StdOut.println("Local minimun in " + "[" + res[0] + ", " + res[1] + "], value = "
                               + m[res[0]][res[1]]);

    }


}
