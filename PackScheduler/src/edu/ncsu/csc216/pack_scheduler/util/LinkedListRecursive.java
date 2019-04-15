package edu.ncsu.csc216.pack_scheduler.util;

/**
 * This class is another implementation of a linked list 
 * where the methods are implemented recursively
 * @author Michael, Matthew, Cameron
 *
 * @param <E>
 */
public class LinkedListRecursive<E> {
	/** This represents the size of the list */
	private int size;
	/** This represents the front of the list */
	private ListNode front;
	
	
	/**
	 * This constructs an empty linked list 
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}
	
	/**
	 * This determins if the list is empty
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * This method returns the size of the list
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}
	/**
	 * This method recursively adds 
	 * the given element to the end of the list
	 * @param element the element to add to the end of the list
	 * @return true if the element was added
	 */
	public boolean add(E element) {
		if(contains(element)) throw new IllegalArgumentException(); 
		add(size, element);
		return true;
	}
	
	/**
	 * This method recursively adds the element at the 
	 * given index
	 * @param index the index to add the element to
	 * @param element the element to add
	 */
	public void add(int index, E element) {
		if(contains(element)) throw new IllegalArgumentException();
		if(index < 0 || index > size) throw new IndexOutOfBoundsException();
		if(element == null) throw new NullPointerException();
		add(index, element, 0, front);
		size++;
		
	}
	
	private void add(int index, E element, int currIndex, ListNode head) {
		if(index == 0) front = new ListNode(element, front);
		else if(front == null) throw new IllegalArgumentException();
		else if(currIndex + 1 == index) {
			head.next = new ListNode(element, head.next);
			return;
		} else {
			add(index, element, currIndex + 1, head.next);
		}
		
	}

	/**
	 * This method gets and returns the element at the
	 * given index
	 * @param index the index of the element to get
	 * @return the element at the given index
	 */
	public E get(int index) {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		return get(front, index);
	}
	
	
	
	
	private E get(ListNode head, int index) {
		if(index == 0) return head.data;
		else return get(head.next, index - 1);
	}

	/**
	 * This method removes the given element from the list
	 * If the element was removed, this will return true
	 * @param element the element to remove
	 * @return true if the element was removed
	 */
	public boolean remove(E element) {
		try {
			remove(indexOf(element, 0, front));
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * This is a private helper method for the remove element method
	 * @param element the element
	 * @param index the index
	 * @param head the head of the list
	 * @return the index of the element
	 */
	private int indexOf(E element, int index, ListNode head) {
		if(head == null) return -1;
		if(head.data.equals(element)) return index;
		else return indexOf(element, index + 1, head.next);
	}

	/**
	 * This method removes the element at the given index
	 * @param index the index to remove the element of
	 * @return the value removed from the list
	 */
	public E remove(int index) {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		if(index == 0) {
			E ret = front.data;
			front = front.next;
			size--;
			return ret;
		} else {
			return remove(index, front);
		}
	}
	private E remove(int index, ListNode head) {
		if(index == 1) {
			E ret = head.next.data;
			head.next = head.next.next;
			size--;
			return ret;
		} else {
			return remove(index - 1, head.next);
		}
	}

	/**
	 * This method replaces the element at the given index with
	 * the given element
	 * @param index the index of the element to replace
	 * @param element the new element 
	 * @return the value that was replaced
	 */
	public E set(int index, E element) { 
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		if(element == null) throw new NullPointerException();
		if(contains(element)) throw new IllegalArgumentException();
		return set(index, element, front);
	}
	
	private E set(int index, E element, ListNode head) {
		if(index == 0) {
			E ret = head.data;
			head.data = element;
			return ret;
		} else {
			return set(index - 1, element, head.next);
		}
	}

	/**
	 * This method determines if the element given
	 * is in the list
	 * @param element the element to check for
	 * @return true if the element is in the list, false otherwise
	 */
	public boolean contains(E element) {
		return contains(front, element);
	}
	
	
	private boolean contains(ListNode head, E element) {
		if(head == null) return false;
		else if(head.data.equals(element)) return true;
		else return contains(head.next, element);
	}


	/**
	 * This class represents one element in the list
	 * @author Michael, Matthew, Cameron
	 *
	 */
	private class ListNode {
		/** This represents the data of this list Node */
		E data;
		/** This represents the next list node in the list */
		ListNode next;
		
		/**
		 * This constructs a list node with the given 
		 * data and next reference
		 * @param data the data of the node
		 * @param next the next reference of the node
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
		
		
	}

}











