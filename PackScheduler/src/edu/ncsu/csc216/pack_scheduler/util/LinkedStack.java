package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

 /** This class implements the functionality outlined in the stack interface using LinkedAbstractLists.
 * 
 * @author Matthew Strickland, Michael, Cameron
 *
 * @param <E> Any element the stack will consist of.
 */
public class LinkedStack<E> implements Stack<E> {
	
	/** The LinkedList the stack will use. */
	private LinkedAbstractList<E> stack;

	/**
	 * Creates an LinkedStack with the default value of 0.
	 */
	public LinkedStack() {
		this(0);
	}
	
	/**
	 * Constructs an LinkedStack with a certain capacity.
	 * 
	 * @param capacity The capacity of the ArrayStack.
	 */
	public LinkedStack(int capacity) {
		stack = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * Pushes a given element onto the stack.
	 */
	@Override
	public void push(E element) {
		stack.add(element);
	}

	/**
	 * Returns the element off the top of the stack as long as it isn't empty.
	 */
	@Override
	public E pop() {
		if (this.size() == 0) {
			throw new EmptyStackException();
		}
		return stack.remove(stack.size() - 1);
	}

	/**
     * Returns if the stack is empty or not.
     */
	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}

	/**
	 * Returns the size of the stack.
	 */
	@Override
	public int size() {
		return stack.size();
	}

	/**
	 * Sets the capacity of the stack.
	 */
	@Override
	public void setCapacity(int capacity) {
		stack.setCapacity(capacity);
	}

}
