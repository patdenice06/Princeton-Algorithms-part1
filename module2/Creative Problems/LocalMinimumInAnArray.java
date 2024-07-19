/**
 * <B>Local minimum in an array.</B> Write a program that, given an array a[] of n distinct
 * integers, finds a local minimum: an index i such that both a[i] < a[i-1]
 * and a[i] < a[i+1] (assuming the neighboring entry is in bounds). Your program
 * should use ~ 2 lg n compares in the worst case.
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/14analysis/">
 * Creative Problems</a>
 * <p>
 * <B>CONSTRAINTS:</B>
 * <BR>- The arrays have at least 3 elements
 * <BR>- The first two numbers are decreasing and the last two numbers are increasing
 * <BR>-The numbers are uniques
 *
 * @author Patrick GIRAUD
 */
public class LocalMinimumInAnArray {
    // Printing colored text in the Java console
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String TEST_PASSED = GREEN + " PASSED" + RESET;
    private static final String TEST_FAILED = RED + " FAILED" + RESET;

    private static int localMinimum(int[] a) {
        boolean find = false;
        int index = 0;
        int lo = 0;
        int hi = a.length - 1;
        int mid = 0;

        while (!find) {
            mid = (lo + hi) / 2;
            if (a[mid - 1] > a[mid] && a[mid] < a[mid + 1]) {
                index = mid;
                find = true;
            }
            else {
                if (a[mid - 1] < a[mid + 1])
                    hi = mid - 1;
                if (a[mid - 1] > a[mid + 1])
                    lo = mid + 1;
            }
        }

        return index;
    }


    public static void main(String[] args) {
        int[] a1 = new int[] { 8, 5, 7, 2, 3, 4, 1, 9 };
        int[] a2 = new int[] { 8, 5, 2, 7, 3, 4, 1, 9 };
        int[] a3 = new int[] { -2, -5, 5, 2, 4, 7, 1, 8, 0, -8, 10 };

        if (localMinimum(a1) == 3)
            StdOut.println("a1 : " + TEST_PASSED);
        else
            StdOut.println("a1 : " + TEST_FAILED);

        if (localMinimum(a2) == 2)
            StdOut.println("a2 : " + TEST_PASSED);
        else
            StdOut.println("a2 : " + TEST_FAILED);

        if (localMinimum(a3) == 9)
            StdOut.println("a3 : " + TEST_PASSED);
        else
            StdOut.println("a3 : " + TEST_FAILED);

    }

}
