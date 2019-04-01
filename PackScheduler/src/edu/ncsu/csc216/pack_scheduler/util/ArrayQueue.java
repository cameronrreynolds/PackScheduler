package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * This class represents a queue implemented with an 
 * array list. It has the functionality of adding, removing, 
 * and checking if it is empty. 
 * 
 * @author Michael, Matthew, Cameron
 *
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E> {
	/** This represents the queue */
	private ArrayList<E> queue;
	/** This represents the max capacity of the queue */
	private int capacity;
	
	/**
	 * This is the default no arg constructor of a 
	 * queue. This constructor constructs the queue with a 
	 * default size of zero.
	 */
	public ArrayQueue() {
		this(0);
	}
	
	/**
	 * This constructs a queue and sets the capacity to the
	 * given capacity
	 * @param capacity the capacity of the queue
	 */
	public ArrayQueue(int capacity) {
		queue = new ArrayList<E>();
		setCapacity(capacity);
	}

	/**
	 * This methods adds the element to the back of the queue.
	 * If the size of the queue has meet the capacity, 
	 * it will throw an IllegalArgumentExcpetion
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
	 * This method will remove an element from the front 
	 * of the queue. It will remove the element that it removes
	 * @return the element at the front of the queue
	 */
	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException("Queue is empty");
		}
		return queue.remove(0);
	}

	/**
	 * This method checks if the queue is empty. It will
	 * return true if the queue is empty, false otherwise
	 * @return true if empty
	 */
	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	/**
	 * This method will return the size of the queue.
	 * @return the size of the queue
	 */
	@Override
	public int size() {
		return queue.size();
	}

	/**
	 * This method will set the capacity of the queue. If
	 * the given capacity is less than zero or less than the
	 * current size of the queue, it will throw an
	 * IllegalArgumentException
	 * @param capacity the capacity to set to
	 */
	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		this.capacity = capacity;
	}

}
