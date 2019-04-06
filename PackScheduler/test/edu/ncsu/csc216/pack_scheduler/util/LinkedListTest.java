package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.ListIterator;

import org.junit.Test;
/**
 * Tests the ArrayList Class
 * @author jhdav
 *
 */
public class LinkedListTest {

	/**
	 * Tests the constructor for the ArrayList Class
	 */
	@Test
	public void testArrayList() {
		LinkedList<String> test = new LinkedList<String>();
		assertNotNull(test);
		LinkedList<Integer> test2 = new LinkedList<Integer>();
		assertNotNull(test2);
	}

	/**
	 * Tests the add method for the ArrayList Class
	 */
	@Test
	public void testAdd() {
		LinkedList<String> test = new LinkedList<String>();
		test.add(0, "First");
		test.add(1, "Second");
		test.add(2, "Third");
		test.add(3, "Fourth");
		assertEquals(test.get(0), "First");
		assertEquals(4, test.size());
		test.add(2, "Fifth");
		assertEquals(test.get(0), "First");
		assertEquals(test.get(1), "Second");
		assertEquals(test.get(2), "Fifth");
		assertEquals(test.get(3), "Third");
		assertEquals(test.get(4), "Fourth");
		
		test.clear();
		test.add(0, "First");
		test.add(1, "Second");
		test.add(2, "Third");
		test.add(3, "Fourth");
		test.add(4, "Fifth");
		test.add(5, "Sixth");
		test.add(6, "Seventh");
		test.add(7, "Eighth");
		test.add(8, "Ninth");
		test.add(9, "Tenth");
		test.add(10, "Eleventh");
		test.add(11, "Twelfth");
		assertEquals(test.get(0), "First");
		assertEquals(test.get(1), "Second");
		assertEquals(test.get(2), "Third");
		assertEquals(test.get(3), "Fourth");
		assertEquals(test.get(4), "Fifth");
		assertEquals(test.get(5), "Sixth");
		assertEquals(test.get(6), "Seventh");
		assertEquals(test.get(7), "Eighth");
		assertEquals(test.get(8), "Ninth");
		assertEquals(test.get(9), "Tenth");
		assertEquals(test.get(10), "Eleventh");
		assertEquals(test.get(11), "Twelfth");
	}
	
	/**
	 * Tests the remove method for the ArrayList Class
	 */
	@Test
	public void testRemove() {
		LinkedList<String> test = new LinkedList<String>();
		test.add(0, "First");
		test.add(1, "Second");
		test.add(2, "Third");
		test.add(3, "Fourth");
		test.remove(2);
		assertEquals(test.get(0), "First");
		assertEquals(test.get(1), "Second");
		assertEquals(test.get(2), "Fourth");
		
	}
	
	/**
	 * Tests the get method for the ArrayList Class
	 */
	@Test
	public void testGet() {
		LinkedList<String> test = new LinkedList<String>();
		test.add(0, "First");
		assertEquals(test.get(0), "First");
	}
	
	/**
	 * Tests the set method for the ArrayList Class
	 */
	@Test
	public void testSet() {
		LinkedList<String> test = new LinkedList<String>();
		test.add(0, "First");
		test.set(0, "Second");
		assertEquals(test.get(0), "Second");
		try {
			test.set(0, null);
			fail();
		} catch(NullPointerException e) {
			assertEquals(test.get(0), "Second");
		}
		try {
			test.set(-1, "Third");
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertEquals(test.get(0), "Second");
		}
		try {
			test.set(0, "Second");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals(test.get(0), "Second");
		}
		
	}
	
	/**
	 * Tests the size method for the ArrayList Class
	 */
	@Test
	public void testSize() {
		LinkedList<String> test = new LinkedList<String>();
		assertEquals(0, test.size());
		test.add(0, "First");
		assertEquals(1, test.size());
	}
	
	/**
	 * Replicates a failing TS test.
	 */
	@Test
	public void testTSAdd() {
		LinkedList<String> test = new LinkedList<String>();
		assertEquals(0, test.size());
		
		test.add(0, "apple");
		assertEquals(1, test.size());
		assertEquals("apple", test.get(0));
		
		test.add(0, "orange");
		assertEquals(2, test.size());
		assertEquals("orange", test.get(0));
		assertEquals("apple", test.get(1));
		
		test.add(1, "banana");
		assertEquals(3, test.size());
		assertEquals("orange", test.get(0));
		assertEquals("banana", test.get(1));
		assertEquals("apple", test.get(2));
		
		test.add(3, "kiwi");
		assertEquals("orange", test.get(0));
		assertEquals("banana", test.get(1));
		assertEquals("apple", test.get(2));
		assertEquals("kiwi", test.get(3));
		
		try {
			test.add(-1, "mango");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("orange", test.get(0));
			assertEquals("banana", test.get(1));
			assertEquals("apple", test.get(2));
			assertEquals("kiwi", test.get(3));
		}
		
	}
	
	/**
	 * Tests the add method for the LinkedList Class
	 */
	@Test
	public void testAddatEnd() {
		LinkedList<String> test = new LinkedList<String>();
		test.add(0, "First");
		test.add(1, "Second");
		test.add("Third");
		assertEquals("First", test.get(0));
		assertEquals("Second", test.get(1));
		assertEquals("Third", test.get(2));
	}
	
	
	
	/**
	 * Tests the remove method for the LinkedList Class
	 */
	@Test
	public void testIterator() {
		LinkedList<String> test = new LinkedList<String>();
		test.add(0, "First");
		test.add(1, "Second");
		test.add("Third");
		ListIterator<String> it = test.listIterator();
		assertFalse(it.hasPrevious());
		assertEquals(0, it.nextIndex());
		assertEquals(-1, it.previousIndex());
		it.next();
		assertEquals("First", it.previous());
	}
	
	
	
	
	
	
	
	
}
