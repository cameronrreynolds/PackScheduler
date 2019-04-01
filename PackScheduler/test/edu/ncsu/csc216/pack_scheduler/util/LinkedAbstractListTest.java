package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of the LinkedAbstractList class.
 * 
 * @author William Boyles, Matthew Strickland, Joey Davidson
 *
 */
public class LinkedAbstractListTest {

	/** A list of type String that will be used for testing very often */
	private LinkedAbstractList<String> l;
	/** The initial capacity of the list */
	private static final int INITIAL_LIST_CAP = 10;

	/**
	 * Makes the l field an empty String list with a capacity of the
	 * INITIAL_LIST_CAP field.
	 */
	@Before
	public void setUp() {
		l = new LinkedAbstractList<String>(INITIAL_LIST_CAP);
	}

	/**
	 * Tests the construction of a LinkedAbstractList.
	 */
	@Test
	public void testLinkedAbstractList() {
		assertEquals(0, l.size());
	}

	/**
	 * Tests the method that inserts elements into the list at a given index.
	 */
	@Test
	public void testAddIntE() {
		assertEquals(0, l.size());

		// index out of bounds
		try {
			l.add(Integer.MAX_VALUE, "Infinity");
			fail("Added at too high index");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, l.size());
		}

		try {
			l.add(Integer.MIN_VALUE, "Infinity");
			fail("Added at too low index");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, l.size());
		}

		// front
		l.add(0, "0");
		assertEquals(1, l.size());
		assertEquals("0", l.get(0));

		// illegal arg duplicate
		try {
			l.add(0, "0");
			fail("Duplicate element added");
		} catch (IllegalArgumentException e) {
			assertEquals(1, l.size());
			assertEquals("0", l.get(0));
		}

		// illegal arg filled completely
		l.setCapacity(1);
		try {
			l.add(1, "1");
			fail("Added beyond capacity");
		} catch (IllegalArgumentException e) {
			assertEquals(1, l.size());
			assertEquals("0", l.get(0));
		}

		// null pointer null element
		l.setCapacity(4);
		try {
			l.add(1, null);
			fail("Added null element");
		} catch (NullPointerException e) {
			assertEquals(1, l.size());
			assertEquals("0", l.get(0));
		}

		// end
		l.add(1, "2");
		assertEquals(2, l.size());
		assertEquals("0", l.get(0));
		assertEquals("2", l.get(1));

		// middle
		l.add(1, "1");
		assertEquals(3, l.size());
		assertEquals("0", l.get(0));
		assertEquals("1", l.get(1));
		assertEquals("2", l.get(2));
	}

	/**
	 * Tests the method that adds elements to the and of the list.
	 */
	@Test
	public void testAddE() {
		assertEquals(0, l.size());

		l.add("0");
		assertEquals(1, l.size());
		assertEquals("0", l.get(0));

		l.add("1");
		assertEquals(2, l.size());
		assertEquals("0", l.get(0));
		assertEquals("1", l.get(1));
	}

	/**
	 * Tests getting the size of a LinkedAbstractList.
	 */
	@Test
	public void testSize() {
		// add some elements
		testGetInt();

		assertEquals(5, l.size());
		assertEquals("0", l.get(0));
		assertEquals("1", l.get(1));
		assertEquals("2", l.get(2));
		assertEquals("3", l.get(3));
		assertEquals("4", l.get(4));

		l.add(l.size(), "5");
		assertEquals(6, l.size());
		assertEquals("0", l.get(0));
		assertEquals("1", l.get(1));
		assertEquals("2", l.get(2));
		assertEquals("3", l.get(3));
		assertEquals("4", l.get(4));
		assertEquals("5", l.get(5));

		l.remove(5);
		assertEquals(5, l.size());
		assertEquals("0", l.get(0));
		assertEquals("1", l.get(1));
		assertEquals("2", l.get(2));
		assertEquals("3", l.get(3));
		assertEquals("4", l.get(4));
	}

	/**
	 * Tests setting the capacity of a LinkedAbstractList.
	 */
	@Test
	public void testSetCapacity() {
		//valid
		l.setCapacity(0);
		
		try {
			l.add("0");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Capacity has been met", e.getMessage());
		}
		
		//Illegal arg (cap < size)
		l.setCapacity(3);
		
		l.add("0");
		l.add("1");
		l.add("2");
		assertEquals(3, l.size());
		
		try {
			l.setCapacity(2);
			fail("Set capacity lower than size");
		} catch (IllegalArgumentException e) {
			assertEquals(3, l.size());
		}
	}

	/**
	 * Tests getting an integer from a LinkedAbstractList.
	 */
	@Test
	public void testGetInt() {
		l.add("0");
		l.add("1");
		l.add("2");
		l.add("3");
		l.add("4");

		assertEquals(5, l.size());
		assertEquals("0", l.get(0));
		assertEquals("1", l.get(1));
		assertEquals("2", l.get(2));
		assertEquals("3", l.get(3));
		assertEquals("4", l.get(4));

		// index exception
		// low
		try {
			l.get(Integer.MAX_VALUE);
			fail("Got value at high index");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(5, l.size());
			assertEquals("0", l.get(0));
			assertEquals("1", l.get(1));
			assertEquals("2", l.get(2));
			assertEquals("3", l.get(3));
			assertEquals("4", l.get(4));
		}

		// high
		try {
			l.get(Integer.MIN_VALUE);
			fail("Got value at high index");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(5, l.size());
			assertEquals("0", l.get(0));
			assertEquals("1", l.get(1));
			assertEquals("2", l.get(2));
			assertEquals("3", l.get(3));
			assertEquals("4", l.get(4));
		}
	}

	/**
	 * Tests null values in the list.
	 */
	@Test
	public void testSetIntE() {
		// add some elements
		testGetInt();

		// null pointer
		try {
			l.set(0, null);
		} catch (NullPointerException e) {
			assertEquals(5, l.size());
			assertEquals("0", l.get(0));
			assertEquals("1", l.get(1));
			assertEquals("2", l.get(2));
			assertEquals("3", l.get(3));
			assertEquals("4", l.get(4));
		}

		// illegal arg (duplicate)
		try {
			l.set(1, "0");
			fail("Added duplicate element");
		} catch (IllegalArgumentException e) {
			assertEquals(5, l.size());
			assertEquals("0", l.get(0));
			assertEquals("1", l.get(1));
			assertEquals("2", l.get(2));
			assertEquals("3", l.get(3));
			assertEquals("4", l.get(4));
		}

		// index bounds
		// low
		try {
			l.set(Integer.MIN_VALUE, "-Infinity");
			fail("Added at low index");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(5, l.size());
			assertEquals("0", l.get(0));
			assertEquals("1", l.get(1));
			assertEquals("2", l.get(2));
			assertEquals("3", l.get(3));
			assertEquals("4", l.get(4));
		}

		// high
		try {
			l.set(Integer.MAX_VALUE, "Infinity");
			fail("Added at high index");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(5, l.size());
			assertEquals("0", l.get(0));
			assertEquals("1", l.get(1));
			assertEquals("2", l.get(2));
			assertEquals("3", l.get(3));
			assertEquals("4", l.get(4));
		}

		// valid
		l.set(1, "5");
		assertEquals(5, l.size());
		assertEquals("0", l.get(0));
		assertEquals("5", l.get(1));
		assertEquals("2", l.get(2));
		assertEquals("3", l.get(3));
		assertEquals("4", l.get(4));
	}

	/**
	 * Tests removing integers from the list.
	 */
	@Test
	public void testRemoveInt() {
		// add some elements
		testGetInt();

		assertEquals(5, l.size());
		assertEquals("0", l.get(0));
		assertEquals("1", l.get(1));
		assertEquals("2", l.get(2));
		assertEquals("3", l.get(3));
		assertEquals("4", l.get(4));

		// index bounds
		// low
		try {
			l.remove(Integer.MIN_VALUE);
			fail("Added at low index");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(5, l.size());
			assertEquals("0", l.get(0));
			assertEquals("1", l.get(1));
			assertEquals("2", l.get(2));
			assertEquals("3", l.get(3));
			assertEquals("4", l.get(4));
		}

		// high
		try {
			l.remove(Integer.MAX_VALUE);
			fail("Added at high index");
		} catch (IndexOutOfBoundsException e) {
			assertEquals(5, l.size());
			assertEquals("0", l.get(0));
			assertEquals("1", l.get(1));
			assertEquals("2", l.get(2));
			assertEquals("3", l.get(3));
			assertEquals("4", l.get(4));
		}

		// valid
		// front
		l.remove(0);
		assertEquals(4, l.size());
		assertEquals("1", l.get(0));
		assertEquals("2", l.get(1));
		assertEquals("3", l.get(2));
		assertEquals("4", l.get(3));

		// middle
		l.remove(1);
		assertEquals(3, l.size());
		assertEquals("1", l.get(0));
		assertEquals("3", l.get(1));
		assertEquals("4", l.get(2));

		// back
		l.remove(l.size() - 1);
		assertEquals(2, l.size());
		assertEquals("1", l.get(0));
		assertEquals("3", l.get(1));
	}

}
