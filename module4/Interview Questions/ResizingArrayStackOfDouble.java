import java.util.Iterator;
import java.util.NoSuchElementException;

/*
Interview Questions: Stacks and Queues (ungraded).
Question 2:
Stack with max. Create a data structure that efficiently supports the stack operations
(push and pop) and also a return-the-maximum operation. Assume the elements are real
numbers so that you can compare them.
 */

public class ResizingArrayStackOfDouble<Double> implements Iterable<Double> {
    private double[] items;     // array of doubles
    private int n = 0;          // number of elements on stack

    // create an empty stack
    public ResizingArrayStackOfDouble() {
        items = new double[2];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;
        double[] temp = new double[capacity];
        for (int i = 0; i < n; i++)
            temp[i] = items[i];
        items = temp;
    }

    // push a new item onto the stack
    public void push(double item) {
        if (n == items.length) resize(2 * items.length);  // double array length if necessary
        items[n++] = item;                              // add item
    }

    // delete and return the item most recently added
    public double pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        double item = items[n - 1];
        items[n - 1] = 0.0;        // to avoid loitering
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == items.length / 4) resize(items.length / 2);
        return item;
    }

    public Iterator<Double> iterator() {
        return (Iterator<Double>) new ReverseArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ReverseArrayIterator implements Iterator<java.lang.Double> {
        private int i = n - 1;

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public java.lang.Double next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[i--];
        }
    }

    public double returnTheMaximum(ResizingArrayStackOfDouble<java.lang.Double> s) {
        double max = s.pop();
        Iterator<java.lang.Double> it = s.iterator();
        while (it.hasNext() && !s.isEmpty()) {
            double v = it.next();
            if (v > max)
                max = v;
        }
        return max;
    }

    /***************************************************************************
     * Test routine.
     ***************************************************************************/
    public static void main(String[] args) {
        ResizingArrayStackOfDouble<java.lang.Double> s
                = new ResizingArrayStackOfDouble<java.lang.Double>();
        // data for test
        s.push(64.0);
        s.push(16.0);
        s.push(8.0);
        s.push(128.0);
        s.push(4.0);
        s.push(32.0);

        // Return the maximum
        double max = s.returnTheMaximum(s);
        StdOut.println(max);
    }
}
