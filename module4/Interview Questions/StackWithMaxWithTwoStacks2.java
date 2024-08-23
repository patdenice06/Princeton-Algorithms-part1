/*
Interview Questions: Stacks and Queues (ungraded).
Question 2:
Stack with max. Create a data structure that efficiently supports the stack operations
(push and pop) and also a return-the-maximum operation. Assume the elements are real
numbers so that you can compare them.
Use of two stacks, one to store all the elements and a second stack to store the maximums
 */

public class StackWithMaxWithTwoStacks2<Double> {


    /***************************************************************************
     * Test routine.
     ***************************************************************************/
    public static void main(String[] args) {
        Stack<java.lang.Double> vals = new Stack<java.lang.Double>();
        Stack<java.lang.Double> maximums = new Stack<java.lang.Double>();

        vals.push(64.0);
        vals.push(16.0);
        vals.push(128.0);
        vals.push(8.0);
        vals.push(4.0);
        vals.push(32.0);

        maximums.push(vals.peek());
        for (double a : vals) {
            if (a > maximums.peek())
                maximums.push(a);
        }
        StdOut.println(maximums.pop());
    }
}
