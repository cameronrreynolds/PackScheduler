/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.EmptyStackException;

import org.junit.*;


/**
 * This class tests the ArrayStack class and all of its methods
 * @author Cameron, Michael, Matthew
 *
 */
public class ArrayStackTest {

	/**
	 * This tests the push method
	 */
	@Test
	public void testPush() {
		ArrayStack<String> stack = new ArrayStack<String>(2);
		try {
			stack.push("First");
			assertEquals("First", stack.pop());
		} catch (IllegalArgumentException e) {
			fail();
		}
		try {
			stack.push("First");
			stack.push("Top");
			assertEquals("Top", stack.pop());
			assertEquals("First", stack.pop());
			assertTrue(stack.isEmpty());
		} catch (IllegalArgumentException e) {
			fail();
		}
		try {
			stack.push("First");
			stack.push("Second");
			stack.push("Third");
			fail();
		} catch (IllegalArgumentException e) {
			// do nothing test passed
		}
	}
	
	/**
	 * This tests the pop method
	 */
	@Test
	public void testPop() {
		ArrayStack<String> stack = new ArrayStack<String>(10);
		try {
			stack.push("First");
			stack.push("Second");
			assertEquals("Second", stack.pop());
			assertEquals("First", stack.pop());
		} catch (IllegalArgumentException e) {
			fail();
		}
		try {
			stack.push("First");
			stack.push("Second");
			assertEquals("Second", stack.pop());
			assertEquals("First", stack.pop());
			stack.pop();
			fail();
		} catch (EmptyStackException e) {
			// do nothing test passed
		}
	}
	
	/**
	 * This tests the isEmpty method
	 */
	@Test
	public void testIsEmpty() {
		ArrayStack<String> stack = new ArrayStack<String>(10);
		assertEquals(0, stack.size());
	}
	
	/**
	 * This tests the setCapacity method
	 */
	@Test
	public void testSetCapacity() {
		ArrayStack<String> stack = new ArrayStack<String>();
		try {
			stack.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, stack.size());
		}
	}
}
