import java.util.NoSuchElementException;

public class QueueWithTwoStacks<Item> {

    private ResizingArrayStackOfStrings s1;
    private ResizingArrayStackOfStrings s2;
    private int n = 0;           // size of the stacks

    public QueueWithTwoStacks() {
        s1 = new ResizingArrayStackOfStrings();
        s2 = new ResizingArrayStackOfStrings();
    }

    public void enqueue(String item) {
        s1.push(item);                              // add item
    }

    public String dequeue() {
        String item;

        /* If both stacks are empty then error */
        if (s1.isEmpty() && s2.isEmpty())
            throw new NoSuchElementException("Stacks underflow");

        /* Move elements from s1 to s2 only if s2 is empty */
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                item = s1.pop();
                s2.push(item);
            }
        }

        item = s2.pop();
        return item;
    }


    /***************************************************************************
     * Test routine.
     ***************************************************************************/
    public static void main(String[] args) {
        QueueWithTwoStacks<String> q = new QueueWithTwoStacks<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) q.enqueue(item);
            else if (!q.s1.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        int leftOnStacks = q.s1.size() + q.s2.size();
        StdOut.println("(" + leftOnStacks + " left on stack)");
    }
}
