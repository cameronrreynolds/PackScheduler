package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Creates a schedule, which contains an ArrayList of courses and a title.
 * Allows users to manage their course schedule.
 * 
 * @author Matthew Strickland, William Boyles, Joey Davidson
 *
 */
public class Schedule {

	/** An array list containing the courses in the schedule. */
	private ArrayList<Course> schedule;
	/** The title of the schedule. */
	private String title;

	/**
	 * Creates a new empty schedule with the default name.
	 */
	public Schedule() {
		this.schedule = new ArrayList<Course>();
		this.title = "My Schedule";
	}

	/**
	 * Getter method for the schedule.
	 * 
	 * @return An ArrayList containing the courses.
	 */
	public ArrayList<Course> getCourseSchedule() {
		return schedule;
	}

	/**
	 * Getter method for the title.
	 * 
	 * @return The title as a String.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Allows the user to set the title of the schedule.
	 * 
	 * @param title The title the user wants for their schedule.
	 * @throws IllegalArgumentException if the title is null.
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("The title cannot be null.");
		}
		this.title = title;
	}

	/**
	 * Allows users to add a course to their schedule.
	 * 
	 * @param course The course the user wants to add to their schedule.
	 * @return If the course can be added or not.
	 * @throws IllegalArgumentException if the course is a duplicate of as Course
	 *                                  already in the schedule or conflicts with a
	 *                                  Course already in the schedule.
	 */
	public boolean addCourseToSchedule(Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i) instanceof Course && schedule.get(i).getTitle().equals(course.getTitle())) {
				throw new IllegalArgumentException("You are already enrolled in " + schedule.get(i).getName());
			}
			try {
				course.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}

		schedule.add(course);
		return true;
	}

	/**
	 * Allows users to remove an activity from their schedule.
	 * 
	 * @param course The course the user wants to remove.
	 * @return If the course can be removed or not.
	 */
	public boolean removeCourseFromSchedule(Course course) {
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i).isDuplicate(course)) {
				schedule.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the schedule.
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
	}

	/**
	 * Provides the schedule as a 2D array of Strings.
	 * 
	 * @return A 2D String array of the courses in the schedule.
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduleArray = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			scheduleArray[i][0] = ((Course) schedule.get(i)).getName();
			scheduleArray[i][1] = ((Course) schedule.get(i)).getSection();
			scheduleArray[i][2] = schedule.get(i).getTitle();
			scheduleArray[i][3] = schedule.get(i).getMeetingString();
			scheduleArray[i][4] = schedule.get(i).getCourseRoll().getOpenSeats() + "";
		}
		return scheduleArray;
	}

	/**
	 * Returns the value of credit hours currently in the schedule.
	 * 
	 * @return the credit hours
	 */
	public int getScheduleCredits() {
		int totalCreditHours = 0;
		for (int i = 0; i < schedule.size(); i++) {
			totalCreditHours = totalCreditHours + schedule.get(i).getCredits();
		}
		return totalCreditHours;
	}

	/**
	 * Checks if the schedule can add a given course.
	 * 
	 * @param course The course to be added.
	 * @return A boolean showing if the course can be added.
	 */
	public boolean canAdd(Course course) {
		// false if parameter is null
		if (course == null) {
			return false;
		}
		
		//false if already enrolled or conflict exception
		for (int i = 0; i < schedule.size(); i++) {
			if (schedule.get(i) instanceof Course && schedule.get(i).getTitle().equals(course.getTitle())) {
				return false;
			}
			
			try {
				course.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				return false;
			}
		}
		
		//otherwise true
		return true;
	}

}
