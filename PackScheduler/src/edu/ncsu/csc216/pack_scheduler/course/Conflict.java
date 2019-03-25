package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Class that does conflict management
 * @author Elias Robertson
 */
public interface Conflict {
  /**
   * Checks if an activity is conflicting
   * @param a The activity to check the conflict with
   * @throws ConflictException a conflict exception when there is a conflict
   */
  void checkConflict(Activity a) throws ConflictException;
}
