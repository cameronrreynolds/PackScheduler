package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Handles the creation of activities which include that of events and courses
 * 
 * @author Elias Robertson, William Boyles, Joseph Dasilva
 *
 */
public abstract class Activity implements Conflict {
	/** The maximum time in a day */
	private int upperTime = 2359;
	/** The minimum time in a day */
	private int lowerTime = 0000;
	/** The maximum minutes in an hour */
	private int upperMinute = 59;
	/** To get the last two digits with modulus */
	private int hundred = 100;
	/** The time of noon */
	private int midDay = 1200;
	/** Course title */
	protected String title;
	/** Course meeting days */
	protected String meetingDays;
	/** Course start time */
	protected int startTime;
	/** Course end time */
	protected int endTime;

	/**
	 * Retrieves the short version of the array
	 * 
	 * @return the short display array
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Retrieves the long version of the array
	 * 
	 * @return the display array with all fields
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Checks if an activity is a duplicate
	 * 
	 * @return if it is a boolean
	 * @param activity the activity to compare
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Creates an Activity.
	 * 
	 * @param title       the title of the activity as a String.
	 * @param meetingDays the days the activity occurs on as a String.
	 * @param startTime   the time the activity starts as an integer.
	 * @param endTime     the time the activity ends as an integer.
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Returns the Title of the Activity.
	 * 
	 * @return The Activity title as a String.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title of the course
	 * 
	 * @param title The intended Activity title as a String.
	 * @throws IllegalArgumentException if the parameter is null or empty.
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException("Title cannot be null"); //changed post-green
		}
		if (title.equals("")) {
			throw new IllegalArgumentException("Invalid course title"); //changed post-green
		}
		this.title = title;
	}

	/**
	 * Returns the days the Activity meets.
	 * 
	 * @return The Activity meeting days as a String.
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets what days the course meets
	 * 
	 * @param meetingDays The intended meetingDays to set as a String.
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * Returns the time the course starts
	 * 
	 * @return The intended startTime as a String.
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Sets the time the course takes place in
	 * 
	 * @param startTime the intended Activity start time.
	 * @param endTime   the intended Activity end time.
	 * @throws IllegalArgumentException if the parameters don't represent a valid
	 *                                  time, the start time is after the end time,
	 *                                  or there are non-zero starts times with
	 *                                  meeting days arranged.
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (startTime < lowerTime || startTime > upperTime) {
			throw new IllegalArgumentException("Invalid meeting times"); //changed post-green
		}
		if (endTime < lowerTime || endTime > upperTime) {
			throw new IllegalArgumentException("Invalid meeting times"); //changed post-green
		}
		if (startTime > endTime) {
			throw new IllegalArgumentException("Invalid meeting times"); //changed post-green
		}
		if (this.meetingDays.equals("A") && (startTime != 0 || endTime != 0)) {
			throw new IllegalArgumentException("Invalid meeting times"); //changed post-green
		}
		if (startTime % hundred > upperMinute || endTime % hundred > upperMinute) {
			throw new IllegalArgumentException("Invalid meeting times"); //changed post-green
		}
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns the time the activity ends.
	 * 
	 * @return The Activity end time as an integer.
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Gives meeting times as 12-hour time string rather than two 24-hour time
	 * integers.
	 * 
	 * @return Meeting times as 12-hour time interval String
	 */
	public String getMeetingString() {
		String meetingString = "";

		// Appends the proper meeting days and " " if necessary
		if (this.meetingDays.equals("A"))
			return "Arranged";
		else
			meetingString += this.meetingDays + " ";

		// If not arranged, add meeting times in 12-hour format
		meetingString += convertTime(startTime) + "-" + convertTime(endTime);
		return meetingString;
	}

	/**
	 * Converts a 24-hour time integer to a 12-hour time String
	 * 
	 * @param time An integer representing a 24-hour time
	 * @return A String representing the 12-hour time corresponding to a 24-hour
	 *         time integer.
	 */
	private static String convertTime(int time) {
		String twelveHour = "";

		int startMinute = time % 100;
		int startHour = (time - startMinute) / 100;

		// Converts 24-hour to 12-hour and adds colon
		if (startHour == 12)
			twelveHour += Integer.toString(startHour) + ":";
		else if (startHour == 0) {
			twelveHour = "12:";
		}
		else {
			twelveHour += Integer.toString(startHour % 12) + ":";
		}

		// Ensures minutes have leading 0 (like 12:09)
		if (startMinute >= 10)
			twelveHour += Integer.toString(startMinute);
		else
			twelveHour += "0" + Integer.toString(startMinute);

		// Appends AM or PM
		if (startHour >= 12)
			twelveHour += "PM";
		else
			twelveHour += "AM";

		return twelveHour;
	}

	/** Creates a hashcode of this activity */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + hundred;
		result = prime * result + lowerTime;
		result = prime * result + (meetingDays.hashCode());
		result = prime * result + midDay;
		result = prime * result + startTime;
		result = prime * result + (title.hashCode());
		result = prime * result + upperMinute;
		result = prime * result + upperTime;
		return result;
	}

	/**
	 * Checks if the two Activities are equal
	 * 
	 * @param obj the object to compare
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Checks for conflict between this activity and the given activity Checks if at
	 * least one of the days in the string of days is the same as any of the days in
	 * the other activity, then compares times for any being equal
	 * @throws ConflictException if the two Activities are conflicting.
	 */
	@Override
	public void checkConflict(Activity a) throws ConflictException {
		String days1 = a.getMeetingDays();
		String days2 = this.getMeetingDays();
		int timea1 = a.getStartTime();
		int timea2 = a.getEndTime();
		int timethis1 = this.getStartTime();
		int timethis2 = this.getEndTime();
		if (days1.equals("A") || days2.equals("A")) {
			return;
		}

		for (int i = 0; i < days1.length(); i++) {
			for (int j = 0; j < days2.length(); j++) {
				if (days2.charAt(j) == days1.charAt(i)) {
					if (timea1 == timethis1 || timea1 == timethis2 || timea2 == timethis1 || timea2 == timethis2) {
						throw new ConflictException();
					} else if (timea2 > timethis1 && timea2 < timethis2) {
						throw new ConflictException();
					} else if (timethis2 > timea1 && timethis2 < timea2) {
						throw new ConflictException();
					}
				}
			}
		}

	}

}