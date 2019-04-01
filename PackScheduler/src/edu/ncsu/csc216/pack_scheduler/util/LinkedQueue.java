package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * This class represents a queue using a linked list. It can
 * add, removes, check if its empty, and return its size.
 * @author Michael, Matthew, Cameron
 *
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {
	/** This represents the queue as a linked list */
	private LinkedAbstractList<E> queue;
	/** This represents the capacity of the queue */
	private int capacity;
	
	/**
	 * This is the default no arg constructor of a LinkedQueue/
	 * It initializes queue and sets the capacity to zero.
	 */
	public LinkedQueue() {
		this(0);
	}
	/**
	 * This constructs a LinkedQueue and sets the capacity
	 * to the given capacity
	 * @param cap the given capacity of the queue
	 */
	public LinkedQueue(int cap) {
		System.out.println(cap);
		queue = new LinkedAbstractList<E>(cap);
		setCapacity(cap);
	}

	/**
	 * This adds an element to the back of the queue.
	 * @param element the element to add 
	 */
	@Override
	public void enqueue(E element) {
		if(size() == capacity) {
			throw new IllegalArgumentException("Capacity has been met");
		}
		queue.add(element);
	}

	/**
	 * This removes the element at the front of the queue
	 * @return the element that is removed
	 */
	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		return queue.remove(0);
	}

	/**
	 * This returns true if the queue is empty, false otherwise
	 * @return true if empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	/**
	 * This returns the size of the queue
	 * @return the size of the queue
	 */
	@Override
	public int size() {
		return queue.size();
	}

	/**
	 * This sets capacity of the queue. If the capacity is
	 * less than zero or less than the current size, it will
	 * throw an excpetion
	 */
	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		queue.setCapacity(capacity);
	}

	/**
	 * Returns the capacity of the LinkedQueue.
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
}
