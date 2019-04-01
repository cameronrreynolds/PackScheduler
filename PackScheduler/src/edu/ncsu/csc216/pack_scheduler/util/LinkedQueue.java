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
	/** This represents the number of elements in the queue */
	private int size;
	
	
	/**
	 * This constructs a LinkedQueue and sets the capacity
	 * to the given capacity
	 * @param cap the given capacity of the queue
	 */
	public LinkedQueue(int cap) {
		queue = new LinkedAbstractList<E>(cap);
		setCapacity(cap);
		this.size = 0;
	}

	/**
	 * This adds an element to the back of the queue.
	 * @param element the element to add 
	 */
	@Override
	public void enqueue(E element) {
		try {
			queue.add(this.size(), element);
			this.size = queue.size();
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	/**
	 * This removes the element at the front of the queue
	 * @return the element that is removed
	 */
	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		} else {
			this.size = queue.size() - 1;
			return queue.remove(0);
		}
		
	}

	/**
	 * This returns true if the queue is empty, false otherwise
	 * @return true if empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
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
		if(capacity < 0 || capacity < size) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		queue.setCapacity(capacity);
	}

	
	
}
