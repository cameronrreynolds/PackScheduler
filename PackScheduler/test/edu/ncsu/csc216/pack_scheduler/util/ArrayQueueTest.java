package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.*;


/**
 * @author Cameron, Michael, Matthew
 * @param <E>
 *
 */
public class ArrayQueueTest {

	@Test
	public void testEnqueue() {
		ArrayQueue<String> queue = new ArrayQueue<String>(2);
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
		ArrayQueue<String> queue = new ArrayQueue<String>(10);
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
		} catch (IllegalArgumentException e) {
			assertEquals("Queue is empty", e.getMessage());
		}
	}
	
	@Test
	public void testIsEmpty() {
		ArrayQueue<String> queue = new ArrayQueue<String>(10);
		assertEquals(0, queue.size());
	}
	
	@Test
	public void testSetCapacity() {
		ArrayQueue<String> queue = new ArrayQueue<String>();
		try {
			queue.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, queue.size());
		}
	}
}
