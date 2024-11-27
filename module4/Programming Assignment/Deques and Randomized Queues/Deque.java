/* *****************************************************************************
 *  Name: Deque.java
 *  Author : Patrick GIRAUD
 *  Date:   26/08/2024
 *  Description:
 * https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;         // number of elements on queue
    private Node head;  // reference to the first node
    private Node tail;  // reference to the last node

    // construct an empty deque
    public Deque() {
        head = null;
        tail = null;
        n = 0;

    }

    // helper linked list class
    private class Node {
        Item item;   // the item in the node
        Node next;   // reference to next item
        Node prev;   // reference to previous item
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = head;

        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;

        if (tail == null) {
            // If the list was empty, also set the tail to the new node
            tail = newNode; // update the first node to be the new node
        }
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
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

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        // If there is only one node in the list
        Item item;
        if (head == tail) {
            item = tail.item;
            // Remove the only element in the list
            head = null;
            tail = null;
            n--;
            return item;
        }

        // Otherwise, there are multiple nodes in the list
        item = head.item;  // Get the value of the first node
        head = head.next;  // Move the head pointer to the next node
        head.prev = null;  // Set the new head's prev reference to null
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        // If there is only one node in the list
        Item item;
        if (head == tail) {
            item = tail.item;
            // Remove the only element in the list
            head = null;
            tail = null;
            n--;
            return item;
        }

        // Otherwise, there are multiple nodes in the list
        item = tail.item;  // Get the value of the last node
        tail = tail.prev;  // Move the tail pointer to the previous node
        tail.next = null;  // Set the new tail's next reference to null
        n--;
        return item;
    }

    private String print() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DoublyLinkedListFrontToBackIterator();
    }

    private class DoublyLinkedListFrontToBackIterator implements Iterator<Item> {
        private Node current = head;  // node containing current item
        // reset to null upon intervening remove() or add()
        private int index = 0;

        public boolean hasNext() {
            return index < n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            // the last node to be returned by prev() or next()
            Node lastAccessed = current;
            Item item = current.item;
            current = current.next;
            index++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    // unit testing (required)
    public static void main(String[] args) {

        /********************************************************************************
         *  PERSONAL TESTS
         ********************************************************************************/
        // Create an empty doubly linked list
        Deque<Integer> deque = new Deque<Integer>();

        StdOut.println("Add 10 nodes at the beginning");
        for (int i = 1; i < 11; i++) {
            deque.addFirst(i);
        }
        StdOut.println(deque.print());
        //
        // // // go forwards with next()
        // StdOut.println("Print dequeue from front to back using iterator");
        // Iterator<Integer> it = deque.iterator();
        // while (it.hasNext()) {
        //     Integer i = it.next();
        //     StdOut.print(i + " ");
        // }
        // StdOut.println();
        // //
        // StdOut.println("Add 3 nodes at the end");
        // deque.addLast(11);
        // deque.addLast(12);
        // deque.addLast(13);
        // StdOut.println(deque.print());
        // StdOut.println();
        // //
        // StdOut.println("Remove 2 nodes at the front");
        // StdOut.println(deque.removeFirst());
        // StdOut.println(deque.removeFirst());
        // StdOut.println(deque.print());
        // StdOut.println();
        // //
        // StdOut.println("Remove 2 nodes at the back");
        // StdOut.println(deque.removeLast());
        // StdOut.println(deque.removeLast());
        //
        // StdOut.println(deque.print());
        // StdOut.println();
        // // //
        // StdOut.println("Remove first all 9 nodes remaining");
        // Iterator<Integer> it2 = deque.iterator();
        // while (it2.hasNext()) {
        //     StdOut.println(deque.removeFirst());
        // }
        // StdOut.println();
        // StdOut.println("size = " + deque.size());
        // StdOut.println("is empty? " + deque.isEmpty());

        // StdOut.println("Remove on an empty dequeue");
        // StdOut.println(deque.removeFirst());

        // //  call addFirst() with the numbers 1 through n in ascending order, then call
        // //  removeLast() n times, you should see the numbers 1 through n in ascending order
        // StdOut.println();
        // StdOut.println("call addFirst() with the numbers 1 through 10 in ascending order");
        // for (int i = 1; i < 11; i++) {
        //     deque.addFirst(i);
        // }
        // StdOut.println(deque.print());
        //
        // StdOut.println(
        //         "call removeLast() 10 times, you should see the numbers 1 through 10 in ascending order");
        // for (int i = 0; i < 10; i++) {
        //     StdOut.print(deque.removeLast() + " ");
        // }
        // StdOut.println();
        //
        // StdOut.println("size = " + deque.size());
        // StdOut.println("is empty? " + deque.isEmpty());
        //
        // // addFirst and removeFirst 10 times
        // StdOut.println();
        // StdOut.println("call addFirst() with the numbers 1 through 10 in ascending order");
        // for (int i = 1; i < 11; i++) {
        //     deque.addFirst(i);
        // }
        // StdOut.println(deque.print());
        //
        // StdOut.println(
        //         "call removeFirst() 10 times, you should see the numbers 1 through 10 in descending order");
        // for (int i = 0; i < 10; i++) {
        //     StdOut.print(deque.removeFirst() + " ");
        // }
        // StdOut.println();
        //
        // StdOut.println("size = " + deque.size());
        // StdOut.println("is empty? " + deque.isEmpty());

    }

}
