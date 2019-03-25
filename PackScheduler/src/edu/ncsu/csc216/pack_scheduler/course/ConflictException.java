package edu.ncsu.csc216.pack_scheduler.course;

/**
 * An exception that is thrown when two activities conflict with one another
 * 
 * @author Elias Robertson
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a conflict exception
	 * 
	 * @param message the conflict message.
	 */
	ConflictException(String message) {
		super(message);
	}

	/**
	 * Constructs a conflict exception with the default message of "Schedule
	 * conflict."
	 */
	ConflictException() {
		super("Schedule conflict.");
	}
}
