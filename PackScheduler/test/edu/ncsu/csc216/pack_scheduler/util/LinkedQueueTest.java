package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;


/**
 * @author Cameron, Michael, Matthew
 * @param <E>
 *
 */
public class LinkedQueueTest {

	@Test
	public void testEnqueue() {
		LinkedQueue<String> queue = new LinkedQueue<String>(2);
		try {
			queue.enqueue("First");
			assertEquals("First", queue.dequeue());
		} catch (IllegalArgumentException e) {
			fail();
		}
		try {
			queue.enqueue("First");
			queue.enqueue("Top");
			assertEquals("First", queue.dequeue());
			assertEquals("Top", queue.dequeue());
			assertTrue(queue.isEmpty());
		} catch (IllegalArgumentException e) {
			fail();
		}
		try {
			queue.enqueue("First");
			queue.enqueue("Second");
			queue.enqueue("Third");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Capacity has been met", e.getMessage());
		}
	}
	
	@Test
	public void testDequeue() {
		LinkedQueue<String> queue = new LinkedQueue<String>(10);
		try {
			queue.enqueue("First");
			queue.enqueue("Second");
			assertEquals("First", queue.dequeue());
			assertEquals("Second", queue.dequeue());
		} catch (IllegalArgumentException e) {
			fail();
		}
		try {
			queue.enqueue("First");
			queue.enqueue("Second");
			assertEquals("First", queue.dequeue());
			assertEquals("Second", queue.dequeue());
			queue.dequeue();
			fail();
		} catch (NoSuchElementException e) {
			assertEquals("Queue is empty", e.getMessage());
		}
	}
	
	@Test
	public void testIsEmpty() {
		LinkedQueue<String> queue = new LinkedQueue<String>(10);
		assertTrue(queue.isEmpty());
	}
	
	@Test
	public void testSetCapacity() {
		LinkedQueue<String> queue = new LinkedQueue<String>(0);
		try {
			queue.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, queue.size());
		}
	}
}



