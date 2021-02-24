/*
 * Name: Diane Li
 * PID:  A15773774
 */

import java.util.*;

/**
 * A class that implements dHeapInterface and uses heap-structured array to represent a d-ary heap
 * 
 * @param <T> Generic type
 * @author Diane Li
 * @since 02/23/2021
 */
public class dHeap<T extends Comparable<? super T>> implements dHeapInterface<T> {

    private T[] heap; // heap array
    private int d; // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // boolean to indicate whether heap is max or min

    private static final int DEFAULT_SIZE = 6;
    private static final int BINARY_D = 2;
    private static final int DOUBLE_SIZE = 2;

    /**
     * Initializes a binary max heap with capacity = 6
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this.heap = (T[]) new Comparable[DEFAULT_SIZE];
        this.d = BINARY_D;
        this.nelems = 0;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this.heap = (T[]) new Comparable[heapSize];
        this.d = BINARY_D;
        this.nelems = 0;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial capacity.
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if (d < 1) { throw new IllegalArgumentException(); }
        this.heap = (T[]) new Comparable[heapSize];
        this.d = d;
        this.nelems = 0;
        this.isMaxHeap = isMaxHeap;
    }

    /**
     * Returns the number of elements stored in the heap
     */
    @Override
    public int size() { return this.nelems; }

    /**
     * Adds the given data to the heap
     * @param data the new element to be added to the heap
     * @throws NullPointerException if the given data is null
     */
    @Override
    public void add(T data) throws NullPointerException {
        if (data == null) { throw new NullPointerException(); }

        // resizes array if it is full
        // adds new data to the end of the array and bubbles it up as needed
        if (this.nelems == this.heap.length) { this.resize(); }
        this.heap[this.nelems] = data;
        bubbleUp(this.nelems);
        this.nelems++;
    }

    /**
     * Returns and removes the root element from the heap
     * @throws NoSuchElementException if the heap is empty
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (this.nelems == 0) { throw new NoSuchElementException(); }

        // stores current root to return
        // makes current last element the new root and trickles it down as needed
        T root = this.heap[0];
        this.heap[0] = this.heap[this.nelems - 1];
        nelems--;
        trickleDown(0);
        return root;
    }

    /**
     * Clears all elements in the heap
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        this.heap = (T[]) new Comparable[this.heap.length];
        this.nelems = 0;
    }

    /**
     * Returns the root element from the heap (without removing it)
     * @throws NoSuchElementException if the heap is empty
     */
    public T element() throws NoSuchElementException {
        if (this.nelems == 0) { throw new NoSuchElementException(); }
        else { return this.heap[0]; }
    }

    /**
     * Swaps an element down an array until heap properties are met
     * @param index of element to be trickled down the array
     */
    private void trickleDown(int index) {
        T extremeEl = this.heap[index];
        int extremeIdx = index;
        int childIdx;
        int comparedChild;

        // loops through child indices
        for (int i = 1; i <= this.d; i++) {
            // if child is out of bounds, break loop
            if (index * this.d + i >= this.nelems) { break; }
            else {
                // if child element is bigger (maxheap) or smaller (minheap), update extreme el
                childIdx = index * this.d + i;
                comparedChild = extremeEl.compareTo(this.heap[childIdx]);
                if ((isMaxHeap && comparedChild < 0) || (!isMaxHeap && comparedChild > 0)) {
                    extremeEl = this.heap[childIdx];
                    extremeIdx = childIdx;
                }
            }
        }

        // if extreme index was updated, swap elements with the extreme and recurse on that element
        if (extremeIdx != index) {
            T temp = this.heap[index];
            this.heap[index] = this.heap[extremeIdx];
            this.heap[extremeIdx] = temp;
            trickleDown(extremeIdx);
        }
    }

    /**
     * Swaps an element up an array until heap properties are met
     * @param index of element to be bubbled up the array
     */
    private void bubbleUp(int index) {
        // return if index is root or maxheap w child less than parent or minheap w child greater
        if (index <= 0) { return; }
        int comparedParent = this.heap[index].compareTo(this.heap[parent(index)]);
        if ((isMaxHeap && comparedParent <= 0) || (!isMaxHeap && comparedParent >= 0)) { return; }

        // otherwise, swap elements at this and parent's indices and recurse
        T temp = this.heap[index];
        this.heap[index] = this.heap[parent(index)];
        this.heap[parent(index)] = temp;
        bubbleUp(parent(index));
    }

    /**
     * Doubles the size of an array (used before adding if the array is full)
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        // creates and repopulates new heap array of twice the length
        T[] resized = (T[]) new Comparable[this.heap.length * DOUBLE_SIZE];
        for (int i = 0; i < this.heap.length; i++) { resized[i] = this.heap[i]; }
        this.heap = resized;
    }

    /**
     * Finds the index of the parent of a child index
     * @param index of the child element
     */
    private int parent(int index) { return (index - 1) / this.d; }

}
