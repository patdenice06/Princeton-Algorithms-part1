/**
 * Interview Questions: Analysis of Algorithms. Question 2:
 * <p>
 * Search in a bitonic array. An array is bitonic if it is comprised of an increasing
 * sequence of integers followed immediately by a decreasing sequence of integers.
 * Write a program that, given a bitonic array of n distinct integer values, determines
 * whether a given integer is in the array.
 * <p>
 * Standard version: Use ∼ 3 lg n compares in the worst case.
 * <p>
 * Signing bonus: Use ∼ 2 lg 2 compares in the worst case (and prove that no algorithm can
 * guarantee to perform fewer than ∼ 2 lg 2 compares in the worst case).
 *
 * @author Patrick GIRAUD
 */
public class BitonicArray {

    /**
     * Search key in half ascending and/or half descending part of the bitonic array
     *
     * @param a   The bitonic array
     * @param key Key to find
     * @return index of the key or -1 if key not found
     */
    private static int Search(int[] a, int key) {
        int n = a.length - 1;
        int k = BitonicMax.max(a, 0, n);
        int indexLeft = BinSearchLeft(a, key, 0, k - 1);
        if (indexLeft != -1)
            return indexLeft;
        else {
            int indexRight = BinSearchRight(a, key, k, n);
            if (indexRight != -1)
                return indexRight;
        }
        return -1;
    }


    /**
     * Search key in the left ascending part of the bitonic array
     *
     * @param a   The bitonic array
     * @param key The key to find
     * @param lo  low bound
     * @param hi  high bound
     * @return index of the key or -1 if key not found
     */
    private static int BinSearchLeft(int[] a, int key, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    /**
     * Search key in the right descending part of the bitonic array
     *
     * @param a   The bitonic array
     * @param key The key to find
     * @param lo  low bound
     * @param hi  high bound
     * @return index of the key or -1 if key not found
     */
    private static int BinSearchRight(int[] a, int key, int lo, int hi) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) lo = mid + 1;
            else if (key > a[mid]) hi = mid - 1;
            else return mid;
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] a = new int[] { 0, 1, 10, 19, 24, 30, 38, 37, 34, 28 };
        int key = 4;
        int index = Search(a, key);
        if (index != -1)
            StdOut.println("key " + key + " found at index " + index);
        else
            StdOut.println("key " + key + " not found");
    }
}
