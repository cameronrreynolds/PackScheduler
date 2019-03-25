package edu.ncsu.csc216.pack_scheduler.util;

public interface Queue<E> {
	
	/**
	 * This method adds the element to the back of the 
	 * queue. If there is no room(capacity has been reached),
	 * an IllegalArgumentException is thrown
	 * @param eleement the element to add to the queue
	 */
	public void enqueue(E element);
	
	/**
	 * This method removes and returns the element 
	 * at the front of the queue. If the queue is empty, a
	 * NoSuchElementException is thrown
	 * @return the element being removed
	 */
	public E dequeue();
	
	
	/**
	 * This method returns true if the queue is empty. 
	 * False otherwise. 
	 * @return true if queue is empty, false otherwise
	 */
	public boolean isEmpty();
	
	
	/**
	 * This method returns the number of elements in the 
	 * queue. 
	 * @return the number of elements in the queue
	 */
	public int size();
	
	
	/**
	 * This method sets the queue's capacity. If the actual
	 * parameter is negative of if it is less than the number
	 * of elements in the queue, an IllegalArgumentException
	 * is thrown
	 * @param capacity the capacity to set to
	 */
	public void setCapacity(int capacity);
	

}
