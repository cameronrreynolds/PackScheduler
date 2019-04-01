package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * This list doesn't allow for null or duplicate elements.
 * 
 * @author William Boyles,  Matthew Strickland, Joey Davidson
 *
 * @param <E> The type of Object that the list will contain.
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** The ListNode representing the first element in the list. */
	private ListNode front;
	/** The ListNode representing the last element in the list. */
	private ListNode back;
	/** The number elements in the list. */
	private int size;
	/** The maximum number of element available in the list */
	private int capacity;
	

	/**
	 * Constructs a new, empty list with a given maximum number of elements.
	 * 
	 * @param cap The maximum number of element allowed in the list.
	 */
	public LinkedAbstractList(int cap) {
		this.size = 0;
		setCapacity(cap);
	}

	/**
	 * Sets the maximum capacity of the list to a given value.
	 * 
	 * @param cap The maximum capacity of the list.
	 * @throws IllegalArgumentException if the parameter is less than the list's
	 *                                  current size.
	 */
	public void setCapacity(int cap) {
		if (cap < size) { // this also tests if cap < 0
			throw new IllegalArgumentException();
		}
		
		this.capacity = cap;
	}

	/**
	 * Adds an element at the end of the list. Calls add(size, value)
	 * 
	 * @param value The value that the specified index will have in the list.
	 */
	@Override
	public boolean add(E value) {
		add(size, value);
		return true;
	}

	/**
	 * Gets the object a a specific index in the list.
	 * 
	 * @param index The index to get the value in the list from.
	 * @return The object at a specific index in the list.
	 * @throws IndexOutOfBoundsException if the parameter is less than 0 or greater
	 *                                  than or equal to the list's size.
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}

		return current.data;
	}

	/**
	 * Sets the value of the data at a specific index in the list to a given value.
	 * 
	 * @param index The index in the list that contains the value to be set.
	 * @param value The value to set at the given index.
	 * @return The value previously in the list.
	 * @throws NullPointerException      if the element to set is null.
	 * @throws IllegalArgumentException  if the element to set is a duplicate of an
	 *                                   element already in the list.
	 * @throws IndexOutOfBoundsException If the index parameter is less than 0 or
	 *                                   greater than or equal to the list size.
	 */
	@Override
	public E set(int index, E value) {
		// check null value
		if (value == null) {
			throw new NullPointerException(); // TODO
		}

		// check valid index to add/
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		// check duplicates
		for (int i = 0; i < size; i++) {
			if (value.equals(get(i))) {
				throw new IllegalArgumentException(); // TODO
			}
		}

		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		E ret = current.data;
		current.data = value;
		return ret;
	}

	/**
	 * Adds an element at the specific index, moving elements to the right over by
	 * one.
	 * 
	 * @param index The index to insert an element.
	 * @param value The value that the specified index will have in the list.
	 * 
	 * @throws IndexOutOfBoundsException if the index parameter is less than 0 or
	 *                                   greater than the list size.
	 * @throws IllegalArgumentException  if the list is already filled to capacity.
	 * @throws IllegalArgumentException  if the vale parameter is a duplicate of one
	 *                                   already in the list.
	 * @throws NullPointerException      if the value parameter is null.
	 */
	@Override
	public void add(int index, E value) {
		// bounds check
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		// check for null element
		if (value == null) {
			throw new NullPointerException(); // TODO check exception type and message
		}

		// check that adding wouldn't make the list too big
		if (size == capacity) {
			throw new IllegalArgumentException(); // TODO check exception type and message
		}

		// check if element is a duplicate
		for (int i = 0; i < size; i++) {
			if (value.equals(get(i))) {
				throw new IllegalArgumentException(); // TODO check exception type and message
			}
		}

		// check for front of the list and null front
		if (index == 0) {
			front = new ListNode(value, front);
			if(size() == 0) {
				back = front;
			}
		} else if(index == size()) {
			ListNode insert = new ListNode(value, null);
			back.next = insert;
			back = insert;
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			current.next = new ListNode(value, current.next);
		}
		size++;
	}

	/**
	 * Removes an element at a specified index form the list.
	 * 
	 * @param index The index to remove an element from.
	 * @return The element that was removed.
	 * @throws IndexOutOfBoundsException if the parameter is less than 0 or greater
	 *                                   that or equal to the list's size.
	 */
	public E remove(int index) {
		// bounds check
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		// front of the list
		if (index == 0) {
			E ret = front.data;
			front = front.next;
			size--;
			return ret;
		}
		

		ListNode current = front;
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
		E ret = current.next.data;
		current.next = current.next.next;
		back = current.next;
		size--;
		return ret;
	}

	/**
	 * Gets the size, which is the number of elements in the list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Represents one element in the list that references the next element. The last
	 * element in the list has this next pointer as null.
	 * 
	 * @author William Boyles,  Matthew Strickland, Joey Davidson
	 */
	private class ListNode {
		/** The information to the stored in this particular element of the the list. */
		private E data;
		/** A reference to the next ListNode in the LinkedList. */
		private ListNode next;

		/**
		 * Constructs a new node with a piece of data and a reference to the next
		 * ListNode in the list.
		 * 
		 * @param data The information to the stored in the list.
		 * @param next A reference to the next ListNode in the LinkedList.
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}