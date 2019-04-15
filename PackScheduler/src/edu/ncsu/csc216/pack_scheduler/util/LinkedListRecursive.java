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
		if(isEmpty()) {
			front = new ListNode(element, null);
			size++;
			return true;
		}
		front.add(size, element);
		size++;
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
		if(index == 0) {
			front = new ListNode(element, front);
			size++;
			return;
		}
		else {
			front.add(index, element);
			size++;
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
		return front.get(index);
	}
	/**
	 * This method removes the given element from the list
	 * If the element was removed, this will return true
	 * @param element the element to remove
	 * @return true if the element was removed
	 */
	public boolean remove(E element) {
		if(element == null) throw new NullPointerException();
		if(isEmpty()) return false;
		if(front.data.equals(element)) {
			front = front.next;
			size--;
			return true;
		} else {
			boolean b = front.remove(element);
			size--;
			return b;
		}
	}
	/**
	 * This method removes the element at the given index
	 * @param index the index to remove the element of
	 * @return the value removed from the list
	 */
	public E remove(int index) {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		if(index == 0) {
			E data = front.data;
			front = front.next;
			size--;
			return data;
		} else {
			E val = front.remove(index);
			size--;
			return val;
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
		return front.set(index, element);
	}
	
	/**
	 * This method determines if the element given
	 * is in the list
	 * @param element the element to check for
	 * @return true if the element is in the list, false otherwise
	 */
	public boolean contains(E element) {
		if(isEmpty()) return false;
		return front.contains(element);
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
		
		/**
		 * This method recursively adds the element 
		 * to the given index
		 * @param index the index to add to
		 * @param element the element to add
		 */
		public void add(int index, E element) {
			if(index == 1) {
				next = new ListNode(element, next);
				return;
			}
			else {
				if(next == null) {
					next = new ListNode(element, null);
				}
				next.add(index - 1, element);
			}
		}
		/**
		 * This returns the element at the given index
		 * @param index the index of the element to get
		 * @return the element at the give index
		 */
		public E get(int index) {
			if(index == 0) {
				return data;
			} else {
				return next.get(index - 1);
			}
		}
		/**
		 * This method removes the element at the given index
		 * @param index the index of the element to remove
		 */
		public E remove(int index) {
			if(index == 1) {
				if(next.next == null) {
					E val = next.data;
					next = null;
					return val;
				}
				E val = next.data;
				next = next.next;
				return val;
			}
			else {
				return next.remove(index - 1);
			}
		}
		/**
		 * This removes the element from the list
		 * @param element the element to remove
		 * @return true if the element was removed
		 */
		public boolean remove(E element) {
			if(next == null) return false;
			if(next.data.equals(element)) {
				if(next.next == null) {
					next = null;
					return true;
				} else {
					next = next.next;
					return true;
				}
			} else {
				return next.remove(element);
			}
		}
		/**
		 * This method replaces the element at the given
		 * index with the given element
		 * @param index the index of the element to replace 
		 * @param element the element to put into the list
		 */
		public E set(int index, E element) {
			if(index == 0) {
				E ret = data;
				data = element;
				return ret;
			} else {
				return next.set(index - 1, element);
			}
		}
		/**
		 * This method determines if the given element
		 * is in the list 
		 * @param element the element to check for
		 * @return true if the element is in the list, false otherwise
		 */
		public boolean contains(E element) {
			if(data.equals(element)) return true;
			if(next == null) return false;
			return next.contains(element);
			
		}
	}

}











