package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * This class implements the functionality outlined in the stack interface using ArrayLists.
 * 
 * @author Matthew Strickland, Michael, Cameron
 *
 * @param <E> Any element the stack will consist of.
 */
public class LinkedStack<E> implements Stack<E> {
	
	/** The capacity of the stack. */
	private int capacity;
	/** The ArrayList the stack will use. */
	private ArrayList<E> stack;
	
	/**
	 * Creates an ArrayStack with the default value of 0.
	 */
	public LinkedStack() {
		this(0);
	}
	
	/**
	 * Constructs an ArrayStack with a certain capacity.
	 * 
	 * @param capacity The capacity of the ArrayStack.
	 */
    public LinkedStack(int capacity) {
    	stack = new ArrayList<E>();
    	setCapacity(capacity);
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
		if (capacity < 0 || capacity < stack.size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

	/**
	 * Pushes a given element onto the stack.
	 */
	@Override
	public void push(E element) {
		if (this.size() == capacity) {
			throw new IllegalArgumentException();
		}
		stack.add(element);
	}

	/**
	 * Returns the element off the top of the stack.
	 */
	@Override
	public E pop() {
		try {
			return stack.remove(stack.size() - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new EmptyStackException();
		}
	}

}
