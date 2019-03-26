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
 * @author Cameron, Michael, Matthew
 * @param <E>
 *
 */
public class LinkedStackTest {

	@Test
	public void testPush() {
		LinkedStack<String> stack = new LinkedStack<String>(2);
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

		}
	}
	
	@Test
	public void testPop() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
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
		
		}
	}
	
	@Test
	public void testIsEmpty() {
		LinkedStack<String> stack = new LinkedStack<String>(10);
		assertEquals(0, stack.size());
	}
	
	@Test
	public void testSetCapacity() {
		LinkedStack<String> stack = new LinkedStack<String>();
		try {
			stack.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, stack.size());
		}
	}
}
