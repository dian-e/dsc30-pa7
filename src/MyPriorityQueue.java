/*
 * Name: Diane Li
 * PID:  A15773774
 */

/**
 *  A class that uses dHeap implementation and Adapter Pattern Design to build a Priority Queue
 *
 * @param <T> Generic type
 * @author Diane Li
 * @since 02/23/2021
 */
public class MyPriorityQueue<T extends Comparable<? super T>> {

    private dHeap<T> pQueue;
    private static final int DEFAULT_D = 5;

    /**
     * Constructor that creates a new priority queue
     * @param initialSize the given size
     */
    public MyPriorityQueue(int initialSize) {
        this.pQueue = new dHeap<>(DEFAULT_D, initialSize, true);
    }

    /**
     * Inserts an element into the Priority Queue (the element received can't be null)
     * @param element Element to be inserted.
     * @throws NullPointerException if the element received is null.
     * @return returns true
     */
    public boolean offer(T element) throws NullPointerException {
        this.pQueue.add(element);
        return true;
    }

    /**
     * Retrieves the head of this Priority Queue (largest element), or null if the
     * queue is empty.
     *
     * @return The head of the queue (largest element), or null if queue is empty.
     */
    public T poll() {
        if (this.isEmpty()) { return null; }
        else { return pQueue.remove(); }
    }

    /**
     * Clears the contents of the queue
     */
    public void clear() { pQueue.clear(); }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if
     * this queue is empty.
     * @return the next item to be removed, null if the queue is empty
     */
    public T peek() { return pQueue.element(); }

    /**
     * Return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() { return pQueue.size() == 0; }

}
