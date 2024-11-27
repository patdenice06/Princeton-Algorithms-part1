/* *****************************************************************************
 *  Name: RandomizedQueue.java
 *  Author : Patrick GIRAUD
 *  Date: 22/11/2024
 *  Description:
 * https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int n;      // number of elements on list
    private Node head;  // sentinel before first item
    private Node tail;  // sentinel after last item

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        head = null;
        tail = null;
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException(
                    " argument has null value ");
        Node newNode = new Node();
        newNode.item = item;

        if (tail == null) {
            // If the list is empty, set both head and tail to the new node
            head = newNode;
            tail = newNode;
        }
        else {
            // Otherwise, add the new node to the end of the list
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        n++;
    }

    private Node getRandPointer() {
        int rand = StdRandom.uniformInt(n);
        Node pointer = null;
        if (rand == 0) {
            pointer = head;
        }
        else if (rand == n - 1) {
            pointer = tail;
        }
        else {
            int mid = (n - 2) / 2;
            if (rand <= mid) {
                Node current = head.next;
                for (int i = 0; i < rand; i++) {
                    pointer = current;
                    current = current.next;
                }
            }
            else {   // rand > mid
                Node current = tail.prev;
                for (int i = n - 2; i >= rand; i--) {
                    pointer = current;
                    current = current.prev;
                }
            }
        }

        return pointer;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException(
                    " the randomized queue is empty");
        Node pointer = getRandPointer();
        Item item = pointer.item;

        if (n == 1) {
            head = null;
            tail = null;
        }
        else {
            if (pointer == head)
                head = pointer.next;
            else if (pointer == tail) {
                tail = pointer.prev;
            }
            else {
                pointer.prev.next = pointer.next;
                pointer.next.prev = pointer.prev;
            }
        }
        n--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException(
                    " the randomized queue is empty");
        Node pointer = getRandPointer();
        return pointer.item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        private Item[] q;
        private int index = 0;

        private RandomIterator() {
            createRandomizedQueue();
            q = (Item[]) new Object[n];
            Node c = head;
            int i = 0;
            while (c != null) {
                q[i] = c.item;
                c = c.next;
                i++;
            }
            n = q.length;
        }

        //
        private void createRandomizedQueue() {
            Node newHead = null;
            Node newTail = null;
            int oldN = n;

            while (!isEmpty()) {
                Node newNode = new Node();
                newNode.item = dequeue();
                if (newTail == null) {
                    newHead = newNode;
                    newTail = newNode;
                }
                else {
                    newTail.next = newNode;
                    newNode.prev = newTail;
                    newTail = newNode;
                }
            }

            head = newHead;
            tail = newTail;
            n = oldN;
        }

        public boolean hasNext() {
            return index < n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[index];
            index++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        // RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        // int n = 10;
        // for (int i = 0; i < n; i++) {
        //     rq.enqueue(i);
        // }
        // StdOut.println("queue");
        // for (Integer i : rq) {
        //     StdOut.print(i + " ");
        // }
        // StdOut.println();
        //
        // // StdOut.println("randomized queue using dequeue()");
        // // for (int i = 0; i < n; i++) {
        // //     StdOut.print(rq.dequeue() + " ");
        // // }
        // // StdOut.println();
        //
        // StdOut.println("randomized queue using iterator()");
        // for (Integer i : rq) {
        //     StdOut.print(i + " ");
        // }
        // StdOut.println();
        //
        //
        // StdOut.println("size = " + rq.size());
        //
        // StdOut.println("dequeue " + rq.dequeue());
        // StdOut.println("dequeue " + rq.dequeue());
        // for (Integer i : rq) {
        //     StdOut.print(i + " ");
        // }
        // StdOut.println();
        //
        // StdOut.println("sample " + rq.sample());
        //
        // StdOut.println("iterator in forward direction using foreach ");
        // for (Integer integer : rq) {
        //     StdOut.print(integer + " ");
        // }
        // StdOut.println();
        //
        // StdOut.println("iterator in forward direction via next ");
        // Iterator<Integer> it = rq.iterator();
        // while (it.hasNext()) {
        //     StdOut.print(it.next() + " ");
        // }
        // StdOut.println();
        //
        // StdOut.println("size = " + rq.size());
        // StdOut.println("is empty? " + rq.isEmpty());

        // From https://coursera.cs.princeton.edu/algs4/assignments/queues/faq.php
        // Test that multiple iterators can be used simultaneously and operate independently of one another
        StdOut.println("Test with two nested iterators");
        int n = 5;
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (int a : queue) {
            for (int b : queue)
                StdOut.print(a + "-" + b + " ");
            StdOut.println();
        }

        // StdOut.println("Test with two nested iterators using while loop");
        // int n = 5;
        // RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        // for (int i = 0; i < n; i++)
        //     queue.enqueue(i);
        // Iterator<Integer> it = queue.iterator();
        // Iterator<Integer> it2 = queue.iterator();
        // while (it.hasNext()) {
        //     int a = it.next();
        //     while (it2.hasNext()) {
        //         int b = it2.next();
        //         StdOut.print(a + "-" + b + " ");
        //     }
        //     StdOut.println();
        // }
        // StdOut.println();

        // StdOut.println("Test with three nested iterators");
        // int n = 3;
        // RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        // for (int i = 0; i < n; i++)
        //     queue.enqueue(i);
        // for (int a : queue) {
        //     for (int b : queue)
        //         for (int c : queue)
        //             StdOut.print(a + "-" + b + "-" + c + " ");
        //     StdOut.println();
        // }


        // StdOut.println("Test with two parallel iterators");
        // int n = 10;
        // RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        // for (int i = 0; i < n; i++)
        //     queue.enqueue(i);
        // for (int a : queue)
        //     StdOut.print(a + " ");
        // StdOut.println();
        // for (int b : queue)
        //     StdOut.print(b + " ");
        // StdOut.println();

    }
}
