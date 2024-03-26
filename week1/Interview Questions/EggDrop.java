/**
 * Interview Questions: Analysis of Algorithms. Question 3:
 * <p>
 * Egg drop. Suppose that you have an n-story building (with floors 1 through n) and plenty of
 * eggs. An egg breaks if it is dropped from floor T or higher and does not break otherwise. Your
 * goal is to devise a strategy to determine the value of  T given the following limitations on the
 * number of eggs and tosses:
 * <p>
 * Version 0: 1 egg, ≤ T tosses.
 * <p>
 * Version 1: ∼ 1 lg n eggs and  ∼ 1 lg n tosses.
 * <p>
 * Version 2: ∼ lg T eggs and  ∼ 2 lg T tosses.
 * <p>
 * Version 3: 2 eggs and  ∼ 2 * sqrt(N) tosses
 * <p>
 * Version 4: 2 eggs and  ≤ c * sqrt(T) tosses for some fixed constant c.
 *
 * @author Patrick GIRAUD
 */

public class EggDrop {

    /**
     * 1 egg, ≤ T tosses.<p>
     * The experiment can be carried out in only one way. Drop the egg from the first-floor window;
     * if it survives, drop it from the second-floor window. Continue upward until it breaks. In the
     * worst case, this method may require N droppings
     *
     * @param N N-story building (with floors 1 through N)
     * @param T Floor from where the egg breaks
     * @param e The number of egg(s) to
     */
    private static int version_0(int[] a, int N, int T, int e) {
        int tosses = 0;
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            ++tosses;
            int mid = lo + (hi - lo) / 2;
            if (T < a[mid]) hi = mid - 1;
            else if (T > a[mid]) lo = mid + 1;
                // else return mid;
            else return tosses;
        }
        // return -1;


        return -1;
    }


    /**
     * Egg drop version 0: 1 egg, ≤ T tosses
     *
     * @param T Critical floor
     */
    private static int eggDropV0(int T) {
        for (int tosses = 1; true; tosses++)
            if (tosses == T) return tosses;
    }


    public static void main(String[] args) {
        int N = 100;
        int T = StdRandom.uniformInt(1, N + 1);    // critical floor
        StdOut.println("N = " + N + " T = " + T + " tosses = " + eggDropV0(T));

    }


}
