package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * A custom ArrayList for the PackScheduler Project. It doesn't allow for null
 * or duplicate elements.
 * 
 * @author jhdav, wmboyle2
 *
 * @param <E> The type of the list.
 */
public class ArrayList<E> extends AbstractList<E> {
	/** The initial size of the ArrayList set at 10 */
	private static final int INIT_SIZE = 10;
	/** The array of objects */
	private E[] list;
	/** the size of the list array */
	private int size;

	/**
	 * Constructor for the ArrayList Class
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		size = 0;
		list = (E[]) (new Object[INIT_SIZE]);
	}

	/**
	 * Grows the array to twice its size when there is no more space and the client
	 * intends to add an element.
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		E[] temp = (E[]) (new Object[size * 2]);
		for (int i = 0; i < size; i++) {
			temp[i] = list[i];
		}
		list = temp;
	}

	/**
	 * Adds an element to a given index
	 * 
	 * @param index   The index the element will be added into as an int.
	 * @param element The element being added.
	 * @throws NullPointerException      if the element is null.
	 * @throws IndexOutOfBoundsException if the index is greater than the size of
	 *                                   the list, or less than 0.
	 * @throws IllegalArgumentException  if the element is a duplicate of one
	 *                                   already in the list.
	 */
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}

		if (size == list.length) {
			growArray();
		}

		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}

		for (int i = size; i >= index + 1; i--) {
			list[i] = list[i - 1];
		}

		list[index] = element;
		size++;
	}

	/**
	 * Removes an element at a given index
	 * 
	 * @param index the index being removed
	 * @return the element being removed
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than
	 *                                   or equal to the size of the list.
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E temp = list[index];
		for (int i = index; i < size - 1; i++) {
			list[i] = list[i + 1];
		}
		list[size - 1] = null;
		size--;
		return temp;
	}

	/**
	 * Sets a specific index to a specific element
	 * 
	 * @param index   the index being overridden
	 * @param element the element being assigned to the index
	 * @return the element that is being overridden
	 * @throws NullPointerException       if the element is null.
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than
	 *                                   or equal to the size of the list.
	 * @throws IllegalArgumentException  if the element is a duplicate of one
	 *                                   already in the list.
	 */
	@Override
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < size; i++) {
			if (list[i].equals(element)) {
				throw new IllegalArgumentException();
			}
		}
		E temp = list[index];
		list[index] = element;
		return temp;

	}

	/**
	 * Gets the element at the given index
	 * 
	 * @param index the index of the element requested
	 * @return the element being requested
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than
	 *                                   or equal to the size of the list.
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return list[index];
	}

	/**
	 * Gets the size of the ArrayList
	 * 
	 * @return the size field
	 */
	@Override
	public int size() {
		return size;
	}

}
