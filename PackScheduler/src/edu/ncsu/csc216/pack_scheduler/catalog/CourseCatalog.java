package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * TODO
 * 
 * @author Elias Robertson, William Boyles, Joseph Dasilva
 */
public class CourseCatalog {
	/** The list of Courses available to add to schedules. */
	private SortedList<Course> catalog;

	/**
	 * Creates an empty Course catalog by default. Courses can be added form a file
	 * in loadCoursesFromFile().
	 */
	public CourseCatalog() {
		this.catalog = new SortedList<Course>();
	}

	 /**
   * Creates an empty Course catalog by default. Courses can be added form a file
   * in loadCoursesFromFile().
   */
  public void newCourseCatalog() {
    this.catalog = new SortedList<Course>();
  }
	
	/**
	 * Loads Courses into the catalog form a specified file.
	 * 
	 * @param filename The path of the file with Course records in it.
	 */
	public void loadCoursesFromFile(String filename) {
		try {
			this.catalog = CourseRecordIO.readCourseRecords(filename);
			// this.schedule = new SortedList<Course>();
			// this.setTitle("My Schedule");
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("File not found.");
		}
	}

	/**
	 * Returns a course from the Catalog by name and section, if it is present in
	 * the catalog.
	 * 
	 * @param name    The name of the Course you're looking for as a String.
	 * @param section The section of the Course you're looking for as a String.
	 * @return The specified Course.
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	} 

	/**
	 * Adds a course to the schedule, if allowed
	 * 
	 * @param name         The name of the Course as a String
	 * @param title        The title of the Course as a String
	 * @param section      The section number of the Course as a String
	 * @param credits      The number of credit hours the Course is worth as an
	 *                     integer.
	 * @param instructorId The unityID of the Course instructor as a String.
	 * @param enrollmentCap The maximum number of students that can enroll in the course.
	 * @param meetingDays  The days of the week the Course meets as a String.
	 * @param startTime the time the course starts
	 * @param endTime the time the course ends
	 * @return True if the Course was successfully added to the catalog.
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			int enrollmentCap, String meetingDays, int startTime, int endTime) {
		Course toAdd = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays, startTime, endTime);

		for (int j = 0; j < catalog.size(); j++) {
			if (catalog.get(j).isDuplicate(toAdd)) {
				return false;
			}
		}

		catalog.add(toAdd);
		return true;
	}

	/**
	 * Removes the Course from the catalog by the Course name and section, if
	 * possible.
	 * 
	 * @param name    The name of the Course to remove as a String.
	 * @param section The section number of the Course to remove as a String.
	 * @return True if the Course was successfully removed.
	 */
	public boolean removeCourseFromCatalog(String name, String section) {

		boolean removed = false;
		for (int j = 0; j < catalog.size(); j++) {
			if (catalog.get(j).getName().equals(name) && catalog.get(j).getSection().equals(section)) {
				catalog.remove(j);
				return true;
			}
		}
		return removed;
	}

	/**
	 * Puts the course Catalog into a string array, with one row per Course. Mainly
	 * used for converting a catalog into a format better-suited for a file.
	 * 
	 * @return The Catalog as a 2D String array.
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogCompare = new String[catalog.size()][5];
		for (int i = 0; i < catalog.size(); i++) {
			catalogCompare[i][0] = catalog.get(i).getName();
			catalogCompare[i][1] = catalog.get(i).getSection();
			catalogCompare[i][2] = catalog.get(i).getTitle();
			catalogCompare[i][3] = catalog.get(i).getMeetingString();
			catalogCompare[i][4] = Integer.toString(catalog.get(i).getCourseRoll().getEnrollmentCap());
		}

		return catalogCompare;
	}

	/**
	 * Exports the Course catalog as a file. One course per row. Each field of a
	 * Course is separated by a comma.
	 * 
	 * @param filename The pathname of the file to export to as a String.
	 */
	public void saveCourseCatalog(String filename) {
		try {
			CourseRecordIO.writeCourseRecords(filename, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}

}
