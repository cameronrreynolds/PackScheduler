package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * This class represents a custom LinkedList. In this linked list, it can not have any duplicates or
 * null values
 * @author Michael, Matthew, Cameron
 *
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	/** This represnets the front of the list */
	public ListNode front;
	/** This represents the back of the list */
	public ListNode back;
	/** This represents the size of the list */
	public int size;
	
	/**
	 * This constructs a LinkedList where the the front and back are initially contain
	 * data that are null. And the size is 0.
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}
	/**
	 * This method returns the current size of the Linked list
	 */
	public int size() {
		return size;
	}
	
	
	
	
	
	
	/**
	 * This method sets the given element at the given index. This is overridden to check
	 * that the element we are setting is not a duplicate of another element as defined
	 * by the contains method
	 * @param index the index to set to
	 * @param element the element's data
	 * @return the element previously at the specified index
	 */
	public E set(int index, E element) {
		if(contains(element)) throw new IllegalArgumentException();
		return super.set(index, element);
	}
	
	
	/**
	 * This method adds the given element at the given index. This is overridden to
	 * check that we are not adding duplicate values as defined by the contains method
	 * @param index the index to add the element
	 * @param element the element to add
	 */
	public void add(int index, E element) {
		if(contains(element)) throw new IllegalArgumentException();
		super.add(index, element);
	}
	/**
	 * This constructs and returns a list iterator that would return the node at 
	 * the given index with a call to next()
	 * @param index the index to position the iterator
	 * @return the list iterator
	 */
	public ListIterator<E> listIterator(int index) {
		try {
			LinkedListIterator ret = new LinkedListIterator(index);
			return ret;
		} catch(IndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}
	}
	
	
	
	
	/**
	 * This class represents the iterator of a LinkedList
	 * @author Michael, Matthew, Cameron
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** Represents the ListNode that would be returned on a call to previous() */
		public ListNode previous;
		/** Represents the ListNode that would be returned on a call to next() */
		public ListNode next;
		/** The index that would be returned on a call to previousIndex() */
		public int previousIndex;
		/** The index that would be returned on a call to nextIndex() */
		public int nextIndex;
		/** 
		 * represents the ListNode that was returned on the last call 
		 * to either previous() or next() or null if a call to previous() 
		 * or next() was not the last call on the ListIterator.
		 */
		public ListNode lastRetrieved;
		
		
		/**
		 * This constructs a LinkedListIterator at the given index
		 * @param index the index to position the iterator at
		 */
		public LinkedListIterator(int index) {
			if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
			lastRetrieved = null;
			nextIndex = index;
			previousIndex = index - 1;
			ListNode curr = front;
			int i = 0;
			while(i < index) {
				curr = curr.next;
			}
			previous = curr;
			next = curr.next;
			
		}


		/**
		 * This method determines if there is a next node. There is a 
		 * next node if the next node's data is not null
		 * @return true if the next node's data is not null, false otherwise
		 */
		public boolean hasNext() {
			if(next.data != null) return true;
			return false;
		}
		/**
		 * This method determines if there is a prev node. There is a 
		 * prev node if the prev node's data is not null
		 * @return true if the prev node's data is not null, false otherwise
		 */
		public boolean hasPrevious() {
			if(previous.data != null) return true;
			return false;
		}

		/**
		 * Returns the index of the next node
		 * @return the index of the next node
		 */
		public int nextIndex() {
			return nextIndex;
		}


		/**
		 * Returns the index of the previous node
		 * @return the index of the previous node
		 */
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * This method returns the data at the next node. This will also shift the
		 * iterator one node to the next.
		 * @return the data at the next node
		 */
		public E next() {
			if(next.data == null) throw new NoSuchElementException();
			lastRetrieved = next;
			E data = next.data;
			ListNode tmp = next;
			next = next.next;
			previous = tmp;
			nextIndex++;
			previousIndex++;
			return data;
		}


		/**
		 * This method returns the data at the previous node. This will also shift the
		 * iterator one node to the previous
		 * @return the data at the previous node.
		 */
		public E previous() {
			if(previous.data == null) throw new NoSuchElementException();
			lastRetrieved = previous;
			E data = previous.data;
			ListNode tmp = previous;
			previous = previous.prev;
			next = tmp;
			nextIndex--;
			previousIndex--;
			return data;
		}
		
		
		/**
		 * This method adds a node with the given element in between the next and 
		 * previous nodes. 
		 * @param element the data of the node to add
		 */
		public void add(E element) {
			if(element == null) throw new NullPointerException();
			ListNode insert = new ListNode(element, previous, next);
			previous.next = insert;
			previous = insert;
			next.prev = insert;
			
			lastRetrieved = null;
			size++;
		}
		
		
		/**
		 * This method replaces the element last returned by the next() or 
		 * previous() method calls
		 * @param element the element's new data
		 */
		public void set(E element) {
			if(element == null) throw new NullPointerException();
			if(lastRetrieved == null) throw new IllegalStateException();
			lastRetrieved.data = element;
		}


		/**
		 * This method removes the last retrieved node as determined by the last 
		 * call to next() or previous(). If lastRetrieved is null, this will throw 
		 * a IllegalStateException.  
		 */
		public void remove() {
			if(lastRetrieved == null) throw new IllegalStateException();
			next.prev = lastRetrieved.prev;
			previous.next = lastRetrieved.next;
			lastRetrieved = null;
			size--;
			
		}

	}
	
	/**
	 * This class represents a doubly linked list as it refers to both the next and 
	 * previous nodes
	 * @author Michael, Cameron, Matthew
	 *
	 */
	private class ListNode { 
		/** This represents the data of this node. Of type E */
		public E data;
		/** This represents the next node */
		public ListNode next;
		/** This represents the previous node */
		public ListNode prev;
		/**
		 * This constructs a ListNode with the given data. Its next and prev reference is null
		 * @param data the data of the node
		 */
		public ListNode(E data) {
			this(data, null, null);
		}
		
		/**
		 * This constructs a list node with the given data value, previous reference, and next reference
		 * @param data the data of the node
		 * @param prev the previous reference
		 * @param next the next reference
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

	

}












