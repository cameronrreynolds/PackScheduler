package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Tests the SortedList class
 * 
 * @author William Boyles, Joseph Dasilva, Elias Robertson
 *
 */
public class SortedListTest {

	/**
	 * Test the SortedList constructor
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		// Test that the list grows by adding at least 11 elements
		list.add("pear");
		list.add("grape");
		list.add("peach");
		list.add("apricot");
		list.add("banana");
		list.add("grapefruit");
		list.add("lime");
		list.add("lemon");
		list.add("plum");
		list.add("watermelon");

		// Remember the list's initial capacity is 10
		try {
			list.add("mango");
			list.add("cantalope");
			assertEquals(12, list.size());
		} catch (IllegalArgumentException e) {
			fail();
		}

	}

	/**
	 * Tests add
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		// Test adding to the front, middle and back of the list
		list.add("apple");
		list.add("orange");
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("orange", list.get(2));

		// Test adding a null element
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals("orange", list.get(2));
		}

		// Test adding a duplicate element
		try {
			list.add("apple");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(3, list.size());
		}

	}

	/**
	 * Test get
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		// Since get() is used throughout the tests to check the
		// contents of the list, we don't need to test main flow functionality
		// here. Instead this test method should focus on the error
		// and boundary cases.

		// Test getting an element from an empty list
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		// Add some elements to the list
		list.add("apple");
		list.add("banana");
		list.add("pear");
		list.add("zebra");

		// Test getting an element at an index < 0
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("pear", list.get(2));
			assertEquals("zebra", list.get(3));
		}

		// Test getting an element at size
		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("pear", list.get(2));
			assertEquals("zebra", list.get(3));
		}
	}

	/**
	 * tests remove
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		// Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		// Add some elements to the list - at least 4
		list.add("apple");
		list.add("banana");
		list.add("pear");
		list.add("zebra");

		// Test removing an element at an index < 0
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("pear", list.get(2));
			assertEquals("zebra", list.get(3));
		}

		// Test removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("pear", list.get(2));
			assertEquals("zebra", list.get(3));
		}

		// Test removing a middle element
		list.remove(1);
		assertEquals("pear", list.get(1));

		// Test removing the last element
		list.remove(list.size() - 1);
		assertEquals("pear", list.get(list.size() - 1));

		// Test removing the first element
		list.remove(0);
		assertEquals("pear", list.get(0));

		// Test removing the last element
		list.remove(list.size() - 1);
		assertEquals(0, list.size());
	}

	/**
	 * tests indexOf
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		// Test indexOf on an empty list
		try {
			assertEquals(-1, list.indexOf("watermelon"));
		} catch (NoSuchElementException e) {
			assertEquals(0, list.size());
		}
		// Add some elements
		list.add("pear");
		list.add("grape");
		list.add("peach");
		list.add("apricot");
		list.add("banana");
		list.add("grapefruit");
		list.add("lime");
		list.add("lemon");
		list.add("plum");
		list.add("watermelon");

		// Test various calls to indexOf for elements in the list
		// and not in the list
		try {
			// Inside the list
			assertEquals(0, list.indexOf("apricot"));
			assertEquals(1, list.indexOf("banana"));
			assertEquals(2, list.indexOf("grape"));
			assertEquals(3, list.indexOf("grapefruit"));
			assertEquals(4, list.indexOf("lemon"));
			assertEquals(5, list.indexOf("lime"));
			assertEquals(6, list.indexOf("peach"));
			assertEquals(7, list.indexOf("pear"));
			assertEquals(8, list.indexOf("plum"));
			assertEquals(9, list.indexOf("watermelon"));

			// Not in the list
			assertEquals(-1, list.indexOf("mango"));
		} catch (NoSuchElementException e) {
			assertEquals(10, list.size());
		}

		// Test checking the index of null
		try {
			list.indexOf(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(10, list.size());
		}
	}

	/**
	 * tests clear
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		// Add some elements
		list.add("pear");
		list.add("grape");

		// Clear the list
		list.clear();

		// Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * tests isEmpty
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();

		// Test that the list starts empty
		assertTrue(list.isEmpty());

		// Add at least one element
		list.add("fruit");

		// Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * tests contains
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		// Test the empty list case
		assertFalse(list.contains("fruit"));

		// Add some elements
		list.add("fruit");

		// Test some true and false cases
		assertTrue(list.contains("fruit"));
		assertFalse(list.contains("vegetables"));
	}

	/**
	 * tests equals
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("apricot");
		list1.add("banana");
		list1.add("grapefruit");
		list2.add("apricot");
		list2.add("banana");
		list2.add("grapefruit");
		list3.add("zucchini");
		list3.add("corn");
		list3.add("broccoli");

		// Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		assertFalse(list2.equals(list3));
		assertFalse(list3.equals(list2));
		assertFalse(list1.equals(list3));
		assertFalse(list3.equals(list1));
	}

	/**
	 * tests hashCode
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("apricot");
		list1.add("banana");
		list1.add("grapefruit");
		list2.add("apricot");
		list2.add("banana");
		list2.add("grapefruit");
		list3.add("zucchini");
		list3.add("corn");
		list3.add("broccoli");

		// Test for the same and different hashCodes
		assertTrue(list1.hashCode() == list2.hashCode());
		assertFalse(list2.hashCode() == list3.hashCode());
		assertFalse(list3.hashCode() == list1.hashCode());
	}

}
