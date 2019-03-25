package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

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

	/**
	 * Constructor of the CourseRoll Class
	 * 
	 * @param cap the maximum number of students allowed on the roll
	 */
	public CourseRoll(int cap) {
		roll = new LinkedAbstractList<Student>(cap);
		setEnrollmentCap(cap);
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

		roll.setCapacity(cap);
		this.enrollmentCap = cap;
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
		if ((s == null) || (roll.size() == enrollmentCap)) {
			throw new IllegalArgumentException();
		}

		// if the student is a duplicate, the IllegalArgumentException will just be
		// thrown from add()
		if (canEnroll(s)) {
			roll.add(s);
		} else {
			throw new IllegalArgumentException();
		}
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

		roll.remove(s);
	}

	/**
	 * Gets the remaining number of seats in the Course
	 * 
	 * @return the remaining number of seats in the Course
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}

	/**
	 * Checks to see if the Student can enroll in the Course
	 * 
	 * @param s the Student
	 * @return true unless the Course is full or the Student is already enrolled in
	 *         that Course
	 */
	public boolean canEnroll(Student s) {
		// class if full
		if (roll.size() == enrollmentCap) {
			return false;
		}
		
		// student is already enrolled
		for (int i = 0; i < roll.size(); i++) {
			if (s.equals(roll.get(i))) {
				return false;
			}
		}

		return true;
	}
}
