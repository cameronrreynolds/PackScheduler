package edu.ncsu.csc216.pack_scheduler.util;

/**
 * This class represents a queue using a linked list. It can
 * add, removes, check if its empty, and return its size.
 * @author Michael, Matthew, Cameron
 *
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {
	
	private LinkedAbstractList<E> queue;
	
	private int capacity;

	@Override
	public void enqueue(E element) {
		
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCapacity(int capacity) {
		// TODO Auto-generated method stub
		
	}

}
