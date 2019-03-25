package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Creates a student object when constructed and lets it be manipulated
 * 
 * @author William Boyles, Joseph Dasilva, Elias Robertson
 */
public class Student extends User implements Comparable<Student> {

	/** The Student's schedule. */
	private Schedule schedule;
	/** The maximum credit hours the student can enroll in. */
	private int maxCredits;

	/** Max credits allowed to enroll */
	public static final int MAX_CREDITS = 18; //check value

	/**
	 * Creates a student with custom max credit hours
	 * 
	 * @param firstName  the first name
	 * @param lastName   the last name
	 * @param id         the id of the student
	 * @param email      the email of the student
	 * @param hashPW     the hashed password
	 * @param maxCredits Custom maximum credits
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		this.schedule = new Schedule();
	}

	/**
	 * Creates a student object with all parameters except the credits
	 * 
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @param id        the student's ID
	 * @param email     The email of the student
	 * @param hashPW    The password in hash
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
	}

	/**
	 * Returns the max credits this student may take
	 * 
	 * @return the max credits this student may take
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the max credits a student can take in a semester
	 * 
	 * @param maxCredits the max amount of credits
	 * @throws IllegalArgumentException if the parameter is less than three or
	 *                                  greater than eighteen.
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		}

		this.maxCredits = maxCredits;
	}

	/**
	 * Returns a Student's fields as a comma-separated list
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ Integer.toString(maxCredits);
	}

	/**
	 * Compares two Students to tell the "difference" between them
	 * 
	 * @param student The Student to compare against this
	 * @throws NullPointerException if this or the argument is null.
	 */
	@Override
	public int compareTo(Student student) {
		if (student == null || this == null) {
			throw new NullPointerException();
		}

		int lastNameDifference = getLastName().compareTo(student.getLastName());
		if (lastNameDifference != 0) {
			return lastNameDifference;
		} else {
			int firstNameDifference = getFirstName().compareTo(student.getFirstName());
			if (firstNameDifference != 0) {
				return firstNameDifference;
			} else {
				return getId().compareTo(student.getId());
			}
		}
	}

	/**
	 * Generates a hash code for a Student using Student's and its superclass'
	 * fields.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Tells if an Object is equal to this instance of Student.
	 * 
	 * @param obj the Object to compare against this instance of Student.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}

	/**
	 * Gets the student's schedule
	 * @return A Schedule object representing the student's schedule.
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Determines if the student is allowed to add the course to their schedule.
	 *
	 * @param course The course to be added.
	 * @return true if the course can be added to the student's schedule.
	 */
	public boolean canAdd(Course course) {
		if (schedule.canAdd(course) && (schedule.getScheduleCredits() + course.getCredits()) <= maxCredits) {
			return true;
		}
		return false;
	}

}
