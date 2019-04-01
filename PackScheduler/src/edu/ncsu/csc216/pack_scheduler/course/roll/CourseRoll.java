package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Creates an Enrollment Process for students to enroll and drop from a Course
 * and also sets the enrollment cap for the specific Course
 * 
 * @author jhdav
 *
 */
public class CourseRoll {
	
	/** A LinkedAbstractList of Students in a certain Course */
	private LinkedAbstractList<Student> roll;
	/** The maximum number of Students allowed in a Course */
	private int enrollmentCap;
	/** The lowest possible enrollmentCap */
	private static final int MIN_ENROLLMENT = 10;
	/** The highest possible enrollmentCap */
	private static final int MAX_ENROLLMENT = 250;
	/** The waitlist for students trying to enroll in a course. */
	private LinkedQueue<Student> waitlist;
	/** Course that is with this course roll */
	private Course c;
	

	/**
	 * Constructor of the CourseRoll Class
	 * 
	 * @param cap the maximum number of students allowed on the roll
	 * @param c the course for the course Roll
	 */
	public CourseRoll(int cap, Course c) {
		this.c = c;
		roll = new LinkedAbstractList<Student>(cap);
		setEnrollmentCap(cap);
		waitlist = new LinkedQueue<Student>(10);
	}

	/**
	 * Sets the enrollment to a maximum capacity as long as it is within the MIN and
	 * MAX enrollment capacity set by the school/university
	 * 
	 * @param cap the maximum number of students allowed on the roll
	 * @throws IllegalArgumentException if the parameter is less than MIN_ENROLLMENT
	 *                                  or greater than MAX_ENROLLMENT.
	 */
	public void setEnrollmentCap(int cap) {
		if (cap < MIN_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		if (cap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}
		if(cap < roll.size()) {
			throw new IllegalArgumentException();
		}

		this.enrollmentCap = cap;
		roll.setCapacity(cap);
	}

	/**
	 * Gets the enrollment cap for the instance
	 * 
	 * @return enrollmentCap the max number of students allowed in the course
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * Enrolls a student into a Course
	 * 
	 * @throws IllegalArgumentException if the Student is null, the class is full,
	 *                                  if the student is already enrolled, or if
	 *                                  the student can't enroll.
	 * @param s the Student being enrolled
	 */
	public void enroll(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}

		for(int i = 0; i < roll.size(); i++) {
			if(roll.get(i).equals(s)) {
				throw new IllegalArgumentException();
			}
		}
		
		if(roll.size() ==  roll.getCapacity()) {
			if(waitlist.size() == 10) {
				throw new IllegalArgumentException();
			}
			try {
				waitlist.enqueue(s);
				return;
			} catch(IllegalArgumentException e) {
				throw new IllegalArgumentException();
			}
		}
		roll.add(s);
	}


	/**
	 * Drops a student from the Course
	 * 
	 * @throws IllegalArgumentException if the Student is null
	 * @param s the Student being enrolled
	 */
	public void drop(Student s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		for(int i = 0; i < roll.size(); i++) {
			if(roll.get(i).equals(s)) {
				roll.remove(i);
				if(!waitlist.isEmpty()) {
					Student s1 = waitlist.dequeue();
					roll.add(s1);
					s1.getSchedule().addCourseToSchedule(c);
				}
				return;
			}
		}
		
		// This goes through waitlist and removes them from the list
		Student s1;
		for(int i = 0; i < waitlist.size(); i++) {
			s1 = waitlist.dequeue();
			if(!s.equals(s1)) {
				waitlist.enqueue(s1);
			}
		}
	}

	/**
	 * Gets the remaining number of seats in the Course
	 * 
	 * @return the remaining number of seats in the Course
	 */
	public int getOpenSeats() {
		return roll.getCapacity() - roll.size();
	}

	/**
	 * Checks to see if the Student can enroll in the Course
	 * 
	 * @param s the Student
	 * @return true unless the Course is full or the Student is already enrolled in
	 *         that Course
	 */
	public boolean canEnroll(Student s) {
		if(waitlist.size() == 10 && roll.size() - enrollmentCap == 0) {
			return false;
		}
		for(int i = 0; i < roll.size(); i++) {
			if(roll.get(i).equals(s)) return false;
		}
		Student s1;
		boolean test = true;
		for(int i = 0; i < waitlist.size(); i++) {
			s1 = waitlist.dequeue();
			if(s.equals(s1)) {
				test = false;
			}
			waitlist.enqueue(s1);
		}
		return test;
	}
	
	/**
	 * Gives the number of students on the waitlist.
	 * 
	 * @return The number of students on the waitlist.
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
	
}
