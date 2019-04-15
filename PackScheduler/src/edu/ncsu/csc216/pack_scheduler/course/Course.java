package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * A Course has a certain name, title, section, credits, instructor, and meeting
 * times. Courses can be loaded from catalogs and added to Student schedules.
 * 
 * @author Elias Robertson, William Boyles, Joseph Dasilva
 *
 */
public class Course extends Activity implements Comparable<Course> {
	/** The character length of the section name */
	private static final int SECTION_LENGTH = 3; // TODO check value
	/** The minimum number of credits a Course can have */
	private static final int MIN_CREDITS = 1; // TODO check value
	/** The maximum number of credit hours a Course can have */
	private static final int MAX_CREDITS = 5; // TODO check value

	/** Course name */
	private String name;
	/** Course section */
	private String section;
	/** Course credit hours */
	private int credits;
	/** Course instructor */
	private String instructorId;
	/** The roll of students enrolled in a course. */
	private CourseRoll roll;

	/**
	 * Creates a Course with all fields defined.
	 * 
	 * @param name         the name
	 * @param title        the title
	 * @param section      the section
	 * @param credits      the credits
	 * @param instructorId the instructor's ID
	 * @param enrollmentCap the maximum number of students that can enroll in the course.
	 * @param meetingDays  the meeting days
	 * @param startTime    the start time
	 * @param endTime      the end time
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		this.roll = new CourseRoll(enrollmentCap, this);
	} 

	/**
	 * Creates a course without start/end times.
	 * 
	 * @param name         the name
	 * @param title        the title
	 * @param section      the section
	 * @param credits      the amount of credits for this class
	 * @param instructorId the instructor's ID
	 * @param enrollmentCap the maximum number of students that can enroll in the course.
	 * @param meetingDays  the days the class meets
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap,
			String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the name of the course as a String.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the course
	 * 
	 * @param name The intended name of the Course as a String.
	 * @throws IllegalArgumentException if the parameter is null or not between 4
	 *                                  and 6.
	 */
	private void setName(String name) {
		CourseNameValidator v = new CourseNameValidator();

		if (name == null) {
			throw new IllegalArgumentException("Name is null");
		}

		boolean isValid;
		try {
			isValid = v.isValid(name);
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		if (isValid) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Invalid course Name.");
		}
	}

	/**
	 * Returns the section of the course as a String.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the section of the course.
	 * 
	 * @param section The intended course section as a String.
	 * @throws IllegalArgumentException if the parameter is null, empty, or not
	 *                                  exactly 3 digits.
	 */
	public void setSection(String section) {
		if (section == null)
			throw new IllegalArgumentException("Invalid section number"); // changed post-green
		if ("".equals(section))
			throw new IllegalArgumentException("Invalid section number"); // changed post-green
		if (section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section number"); // changed post-green
		}
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section number"); // changed post-green
			}
		}
		this.section = section;
	}

	/**
	 * Returns the credit hours for the Course.
	 * 
	 * @return The Course credit hours as an integer.
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets how many credit hours the course is
	 * 
	 * @param credits The intended number of Course credits as a integer.
	 * @throws IllegalArgumentException if the parameter is not between 1 and 5.
	 */
	public void setCredits(int credits) {
		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Credits must be between 1 and 5");
		}
		this.credits = credits;
	}

	/**
	 * Returns the instructor's ID
	 * 
	 * @return the instructorId as a String.
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the instructor ID for the course
	 * 
	 * @param instructorId The intended instructor ID for the Course.
	 * @throws IllegalArgumentException if 5the parameter is null or empty.
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId != null && instructorId.equals("")) {
			throw new IllegalArgumentException("Invalid instructor unity id"); // changed post-green
		}
		this.instructorId = instructorId;
	}

	/**
	 * Getter method for the courseroll.
	 * 
	 * @return The courseroll for the course.
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

	/** Creates a hashcode for the course object */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + (instructorId.hashCode());
		result = prime * result + (name.hashCode());
		result = prime * result + (section.hashCode());
		return result;
	}

	/**
	 * Checks if two courses are equal by looking for equality on all fields.
	 * 
	 * @param obj The Object to compare against this instance of Course
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (this.getClass() != obj.getClass()) return false;

		
		
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if(instructorId == null) {
			if(other.instructorId != null) return false;
		} else if(!instructorId.equals(other.instructorId)) return false;
		if(name == null) {
			if(other.name != null) return false;
		} else if(!name.equals(other.name)) return false;
		
		if(section == null) {
			if(other.section != null) return false;
		} else if(!section.equals(other.section)) return false;
		
		return true;
	}

	/**
	 * Returns a comma-separated string of the values of course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (this.getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ this.roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
				+ this.roll.getEnrollmentCap() + "," + getMeetingDays() + "," + this.getStartTime() + ","
				+ this.getEndTime();
	}

	/**
	 * Returns a string array of the short form of what to display
	 * 
	 * @return A String array of a Course's name, section, title, meeting times, and
	 *         number of open seats.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] a = new String[5];
		a[0] = getName();
		a[1] = getSection();
		a[2] = getTitle();
		a[3] = this.getMeetingString();
		a[4] = this.roll.getOpenSeats() + "";
		return a;
	}

	/**
	 * Sets the days the course meets
	 * 
	 * @param meetingDays the days the course meets
	 * @throws IllegalArgumentException if the parameter is null, empty, has invalid
	 *                                  day abbreviations, or has other days with
	 *                                  Arranged.
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days"); // changed post-green
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			char a = meetingDays.charAt(i);
			if (a == 'A' && meetingDays.length() > 1) {
				throw new IllegalArgumentException("Cannot have both A and other days");
			}
			if (a != 'M' && a != 'T' && a != 'W' && a != 'H' && a != 'F' && a != 'A') {
				throw new IllegalArgumentException("Must be a valid day");
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Returns a string array of the long version of what event attributes to
	 * display
	 * 
	 * @return A String array of a Course's name, section, title, credits,
	 *         instructor ID, meeting times, and an empty string.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] a = new String[7];
		a[0] = getName();
		a[1] = getSection();
		a[2] = getTitle();
		a[3] = Integer.toString(getCredits());
		a[4] = getInstructorId();
		a[5] = this.getMeetingString();
		a[6] = "";

		return a;
	}

	/**
	 * Checks if the activity is a duplicate of this instance.
	 * 
	 * @param activity The activity to compare against this instance of Course.
	 * @throws IllegalArgumentException if the two Activities are indeed duplicates.
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course a = (Course) activity;
			if (a.getName().equals(this.getName()) && a.getSection().equals(this.getSection())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Compares Courses by name, then section.
	 * 
	 * @param c The Course to compare to this instance of Course.
	 */
	@Override
	public int compareTo(Course c) {
		int nameCompare = getName().compareTo(c.getName());

		if (nameCompare == 0) {
			return getSection().compareTo(c.getSection());
		} else {
			return nameCompare;
		}
	}

}
