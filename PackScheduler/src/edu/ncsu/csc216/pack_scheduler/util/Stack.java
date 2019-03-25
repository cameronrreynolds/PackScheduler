package edu.ncsu.csc216.pack_scheduler.util;

/**
 * Outlines the methods for a Stack.
 * 
 * @author matthewstrickland
 *
 * @param <E> The element type the stack will consist of.
 */
public interface Stack<E> {
	
	/**
	 * Pushes an element to the stack.
	 * 
	 * @param element The element to be added to the stack.
	 */
	public void push(E element);
	
	/**
	 * Retrieves the element off the top of the stack.
	 * 
	 * @return The element on the top of the stack.
	 */
	public E pop();
	
	/**
	 * Determines if the stack is empty or not.
	 * 
	 * @return A boolean showing if the stack is empty.
	 */
	public boolean isEmpty();
	
	/**
	 * Gives the size of the stack.
	 * 
	 * @return An int of the size of the stack.
	 */
	public int size();
	
	/**
	 * Sets the capacity of the stack.
	 * 
	 * @param capacity The capacity the stack will be set to.
	 */
	public void setCapacity(int capacity);

}
